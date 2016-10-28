package com.alsfox.mall.bean.merchant;

/**
 * Created by 浩 on 2016/10/28.
 * 店铺信息
 */

public class MerchantInfoBean {
    private int dianpuId;// 店铺ID
    private int collectionCount;//收藏数
    private int shopCount;//商品数
    private double juli;//当前距离
    private float hplv;//好评率，0-5，大于满星显示
    private String dianpuName;//店铺名称
    private String dianpuUrl;//店铺图片地址
    private String dianpuAddr;//店铺地址
    private String dianpuPhone;//店铺电话
    private int dianpuLevel;//用户等级，1-5，分别为青铜-钻石
    private int isCampaign;//默认0没活动， 1时此店铺有活动

    public int getDianpuId() {
        return dianpuId;
    }

    public void setDianpuId(int dianpuId) {
        this.dianpuId = dianpuId;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getShopCount() {
        return shopCount;
    }

    public void setShopCount(int shopCount) {
        this.shopCount = shopCount;
    }

    public double getJuli() {
        return juli;
    }

    public void setJuli(double juli) {
        this.juli = juli;
    }

    public float getHplv() {
        return hplv;
    }

    public void setHplv(float hplv) {
        this.hplv = hplv;
    }

    public String getDianpuName() {
        return dianpuName;
    }

    public void setDianpuName(String dianpuName) {
        this.dianpuName = dianpuName;
    }

    public String getDianpuUrl() {
        return dianpuUrl;
    }

    public void setDianpuUrl(String dianpuUrl) {
        this.dianpuUrl = dianpuUrl;
    }

    public String getDianpuAddr() {
        return dianpuAddr;
    }

    public void setDianpuAddr(String dianpuAddr) {
        this.dianpuAddr = dianpuAddr;
    }

    public String getDianpuPhone() {
        return dianpuPhone;
    }

    public void setDianpuPhone(String dianpuPhone) {
        this.dianpuPhone = dianpuPhone;
    }

    public int getDianpuLevel() {
        return dianpuLevel;
    }

    public void setDianpuLevel(int dianpuLevel) {
        this.dianpuLevel = dianpuLevel;
    }

    public int getIsCampaign() {
        return isCampaign;
    }

    public void setIsCampaign(int isCampaign) {
        this.isCampaign = isCampaign;
    }
}
