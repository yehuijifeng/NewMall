package com.alsfox.mall.http.request;


import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by Luhao on 2016/6/23.
 * 网络请求的主要枚举
 */
public enum RequestAction {

    GET_INDE_DATA(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getIndexData(params.getParams());
        }
    },

    GET_CLASSIFY_DATA(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getClassifyData(params.getParams());
        }
    },;

    public Call<ResponseBody> call;//下载文件专用，okhttp类型的回调
    public Observable observable;//普通网络请求使用，网络请求的操作实例
    public RequestParams params;//请求参数实例

    RequestAction(RequestParams paramss) {
        params = paramss;
    }

    //发送请求，生成预请求对象
    public abstract void getRequest();


}
