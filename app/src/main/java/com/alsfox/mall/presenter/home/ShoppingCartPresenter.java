package com.alsfox.mall.presenter.home;

import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.model.home.ShoppingCartModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.home.IShoppingCartvView;

/**
 * Created by 浩 on 2016/10/25.
 */

public class ShoppingCartPresenter extends BasePresenter<IShoppingCartvView, ShoppingCartModel> {


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


    public void deleteShoppingCart(ShoppingCartBean shopId) {
        mView.deleteShoppingCart(mModel.deleteShoppingCart(shopId));
        querySelectGoodsPrice();
        isAllSelect();
    }

    public void updateShoppingCart(ShoppingCartBean shoppingCartBean) {
        mView.updateShoppingCart(mModel.updateShoppingCart(shoppingCartBean));
        querySelectGoodsPrice();
        isAllSelect();
    }

    /**
     * 是否全选
     */
    public void isAllSelect() {
        mView.isAllSelect(mModel.isAllSelect());
    }

    /**
     * 手动全选
     *
     * @param bl
     */
    public void setAllSelect(boolean bl) {
        mView.setAllSelect(mModel.setAllSelect(bl));
        querySelectGoodsPrice();
    }

    /**
     * 计算所有选中商品的价格
     *
     * @return
     */
    public void querySelectGoodsPrice() {
        mView.querySelectGoodsPrice(mModel.querySelectGoodsPrice()[0], (int) mModel.querySelectGoodsPrice()[1]);
    }

    /**
     * 返回所有选中的商品信息
     *
     * @return
     */
    public void querySelectGoodsList() {
        mView.querySelectGoodsList(mModel.querySelectGoodsList());
    }

}
