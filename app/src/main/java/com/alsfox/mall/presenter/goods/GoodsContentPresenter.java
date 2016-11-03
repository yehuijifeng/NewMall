package com.alsfox.mall.presenter.goods;

import com.alsfox.mall.model.goods.GoodsContentModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.goods.IGoodsContentView;

/**
 * Created by 浩 on 2016/11/3.
 * 商品详情
 */

public class GoodsContentPresenter extends BasePresenter<IGoodsContentView,GoodsContentModel> {
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public GoodsContentPresenter(IGoodsContentView mView) {
        super(mView);
        mModel = new GoodsContentModel();
    }
}
