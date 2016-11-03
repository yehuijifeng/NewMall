package com.alsfox.mall.bean.shop;

import com.alsfox.mall.http.request.RequestUrls;

import java.util.List;

/**
 * Created by 浩 on 2016/10/28.
 * 商品评价
 */

public class ShopCommentBean {
    private List<ShopCommentImgBean> shopCommentImgList;               // 商品评价图片列表
    private String userName;             // 商品评价用户名称
    private String userAvatar;           // 用户头像
    private String shopSpecName;         // 商品规格名称
    private int commentId;                     // 评价ID
    private int shopId;                        // 商品ID
    private int userId;                        // 用户ID
    private int orderId;                       // 订单ID
    private int orderDetailId;                 // 订单详情ID
    private float commentLv;                     // 评论星级
    private String commentCon;                     // 评价内容
    private String createTime;             // 创建时间

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ShopCommentImgBean> getShopCommentImgList() {
        return shopCommentImgList;
    }

    public void setShopCommentImgList(List<ShopCommentImgBean> shopCommentImgList) {
        this.shopCommentImgList = shopCommentImgList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return RequestUrls.ROOT_URL + userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getShopSpecName() {
        return shopSpecName;
    }

    public void setShopSpecName(String shopSpecName) {
        this.shopSpecName = shopSpecName;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public float getCommentLv() {
        return commentLv;
    }

    public void setCommentLv(float commentLv) {
        this.commentLv = commentLv;
    }

    public String getCommentCon() {
        return commentCon;
    }

    public void setCommentCon(String commentCon) {
        this.commentCon = commentCon;
    }
}
