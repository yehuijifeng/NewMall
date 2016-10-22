package com.alsfox.mall.bean.classify;

import com.alsfox.mall.http.request.RequestUrls;

import java.util.List;

/**
 * Created by 浩 on 2016/10/22.
 * 分类
 */

public class ShopTypeBean {

    private List<ShopTypeBean> sonShopTypeList;              // 子分类列表
    private int typeId;               // 商品分类ID
    private int parentId;             // 商品分类父ID
    private String typeName;              // 商品分类名称
    private String typeIcon;              // 商品分类图标地址链接

    public List<ShopTypeBean> getSonShopTypeList() {
        return sonShopTypeList;
    }

    public void setSonShopTypeList(List<ShopTypeBean> sonShopTypeList) {
        this.sonShopTypeList = sonShopTypeList;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeIcon() {
        return RequestUrls.IP + typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }
}
