package com.alsfox.mall.model.user;

import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.db.user.UserInfoDao;
import com.alsfox.mall.function.RxBus;
import com.alsfox.mall.model.base.BaseModel;

/**
 * Created by 浩 on 2016/10/26.
 * 用户登录
 */

public class UserLoginModel extends BaseModel {
    private UserInfoDao userInfoDao;

    public UserLoginModel() {
        userInfoDao = new UserInfoDao();
    }

    /**
     * 缓存用户信息到数据库
     *
     * @param userBean
     */
    public void userInfoCache(final UserBean userBean) {
        RxBus.getDefault().post(userBean);
        new Thread(new Runnable() {
            @Override
            public void run() {
                userInfoDao.insert(userBean);
            }
        }).start();
    }

    /**
     * 查询本地用户信息
     *
     * @return
     */
    public UserBean queryUserInfo() {
        RxBus.getDefault().post(userInfoDao.query());
        return userInfoDao.query();
    }
}
