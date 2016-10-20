package com.alsfox.mall.http;

import android.text.TextUtils;

import com.alsfox.mall.utils.MD5Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 浩 on 2016/6/22.
 * 签名工具类
 */
public class SignUtils {


    public static final String KEY_SIGN = "sign";

    public static final String KEY_PRIVATE = "key";

    public static final String KEY_TIMESTAMP = "timestamp";

    public static String getSign(Map<String, Object> param) {
        Collection<String> keySet = param.keySet();
        List<String> list = new ArrayList<>(keySet);

        //对key键值按字典升序排序
        Collections.sort(list);
        String paramStr = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(KEY_SIGN)) continue;
            paramStr += list.get(i) + "=" + param.get(list.get(i)) + "&";
        }
        if (!TextUtils.isEmpty(paramStr))
            paramStr = paramStr.substring(0, paramStr.length() - 1);
        return MD5Util.MD5(paramStr);
    }

    public static Map<String, Object> getParameters() {
        Map<String, Object> params = new HashMap<>();
        params.put(KEY_PRIVATE, "alsfoxShop_plat");
        params.put(KEY_TIMESTAMP, System.currentTimeMillis());
        return params;
    }
}
