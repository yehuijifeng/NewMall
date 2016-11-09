package com.alsfox.mall.utils;


import com.alsfox.mall.bean.shop.ShopInfoBean;
import com.alsfox.mall.bean.shop.ShopSpecBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Luhao
 * on 2016/4/22.
 * 价格显示工具类
 */
public class SpecUtils {

    //价格区间
    public static String getPriceInterval(ShopInfoBean shopInfoVo) {
        String spec = "￥ ";
        if (shopInfoVo.getIsGuige() == 0) {//使用规格
            List<Double> dlist = new ArrayList<>();
            for (int i = 0; i < shopInfoVo.getShopSpecList().size(); i++) {
                dlist.add(shopInfoVo.getShopSpecList().get(i).getSpecPrice());
            }
            if (Collections.min(dlist).equals(Collections.max(dlist))) {
                spec += Collections.min(dlist);
            } else
                spec += Collections.min(dlist) + " ~ " + Collections.max(dlist);

        } else {//不使用规格
            spec += shopInfoVo.getShowPrice();
        }
        return spec;
    }

    //积分区间
    public static String getIntegralInterval(ShopInfoBean shopInfoVo) {
        String integral = "可获得";
        if (shopInfoVo.getIsGuige() == 0) {//使用规格
            List<Integer> dlist = new ArrayList<>();
            for (int i = 0; i < shopInfoVo.getShopSpecList().size(); i++) {
                dlist.add(shopInfoVo.getShopSpecList().get(i).getGetIntegral());
            }
            if (Collections.min(dlist).equals(Collections.max(dlist))) {
                integral += Collections.min(dlist);
            } else
                integral += Collections.min(dlist) + " ~ " + Collections.max(dlist) + "积分";
        } else {
            integral += shopInfoVo.getGetIntegral() + "积分";
        }
        return integral;
    }

    //价格区间，规格
    public static String getPriceInterval(ShopSpecBean currentShopSpec) {
        String spec = "￥ ";
        spec += currentShopSpec.getSpecPrice();
        return spec;
    }

    //积分区间，规格
    public static String getIntegralInterval(ShopSpecBean currentShopSpec) {
        String integral = "可获得";
        integral += currentShopSpec.getGetIntegral() + "积分";
        return integral;
    }
}
