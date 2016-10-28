package com.alsfox.mall.db.app;

import com.alsfox.mall.bean.app.AppLoadingImgBean;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.db.base.BaseDBDao;

/**
 * Created by 浩 on 2016/10/27.
 * 版本号，loading页面
 */

public class AppDao {

    private BaseDBDao<AppVersionBean, Integer> versionDao;
    private BaseDBDao<AppLoadingImgBean, Integer> loadingDao;
    private BaseDBDao<UserBean, Integer> userDao;

    public AppDao() {
        versionDao = new BaseDBDao<>(AppVersionBean.class);
        loadingDao = new BaseDBDao<>(AppLoadingImgBean.class);
        userDao = new BaseDBDao<>(UserBean.class);
    }

    public void insertVersion(AppVersionBean appVersionBean) {
        if (appVersionBean == null) return;
        versionDao.resetTable();
        versionDao.insertData(appVersionBean);
    }

    public void insertLoadingImg(AppLoadingImgBean appLoadingImgBean) {
        if (appLoadingImgBean == null) return;
        loadingDao.resetTable();
        loadingDao.insertData(appLoadingImgBean);
    }

    public void insertUserInfo(UserBean userBean) {
        if (userBean == null) return;
        userDao.resetTable();
        userDao.insertData(userBean);
    }

    public AppVersionBean queryVersion() {
        try {
            if (versionDao.queryAll() == null || versionDao.queryAll().isEmpty()) return null;
            return versionDao.queryAll().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AppLoadingImgBean queryLoadingImg() {
        try {
            if (loadingDao.queryAll() == null || loadingDao.queryAll().isEmpty()) return null;
            return loadingDao.queryAll().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserBean queryUserInfo() {
        try {
            if (userDao.queryAll() == null || userDao.queryAll().isEmpty()) return null;
            return userDao.queryAll().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
