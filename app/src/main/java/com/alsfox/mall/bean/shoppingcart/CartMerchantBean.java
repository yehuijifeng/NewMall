package com.alsfox.mall.bean.shoppingcart;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by 浩 on 2016/10/24.
 * 商铺
 */
@DatabaseTable()
public class CartMerchantBean implements Parcelable{

    @DatabaseField(generatedId = true)
    private int merchantId;//数据库用id,自增长@DatabaseField(generatedId = true)
    @DatabaseField
    private int dianpuId;//店铺ID
    @DatabaseField()
    private String merchantName;//店铺名称
    @DatabaseField()
    private boolean isChecked;//是否被选中
    @ForeignCollectionField()
    private Collection<ShoppingCartBean> shoppingCarts;//店铺商品，属于数据库外键中的一对多

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.merchantId);
        dest.writeInt(this.dianpuId);
        dest.writeString(this.merchantName);
        dest.writeByte(isChecked ? (byte) 1 : (byte) 0);
    }

    public CartMerchantBean() {
    }

    private CartMerchantBean(Parcel in) {
        this.merchantId = in.readInt();
        this.dianpuId = in.readInt();
        this.merchantName = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Creator<CartMerchantBean> CREATOR = new Creator<CartMerchantBean>() {
        public CartMerchantBean createFromParcel(Parcel source) {
            return new CartMerchantBean(source);
        }

        public CartMerchantBean[] newArray(int size) {
            return new CartMerchantBean[size];
        }
    };
}
