package com.alsfox.mall.bean.shop;

import com.alsfox.mall.http.request.RequestUrls;

/**
 * Created by 浩 on 2016/11/3.
 */

public class ShopCommentImgBean {
    private int commentImgId;               // 商品评价图片ID
    private int commentId;                  // 商品评价ID
    private String imgUrl;                      // 商品评价图片地址

    public int getCommentImgId() {
        return commentImgId;
    }

    public void setCommentImgId(int commentImgId) {
        this.commentImgId = commentImgId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getImgUrl() {
        return RequestUrls.IP + imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
