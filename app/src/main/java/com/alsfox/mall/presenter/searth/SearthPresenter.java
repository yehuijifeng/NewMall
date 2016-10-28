package com.alsfox.mall.presenter.searth;

import com.alsfox.mall.model.searth.SearthModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.searth.ISearthView;

/**
 * Created by 浩 on 2016/10/28.
 * 搜索商品和店铺
 */

public class SearthPresenter extends BasePresenter<ISearthView> {

    private SearthModel searthModel;

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public SearthPresenter(ISearthView mView) {
        super(mView);
        searthModel = new SearthModel();
    }
}
