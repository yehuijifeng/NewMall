package com.alsfox.mall.model.home;

import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.db.shoppingcart.ShoppingCartDao;
import com.alsfox.mall.model.base.BaseModel;

import java.util.List;

/**
 * Created by 浩 on 2016/10/25.
 * 购物车
 */

public class ShoppingCartModel extends BaseModel {

    private ShoppingCartDao shoppingCartDao;

    public ShoppingCartModel() {
        shoppingCartDao = new ShoppingCartDao();
    }

    public List<ShoppingCartBean> queryShoppingCart() {
        return shoppingCartDao.query();
    }

    public int deleteShoppingCart(int shopId) {
        return shoppingCartDao.delete(shopId);
    }

    public int updateShoppingCart(ShoppingCartBean shoppingCartBean) {
        return shoppingCartDao.update(shoppingCartBean);
    }

    public int insterShoppingCart(ShoppingCartBean shoppingCartBean) {
        return shoppingCartDao.insert(shoppingCartBean);
    }
}
