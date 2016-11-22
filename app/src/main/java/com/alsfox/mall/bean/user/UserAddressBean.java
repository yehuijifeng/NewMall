package com.alsfox.mall.bean.user;

/**
 * Created by 浩 on 2016/11/22.
 * 用户收货地址
 */

public class UserAddressBean {

    private int dsptId;     //收货地址ID
    private int userId;     //用户ID
    private String dsptName;    //收货人名称
    private String dsptPhone;   //收货人电话
    private String dsptArea;    //收货地区，由三级级联城市拼接
    private String dsptAddress; //收货详细地址
    private int isDefault;  //设置默认地址：1 代表已设为默认地址

    public int getDsptId() {
        return dsptId;
    }

    public void setDsptId(int dsptId) {
        this.dsptId = dsptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getDsptArea() {
        return dsptArea;
    }

    public void setDsptArea(String dsptArea) {
        this.dsptArea = dsptArea;
    }

    public String getDsptAddress() {
        return dsptAddress;
    }

    public void setDsptAddress(String dsptAddress) {
        this.dsptAddress = dsptAddress;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
