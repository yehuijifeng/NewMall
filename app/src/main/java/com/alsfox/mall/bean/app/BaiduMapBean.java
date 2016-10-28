package com.alsfox.mall.bean.app;

import java.util.List;

/**
 * Created by 浩 on 2016/10/28.
 * 百度地图
 */

public class BaiduMapBean {
    /**
     * 61 ： GPS定位结果，GPS定位成功。
     * 62 ： 无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。
     * 63 ： 网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。
     * 65 ： 定位缓存的结果。
     * 66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。
     * 67 ： 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。
     * 68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。
     * 161： 网络定位结果，网络定位定位成功。
     * 162： 请求串密文解析失败，一般是由于客户端SO文件加载失败造成，请严格参照开发指南或demo开发，放入对应SO文件。
     * 167： 服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。
     * 502： key参数错误，请按照说明文档重新申请KEY。
     * 505： key不存在或者非法，请按照说明文档重新申请KEY。
     * 601： key服务被开发者自己禁用，请按照说明文档重新申请KEY。
     * 602： key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY。
     * 501～700：key验证失败，请按照说明文档重新申请KEY。
     */
    private int errorCode;//错误码。161： 网络定位结果，网络定位定位成功。
    private String time;//yyy-MM-dd hh:mm:ss
    private double latitude;//维度
    private double lontitude;//经度
    private String addr;//地址
    private String describe;//定位描述
    private String locationDescribe;//位置描述
    private float radius;//精度
    private List<BaiduPoiBean> poiList;//兴趣点描述

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLontitude() {
        return lontitude;
    }

    public void setLontitude(double lontitude) {
        this.lontitude = lontitude;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLocationDescribe() {
        return locationDescribe;
    }

    public void setLocationDescribe(String locationDescribe) {
        this.locationDescribe = locationDescribe;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public List<BaiduPoiBean> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<BaiduPoiBean> poiList) {
        this.poiList = poiList;
    }
}
