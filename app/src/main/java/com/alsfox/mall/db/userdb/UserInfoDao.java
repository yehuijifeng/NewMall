package com.alsfox.mall.db.userdb;

import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.db.base.BaseDBDao;

import java.util.List;

/**
 * Created by 浩 on 2016/10/26.
 * 用户信息
 */

public class UserInfoDao {
    private BaseDBDao<UserBean, Integer> userDao;

    public UserInfoDao() {
        userDao = new BaseDBDao<>(UserBean.class);
    }

    public void insert(UserBean userBean) {
        if (userBean == null) return;
        userDao.resetTable();
        userDao.insertOrUpdate(userBean);
    }

    public UserBean query() {
        List<UserBean> userBeens = userDao.queryAll();
        if (userBeens == null || userBeens.size() < 1) return null;
        MallAppliaction.getInstance().userBean = userBeens.get(0);
        return userBeens.get(0);
    }
}
