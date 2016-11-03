package com.alsfox.mall.presenter.app;

import com.alsfox.mall.bean.app.AppLoadingImgBean;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.model.app.AppModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.app.IAppView;

/**
 * Created by 浩 on 2016/10/27.
 * loading页面
 */

public class AppPresenter extends BasePresenter<IAppView,AppModel> {

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public AppPresenter(IAppView mView) {
        super(mView);
        mModel = new AppModel();
    }

    public void insertVersion(AppVersionBean appVersionBean) {
        mModel.insertVersion(appVersionBean);
    }

    public void insertLoadingImg(AppLoadingImgBean appLoadingImgBean) {
        mModel.insertLoadingImg(appLoadingImgBean);
    }

    public void insertUserInfo(UserBean userBean) {
        mModel.insertUserInfo(userBean);
    }

    public AppVersionBean queryVersion() {
        return mModel.queryVersion();
    }

    public AppLoadingImgBean queryLoadingImg() {
        return mModel.queryLoadingImg();
    }

    public UserBean queryUserInfo() {
        return mModel.queryUserInfo();
    }

    public void isDownloadApp(AppVersionBean appVersionBean) {
        AppVersionBean appVersionBean1 = mModel.isDownloadApp(appVersionBean);
        if (appVersionBean1 != null)
            mView.isDownloadApp(true, appVersionBean1);
        else
            mView.isDownloadApp(false, null);
    }
}
