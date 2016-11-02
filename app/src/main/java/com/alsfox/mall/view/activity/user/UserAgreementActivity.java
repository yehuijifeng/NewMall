package com.alsfox.mall.view.activity.user;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alsfox.mall.R;
import com.alsfox.mall.http.SignUtils;
import com.alsfox.mall.http.request.RequestUrls;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.activity.base.BaseActivity;

public class UserAgreementActivity extends BaseActivity {

    private WebView user_agreement_web;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_user_agreement;
    }

    @Override
    protected String setTitleText() {
        return getResources().getString(R.string.str_user_agreement);
    }

    @Override
    protected void initView() {
        user_agreement_web = (WebView) findViewById(R.id.user_agreement_web);
    }

    @Override
    protected void initData() {
        WebSettings settings = user_agreement_web.getSettings();
        settings.setJavaScriptEnabled(true);
        user_agreement_web.loadUrl(RequestUrls.SELECT_USER_XIEYI_URL + "?" + SignUtils.KEY_SIGN + "=" + SignUtils.getSign(SignUtils.getParameters()) + "&" + SignUtils.KEY_TIMESTAMP + "=" + System.currentTimeMillis());
    }

}