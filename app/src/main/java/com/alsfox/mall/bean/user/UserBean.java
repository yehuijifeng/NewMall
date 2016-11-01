package com.alsfox.mall.bean.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.alsfox.mall.http.request.RequestUrls;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 浩 on 2016/10/25.
 * 用户信息
 */
@DatabaseTable
public class UserBean implements Parcelable {
    @DatabaseField(id = true)
    private int userId;                  // 用户ID
    @DatabaseField
    private String userName;                 // 用户名（手机号）
    @DatabaseField
    private String userPwd;                  // 用户密码（32位大写MD5加密）
    @DatabaseField
    private String userAvatar;               // 用户头像
    @DatabaseField
    private int userIntegral;            // 用户积分，整型数值
    @DatabaseField
    private double userBalance;              // 用户余额，浮点型，保留两位小数
    @DatabaseField
    private int userPlat;                // 0：普通用户；1：QQ用户；2：微信用户；3：微博用户；4：后台添加
    @DatabaseField
    private String openId;                   // 第三方开放ID
    @DatabaseField
    private String yzm;           // 验证码信息

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserAvatar() {
        return RequestUrls.ROOT_URL+userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(int userIntegral) {
        this.userIntegral = userIntegral;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    public int getUserPlat() {
        return userPlat;
    }

    public void setUserPlat(int userPlat) {
        this.userPlat = userPlat;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.userPwd);
        dest.writeString(this.userAvatar);
        dest.writeInt(this.userIntegral);
        dest.writeDouble(this.userBalance);
        dest.writeInt(this.userPlat);
        dest.writeString(this.openId);
        dest.writeString(this.yzm);
    }

    public UserBean() {
    }

    private UserBean(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        this.userPwd = in.readString();
        this.userAvatar = in.readString();
        this.userIntegral = in.readInt();
        this.userBalance = in.readDouble();
        this.userPlat = in.readInt();
        this.openId = in.readString();
        this.yzm = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
