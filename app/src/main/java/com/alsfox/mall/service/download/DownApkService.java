package com.alsfox.mall.service.download;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.alsfox.mall.R;
import com.alsfox.mall.constances.AppConstant;
import com.alsfox.mall.function.RxBus;
import com.alsfox.mall.http.download.OnProgressListener;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.request.RetrofitManage;
import com.alsfox.mall.http.response.ResponseAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.utils.AppUtils;
import com.alsfox.mall.utils.file.FileUtils;

import java.io.File;
import java.text.DecimalFormat;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * Created by 浩 on 2016/10/31.
 * 下载最新版app
 */

public class DownApkService extends Service {

    // 文件下载路径
    private String apk_url = "";

    // 文件保存路径(如果有SD卡就保存SD卡,如果没有SD卡就保存到手机包名下的路径)
    private String apk_dir = "";

    //文件保存路径+app文件名称
    private String apk_app = "";

    //通知栏管理器和布局管理
    private NotificationManager mNotificationManager = null;
    private NotificationCompat.Builder builder;

    //广播id
    private final int NotificationID = 0x10000;

    //RxBus注册对象
    public Subscription subscription;//rx的观察者
    public Observable observable;//RxBus 实例

    //参数名
    public static final String APK_URL = "apk_url";

    @Override
    public IBinder onBind(@Nullable Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAPKDir();// 创建保存路径

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 接收Intent传来的参数:
        apk_url = intent.getStringExtra(APK_URL);
        //app名称用app保存路径+app设定名称而定
        apk_app = apk_dir + File.separator + "Mall_" + AppUtils.getVersionCode() + ".apk";
        //下载app
        downFile(apk_url, apk_app);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 初始化网络请求对象和sd卡文件对象
     * <p>
     * 创建路径的时候一定要用[/],不能使用[\],但是创建文件夹加文件的时候可以使用[\].
     * [/]符号是Linux系统路径分隔符,而[\]是windows系统路径分隔符 Android内核是Linux.
     */
    private void initAPKDir() {
        //注册用户信息事件
        observable = RxBus.getDefault().register(ResponseAction.class);
        //Rxjava观察者，用于接收ResponseAction.class 的对象
        subscription = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseAction>() {
                    @Override
                    public void call(ResponseAction responseAction) {
                        if (responseAction instanceof ResponseSuccessAction) {
                            onRequestSuccess((ResponseSuccessAction) responseAction);
                        } else if (responseAction instanceof ResponseFinalAction) {
                            onRequestFinal((ResponseFinalAction) responseAction);
                        }
                    }
                });

        // 判断是否插入SD卡
        if (!FileUtils.isSDCardEnable())
            // 保存到app的包名路径下
            apk_dir = getApplicationContext().getFilesDir().getAbsolutePath() + "/" + getResources().getString(R.string.app_name) + "/Download/";
        else
            // 保存到SD卡路径下
            apk_dir = AppConstant.APK_PATH;

        File destDir = new File(apk_dir);
        // 判断文件夹是否存在，如果不存在创建一个空文件夹出来
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }

    /**
     * 文件下载成功
     *
     * @param success
     */
    private void onRequestSuccess(ResponseSuccessAction success) {
        if (success.getRequestAction() == RequestAction.GET_DOWN_APK) {
            Toast.makeText(this, success.getErrorMessage(), Toast.LENGTH_LONG).show();

            //跳转到系统安装页面，隐式跳转
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(new File(apk_app));
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent mPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentText("下载完成,请点击安装");
            builder.setContentIntent(mPendingIntent);//点击事件
            mNotificationManager.notify(NotificationID, builder.build());
            // 震动提示
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000L);// 参数是震动时间(long类型)
            stopSelf();
            startActivity(intent);// 下载完成之后自动弹出安装界面
            mNotificationManager.cancel(NotificationID);
        }
    }

    /**
     * 下载失败
     *
     * @param finals
     */
    private void onRequestFinal(ResponseFinalAction finals) {
        if (finals.getRequestAction() == RequestAction.GET_DOWN_APK) {
            Toast.makeText(this, finals.getErrorMessage(), Toast.LENGTH_LONG).show();
            builder.setContentText(finals.getErrorMessage());
            mNotificationManager.notify(NotificationID, builder.build());
        }
    }

    /**
     * @param x     当前值
     * @param total 总值
     * @return 当前百分比
     * @Description:返回百分之值
     */
    private String getPercent(int x, int total) {
        String result;// 接受百分比的值
        double x_double = x * 1.0;
        double tempResult = x_double / total;
        // 百分比格式，后面不足2位的用0补齐 ##.00%
        DecimalFormat df1 = new DecimalFormat("0.00%");
        result = df1.format(tempResult);
        return result;
    }

    /**
     * 下载文件
     *
     * @param downUrl  下载路径
     * @param fileName 下载文件路径+文件名
     */
    private void downFile(String downUrl, String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        mNotificationManager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(
                getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("正在下载新版本");
        builder.setContentTitle(AppUtils.getAppName());
        builder.setContentText("正在下载,请稍后...");
        builder.setNumber(0);
        builder.setAutoCancel(true);
        mNotificationManager.notify(NotificationID, builder.build());
        //调用retrofit的下载回调
        RetrofitManage.getInstance().downloadFile(fileName, downUrl, new OnProgressListener() {
            @Override
            public void onProgress(double progress, double total, long bytesRead) {
                builder.setProgress((int) total, (int) progress, false);
                builder.setContentInfo(getPercent((int) progress, (int) total));
                mNotificationManager.notify(NotificationID, builder.build());
            }
        });
    }

    /**
     * Title: onDestroy
     *
     * @Description:
     * @see Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed())
            //如果订阅取消订阅
            subscription.unsubscribe();
        stopSelf();
    }

}
