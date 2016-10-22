package com.alsfox.mall.bean.index;

import com.alsfox.mall.http.request.RequestUrls;

/**
 * Created by 浩 on 2016/10/20.
 * 首页模块内容
 */

public class IndexMokuaiContentBean {
    private int contentId;                  // 内容ID
    private int moudleId;                   // 模块ID
    private int shopId;                     // 商品ID
    private int indexs;                     // 排序序号，索引从1开始
    private String showImg;                     // 显示图片地址，缩略图
    private int fkId;//内容id
    private int contentType;//0,商品，1，商品分类，2，公告

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getMoudleId() {
        return moudleId;
    }

    public void setMoudleId(int moudleId) {
        this.moudleId = moudleId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getIndexs() {
        return indexs;
    }

    public void setIndexs(int indexs) {
        this.indexs = indexs;
    }

    public String getShowImg() {
        return RequestUrls.IP + showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public int getFkId() {
        return fkId;
    }

    public void setFkId(int fkId) {
        this.fkId = fkId;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }
}
