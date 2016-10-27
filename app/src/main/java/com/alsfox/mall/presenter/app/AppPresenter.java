package com.alsfox.mall.presenter.app;

import com.alsfox.mall.bean.app.AppLoadingImgBean;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.model.app.AppModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.app.IAppView;

/**
 * Created by 浩 on 2016/10/27.
 * loading页面
 */

public class AppPresenter extends BasePresenter<IAppView> {
    private AppModel loadingModel;

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public AppPresenter(IAppView mView) {
        super(mView);
        loadingModel = new AppModel();
    }

    public void insertVersion(AppVersionBean appVersionBean) {
        loadingModel.insertVersion(appVersionBean);
    }

    public void insertLoadingImg(AppLoadingImgBean appLoadingImgBean) {
        loadingModel.insertLoadingImg(appLoadingImgBean);
    }

    public AppVersionBean queryVersion() {
        return loadingModel.queryVersion();
    }

    public AppLoadingImgBean queryLoadingImg() {
        return loadingModel.queryLoadingImg();
    }

    public void isDownloadApp(AppVersionBean appVersionBean) {
        AppVersionBean appVersionBean1 = loadingModel.isDownloadApp(appVersionBean);
        if (appVersionBean1 != null)
            mView.isDownloadApp(true, appVersionBean1);
        else
            mView.isDownloadApp(false, null);
    }
}
