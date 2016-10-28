package com.alsfox.mall.bean.app;

/**
 * Created by 浩 on 2016/10/28.
 * 附近建筑物
 */

public class BaiduPoiBean {
    private String poiId;//兴趣点id
    private String poiAddr;//兴趣点描述
    private double poiRank;//兴趣点排名

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getPoiAddr() {
        return poiAddr;
    }

    public void setPoiAddr(String poiAddr) {
        this.poiAddr = poiAddr;
    }

    public double getPoiRank() {
        return poiRank;
    }

    public void setPoiRank(double poiRank) {
        this.poiRank = poiRank;
    }
}
