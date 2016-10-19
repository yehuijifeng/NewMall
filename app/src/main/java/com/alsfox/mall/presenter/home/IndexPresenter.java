package com.alsfox.mall.presenter.home;

import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.home.IIndexView;

/**
 * Created by 浩 on 2016/10/19.
 *
 */

public class IndexPresenter extends BasePresenter<IIndexView> {
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public IndexPresenter(IIndexView mView) {
        super(mView);
    }
}
