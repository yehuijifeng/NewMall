package com.alsfox.mall.db.classifydb;

import com.alsfox.mall.bean.classify.ShopTypeBean;
import com.alsfox.mall.bean.classify.ShopTypeTowBean;
import com.alsfox.mall.db.base.BaseDBDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 浩 on 2016/10/25.
 * 首页分类
 */

public class ClassifyDao {

    private BaseDBDao<ShopTypeBean, Integer> chassifyOneDao;//一级分类
    private BaseDBDao<ShopTypeTowBean, Integer> chassifyTowDao;//二级分类

    public ClassifyDao() {
        chassifyOneDao = new BaseDBDao<>(ShopTypeBean.class);
        chassifyTowDao = new BaseDBDao<>(ShopTypeTowBean.class);
    }

    public void inster(List<ShopTypeBean> shopTypeBeans) {
        chassifyOneDao.resetTable();
        chassifyTowDao.resetTable();
        for (ShopTypeBean shopTypeBean1 : shopTypeBeans) {
            chassifyOneDao.insertOrUpdate(shopTypeBean1);
            Collection<ShopTypeTowBean> shopTypeBeanList = shopTypeBean1.getSonShopTypeList();
            for (ShopTypeTowBean shopTypeBean2 : shopTypeBeanList) {
                chassifyTowDao.insertOrUpdate(shopTypeBean2);
            }
        }
    }

    public List<ShopTypeBean> queryClassify() {
        List<ShopTypeBean> shopTypeBeans1 = chassifyOneDao.queryAll();
        List<ShopTypeTowBean> shopTypeBeans2 = chassifyTowDao.queryAll();
        for (ShopTypeBean shopTypeBean1 : shopTypeBeans1) {
            List<ShopTypeTowBean> shopTypeBeanList = new ArrayList<>();
            for (ShopTypeTowBean shopTypeBean2 : shopTypeBeans2) {
                if (shopTypeBean2.getParentId() == shopTypeBean1.getTypeId()) {
                    shopTypeBeanList.add(shopTypeBean2);
                }
            }
            shopTypeBean1.setSonShopTypeList(shopTypeBeanList);
        }
        return shopTypeBeans1;
    }
}
