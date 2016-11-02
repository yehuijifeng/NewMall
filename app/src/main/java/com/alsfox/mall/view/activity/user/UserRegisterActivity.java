package com.alsfox.mall.view.activity.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.SignUtils;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.user.UserRegisterPresenter;
import com.alsfox.mall.utils.MD5Util;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.customview.user.UserPwdInputBox;
import com.alsfox.mall.view.interfaces.user.IUserRegisterView;

import java.util.Map;

/**
 * Created by 浩 on 2016/11/2.
 * 用户注册
 */

public class UserRegisterActivity extends BaseActivity<UserRegisterPresenter> implements View.OnClickListener, IUserRegisterView {

    private EditText user_register_name_edit;//手机号
    private EditText user_register_code_edit;//验证码
    private CheckBox user_register_protocol_cb;//是否同意协议
    private Button user_do_register_btn;//注册
    private Button user_register_code_btn;//发送验证码
    private TextView user_register_protocol_text;//用户协议
    private UserPwdInputBox user_register_pwd_edit;//密码
    private UserPwdInputBox user_register_pwd_tow_edit;//确认密码

    protected TimeCount mTimeCount;//计时器
    protected UserIdCodeReceiver mUserIdCodeReceiver;//广播接收器
    private String userPhone;
    private String pwd;
    public static final String ACTION_SMS_RECEIVER = "android.provider.Telephony.SMS_RECEIVED";
    protected static final String KEY_SMS = "【AlsFox商城】";

    @Override
    protected UserRegisterPresenter initPresenter() {
        return new UserRegisterPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_user_register;
    }

    @Override
    protected String setTitleText() {
        return getResources().getString(R.string.str_registered);
    }

    @Override
    protected void initView() {
        user_register_name_edit = (EditText) findViewById(R.id.user_register_name_edit);//手机号
        user_register_code_edit = (EditText) findViewById(R.id.user_register_code_edit);//验证码
        user_register_protocol_cb = (CheckBox) findViewById(R.id.user_register_protocol_cb);//是否同意协议
        user_do_register_btn = (Button) findViewById(R.id.user_do_register_btn);//注册
        user_register_code_btn = (Button) findViewById(R.id.user_register_code_btn);//发送验证码
        user_register_protocol_text = (TextView) findViewById(R.id.user_register_protocol_text);//用户协议
        user_register_pwd_edit = (UserPwdInputBox) findViewById(R.id.user_register_pwd_edit);//密码
        user_register_pwd_tow_edit = (UserPwdInputBox) findViewById(R.id.user_register_pwd_tow_edit);//确认密码
        user_do_register_btn.setOnClickListener(this);
        user_register_code_btn.setOnClickListener(this);
        user_register_protocol_text.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mTimeCount = new TimeCount(60 * 1000, 1000);//计时器60s，间隔1s
        user_register_pwd_tow_edit.setHint("请再次输入密码");
        mUserIdCodeReceiver = new UserIdCodeReceiver();
        IntentFilter filter = new IntentFilter(ACTION_SMS_RECEIVER);
        filter.setPriority(1000);
        this.registerReceiver(mUserIdCodeReceiver, filter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_do_register_btn://注册
                requestUserRegister(RequestAction.GET_USER_REGISTER);
                break;
            case R.id.user_register_code_btn://发送验证码
                requestIdCode(RequestAction.GET_USER_REGISTER_CODE);
                break;
            case R.id.user_register_protocol_text://去用户协议
                startActivity(UserAgreementActivity.class);
                break;
        }
    }

    /**
     * 发送验证码,可能是注册，也可能是修改密码
     *
     * @param requestAction
     */
    protected void requestIdCode(RequestAction requestAction) {
        userPhone = user_register_name_edit.getText().toString().trim();
        if (TextUtils.isEmpty(userPhone)) {
            showShortToast("注册手机号不能为空");
            return;
        }
        mTimeCount.start();
        Map<String, Object> params = SignUtils.getParameters();
        params.put(MallConstant.USERINFO_NAME, userPhone);
        requestAction.params.setParams(params);
        sendRequest(requestAction);
    }

    /**
     * 用户注册
     *
     * @param requestAction
     */
    protected void requestUserRegister(RequestAction requestAction) {
        String code = user_register_code_edit.getText().toString().trim();
        pwd = user_register_pwd_edit.getText().trim();
        String again_pwd = user_register_pwd_tow_edit.getText().trim();
        userPhone = user_register_name_edit.getText().toString().trim();
        if (TextUtils.isEmpty(userPhone)) {
            showShortToast("注册手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            showShortToast("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showShortToast("用户密码不能为空");
            return;
        }

        if (TextUtils.isEmpty(again_pwd)) {
            showShortToast("请再一次确认密码");
            return;
        }

        if (!again_pwd.equals(pwd)) {
            showShortToast("两次密码不一致");
            return;
        }

        if (!user_register_protocol_cb.isChecked()) {
            showShortToast("同意用户协议才能注册");
            return;
        }
        String userPwd = MD5Util.MD5(pwd);
        Map<String, Object> params = SignUtils.getParameters();
        params.put(MallConstant.USERINFO_NAME, userPhone);
        params.put(MallConstant.USERINFO_PWD, userPwd);
        params.put(MallConstant.USERINFO_YZM, code);
        requestAction.params.setParams(params);
        user_do_register_btn.setText("正在注册，请稍后");
        user_do_register_btn.setEnabled(false);
        sendRequest(requestAction);
    }


    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_USER_REGISTER_CODE://验证码
                showLongToast(success.getHttpBean().getObject().toString());
                break;
            case GET_USER_REGISTER://注册
                user_do_register_btn.setText("注册成功");
                user_do_register_btn.setEnabled(true);
                startActivity(UserLoginActivity.class);
                finish();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_USER_REGISTER_CODE://验证码
                showLongToast(finals.getErrorMessage());
                mTimeCount.cancel();//关闭计时器
                mTimeCount.onFinish();//重置
                break;
            case GET_USER_REGISTER://注册
                showLongToast(finals.getErrorMessage());
                user_do_register_btn.setText("注册失败，请重新提交");
                user_do_register_btn.setEnabled(true);
                break;
        }
    }

    /**
     * 时间计时器
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            user_register_code_btn.setText("发送验证码");
            user_register_code_btn.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            user_register_code_btn.setEnabled(false);
            user_register_code_btn.setText(String.valueOf("仅剩" + millisUntilFinished / 1000 + "秒"));
        }
    }

    /**
     * 广播接收器
     */
    protected class UserIdCodeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (ACTION_SMS_RECEIVER.equals(intent.getAction())) {
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        StringBuilder sb = new StringBuilder();
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        SmsMessage[] messages = new SmsMessage[pdus != null ? pdus.length : 0];
                        for (int i = 0; i < messages.length; i++) {
                            byte[] pdu = (byte[]) (pdus != null ? pdus[i] : null);
                            messages[i] = SmsMessage.createFromPdu(pdu);
                        }
                        for (SmsMessage msg : messages) {
                            String content = msg.getMessageBody();
                            sb.append(content);
                        }
                        String smsStr = sb.toString();
                        if (smsStr.contains(KEY_SMS)) {
                            user_register_code_edit.setText(smsStr.substring(smsStr.indexOf("：") + 1, smsStr.lastIndexOf("，")));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mUserIdCodeReceiver);
    }
}
