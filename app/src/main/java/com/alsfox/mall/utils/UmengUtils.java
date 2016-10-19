package com.alsfox.mall.utils;

import android.app.Activity;

import com.umeng.utils.UmengQQLoginUtil;
import com.umeng.utils.UmengShareUtil;

/**
 * Created by yehuijifeng
 * on 2016/1/15.
 * 友盟工具类
 */
public class UmengUtils {

    private UmengShareUtil umengShareUtil;
    private UmengQQLoginUtil umengQQLoginUtil;

    /**
     * 默认分享全部授权app
     */
    public void shareDefault(Activity activity) {
        umengShareUtil = new UmengShareUtil(activity);
        umengShareUtil.defaultShare();
    }

    /**
     * qq授权登陆
     */
    public void loginQQ(Activity activity) {
        umengQQLoginUtil = new UmengQQLoginUtil(activity);
        umengQQLoginUtil.qqByLogin();
    }

    /**
     * qq登出
     */
    public void loginOutQQ(Activity activity) {
        umengQQLoginUtil.qqByOutLogin();
    }

    /**
     * qq登录信息
     */
    public String loginQQUserInfo() {
        return umengQQLoginUtil.qqByUserInfo();
    }
}
