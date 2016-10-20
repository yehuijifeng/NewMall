package com.alsfox.mall.bean.index;

/**
 * Created by 浩 on 2016/10/20.
 * 限时抢购列表
 */

public class IndexQianggouInfoBean {
    private String nowTime;//当前时间
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String timeoutName;//场次

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeoutName() {
        return timeoutName;
    }

    public void setTimeoutName(String timeoutName) {
        this.timeoutName = timeoutName;
    }
}
