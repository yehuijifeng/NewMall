package com.alsfox.mall.bean.order;

import com.alsfox.mall.http.request.RequestUrls;

/**
 * Created by 浩 on 2016/11/22.
 * 订单详情
 */

public class OrderDetailBean {

    private int isComment;                     // 该商品是否已经评价，0未评价，1已经评价
    private int detailId;                      // 订单详情ID
    private int orderId;                       // 订单ID
    private int shopId;                        // 商品ID
    private String shopName;                       // 商品名称
    private String shopImg;                        // 商品缩略图
    private int isGuge;                        // 购买商品时候是否有规格，0表示有规格，-1表示没有规格
    private int shopSpecId;                    // 商品规格ID，如果商品没有规格，该字段为-1
    private String shopSpecName;                   // 如果商品没有规格，该字段为“”
    private double shopPrice;                      // 商品价格
    private int shopNum;                       // 商品数量
    private double shopDikou;                      // 商品/商品规格可抵扣金额   【购买商品时，如果没有开启积分系统，这两个字段为默认值0】
    private int shopGetIntegral;               // 商品/商品规格可得积分  【购买商品时，如果没有开启积分系统，这两个字段为默认值0】
    private int yuStock;//商品库存

    /*************************************************************************************
     * isService，serviceType使用描述：
     * 1.	如果isService为100，表示没有参与退换货，可以进行评价或退换货。
     * 2.	如果isService为0，1，2，3并且serviceType为1(退货)，【不可评价，只显示状态】0表示等待商家审核，1表示等待用户退货，2表示等待商家检货，3商家已确定退货
     * 3.	如果isService为-1，-2并且serviceType为1(退货)，【只可评价】-1为驳回退货，-2商家验货驳回
     * <p>
     * 4.	如果isService为0，1，2，3，并且serviceType为2(换货)，【不可评价，只显示状态】0表示等待商家审核，1表示等待用户退货，2表示等待商家检货，3商家已退货，等待用户收货
     * 5.	如果isService为-1，-2，4，并且serviceType为2(换货)，【只可评价】-1为驳回退货，-2商家验货驳回，4用户已收退货
     *************************************************************************************/

    private int isService;//是否参与了退换货
    private int serviceType;//参与服务类型


    public int getIsComment() {
        return isComment;
    }

    public void setIsComment(int isComment) {
        this.isComment = isComment;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public String getShopImg() {
        return RequestUrls.IP + shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public int getIsGuge() {
        return isGuge;
    }

    public void setIsGuge(int isGuge) {
        this.isGuge = isGuge;
    }

    public int getShopSpecId() {
        return shopSpecId;
    }

    public void setShopSpecId(int shopSpecId) {
        this.shopSpecId = shopSpecId;
    }

    public String getShopSpecName() {
        return shopSpecName;
    }

    public void setShopSpecName(String shopSpecName) {
        this.shopSpecName = shopSpecName;
    }

    public double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getShopNum() {
        return shopNum;
    }

    public void setShopNum(int shopNum) {
        this.shopNum = shopNum;
    }

    public double getShopDikou() {
        return shopDikou;
    }

    public void setShopDikou(double shopDikou) {
        this.shopDikou = shopDikou;
    }

    public int getShopGetIntegral() {
        return shopGetIntegral;
    }

    public void setShopGetIntegral(int shopGetIntegral) {
        this.shopGetIntegral = shopGetIntegral;
    }

    public int getYuStock() {
        return yuStock;
    }

    public void setYuStock(int yuStock) {
        this.yuStock = yuStock;
    }

    public int getIsService() {
        return isService;
    }

    public void setIsService(int isService) {
        this.isService = isService;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }
}
