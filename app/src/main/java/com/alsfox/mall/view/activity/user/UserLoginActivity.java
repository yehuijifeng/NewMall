package com.alsfox.mall.view.activity.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.user.UserLoginPresenter;
import com.alsfox.mall.utils.MD5Util;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.customview.user.UserPwdInputBox;
import com.alsfox.mall.view.interfaces.user.IUserLoginView;

import java.util.Map;

import static com.alsfox.mall.http.request.RequestAction.GET_USER_LOGIN;


/**
 * Created by 浩 on 2016/10/26.
 * 用户登录
 */

public class UserLoginActivity extends BaseActivity<UserLoginPresenter> implements IUserLoginView, View.OnClickListener {

    private EditText user_login_name_edit;//用户名
    private UserPwdInputBox user_login_pwd_edit;//密码
    private Button user_do_login_btn;//登录
    private TextView user_to_register_text, user_to_respwd_text;//注册，忘记密码
    private LinearLayout oauth_login_ly;//第三方登录
    private ImageView tiv_user_oauth_qq, tiv_user_oauth_weixin, tiv_user_oauth_weibo;//qq,微信，微博

    @Override
    protected UserLoginPresenter initPresenter() {
        return new UserLoginPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_user_login;
    }

    @Override
    protected String setTitleText() {
        return getResources().getString(R.string.str_login);
    }

    @Override
    protected void initView() {
        user_login_name_edit = (EditText) findViewById(R.id.user_login_name_edit);//用户名
        user_login_pwd_edit = (UserPwdInputBox) findViewById(R.id.user_login_pwd_edit);//密码
        user_do_login_btn = (Button) findViewById(R.id.user_do_login_btn);//登录
        user_to_register_text = (TextView) findViewById(R.id.user_to_register_text);
        user_to_respwd_text = (TextView) findViewById(R.id.user_to_respwd_text);//注册，忘记密码
        oauth_login_ly = (LinearLayout) findViewById(R.id.oauth_login_ly);//第三方登录
        tiv_user_oauth_qq = (ImageView) findViewById(R.id.tiv_user_oauth_qq);
        tiv_user_oauth_weixin = (ImageView) findViewById(R.id.tiv_user_oauth_weixin);
        tiv_user_oauth_weibo = (ImageView) findViewById(R.id.tiv_user_oauth_weibo);//qq,微信，微博

        user_do_login_btn.setOnClickListener(this);
        user_to_register_text.setOnClickListener(this);
        user_to_respwd_text.setOnClickListener(this);
        tiv_user_oauth_qq.setOnClickListener(this);
        tiv_user_oauth_weixin.setOnClickListener(this);
        tiv_user_oauth_weibo.setOnClickListener(this);

        //隐藏第三方登录
        oauth_login_ly.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_do_login_btn://登录
                String userPhone = user_login_name_edit.getText().toString().trim();
                String userPwd = user_login_pwd_edit.getText();
                getUserLogin(userPhone, userPwd);
                break;
            case R.id.user_to_register_text://注册
                break;
            case R.id.user_to_respwd_text://忘记密码
                break;
            case R.id.tiv_user_oauth_qq://qq登录
                break;
            case R.id.tiv_user_oauth_weixin://微信登陆
                break;
            case R.id.tiv_user_oauth_weibo://微博登陆
                break;
        }
    }

    /**
     * 登录
     *
     * @param userPhone
     * @param userPwd
     */
    protected void getUserLogin(String userPhone, String userPwd) {

        if (TextUtils.isEmpty(userPhone)) {
            showShortToast(getResources().getString(R.string.str_enter_tel));
            return;
        }
        if (TextUtils.isEmpty(userPwd)) {
            showShortToast(getResources().getString(R.string.str_enter_pwd));
            return;
        }
        user_do_login_btn.setEnabled(false);
        user_do_login_btn.setText(getResources().getString(R.string.str_login_ing));
        String pwd = MD5Util.MD5(userPwd);
        Map<String, Object> params = GET_USER_LOGIN.params.getParams();
        params.put(MallConstant.USERINFO_NAME, userPhone);
        params.put(MallConstant.USERINFO_PWD, pwd);
        sendRequest(GET_USER_LOGIN);
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_USER_LOGIN:
                user_do_login_btn.setEnabled(true);
                user_do_login_btn.setText(getResources().getString(R.string.str_login));
                UserBean userBean = (UserBean) success.getHttpBean().getObject();
                presenter.userInfoCache(userBean);
                finish();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_USER_LOGIN:
                user_do_login_btn.setEnabled(true);
                user_do_login_btn.setText(getResources().getString(R.string.str_login_error));
                showLongToast(finals.getErrorMessage());
                break;
        }
    }
}
