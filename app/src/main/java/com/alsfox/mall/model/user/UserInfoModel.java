package com.alsfox.mall.model.user;

import android.text.TextUtils;

import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.db.user.UserInfoDao;
import com.alsfox.mall.function.RxBus;
import com.alsfox.mall.model.base.BaseModel;

/**
 * Created by 浩 on 2016/11/1.
 * 个人中心
 */

public class UserInfoModel extends BaseModel {
    private UserInfoDao userInfoDao;

    public UserInfoModel() {
        userInfoDao = new UserInfoDao();
    }

    public void insert(String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) return;
        MallAppliaction.getInstance().userBean.setUserAvatar(imgUrl);
        userInfoDao.insert(MallAppliaction.getInstance().userBean);
        RxBus.getDefault().post(MallAppliaction.getInstance().userBean);
    }
}
