package com.alsfox.mall.xinge;

import android.content.Context;

import com.tencent.android.tpush.XGPushManager;

/**
 * Created by Luhao
 * on 2016/2/18.
 * 设置标签
 * 开发者可以针对不同的用户设置标签，然后在前台根据标签名群发通知。
 * 一个应用最多有10000个tag，
 * 每个token在一个应用下最多100个tag，
 * tag中不准包含空格。
 */
public class TagUtils {

    /**
     * 设置标签
     *
     * @param context 上下文
     * @param tagName 待设置的标签名称，不能为null或空。
     *                处理结果
     *                可通过重载XGPushBaseReceiver的onSetTagResult方法获取。
     */
    public static void setTag(Context context, String tagName) {
        XGPushManager.setTag(context, tagName);
    }

    /**
     * 删除标签
     *
     * @param context 上下文
     * @param tagName 待设置的标签名称，不能为null或空。
     */
    public static void deleteTag(Context context, String tagName) {
        XGPushManager.deleteTag (context, tagName);
    }
}
