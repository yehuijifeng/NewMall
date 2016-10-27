package com.alsfox.mall.view.activity.index;

import android.os.Handler;
import android.widget.ImageView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.ActivityCollector;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.app.AppLoadingImgBean;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.app.AppPresenter;
import com.alsfox.mall.utils.AppUtils;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.baseview.dialog.PromptDialog;
import com.alsfox.mall.view.interfaces.app.IAppView;

import static com.alsfox.mall.http.request.RequestAction.GET_APP_LOADING;
import static com.alsfox.mall.http.request.RequestAction.GET_APP_VERSION;

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
        if (AppUtils.isOneStart()) {
            startActivity(StartAppActivity.class);
            finish();
            return;
        }
        AppLoadingImgBean appLoadingImgBean = presenter.queryLoadingImg();
        if (appLoadingImgBean == null) {
            getLoadingImag();
        } else {
            imageLoader.displayImage(appLoadingImgBean.getImgUrl(), app_loading_img, MallAppliaction.getInstance().defaultOptions);
        }
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
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
    }

    private void getLoadingImag() {
        sendRequest(GET_APP_LOADING);
    }

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
