package com.alsfox.mall.db.shoppingcart;

import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.db.base.BaseDBDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 浩 on 2016/10/24.
 * 购物车操作类
 */

public class ShoppingCartDao {

    private BaseDBDao<ShoppingCartBean, Integer> shoppingCartDao;

    public ShoppingCartDao() {
        shoppingCartDao = new BaseDBDao<>(ShoppingCartBean.class);
    }

    public int insert(ShoppingCartBean shoppingCartData) {
        return shoppingCartDao.insertData(shoppingCartData);
    }

    public int update(ShoppingCartBean shoppingCartData) {
        return shoppingCartDao.updateData(shoppingCartData);
    }

    /**
     * 降序查询购物车
     *
     * @return
     */
    public List<ShoppingCartBean> query() {
        List<ShoppingCartBean> shoppingCartBeens = null;
        try {
            //降序查询
            shoppingCartBeens = shoppingCartDao.getDao().queryBuilder().orderBy("shoppingcartid", false).query();
//            GenericRawResults<ShoppingCartBean> genericRawResults = shoppingCartDao.getDao().queryRaw("select * from 'shoppingcartbean' order by ? desc", "shoppingcartid");
//            shoppingCartBeens = genericRawResults.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingCartBeens;
    }

    public int delete(int shopId) {
        int i;
        try {
            shoppingCartDao.getDao().deleteBuilder().where().eq("shopid", shopId).prepare();
            i = 1;
        } catch (SQLException e) {
            e.printStackTrace();
            i = 0;
        }
        return i;
    }
}
