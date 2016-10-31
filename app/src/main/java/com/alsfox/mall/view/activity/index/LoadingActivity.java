package com.alsfox.mall.view.activity.index;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.ActivityCollector;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.app.AppLoadingImgBean;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.app.AppPresenter;
import com.alsfox.mall.service.download.DownApkService;
import com.alsfox.mall.utils.AppUtils;
import com.alsfox.mall.utils.MD5Util;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.baseview.dialog.PromptDialog;
import com.alsfox.mall.view.interfaces.app.IAppView;

import java.util.Map;

import static com.alsfox.mall.http.request.RequestAction.GET_APP_LOADING;
import static com.alsfox.mall.http.request.RequestAction.GET_APP_VERSION;
import static com.alsfox.mall.http.request.RequestAction.GET_USER_LOGIN;

/**
 * Created by 浩 on 2016/10/27.
 * 启动页
 */

public class LoadingActivity extends BaseActivity<AppPresenter> implements IAppView {

    private ImageView app_loading_img;
    private PromptDialog promptDialog;

    @Override
    protected AppPresenter initPresenter() {
        return new AppPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_loading;
    }

    @Override
    protected String setTitleText() {
        return null;
    }

    @Override
    protected void initView() {
        app_loading_img = (ImageView) findViewById(R.id.app_loading_img);
        promptDialog = new PromptDialog(this);
    }

    @Override
    protected void initData() {
        //判断是否是第一次进入app
        if (AppUtils.isOneStart()) {
            startActivity(StartAppActivity.class);
            finish();
            return;
        }
        //判断数据库是否存储了loading页面
        AppLoadingImgBean appLoadingImgBean = presenter.queryLoadingImg();
        if (appLoadingImgBean == null) {
            getLoadingImag();
        } else {
            imageLoader.displayImage(appLoadingImgBean.getImgUrl(), app_loading_img, MallAppliaction.getInstance().defaultOptions);
        }

        //获得用户信息
        getUserInfo();

        //获得服务器上的版本号，判断是否应该升级
        getAppVersion();
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_APP_LOADING://loading页面
                AppLoadingImgBean appLoadingImgBean = (AppLoadingImgBean) success.getHttpBean().getObject();
                imageLoader.displayImage(appLoadingImgBean.getImgUrl(), app_loading_img, MallAppliaction.getInstance().defaultOptions);
                presenter.insertLoadingImg(appLoadingImgBean);
                break;
            case GET_APP_VERSION://版本号
                AppVersionBean appVersionBean = (AppVersionBean) success.getHttpBean().getObject();
                presenter.isDownloadApp(appVersionBean);
                break;
            case GET_USER_LOGIN://登录成功
                MallAppliaction.getInstance().userBean = (UserBean) success.getHttpBean().getObject();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        //super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_APP_LOADING://loading页面
                break;
            case GET_APP_VERSION://版本号,没有获取到则不能进入app
                showLongToast(finals.getErrorMessage());
                startActivity(HomeActivity.class);
                break;
            case GET_USER_LOGIN://登录失败
                showLongToast(finals.getErrorMessage());
                MallAppliaction.getInstance().userBean = null;
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        UserBean userBean = presenter.queryUserInfo();
        if (userBean == null) return;
        String pwd = MD5Util.MD5(userBean.getUserPwd());
        Map<String, Object> params = GET_USER_LOGIN.params.getParams();
        params.put(MallConstant.USERINFO_NAME, userBean.getUserName());
        params.put(MallConstant.USERINFO_PWD, pwd);
        sendRequest(GET_USER_LOGIN);
    }

    /**
     * 获取loading页面
     */
    private void getLoadingImag() {
        sendRequest(GET_APP_LOADING);
    }

    /**
     * 获取版本号
     */
    private void getAppVersion() {
        sendRequest(GET_APP_VERSION);
    }

    @Override
    public void isDownloadApp(boolean bl, final AppVersionBean appVersionBean) {
        if (bl) {
            promptDialog.showPromptDialog("升级提示：" + appVersionBean.getVersionShow(), appVersionBean.getVersionDesc(), "升级", "退出", new PromptDialog.OnPromptClickListener() {
                @Override
                public void onDetermine() {
                    //下载新包
                    presenter.insertVersion(appVersionBean);
                    Intent updateService = new Intent(LoadingActivity.this, DownApkService.class);
                    updateService.putExtra(DownApkService.APK_URL, appVersionBean.getDownUrl());
                    updateService.setPackage(LoadingActivity.this.getPackageName());
                    LoadingActivity.this.startService(updateService);
                    showLongToast("正在后台下载……");
                }

                @Override
                public void onCancel() {
                    ActivityCollector.finishAll();
                }
            });
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(HomeActivity.class);
                    finish();
                }
            }, 2000);
        }
    }
}
