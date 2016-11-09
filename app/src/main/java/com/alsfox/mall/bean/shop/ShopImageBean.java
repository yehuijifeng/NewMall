package com.alsfox.mall.bean.shop;

import com.alsfox.mall.http.request.RequestUrls;

/**
 * Created by 浩 on 2016/10/28.
 * 商品图片相册
 */

public class ShopImageBean {
    private int imgId;            // 商品图片ID
    private int shopId;           // 所属商品ID
    private String imgUrl;            // 图片地址

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getImgUrl() {
        return RequestUrls.IP + imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
