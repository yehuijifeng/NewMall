package com.alsfox.mall.model.app;

import com.alsfox.mall.bean.app.AppLoadingImgBean;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.db.app.AppDao;
import com.alsfox.mall.model.base.BaseModel;
import com.alsfox.mall.utils.AppUtils;

/**
 * Created by 浩 on 2016/10/27.
 * loading页面
 */

public class AppModel extends BaseModel {

    private AppDao appDao;

    public AppModel() {
        appDao = new AppDao();
    }

    public void insertVersion(AppVersionBean appVersionBean) {
        appDao.insertVersion(appVersionBean);
    }

    public void insertLoadingImg(AppLoadingImgBean appLoadingImgBean) {
        appDao.insertLoadingImg(appLoadingImgBean);
    }

    public AppVersionBean queryVersion() {
        return appDao.queryVersion();
    }

    public AppLoadingImgBean queryLoadingImg() {
        return appDao.queryLoadingImg();
    }

    public AppVersionBean isDownloadApp(AppVersionBean appVersionBean) {
        if (appVersionBean.getVersionNo() > AppUtils.getVersionCode()) {
            return appVersionBean;
        } else {
            return null;
        }
    }

}
