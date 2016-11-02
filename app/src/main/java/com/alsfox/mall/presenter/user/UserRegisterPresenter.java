package com.alsfox.mall.presenter.user;

import com.alsfox.mall.model.user.UserRegisterModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.user.IUserRegisterView;

/**
 * Created by 浩 on 2016/11/2.
 * 用户注册
 */

public class UserRegisterPresenter extends BasePresenter<IUserRegisterView> {


    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public UserRegisterPresenter(IUserRegisterView mView) {
        super(mView);
        mModel = new UserRegisterModel();
    }





}
