package com.alsfox.mall.http.request;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.function.RxBus;
import com.alsfox.mall.http.HttpBean;
import com.alsfox.mall.http.SignUtils;
import com.alsfox.mall.http.StatusCode;
import com.alsfox.mall.http.download.OnProgressListener;
import com.alsfox.mall.http.download.ProgressHelper;
import com.alsfox.mall.http.interfaces.ApiService;
import com.alsfox.mall.http.response.ResponseAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.utils.LogUtils;
import com.alsfox.mall.utils.NetWorkUtils;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 浩 on 2016/6/22.
 * retrofit网络请求框架的管理类
 */
public class RetrofitManage {

    private RetrofitManage() {
        initRetrofit();
    }

    public static RetrofitManage getInstance() {
        return RetrofitManager.retrofitManage;
    }

    private static class RetrofitManager {
        private static final RetrofitManage retrofitManage = new RetrofitManage();
    }

    //默认超时时间
    private int VALUE_DEFAULT_TIME_OUT = 20 * 1000;
    private OkHttpClient.Builder mOkHttpClient;
    private Retrofit retrofit;
    private ApiService service;
    private OnProgressListener onProgressListener;
    private Gson gson;

    public ApiService getService() {
        return service;
    }

    /**
     * 初始化信息，url中只写入ip地址,ip地址写死
     */
    private void initRetrofit() {
        //下载进度回调
        ProgressHelper progressHelper = new ProgressHelper();
        mOkHttpClient = progressHelper.addProgress(null, new OnProgressListener() {
            @Override
            public void onProgress(double progress, double total, long bytesRead) {
                if (onProgressListener != null)
                    onProgressListener.onProgress(progress, total, bytesRead);
            }
        });
        mOkHttpClient.connectTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);//连接超时
        mOkHttpClient.readTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);//读取超时
        mOkHttpClient.writeTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);//写入超时
        retrofit = new Retrofit
                .Builder()
                .baseUrl(RequestUrls.ROOT_URL)
                .client(mOkHttpClient.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 使用RxJava作为回调适配器
                .addConverterFactory(GsonConverterFactory.create())// 使用Gson作为数据转换器
                .build();
        service = retrofit.create(ApiService.class);
        gson = new Gson();
    }

    /**
     * 设置网络请求超时时间
     *
     * @param time 毫秒
     */
    public void setItemOut(long time) {
        mOkHttpClient.connectTimeout(time, TimeUnit.MILLISECONDS);//连接超时
        mOkHttpClient.readTimeout(time, TimeUnit.MILLISECONDS);//读取超时
        mOkHttpClient.writeTimeout(time, TimeUnit.MILLISECONDS);//写入超时
    }

    /**
     * 发送请求
     *
     * @param requesteAction
     * @return
     */
    public Subscription sendRequest(final RequestAction requesteAction) {
        requesteAction.params.getParams().put(SignUtils.KEY_TIMESTAMP, System.currentTimeMillis());
        requesteAction.params.getParams().put(SignUtils.KEY_SIGN, SignUtils.getSign(requesteAction.params.getParams()));

        for (Map.Entry entry : requesteAction.params.getParams().entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            if (value instanceof String) {
                value = SignUtils.getKeyParams(value.toString());
                requesteAction.params.getParams().put(key, value);
            }
            LogUtils.i("key=" + key + " ==> value=" + value + "\n");
        }

        //预备发送请求，将参数生成Observable
        requesteAction.getRequest();
        return requesteAction.observable
                .subscribeOn(Schedulers.newThread())//网络请求必须在子线程中进行
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<HttpBean>() {
                    @Override
                    public void onCompleted() {
                        //请求完成，什么都不做
                    }

                    @Override
                    public void onError(Throwable e) {
                        ResponseFinalAction responseAction = new ResponseFinalAction();
                        responseAction.setThrowable(e);
                        responseAction.setRequestAction(requesteAction);
                        //是否是网络错误
                        if (!NetWorkUtils.isConnected(MallAppliaction.getInstance())) {
                            responseAction.setErrorMessage(MallAppliaction.getInstance().getResources().getString(R.string.network_error));
                            responseAction.setRequestCode(StatusCode.NETWORK_ERROR);
                        } else {
                            //服务器异常，非服务器自身控制错误
                            responseAction.setErrorMessage(e.getMessage());
                            responseAction.setRequestCode(StatusCode.SERVER_ERROR);
                        }
                        RxBus.getDefault().post(responseAction);
                    }

                    @Override
                    public void onNext(HttpBean httpBean) {
                        ResponseAction responseAction;
                        if (httpBean.getStatusCode() == StatusCode.REQUEST_SUCCESS) {
                            //正确结果
                            responseAction = new ResponseSuccessAction();
                            responseAction.setHttpBean(httpBean);
                            responseAction.setRequestAction(requesteAction);
                            responseAction.setRequestCode(StatusCode.REQUEST_SUCCESS);
                        } else {
                            //服务器异常
                            responseAction = new ResponseFinalAction();
                            responseAction.setHttpBean(httpBean);
                            responseAction.setRequestAction(requesteAction);
                            responseAction.setErrorMessage(httpBean.getMessage());
                            responseAction.setRequestCode(httpBean.getStatusCode());
                        }
                        RxBus.getDefault().post(responseAction);
                    }
                });

    }

    /**
     * 注销一个网络请求
     *
     * @param subscription
     */
    public void unsubscribe(Subscription subscription) {
        if (subscription.isUnsubscribed())
            subscription.unsubscribe();
    }


    /**
     * 下载文件
     */
    public void downloadFile(final String filename, String fileUrl, OnProgressListener progressListener) {

        onProgressListener = progressListener;
        //这个下载的枚举对象是通用的
        Call<ResponseBody> call = getService().getDownApk(fileUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseAction responseAction = null;
                try {
                    if (response.isSuccessful()) {
                        InputStream is = response.body().byteStream();
                        File file = new File(filename);
                        FileOutputStream fos = new FileOutputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = bis.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                            fos.flush();
                        }
                        fos.close();
                        bis.close();
                        is.close();
                        responseAction = new ResponseSuccessAction();
                        responseAction.setRequestCode(StatusCode.REQUEST_SUCCESS);
                        responseAction.setRequestAction(RequestAction.GET_DOWN_APK);
                        responseAction.setErrorMessage("文件下载成功!");
                    } else {
                        responseAction = new ResponseFinalAction();
                        responseAction.setRequestCode(StatusCode.SERVER_BUSY);
                        responseAction.setRequestAction(RequestAction.GET_DOWN_APK);
                        responseAction.setErrorMessage("服务器繁忙!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    responseAction = new ResponseFinalAction();
                    responseAction.setRequestCode(StatusCode.SERVER_BUSY);
                    responseAction.setRequestAction(RequestAction.GET_DOWN_APK);
                    responseAction.setErrorMessage("文件解析错误!");
                } finally {
                    RxBus.getDefault().post(responseAction);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                ResponseAction responseAction = new ResponseFinalAction();
                if (NetWorkUtils.isConnected(MallAppliaction.getInstance())) {
                    responseAction.setErrorMessage(e.getMessage());
                    responseAction.setRequestCode(StatusCode.SERVER_ERROR);
                } else {
                    responseAction.setErrorMessage("网络连接已断开");
                    responseAction.setRequestCode(StatusCode.NETWORK_ERROR);
                }
                responseAction.setRequestAction(RequestAction.GET_DOWN_APK);
                responseAction.setThrowable(e);
                RxBus.getDefault().post(responseAction);
            }
        });
    }


    public void uploadFile(final RequestAction requestAction, File[] files, OnProgressListener progressListener) {
        final Map<String, RequestBody> photos = new HashMap<>();
        for (File file : files) {
            RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
            //例如:"photos\"; filename=\"icon.png",前面的photos就是与服务器对应的key，后面filename是服务器得到的文件名
            photos.put("file\"; filename=\"" + file.getName(), photo);
        }
        String sign = SignUtils.getSign(requestAction.params.getParams());
        requestAction.params.getParams().put(SignUtils.KEY_SIGN, sign);
        onProgressListener = progressListener;
        Observable
                .create(new Observable.OnSubscribe<Response<ResponseBody>>() {
                    @Override
                    public void call(Subscriber<? super Response<ResponseBody>> subscriber) {
                        try {
                            Call<ResponseBody> call = service.getUpdateUserIcon(photos, requestAction.params.getParams());
                            Response<ResponseBody> response = call.execute();
                            subscriber.onNext(response);
                        } catch (IOException e) {
                            e.printStackTrace();
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ResponseAction responseAction = new ResponseFinalAction();
                        responseAction.setRequestCode(StatusCode.SERVER_ERROR);
                        responseAction.setRequestAction(requestAction);
                        responseAction.setErrorMessage(e.getMessage());
                        responseAction.setThrowable(e);
                        RxBus.getDefault().post(responseAction);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        ResponseAction responseAction;
                        if (responseBodyResponse.isSuccessful()) {
                            try {
                                String json = new String(responseBodyResponse.body().bytes());
                                HttpBean httpBean = gson.fromJson(json, HttpBean.class);
                                responseAction = new ResponseSuccessAction();
                                responseAction.setHttpBean(httpBean);
                                responseAction.setRequestCode(StatusCode.REQUEST_SUCCESS);
                                responseAction.setRequestAction(requestAction);
                                responseAction.setErrorMessage(json);
                            } catch (IOException e) {
                                e.printStackTrace();
                                responseAction = new ResponseFinalAction();
                                responseAction.setRequestCode(StatusCode.SERVER_BUSY);
                                responseAction.setRequestAction(requestAction);
                                responseAction.setErrorMessage("上传失败!");
                            }
                        } else {
                            responseAction = new ResponseFinalAction();
                            responseAction.setRequestCode(StatusCode.SERVER_BUSY);
                            responseAction.setRequestAction(requestAction);
                            responseAction.setErrorMessage("上传失败!");
                        }
                        RxBus.getDefault().post(responseAction);
                    }
                });
    }
}
