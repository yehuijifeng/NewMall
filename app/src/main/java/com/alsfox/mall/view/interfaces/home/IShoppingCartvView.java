package com.alsfox.mall.view.interfaces.home;

import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.view.interfaces.base.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浩 on 2016/10/25.
 * 购物车
 */

public interface IShoppingCartvView extends IBaseView {
    //查询所有购物车商品
    void queryShoppingCart(List<ShoppingCartBean> shoppingCartBeens);

    //删除指定商品
    void deleteShoppingCart(int i);

    //更新商品数量
    void updateShoppingCart(int i);

    //是否全选商品
    void isAllSelect(boolean bl);

    //改变全选状态
    void setAllSelect(boolean bl);

    //查询所有选中商品价格和结算商品数量
    void querySelectGoodsPrice(double price, int number);

    //查询所有选中商品
    void querySelectGoodsList(ArrayList<ShoppingCartBean> shoppingCartBeens);

}
