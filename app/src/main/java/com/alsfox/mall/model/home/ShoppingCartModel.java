package com.alsfox.mall.model.home;

import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.db.shoppingcart.ShoppingCartDao;
import com.alsfox.mall.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浩 on 2016/10/25.
 * 购物车
 */

public class ShoppingCartModel extends BaseModel {

    private ShoppingCartDao shoppingCartDao;

    private List<ShoppingCartBean> shoppingCartBeans = new ArrayList<>();

    public ShoppingCartModel() {
        shoppingCartDao = new ShoppingCartDao();
    }

    public List<ShoppingCartBean> queryShoppingCart() {
        return shoppingCartDao.query();
    }

    /**
     * 删除商品
     *
     * @param shoppingCartBean
     * @return
     */
    public int deleteShoppingCart(ShoppingCartBean shoppingCartBean) {
        return shoppingCartDao.delete(shoppingCartBean);
    }

    /**
     * 更新商品数量
     *
     * @param shoppingCartBean
     * @return
     */
    public int updateShoppingCart(ShoppingCartBean shoppingCartBean) {
        return shoppingCartDao.update(shoppingCartBean);
    }

    /**
     * 是否全选商品
     *
     * @return
     */
    public boolean isAllSelect() {
        List<ShoppingCartBean> shoppingCartBeans = queryShoppingCart();
        if (shoppingCartBeans == null || shoppingCartBeans.isEmpty()) return false;
        for (ShoppingCartBean shoppingCartBean : shoppingCartBeans) {
            if (!shoppingCartBean.isChecked())
                return false;
        }
        return true;
    }

    /**
     * 全选/不选商品
     *
     * @return
     */
    public boolean setAllSelect(boolean bl) {
        List<ShoppingCartBean> shoppingCartBeans = queryShoppingCart();
        if (shoppingCartBeans == null || shoppingCartBeans.isEmpty()) return bl;
        for (ShoppingCartBean shoppingCartBean : shoppingCartBeans) {
            if (shoppingCartBean.isChecked() != bl) {
                shoppingCartBean.setChecked(bl);
                updateShoppingCart(shoppingCartBean);
            }
        }
        return bl;
    }

    /**
     * 计算所有选中商品的价格
     *
     * @return
     */
    public double[] querySelectGoodsPrice() {
        double[] allPrice = {0.0, 0.0};
        List<ShoppingCartBean> shoppingCartBeans = queryShoppingCart();
        if (shoppingCartBeans == null || shoppingCartBeans.isEmpty()) return allPrice;
        for (ShoppingCartBean shoppingCartBean : shoppingCartBeans) {
            if (shoppingCartBean.isChecked()) {
                allPrice[0] += shoppingCartBean.getPrice() * shoppingCartBean.getShopNum();
                allPrice[1]++;
            }
        }
        //总金额-积分抵扣-优惠券抵扣
        BigDecimal bigDecimal = new BigDecimal(allPrice[0]);
        //保留两位小数
        double total = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        allPrice[0] = total;
        return allPrice;
    }

    /**
     * 返回所有选中的商品信息
     *
     * @return
     */
    public ArrayList<ShoppingCartBean> querySelectGoodsList() {
        List<ShoppingCartBean> shoppingCartBeans = queryShoppingCart();
        if (shoppingCartBeans == null || shoppingCartBeans.isEmpty()) return null;
        ArrayList<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
        for (ShoppingCartBean shoppingCartBean : shoppingCartBeans) {
            if (shoppingCartBean.isChecked()) {
                shoppingCartBeanList.add(shoppingCartBean);
            }
        }
        return shoppingCartBeanList;
    }

}
