package com.alsfox.mall.bean.index;

import com.alsfox.mall.http.request.RequestUrls;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 浩 on 2016/10/20.
 * 首页轮番图
 */
@DatabaseTable
public class IndexLunfanBean {
    @DatabaseField
    private int indexLunfanDbId = 1;//数据库id
    @DatabaseField(id = true)
    private int lunfanId;                  // 轮番ID
    @DatabaseField
    private int lunfanType;                // 轮番内容类型，0表示商品，1表示公告
    @DatabaseField
    private int fkId;                      // lunfanType为0时候，表示商品ID；为1时候表示公告ID
    @DatabaseField
    private int indexs;                    // 排序序号，索引从1开始
    @DatabaseField
    private String showImg;                    // 显示图片地址

    @DatabaseField(foreign = true, columnName = "indexdbid")
    private IndexBean indexBean;//数据库使用外键，这里不做数据参考
    public int getIndexLunfanDbId() {
        return indexLunfanDbId;
    }

    public void setIndexLunfanDbId(int indexLunfanDbId) {
        this.indexLunfanDbId = indexLunfanDbId;
    }

    public int getLunfanId() {
        return lunfanId;
    }

    public void setLunfanId(int lunfanId) {
        this.lunfanId = lunfanId;
    }

    public int getLunfanType() {
        return lunfanType;
    }

    public void setLunfanType(int lunfanType) {
        this.lunfanType = lunfanType;
    }

    public int getFkId() {
        return fkId;
    }

    public void setFkId(int fkId) {
        this.fkId = fkId;
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
}
