package com.alsfox.mall.bean.order;

import com.alsfox.mall.bean.user.UserAddressBean;

import java.util.List;


/**
 * Created by 浩 on 2016/11/22.
 * 确认订单信息
 */

public class OrderConfirmBean {

    private List<OrderDetailBean> orderDetailList;                 // 商品列表信息
    private int couponsCount;                                // 优惠券可用张数
    private int isInteger;                                   // 是否开启了积分系统，0表示开启，-1表示没有开启
    private int myInteger;                                   // 我的积分
    private double integerVsmoney;                               // 1积分等于多少钱
    private UserAddressBean userDspt;                             //用户默认收货地址
    private OrderPreferentialBean orderPayMin;                   //订单优惠
    private OrderDifferenceBean orderPayAdd;                    //订单补差价

    public List<OrderDetailBean> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailBean> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public int getCouponsCount() {
        return couponsCount;
    }

    public void setCouponsCount(int couponsCount) {
        this.couponsCount = couponsCount;
    }

    public int getInteger() {
        return isInteger;
    }

    public void setIsint(int isint) {
        this.isInteger = isint;
    }

    public int getMyInteger() {
        return myInteger;
    }

    public void setMyInteger(int myint) {
        this.myInteger = myint;
    }

    public Double getIntegerVsmoney() {
        return integerVsmoney;
    }

    public void setIntegerVsmoney(double intVsmoney) {
        this.integerVsmoney = intVsmoney;
    }

    public UserAddressBean getUserDspt() {
        return userDspt;
    }

    public void setUserDspt(UserAddressBean userDspt) {
        this.userDspt = userDspt;
    }

    public OrderPreferentialBean getOrderPayMin() {
        return orderPayMin;
    }

    public void setOrderPayMin(OrderPreferentialBean orderPayMin) {
        this.orderPayMin = orderPayMin;
    }

    public OrderDifferenceBean getOrderPayAdd() {
        return orderPayAdd;
    }

    public void setOrderPayAdd(OrderDifferenceBean orderPayAdd) {
        this.orderPayAdd = orderPayAdd;
    }
}
