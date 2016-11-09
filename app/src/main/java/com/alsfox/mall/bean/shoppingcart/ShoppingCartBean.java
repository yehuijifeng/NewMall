package com.alsfox.mall.bean.shoppingcart;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 浩 on 2016/10/24.
 * 购物车
 */
@DatabaseTable
public class ShoppingCartBean implements Parcelable{

    @DatabaseField(generatedId = true)
    private int shoppingCartId;//数据库用id,自增长

    @DatabaseField
    private int shopId;//商品ID
    @DatabaseField()
    private int userId; //
    @DatabaseField()
    private String shopIcon; // 商品图片地址
    @DatabaseField()
    private String shopName;//商品名称
    @DatabaseField()
    private String specName; // 规格名称
    @DatabaseField()
    private int isSpec;//是否使用规格，0表示使用，-1表示没有使用
    @DatabaseField()
    private int specId;//如果使用规格，该值必须，反则不必须
    @DatabaseField()
    private int shopNum;//购买数量
    @DatabaseField()
    private double price;//商品价格或者规格价格
    @DatabaseField()
    private double diKouPrice;//商品或者规格的可抵扣金额
    @DatabaseField()
    private int shopStock;//商品库存
    @DatabaseField()
    private boolean isChecked;//是否被选中
    @DatabaseField()
    private int isTimeout;//是否是限时抢购，默认-1，0为限时抢购
    @DatabaseField()
    private int merchantId;//商品属于的商店id
    @DatabaseField()
    private String merchant;//商品属于的商店名称
    @DatabaseField()
    private int shopTypeId;//商品属于的类型，-2属于报销商品
    @DatabaseField(foreign = true, columnName = "dianpuid")
    private CartMerchantBean merchantBean;//数据库外间，不做数据考虑

    public int getShopType() {
        return shopTypeId;
    }

    public void setShopType(int shopType) {
        this.shopTypeId = shopType;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public int getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(int isSpec) {
        this.isSpec = isSpec;
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public int getShopNum() {
        return shopNum;
    }

    public void setShopNum(int shopNum) {
        this.shopNum = shopNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiKouPrice() {
        return diKouPrice;
    }

    public void setDiKouPrice(double diKouPrice) {
        this.diKouPrice = diKouPrice;
    }

    public int getShopStock() {
        return shopStock;
    }

    public void setShopStock(int shopStock) {
        this.shopStock = shopStock;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getIsTimeout() {
        return isTimeout;
    }

    public void setIsTimeout(int isTimeout) {
        this.isTimeout = isTimeout;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.shoppingCartId);
        dest.writeInt(this.shopId);
        dest.writeInt(this.userId);
        dest.writeString(this.shopIcon);
        dest.writeString(this.shopName);
        dest.writeString(this.specName);
        dest.writeInt(this.isSpec);
        dest.writeInt(this.specId);
        dest.writeInt(this.shopNum);
        dest.writeDouble(this.price);
        dest.writeDouble(this.diKouPrice);
        dest.writeInt(this.shopStock);
        dest.writeByte(isChecked ? (byte) 1 : (byte) 0);
        dest.writeInt(this.isTimeout);
        dest.writeInt(this.merchantId);
        dest.writeString(this.merchant);
        dest.writeParcelable(this.merchantBean, flags);
    }

    public ShoppingCartBean() {
    }

    private ShoppingCartBean(Parcel in) {
        this.shoppingCartId = in.readInt();
        this.shopId = in.readInt();
        this.userId = in.readInt();
        this.shopIcon = in.readString();
        this.shopName = in.readString();
        this.specName = in.readString();
        this.isSpec = in.readInt();
        this.specId = in.readInt();
        this.shopNum = in.readInt();
        this.price = in.readDouble();
        this.diKouPrice = in.readDouble();
        this.shopStock = in.readInt();
        this.isChecked = in.readByte() != 0;
        this.isTimeout = in.readInt();
        this.merchantId = in.readInt();
        this.merchant = in.readString();
        this.merchantBean = in.readParcelable(CartMerchantBean.class.getClassLoader());
    }

    public static final Creator<ShoppingCartBean> CREATOR = new Creator<ShoppingCartBean>() {
        public ShoppingCartBean createFromParcel(Parcel source) {
            return new ShoppingCartBean(source);
        }

        public ShoppingCartBean[] newArray(int size) {
            return new ShoppingCartBean[size];
        }
    };
}
