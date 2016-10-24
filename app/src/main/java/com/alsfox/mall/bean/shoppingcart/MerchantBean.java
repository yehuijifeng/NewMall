package com.alsfox.mall.bean.shoppingcart;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by 浩 on 2016/10/24.
 * 商铺
 */
@DatabaseTable()
public class MerchantBean {

    @DatabaseField(id = true)
    private int dianpuId;//店铺ID
    @DatabaseField()
    private String merchantName;//店铺名称
    @DatabaseField()
    private boolean isChecked;//是否被选中
    @ForeignCollectionField()
    private Collection<ShoppingCartBean> shoppingCarts;//店铺商品

    public int getDianpuId() {
        return dianpuId;
    }

    public void setDianpuId(int dianpuId) {
        this.dianpuId = dianpuId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Collection<ShoppingCartBean> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Collection<ShoppingCartBean> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
