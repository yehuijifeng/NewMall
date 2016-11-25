package com.alsfox.mall.presenter.user;

import com.alsfox.mall.model.user.UserCouponsModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.user.IUserCouponsView;

/**
 * Created by 浩 on 2016/11/24.
 * 用户优惠券
 */

public class UserCouponsPresenter extends BasePresenter<IUserCouponsView, UserCouponsModel> {
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public UserCouponsPresenter(IUserCouponsView mView) {
        super(mView);
    }
}
