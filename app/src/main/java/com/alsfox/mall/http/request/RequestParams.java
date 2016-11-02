package com.alsfox.mall.http.request;

import com.alsfox.mall.http.SignUtils;

import java.util.Map;

/**
 * Created by Luhao on 2016/6/23.
 * 网络请求参数集合
 */
public class RequestParams {

    private Map<String, Object> params= SignUtils.getParameters();//请求参数

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
