package com.alsfox.mall.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.service.ServiceConstant;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;
    private TextView weixin_pay_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        weixin_pay_text = (TextView) findViewById(R.id.weixin_pay_text);
        api = WXAPIFactory.createWXAPI(this, ServiceConstant.WEIXIN_APP_ID);
        api.handleIntent(getIntent(), this);

//		Window window = getWindow();
//		WindowManager.LayoutParams layoutParams = window.getAttributes();
//		//设置窗口的大小及透明度
//		layoutParams.width = 50;
//		layoutParams.height = 50;
//		layoutParams.alpha = 0.5f;
//		window.setAttributes(layoutParams);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            String weixinType = "";
            if (resp.errCode == 0) {
                weixinType = getResources().getString(R.string.str_success_pay);
            } else if (resp.errCode == -2) {
                weixinType = getResources().getString(R.string.str_cancel_pay);
            } else if (resp.errCode == -1) {
                weixinType = getResources().getString(R.string.str_unknown_error);
            }
            weixin_pay_text.setText(getResources().getString(R.string.str_weixin_pay_result) + "\n" + weixinType);

            Intent intent = new Intent(ServiceConstant.WEIXIN_PAY_BACK_TYPE);
            intent.putExtra("weixinPayByType", resp.errCode);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
            localBroadcastManager.sendBroadcast(intent);
        }
    }

}