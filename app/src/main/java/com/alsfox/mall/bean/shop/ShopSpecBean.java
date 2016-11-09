package com.alsfox.mall.bean.shop;

/**
 * Created by 浩 on 2016/10/28.
 * 商品规格
 */

public class ShopSpecBean {

    private int specId;                    // 规格ID
    private int shopId;                    // 商品ID
    private String specName;                   // 规格名称
    private double specPrice;                  // 规格价格
    private int specNum;                   // 规格库存
    private double dikouPrice;                 // 抵扣金额
    private int getIntegral;               // 获得积分

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public double getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(double specPrice) {
        this.specPrice = specPrice;
    }

    public int getSpecNum() {
        return specNum;
    }

    public void setSpecNum(int specNum) {
        this.specNum = specNum;
    }

    public double getDikouPrice() {
        return dikouPrice;
    }

    public void setDikouPrice(double dikouPrice) {
        this.dikouPrice = dikouPrice;
    }

    public int getGetIntegral() {
        return getIntegral;
    }

    public void setGetIntegral(int getIntegral) {
        this.getIntegral = getIntegral;
    }
}
