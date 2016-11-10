package com.alsfox.mall.presenter.home;

import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.model.home.ShoppingCartModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.home.IShoppingCartvView;

/**
 * Created by 浩 on 2016/10/25.
 */

public class ShoppingCartPresenter extends BasePresenter<IShoppingCartvView,ShoppingCartModel> {


    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public ShoppingCartPresenter(IShoppingCartvView mView) {
        super(mView);
        mModel = new ShoppingCartModel();
    }

    public void queryShoppingCart() {
        mView.queryShoppingCart(mModel.queryShoppingCart());
    }

    public void deleteShoppingCart(int shopId) {
        mView.deleteShoppingCart(mModel.deleteShoppingCart(shopId));
    }
    public void deleteShoppingCart(ShoppingCartBean shopId) {
        mView.deleteShoppingCart(mModel.deleteShoppingCart(shopId));
    }
    public void updateShoppingCart(ShoppingCartBean shoppingCartBean) {
        mView.updateShoppingCart(mModel.updateShoppingCart(shoppingCartBean));
    }

    public void insterShoppingCart(ShoppingCartBean shoppingCartBean) {
        mView.insterShoppingCart(mModel.insterShoppingCart(shoppingCartBean));
    }

}
