package com.alsfox.mall.bean.index;

import com.alsfox.mall.http.request.RequestUrls;

/**
 * Created by 浩 on 2016/10/20.
 * 首页轮番图
 */

public class IndexLunfanInfoBean {

    private int lunfanId;                  // 轮番ID
    private int lunfanType;                // 轮番内容类型，0表示商品，1表示公告
    private int fkId;                      // lunfanType为0时候，表示商品ID；为1时候表示公告ID
    private int indexs;                    // 排序序号，索引从1开始
    private String showImg;                    // 显示图片地址

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
