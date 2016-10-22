package com.alsfox.mall.bean.index;

import java.util.List;

/**
 * Created by 浩 on 2016/10/20.
 * 首页模块
 */

public class IndexMokuaiBean {

    private List<IndexMokuaiContentBean> indexMoudleContentList;// 模块内容列表
    private int moudleId;                   // 模块ID
    private String moudleName;                  // 模块名称
    private int moudleType;                 // 模块布局类型,0：一排三列；1为├型；2为田型；3为┤型，4为┬型，5为┴型,6为一型
    private int indexs;                     // 排序序号
    private int isShowName;                 // 是否显示模块名称，0显示，-1不显示，默认0

    public List<IndexMokuaiContentBean> getIndexMoudleContentList() {
        return indexMoudleContentList;
    }

    public void setIndexMoudleContentList(List<IndexMokuaiContentBean> indexMoudleContentList) {
        this.indexMoudleContentList = indexMoudleContentList;
    }

    public int getMoudleId() {
        return moudleId;
    }

    public void setMoudleId(int moudleId) {
        this.moudleId = moudleId;
    }

    public String getMoudleName() {
        return moudleName;
    }

    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    public int getMoudleType() {
        return moudleType;
    }

    public void setMoudleType(int moudleType) {
        this.moudleType = moudleType;
    }

    public int getIndexs() {
        return indexs;
    }

    public void setIndexs(int indexs) {
        this.indexs = indexs;
    }

    public int getIsShowName() {
        return isShowName;
    }

    public void setIsShowName(int isShowName) {
        this.isShowName = isShowName;
    }
}
