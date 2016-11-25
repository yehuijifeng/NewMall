package com.alsfox.mall.bean.order;

/**
 * Created by 浩 on 2016/11/25.
 * 获取订单支付方式
 */

public class OrderPayTypeBean {
    private int payNumber;//支付类型id
    private String payType;//支付描述

    public int getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(int payNumber) {
        this.payNumber = payNumber;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
