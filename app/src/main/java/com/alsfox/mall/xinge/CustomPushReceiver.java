package com.alsfox.mall.xinge;

import android.content.Context;

import com.alsfox.mall.utils.LogUtils;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * Created by Luhao
 * on 2016/2/18.
 * 信鸽广播接收器
 */
public class CustomPushReceiver extends XGPushBaseReceiver {

    /**
     * 接收消息
     *
     * @param context           应用当前上下文
     * @param xgPushTextMessage 接收到消息结构体，其中XGPushTextMessage的方法列表如下
     *                          getContent() String 消息正文内容，通常只需要下发本字段即可
     *                          getCustomContent() String 消息自定义key-value
     *                          getTitle() String 消息标题（注意：从前台下发消息命令字中的描述不属于标题）
     */
    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        LogUtils.i(xgPushTextMessage.getContent());
    }

    /**
     * 注册结果
     *
     * @param context
     * @param i
     * @param xgPushRegisterResult
     */
    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
        String a = xgPushRegisterResult.getToken();
        //将token提交到服务器

    }

    /**
     * 反注册结果
     *
     * @param context
     * @param i
     */
    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    /**
     * 给当前用户设置标签（tag）的回调结果
     *
     * @param context 上下文
     * @param i
     * @param s
     */
    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    /**
     * 删除当前用户标签（tag）的结果回调
     *
     * @param context
     * @param i
     * @param s
     */
    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }


    /**
     * 通知在通知栏被点击或清除时的回调
     *
     * @param context
     * @param xgPushClickedResult
     */
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
        if (context == null || xgPushClickedResult == null) {
            return;
        }
        if (xgPushClickedResult.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
            // 通知在通知栏被点击啦。。。。。
            // APP自己处理点击的相关动作
            // 这个动作可以在activity的onResume也能监听，请看第3点相关内容
        } else if (xgPushClickedResult.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
            // 通知被清除啦。。。。
            // APP自己处理通知被清除后的相关动作
        }
    }

    /**
     * 通知的下发和展示完全是由信鸽SDK控制的，但有的开发者需要在本地存储被展示过的通知内容
     *
     * @param context            当前应用上下文
     * @param xgPushShowedResult 被展示的通知对象
     */
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {

    }
}
