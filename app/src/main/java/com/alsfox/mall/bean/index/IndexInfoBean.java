package com.alsfox.mall.bean.index;

import java.util.List;

/**
 * Created by 浩 on 2016/10/20.
 * 首页数据
 */

public class IndexInfoBean {

    private List<IndexLunfanBean> indexLunfanContentList;               // 轮番信息列表
    private List<IndexDaohangBean> indexNavList;                                   // 导航列表
    private List<IndexMokuaiBean> indexMoudleList;                              // 模块列表
    private IndexQianggouBean shopTimeOut;//限时抢购列表

    public List<IndexLunfanBean> getIndexLunfanContentList() {
        return indexLunfanContentList;
    }

    public void setIndexLunfanContentList(List<IndexLunfanBean> indexLunfanContentList) {
        this.indexLunfanContentList = indexLunfanContentList;
    }

    public List<IndexDaohangBean> getIndexNavList() {
        return indexNavList;
    }

    public void setIndexNavList(List<IndexDaohangBean> indexNavList) {
        this.indexNavList = indexNavList;
    }

    public List<IndexMokuaiBean> getIndexMoudleList() {
        return indexMoudleList;
    }

    public void setIndexMoudleList(List<IndexMokuaiBean> indexMoudleList) {
        this.indexMoudleList = indexMoudleList;
    }

    public IndexQianggouBean getShopTimeOut() {
        return shopTimeOut;
    }

    public void setShopTimeOut(IndexQianggouBean shopTimeOut) {
        this.shopTimeOut = shopTimeOut;
    }
}
