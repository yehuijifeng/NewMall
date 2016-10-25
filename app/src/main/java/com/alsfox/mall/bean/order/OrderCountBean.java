package com.alsfox.mall.bean.order;

/**
 * Created by 浩 on 2016/10/25.
 * 用户订单数量
 */

public class OrderCountBean {

    private int waitPayNum;//等待付款订单数
    private int waitSendNum;//等待发货订单数
    private int waitTakeNum;//等待收货订单数
    private int waitCommentNum;//等待评价订单数

    public int getWaitPayNum() {
        return waitPayNum;
    }

    public void setWaitPayNum(int waitPayNum) {
        this.waitPayNum = waitPayNum;
    }

    public int getWaitSendNum() {
        return waitSendNum;
    }

    public void setWaitSendNum(int waitSendNum) {
        this.waitSendNum = waitSendNum;
    }

    public int getWaitTakeNum() {
        return waitTakeNum;
    }

    public void setWaitTakeNum(int waitTakeNum) {
        this.waitTakeNum = waitTakeNum;
    }

    public int getWaitCommentNum() {
        return waitCommentNum;
    }

    public void setWaitCommentNum(int waitCommentNum) {
        this.waitCommentNum = waitCommentNum;
    }
}
