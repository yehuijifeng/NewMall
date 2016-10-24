package com.alsfox.mall.http.interfaces;


import com.alsfox.mall.bean.classify.ShopTypeBean;
import com.alsfox.mall.bean.index.IndexBean;
import com.alsfox.mall.http.HttpBean;
import com.alsfox.mall.http.request.RequestUrls;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 浩 on 2016/6/22.
 * retrofit服务接口
 * 每一个函数都必须有提供请求方式和相对URL的Http注解，
 * Retrofit提供了5种内置的注解：GET、POST、PUT、DELETE和HEAD，在注解中指定的资源的相对URL
 */
public interface ApiService {
    /**
     * 注解的使用方法：
     * 在外面，你将会为你的请求设置一个url
     * 所以，无论哪一种注解都是拼接在url后面的
     */

    //实际上在get的开始已经有一个rul存在了
    //例如，我们的url是“http://gc.ditu.aliyun.com/”
    //那么get注解前就已经存在了这个url，并且使用{}替换符得到的最终url是：
    // http://gc.ditu.aliyun.com/geocoding?a=苏州市
    //那么key中的值也就是从注解path中得到的，它并要求和{}替换符中的字符一致
    //参数不能为null,且不能只有url的参数，还应该包括地址的字段；正确：geocoding?a=苏州市；错误：a=苏州市

    //首页
    @POST(RequestUrls.GET_INDEX_CONTENT_URL)
    Observable<HttpBean<IndexBean>> getIndexData(@QueryMap Map<String, Object> options);

    //分类
    @POST(RequestUrls.GET_COMMODITY_CLASSIFY_URL)
    Observable<HttpBean<ShopTypeBean>> getClassifyData(@QueryMap Map<String, Object> options);



}
