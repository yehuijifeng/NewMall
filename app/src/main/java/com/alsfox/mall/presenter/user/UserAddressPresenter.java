package com.alsfox.mall.presenter.user;

import com.alsfox.mall.model.user.UserAddressModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.user.IUserAddressView;

/**
 * Created by 浩 on 2016/11/23.
 * 用户收货地址管理
 */

public class UserAddressPresenter extends BasePresenter<IUserAddressView, UserAddressModel> {
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public UserAddressPresenter(IUserAddressView mView) {
        super(mView);
    }
}
