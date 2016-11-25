package com.alsfox.mall.bean.order;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 浩 on 2016/11/25.
 * 订单信息
 */

public class OrderInfoBean implements Parcelable{

    private String userAvatar;                // 用户头像
    private String dsptName;                  // 收货人名称
    private String dsptPhone;                 // 收货人电话
    private String dsptAddress;               // 收货地址
    private List<OrderDetailBean> orderDetailList;   // 订单详情信息列表
    private int orderId;                     // 订单ID
    private String orderNo;                      // 订单编号
    private int orderType;                   // 0为线上订单，1为线下订单[货到付款]
    private int payType;                     // 支付方式，0支付宝、1微信、2银联、3钱包、4货到付款
    private int userId;                      // 订单关联用户ID
    private int dsptId;                      // 用户收货地址ID
    private String orderDesc;                    // 订单留言信息
    private int orderSendUser;               // 订单发货人
    private String orderExpressName;             // 快递公司名称
    private String orderExpressNo;               // 快递运单号
    private String orderPeisongTime;             // 订单配送时间（用户自主选择）
    private int isUseCoupons;                // 是否使用优惠券，0不使用，1使用，默认0
    private int couponsId;                   // 如果没有使用优惠券，该值为空
    private double couponsMoney;                 // 如果没有使用优惠券，该值为空
    private int isOnIntegral;                // 是否开启了积分系统，0表示开启了积分系统，-1表示没有，默认0
    private int integralPay;                 // 订单支付积分，如果没有开启积分系统该值为空
    private double integralMoney;                // 积分抵扣金额，如果没有开启积分系统该值为空
    private int getFanIntegral;              // 该订单用户可获取积分，如果没有开启积分系统，该值为空
    private int orderNum;                    // 订单商品数量
    private double orderTotal;                   // 订单总金额
    private double orderNeedPay;                 // 订单应支付金额
    private int serviceStatus;               // 售后处理状态，0退款，1退货，2换货
    private String pay_from;//
    private String createTime;             // 创建时间
    private String updateTime;             // 更新时间
    private int status;                // 状态 0未支付，1已支付待发货|待发货，2为已发货，3为已收货,4为售后处理，-1过期[驳回，取消]

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getDsptName() {
        return dsptName;
    }

    public void setDsptName(String dsptName) {
        this.dsptName = dsptName;
    }

    public String getDsptPhone() {
        return dsptPhone;
    }

    public void setDsptPhone(String dsptPhone) {
        this.dsptPhone = dsptPhone;
    }

    public String getDsptAddress() {
        return dsptAddress;
    }

    public void setDsptAddress(String dsptAddress) {
        this.dsptAddress = dsptAddress;
    }

    public List<OrderDetailBean> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailBean> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDsptId() {
        return dsptId;
    }

