package com.alsfox.mall.bean.coupons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 浩 on 2016/11/22.
 * 优惠券信息
 */

public class CouponsBean implements Parcelable{

    private int isTask;            // 是否已经领取过该优惠券，0表示没有，1表示已经领取过
    private double totalPrice;               // 订单总金额，用来查询优惠券是否满足使用条件
    private int recordId;                // 记录ID
    private int couponsId;               // 关联优惠券ID
    private int couponsType;             // 领取记录类型，0为注册赠送，1为系统发放
    private String couponsName;              // 优惠券名称，用来描述优惠券信息
    private double useTerm;                  // 使用条件，满多少元可用
    private double money;                    // 优惠券面值
    private String startTime;                // 优惠券开始时间
    private String endTime;                  // 优惠券结束时间
    private int userId;                  // 优惠券领取人ID
    private String createTime;             // 创建时间
    private String updateTime;             // 更新时间
    private int status;                // 状态

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

    public int getIsTask() {
        return isTask;
    }

    public void setIsTask(int isTask) {
        this.isTask = isTask;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getCouponsId() {
        return couponsId;
    }

    public void setCouponsId(int couponsId) {
        this.couponsId = couponsId;
    }

    public int getCouponsType() {
        return couponsType;
    }

    public void setCouponsType(int couponsType) {
        this.couponsType = couponsType;
    }

    public String getCouponsName() {
        return couponsName;
    }

    public void setCouponsName(String couponsName) {
        this.couponsName = couponsName;
    }

    public double getUseTerm() {
        return useTerm;
    }

    public void setUseTerm(double useTerm) {
        this.useTerm = useTerm;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.isTask);
        dest.writeDouble(this.totalPrice);
        dest.writeInt(this.recordId);
        dest.writeInt(this.couponsId);
        dest.writeInt(this.couponsType);
        dest.writeString(this.couponsName);
        dest.writeDouble(this.useTerm);
        dest.writeDouble(this.money);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeInt(this.userId);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeInt(this.status);
    }

    public CouponsBean() {
    }

    private CouponsBean(Parcel in) {
        this.isTask = in.readInt();
        this.totalPrice = in.readDouble();
        this.recordId = in.readInt();
        this.couponsId = in.readInt();
        this.couponsType = in.readInt();
        this.couponsName = in.readString();
        this.useTerm = in.readDouble();
        this.money = in.readDouble();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.userId = in.readInt();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.status = in.readInt();
    }

    public static final Creator<CouponsBean> CREATOR = new Creator<CouponsBean>() {
        public CouponsBean createFromParcel(Parcel source) {
            return new CouponsBean(source);
        }

        public CouponsBean[] newArray(int size) {
            return new CouponsBean[size];
        }
    };
}
