package com.alsfox.mall.bean.searth;

/**
 * Created by 浩 on 2016/10/28.
 * 搜索热门关键词
 */

public class HotWordBean {
    private int hotId;           // 热门词ID
    private String hotName;          // 热门词名称

    public int getHotId() {
        return hotId;
    }

    public void setHotId(int hotId) {
        this.hotId = hotId;
    }

    public String getHotName() {
        return hotName;
    }

    public void setHotName(String hotName) {
        this.hotName = hotName;
    }
}
