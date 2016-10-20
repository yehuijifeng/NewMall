package com.alsfox.mall.bean.index;

import com.alsfox.mall.http.request.RequestUrls;

/**
 * Created by 浩 on 2016/10/20.
 * 首页四个导航栏，圆形
 */

public class IndexDaohangInfoBean {

    private int navId;                   // 导航ID
    private String navName;                  // 导航名称
    private int shopTypeId;              // 商品类型ID
    private int indexs;                  // 排序序号,索引从1开始
    private String showImg;                  // 显示图片地址
    private int showImgRes;                 //图片资源文件

    public int getNavId() {
        return navId;
    }

    public void setNavId(int navId) {
        this.navId = navId;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public int getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(int shopTypeId) {
        this.shopTypeId = shopTypeId;
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

    public int getShowImgRes() {
        return showImgRes;
    }

    public void setShowImgRes(int showImgRes) {
        this.showImgRes = showImgRes;
    }

}
