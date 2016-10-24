package com.alsfox.mall.bean.index;

import com.alsfox.mall.http.request.RequestUrls;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 浩 on 2016/10/20.
 * 首页四个导航栏，圆形
 */
@DatabaseTable
public class IndexDaohangBean {
    @DatabaseField
    private int indexDaohangDbId =1;//数据库id
    @DatabaseField(id = true)
    private int navId;                   // 导航ID
    @DatabaseField
    private String navName;                  // 导航名称
    @DatabaseField
    private int shopTypeId;              // 商品类型ID
    @DatabaseField
    private int indexs;                  // 排序序号,索引从1开始
    @DatabaseField
    private String showImg;                  // 显示图片地址
    @DatabaseField
    private int showImgRes;                 //图片资源文件
    @DatabaseField(foreign = true, columnName = "indexdbid")
    private IndexBean indexBean;//数据库使用外键，这里不做数据参考
    public int getIndexDaohangDbId() {
        return indexDaohangDbId;
    }

    public void setIndexDaohangDbId(int indexDaohangDbId) {
        this.indexDaohangDbId = indexDaohangDbId;
    }

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
