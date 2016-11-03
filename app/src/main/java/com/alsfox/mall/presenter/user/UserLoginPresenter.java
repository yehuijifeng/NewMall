package com.alsfox.mall.presenter.user;

import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.model.user.UserLoginModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.user.IUserLoginView;

/**
 * Created by 浩 on 2016/10/26.
 * 用户登录
 */

public class UserLoginPresenter extends BasePresenter<IUserLoginView,UserLoginModel> {

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public UserLoginPresenter(IUserLoginView mView) {
        super(mView);
        mModel = new UserLoginModel();
    }

    /**
     * 缓存用户信息到数据库
     *
     * @param userBean
     */
    public void userInfoCache(UserBean userBean) {
        mModel.userInfoCache(userBean);
    }

    /**
     * 查询本地用户信息
     *
     * @return
     */
    public UserBean queryUserInfo() {
        return mModel.queryUserInfo();
    }
}
