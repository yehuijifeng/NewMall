package com.alsfox.mall.presenter.user;

import com.alsfox.mall.model.user.UserInfoModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.user.IUserInfoView;

/**
 * Created by 浩 on 2016/11/1.
 * 个人中心
 */

public class UserInfoPresenter extends BasePresenter<IUserInfoView> {

    private UserInfoModel userInfoModel;

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public UserInfoPresenter(IUserInfoView mView) {
        super(mView);
        userInfoModel = new UserInfoModel();
    }
}
