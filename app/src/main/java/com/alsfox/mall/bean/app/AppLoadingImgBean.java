package com.alsfox.mall.bean.app;

import com.alsfox.mall.http.request.RequestUrls;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 浩 on 2016/10/27.
 * loaind页面
 */
@DatabaseTable
public class AppLoadingImgBean {
    @DatabaseField(id = true)
    private Integer startId;                 // 配置ID
    @DatabaseField
    private Integer indexs;                  // 排序序号
    @DatabaseField
    private Integer startType;               // 启动图片类型，0为新手指引，-1为启动图片，默认0
    @DatabaseField
    private Integer imgVersion;              // 启动图片版本
    @DatabaseField
    private String imgUrl;                   // 启动图片地址

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public Integer getIndexs() {
        return indexs;
    }

    public void setIndexs(Integer indexs) {
        this.indexs = indexs;
    }

    public Integer getStartType() {
        return startType;
    }

    public void setStartType(Integer startType) {
        this.startType = startType;
    }

    public Integer getImgVersion() {
        return imgVersion;
    }

    public void setImgVersion(Integer imgVersion) {
        this.imgVersion = imgVersion;
    }

    public String getImgUrl() {
        return RequestUrls.IP + imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
