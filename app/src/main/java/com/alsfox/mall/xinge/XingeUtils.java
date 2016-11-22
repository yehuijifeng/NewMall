package com.alsfox.mall.xinge;

import android.content.Context;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

/**
 * Created by Luhao
 * on 2016/2/19.
 * 信鸽工具类
 */
public class XingeUtils {


    /**
     * debug模式
     * 默认为false。如果要开启debug日志，设为true
     */
    public static void enableDebug(Context context, boolean debugMode) {
        // 开启logcat输出，方便debug，发布时请关闭
        XGPushConfig.enableDebug(context, debugMode);
    }

    /**
     * 注册信鸽
     *
     * @param context 当前应用上下文对象，不能为null
     *                callback：callback调用，主要包括操作成功和失败的回调，不能为null
     */
    public static void registerPush(Context context) {
        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
        // 具体可参考详细的开发指南
        // 传递的参数为ApplicationContext
        //XGPushManager.registerPush(context);

        // 2.36（不包括）之前的版本需要调用以下2行代码
        //Intent service = new Intent(context, XGPushService.class);
        //context.startService(service);

        // 其它常用的API：
        // 绑定账号（别名）注册：registerPush(context,account)或registerPush(context,account, XGIOperateCallback)，其中account为APP账号，可以为任意字符串（qq、openid或任意第三方），业务方一定要注意终端与后台保持一致。
        // 取消绑定账号（别名）：registerPush(context,"*")，即account="*"为取消绑定，解绑后，该针对该账号的推送将失效
        // 反注册（不再接收消息）：unregisterPush(context)
        // 设置标签：setTag(context, tagName)
        // 删除标签：deleteTag(context, tagName)
        XGPushManager.registerPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                //Log.d("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object o, int i, String s) {
                // Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }

    /**
     * 注册信鸽
     *
     * @param context 当前应用上下文对象，不能为null
     *                callback：callback调用，主要包括操作成功和失败的回调，不能为null
     */
    public static void registerPush(Context context, final XGIOperateCallback xgiOperateCallback) {
        XGPushManager.registerPush(context, xgiOperateCallback);
    }

    /**
     * 绑定账号的注册
     * 注意：如果要按别名推送，那就需要开发者在调用注册接口时把别名设置在注册请求里面的account字段，
     * 一台设备只允许有一个别名，但一个别名下可以有最多10台设备，不能为null
     *
     * @param context 当前应用上下文对象，不能为null
     * @param account 绑定的账号，绑定后可以针对账号发送推送消息。
     */
    public static void registerPush(final Context context, final String account, final XGIOperateCallback xgiOperateCallback) {
        XGPushManager.registerPush(context, account, xgiOperateCallback);
    }

    public static void registerPush(final Context context, final String account) {
        XGPushManager.registerPush(context, account);
    }

    /**
     * 反注册,不再接收消息
     */
    public static void unregisterPush(Context context) {
        XGPushManager.unregisterPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {

            }

            @Override
            public void onFail(Object o, int i, String s) {

            }
        });
    }


}
