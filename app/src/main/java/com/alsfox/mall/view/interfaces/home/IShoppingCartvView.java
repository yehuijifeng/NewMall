package com.alsfox.mall.view.interfaces.home;

import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.view.interfaces.base.IBaseView;

import java.util.List;

/**
 * Created by 浩 on 2016/10/25.
 * 购物车
 */

public interface IShoppingCartvView extends IBaseView {

    void queryShoppingCart(List<ShoppingCartBean> shoppingCartBeens);

    void deleteShoppingCart(int i);

    void updateShoppingCart(int i);

    void insterShoppingCart(int i);
}
