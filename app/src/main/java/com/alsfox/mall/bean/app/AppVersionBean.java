package com.alsfox.mall.bean.app;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 浩 on 2016/10/27.
 * app版本信息
 */
@DatabaseTable
public class AppVersionBean {
    @DatabaseField(id = true)
    private int configId;                    // 配置ID
    @DatabaseField
    private int versionNo;                   // 版本号
    @DatabaseField
    private String versionShow;                  // 版本展示号
    @DatabaseField
    private String versionDesc;                  // 版本描述
    @DatabaseField
    private String downUrl;                      // 安装包下载地址

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionShow() {
        return versionShow;
    }

    public void setVersionShow(String versionShow) {
        this.versionShow = versionShow;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }
}
