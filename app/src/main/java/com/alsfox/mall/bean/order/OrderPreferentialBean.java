package com.alsfox.mall.bean.order;

/**
 * Created by 浩 on 2016/11/22.
 * 订单优惠
 */

public class OrderPreferentialBean {

    private double changeMoney; //减多少元
    private double totalPay; //满多少元
    private int status;  //0开启，-1关闭
    private String remark;//优惠公告

    public double getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(double changeMoney) {
        this.changeMoney = changeMoney;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
