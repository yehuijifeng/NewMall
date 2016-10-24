package com.alsfox.mall.bean.index;

import com.alsfox.mall.http.request.RequestUrls;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 浩 on 2016/10/20.
 * 首页模块内容
 */
@DatabaseTable
public class IndexMokuaiContentBean {

    @DatabaseField
    private int moudleId;                   // 模块ID，父id
    @DatabaseField(id = true)
    private int contentId;                  // 内容ID,子id
    @DatabaseField
    private int shopId;                     // 商品ID
    @DatabaseField
    private int indexs;                     // 排序序号，索引从1开始
    @DatabaseField
    private String showImg;                     // 显示图片地址，缩略图
    @DatabaseField
    private int fkId;//内容id
    @DatabaseField
    private int contentType;//0,商品，1，商品分类，2，公告

    @DatabaseField(foreign = true, columnName = "indexmokuaidbid")
    private IndexMokuaiBean indexMokuaiBean;//数据库使用外键，这里不做数据参考

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
