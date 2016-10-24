package com.alsfox.mall.bean.shoppingcart;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 浩 on 2016/10/24.
 * 购物车
 */
@DatabaseTable
public class ShoppingCartBean {

    @DatabaseField(id = true)
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

    @DatabaseField(foreign = true, columnName = "dianpuid")
    private MerchantBean merchantBean;//数据库外间，不做数据考虑

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
}
