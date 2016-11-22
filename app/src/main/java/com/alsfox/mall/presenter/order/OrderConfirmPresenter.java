package com.alsfox.mall.presenter.order;

import com.alsfox.mall.model.order.OrderConfirmModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.order.IOrderConfirmView;

/**
 * Created by 浩 on 2016/11/22.
 * 确认订单
 */

public class OrderConfirmPresenter extends BasePresenter<IOrderConfirmView, OrderConfirmModel> {

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public OrderConfirmPresenter(IOrderConfirmView mView) {
        super(mView);
    }


}
