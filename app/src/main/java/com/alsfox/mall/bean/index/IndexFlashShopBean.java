package com.alsfox.mall.bean.index;

import com.alsfox.mall.http.request.RequestUrls;

/**
 * Created by luhao
 * on 2015/11/18.
 * 限时抢购商品列表
 */
public class IndexFlashShopBean {

    private  double shopZhPj;//星级评价
    private  int shopId;//商品id
    private  String shopName;//商品name
    private  String shopIcon;//商品缩略图
    private  double showPrice;//商品价格
    private  int shopPjNum;//评价数量
    private  double delPrice;// 商品标价

    public double getDelPrice() {
        return delPrice;
    }

    public void setDelPrice(double delPrice) {
        this.delPrice = delPrice;
    }

    public double getShopZhPj() {
        return shopZhPj;
    }

    public void setShopZhPj(double shopZhPj) {
        this.shopZhPj = shopZhPj;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopIcon() {
        return RequestUrls.IP+shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }

    public int getShopPjNum() {
        return shopPjNum;
    }

    public void setShopPjNum(int shopPjNum) {
        this.shopPjNum = shopPjNum;
    }
}
