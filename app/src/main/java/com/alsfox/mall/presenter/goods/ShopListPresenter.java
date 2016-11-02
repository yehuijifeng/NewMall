package com.alsfox.mall.presenter.goods;

import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.goods.IShopListView;

/**
 * Created by 浩 on 2016/11/2.
 * 商品列表
 */

public class ShopListPresenter extends BasePresenter<IShopListView>{

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public ShopListPresenter(IShopListView mView) {
        super(mView);
    }
}
