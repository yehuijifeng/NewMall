package com.alsfox.mall.service.weixinpay;

import android.content.Context;
import android.widget.Toast;

import com.alsfox.mall.service.ServiceConstant;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WeixinPayPort {
    private IWXAPI api;
    private StringBuilder sb;
    private static volatile WeixinPayPort weixinPayPort = null;

    public static WeixinPayPort getWeixinPayPort() {
        if (weixinPayPort == null) {
            synchronized (WeixinPayPort.class) {
                if (weixinPayPort == null) {
                    weixinPayPort = new WeixinPayPort();
                }
            }
        }
        return weixinPayPort;
    }

    /**
     * 微信支付
     */
    public boolean weixinPay(Context context, WeixinBean weixinBean) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, ServiceConstant.WEIXIN_APP_ID, true);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (isPaySupported) {
            sendPayReq(weixinBean);// 发送支付请求
            return true;
        } else {
            Toast.makeText(context, " 您的微信版本不支持支付", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 发送支付请求
     */
    private void sendPayReq(WeixinBean weixinBean) {

        // 支付请求参数
        PayReq req = new PayReq();
        req.appId = weixinBean.getAPPID();
        req.partnerId = weixinBean.getPartid();
        req.prepayId = weixinBean.getPrepay_id();
        req.nonceStr = weixinBean.getNonceStr();// 临时标志
        req.timeStamp = weixinBean.getTimeStamp();//时间戳
        req.packageValue = weixinBean.getPackageValue();
        req.sign = weixinBean.getPaySign();
        api.registerApp(weixinBean.getAPPID());

        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

}
