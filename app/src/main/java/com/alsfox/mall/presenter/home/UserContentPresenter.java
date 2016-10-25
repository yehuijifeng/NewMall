package com.alsfox.mall.presenter.home;

import com.alsfox.mall.model.home.UserContentModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.home.IUsercontentView;

/**
 * Created by 浩 on 2016/10/25.
 * 个人中心
 */

public class UserContentPresenter extends BasePresenter<IUsercontentView> {

    private UserContentModel userContentModel;

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public UserContentPresenter(IUsercontentView mView) {
        super(mView);
        userContentModel = new UserContentModel();
    }
}
