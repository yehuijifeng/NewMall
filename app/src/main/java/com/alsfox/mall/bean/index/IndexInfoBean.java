package com.alsfox.mall.bean.index;

import java.util.List;

/**
 * Created by 浩 on 2016/10/20.
 * 首页数据
 */

public class IndexInfoBean {

    private List<IndexLunfanInfoBean> indexLunfanContentList;               // 轮番信息列表
    private List<IndexDaohangInfoBean> indexNavList;                                   // 导航列表
    private List<IndexMokuaiInfoBean> indexMoudleList;                              // 模块列表
    private IndexQianggouInfoBean shopTimeOut;//限时抢购列表

    public List<IndexLunfanInfoBean> getIndexLunfanContentList() {
        return indexLunfanContentList;
    }

    public void setIndexLunfanContentList(List<IndexLunfanInfoBean> indexLunfanContentList) {
        this.indexLunfanContentList = indexLunfanContentList;
    }

    public List<IndexDaohangInfoBean> getIndexNavList() {
        return indexNavList;
    }

    public void setIndexNavList(List<IndexDaohangInfoBean> indexNavList) {
        this.indexNavList = indexNavList;
    }

    public List<IndexMokuaiInfoBean> getIndexMoudleList() {
        return indexMoudleList;
    }

    public void setIndexMoudleList(List<IndexMokuaiInfoBean> indexMoudleList) {
        this.indexMoudleList = indexMoudleList;
    }

    public IndexQianggouInfoBean getShopTimeOut() {
        return shopTimeOut;
    }

    public void setShopTimeOut(IndexQianggouInfoBean shopTimeOut) {
        this.shopTimeOut = shopTimeOut;
    }
}
