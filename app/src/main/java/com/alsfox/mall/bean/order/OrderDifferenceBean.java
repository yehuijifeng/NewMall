package com.alsfox.mall.bean.order;

/**
 * Created by 浩 on 2016/11/22.
 * 邮费补差价
 */

public class OrderDifferenceBean {

    private double changeMoney; //加多少元
    private double totalPay; //未满多少元
    private int status;  //0开启，-1关闭

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
}
