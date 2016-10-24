package com.alsfox.mall.bean.index;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

/**
 * Created by 浩 on 2016/10/20.
 * 首页数据
 */
@DatabaseTable()
public class IndexBean {
    @DatabaseField(id = true)
    private int indexDbId =1;//数据库id
    @ForeignCollectionField()
    private Collection<IndexLunfanBean> indexLunfanContentList;               // 轮番信息列表
    @ForeignCollectionField()
    private Collection<IndexDaohangBean> indexNavList;                                   // 导航列表
    @ForeignCollectionField()
    private Collection<IndexMokuaiBean> indexMoudleList;                              // 模块列表

    public int getIndexDbId() {
        return indexDbId;
    }

    public void setIndexDbId(int indexDbId) {
        this.indexDbId = indexDbId;
    }

    private IndexQianggouBean shopTimeOut;//限时抢购列表

    public Collection<IndexLunfanBean> getIndexLunfanContentList() {
        return indexLunfanContentList;
    }

    public void setIndexLunfanContentList(List<IndexLunfanBean> indexLunfanContentList) {
        this.indexLunfanContentList = indexLunfanContentList;
    }

    public Collection<IndexDaohangBean> getIndexNavList() {
        return indexNavList;
    }

    public void setIndexNavList(List<IndexDaohangBean> indexNavList) {
        this.indexNavList = indexNavList;
    }

    public Collection<IndexMokuaiBean> getIndexMoudleList() {
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
