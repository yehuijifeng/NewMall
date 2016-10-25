package com.alsfox.mall.bean.classify;

import com.alsfox.mall.http.request.RequestUrls;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by 浩 on 2016/10/22.
 * 分类
 */
@DatabaseTable
public class ShopTypeTowBean {
    @DatabaseField(generatedId = true)
    private int shopTypeId;//数据库用自增长id
    @DatabaseField
    private int typeId;               // 商品分类ID
    @DatabaseField
    private int parentId;             // 商品分类父ID
    @DatabaseField
    private String typeName;              // 商品分类名称
    @DatabaseField
    private String typeIcon;              // 商品分类图标地址链接

    private Collection<ShopTypeTowBean> sonShopTypeList;              // 子分类列表

    public int getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(int shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public Collection<ShopTypeTowBean> getSonShopTypeList() {
        return sonShopTypeList;
    }

    public void setSonShopTypeList(Collection<ShopTypeTowBean> sonShopTypeList) {
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