    public void setDsptId(int dsptId) {
        this.dsptId = dsptId;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public int getOrderSendUser() {
        return orderSendUser;
    }

    public void setOrderSendUser(int orderSendUser) {
        this.orderSendUser = orderSendUser;
    }

    public String getOrderExpressName() {
        return orderExpressName;
    }

    public void setOrderExpressName(String orderExpressName) {
        this.orderExpressName = orderExpressName;
    }

    public String getOrderExpressNo() {
        return orderExpressNo;
    }

    public void setOrderExpressNo(String orderExpressNo) {
        this.orderExpressNo = orderExpressNo;
    }

    public String getOrderPeisongTime() {
        return orderPeisongTime;
    }

    public void setOrderPeisongTime(String orderPeisongTime) {
        this.orderPeisongTime = orderPeisongTime;
    }

    public int getIsUseCoupons() {
        return isUseCoupons;
    }

    public void setIsUseCoupons(int isUseCoupons) {
        this.isUseCoupons = isUseCoupons;
    }

    public int getCouponsId() {
        return couponsId;
    }

    public void setCouponsId(int couponsId) {
        this.couponsId = couponsId;
    }

    public double getCouponsMoney() {
        return couponsMoney;
    }

    public void setCouponsMoney(double couponsMoney) {
        this.couponsMoney = couponsMoney;
    }

    public int getIsOnIntegral() {
        return isOnIntegral;
    }

    public void setIsOnIntegral(int isOnIntegral) {
        this.isOnIntegral = isOnIntegral;
    }

    public int getIntegralPay() {
        return integralPay;
    }

    public void setIntegralPay(int integralPay) {
        this.integralPay = integralPay;
    }

    public double getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(double integralMoney) {
        this.integralMoney = integralMoney;
    }

    public int getGetFanIntegral() {
        return getFanIntegral;
    }

    public void setGetFanIntegral(int getFanIntegral) {
        this.getFanIntegral = getFanIntegral;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public double getOrderNeedPay() {
        return orderNeedPay;
    }

    public void setOrderNeedPay(double orderNeedPay) {
        this.orderNeedPay = orderNeedPay;
    }

    public int getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(int serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getPay_from() {
        return pay_from;
    }

    public void setPay_from(String pay_from) {
        this.pay_from = pay_from;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public OrderInfoBean() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userAvatar);
        dest.writeString(this.dsptName);
        dest.writeString(this.dsptPhone);
        dest.writeString(this.dsptAddress);
        dest.writeTypedList(orderDetailList);
        dest.writeInt(this.orderId);
        dest.writeString(this.orderNo);
        dest.writeInt(this.orderType);
        dest.writeInt(this.payType);
        dest.writeInt(this.userId);
        dest.writeInt(this.dsptId);
        dest.writeString(this.orderDesc);
        dest.writeInt(this.orderSendUser);
        dest.writeString(this.orderExpressName);
        dest.writeString(this.orderExpressNo);
        dest.writeString(this.orderPeisongTime);
        dest.writeInt(this.isUseCoupons);
        dest.writeInt(this.couponsId);
        dest.writeDouble(this.couponsMoney);
        dest.writeInt(this.isOnIntegral);
        dest.writeInt(this.integralPay);
        dest.writeDouble(this.integralMoney);
        dest.writeInt(this.getFanIntegral);
        dest.writeInt(this.orderNum);
        dest.writeDouble(this.orderTotal);
        dest.writeDouble(this.orderNeedPay);
        dest.writeInt(this.serviceStatus);
        dest.writeString(this.pay_from);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeInt(this.status);
    }

    private OrderInfoBean(Parcel in) {
        this.userAvatar = in.readString();
        this.dsptName = in.readString();
        this.dsptPhone = in.readString();
        this.dsptAddress = in.readString();
        in.readTypedList(orderDetailList, OrderDetailBean.CREATOR);
        this.orderId = in.readInt();
        this.orderNo = in.readString();
        this.orderType = in.readInt();
        this.payType = in.readInt();
        this.userId = in.readInt();
        this.dsptId = in.readInt();
        this.orderDesc = in.readString();
        this.orderSendUser = in.readInt();
        this.orderExpressName = in.readString();
        this.orderExpressNo = in.readString();
        this.orderPeisongTime = in.readString();
        this.isUseCoupons = in.readInt();
        this.couponsId = in.readInt();
        this.couponsMoney = in.readDouble();
        this.isOnIntegral = in.readInt();
        this.integralPay = in.readInt();
        this.integralMoney = in.readDouble();
        this.getFanIntegral = in.readInt();
        this.orderNum = in.readInt();
        this.orderTotal = in.readDouble();
        this.orderNeedPay = in.readDouble();
        this.serviceStatus = in.readInt();
        this.pay_from = in.readString();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.status = in.readInt();
    }

    public static final Creator<OrderInfoBean> CREATOR = new Creator<OrderInfoBean>() {
        public OrderInfoBean createFromParcel(Parcel source) {
            return new OrderInfoBean(source);
        }

        public OrderInfoBean[] newArray(int size) {
            return new OrderInfoBean[size];
        }
    };
}
