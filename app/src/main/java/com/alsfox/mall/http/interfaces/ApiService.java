package com.alsfox.mall.http.interfaces;


import com.alsfox.mall.bean.app.AppLoadingImgBean;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.bean.classify.ShopTypeBean;
import com.alsfox.mall.bean.index.IndexBean;
import com.alsfox.mall.bean.merchant.MerchantInfoBean;
import com.alsfox.mall.bean.order.OrderConfirmBean;
import com.alsfox.mall.bean.order.OrderCountBean;
import com.alsfox.mall.bean.searth.HotWordBean;
import com.alsfox.mall.bean.shop.ShopInfoBean;
import com.alsfox.mall.bean.user.UserAddressBean;
import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.http.HttpBean;
import com.alsfox.mall.http.request.RequestUrls;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
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

    //用户订单数量
    @POST(RequestUrls.GET_USER_ORDER_COUNT_URL)
    Observable<HttpBean<OrderCountBean>> getUserOderCount(@QueryMap Map<String, Object> options);

    //用户登录
    @POST(RequestUrls.REQUEST_USER_LOGIN_URL)
    Observable<HttpBean<UserBean>> getUserLogin(@QueryMap Map<String, Object> options);

    //loading页
    @POST(RequestUrls.GET_LOADING_IMAGE_URL)
    Observable<HttpBean<AppLoadingImgBean>> getAppLoading(@QueryMap Map<String, Object> options);

    //版本号
    @POST(RequestUrls.GET_VERSION_INFO_URL)
    Observable<HttpBean<AppVersionBean>> getAppVersion(@QueryMap Map<String, Object> options);

    // 获取热门搜索关键字的URL
    @POST(RequestUrls.GET_SEARCH_HOT_WORDS_URL)
    Observable<HttpBean<HotWordBean>> getSearthHot(@QueryMap Map<String, Object> options);

    //获取商品搜索列表
    @POST(RequestUrls.GET_SEARCH_COMMODITY_LIST_URL)
    Observable<HttpBean<ShopInfoBean>> getSearthGoodsList(@QueryMap Map<String, Object> options);

    //获取店铺列表
    @POST(RequestUrls.GET_VERSION_INFO_URL)
    Observable<HttpBean<MerchantInfoBean>> getSearthMerchantList(@QueryMap Map<String, Object> options);

    //下载通用对象
    @GET
    Call<ResponseBody> getDownApk(@Url String fileUrl);

    //更换用户头像
    @Multipart
    @POST(RequestUrls.REQUEST_MODIFY_USER_HEAD_IMG_URL)
    Call<ResponseBody> getUpdateUserIcon(@PartMap Map<String, RequestBody> params, @QueryMap Map<String, Object> options);

    //获取注册验证码
    @POST(RequestUrls.GET_REGISTER_ID_CODE_URL)
    Observable<HttpBean<String>> getUserRegisterCode(@QueryMap Map<String, Object> options);

    //用户注册
    @POST(RequestUrls.REQUEST_USER_REGISTER_URL)
    Observable<HttpBean<String>> getUserRegister(@QueryMap Map<String, Object> options);

    //商品列表
    @POST(RequestUrls.GET_COMMODITY_LIST_URL)
    Observable<HttpBean<ShopInfoBean>> getGoodsList(@QueryMap Map<String, Object> options);

    //商品列表
    @POST(RequestUrls.GET_COMMODITY_DETAILS_URL)
    Observable<HttpBean<ShopInfoBean>> getGoodsContent(@QueryMap Map<String, Object> options);

    //添加商品收藏
    @POST(RequestUrls.ADD_COMMODITY_COLLECT_URL)
    Observable<HttpBean<String>> getAddCollectionGoods(@QueryMap Map<String, Object> options);

    //删除商品收藏
    @POST(RequestUrls.DEL_COMMODITY_COLLECT_URL)
    Observable<HttpBean<String>> getDeleteCollectionGoods(@QueryMap Map<String, Object> options);

    //挤掉用户
    @POST(RequestUrls.GET_IS_LOGOUT_URL)
    Observable<HttpBean<String>> getIsLoginOut(@QueryMap Map<String, Object> options);

    //获取确认订单信息
    @POST(RequestUrls.GET_ORDER_CONFIRM_INFO_URL)
    Observable<HttpBean<OrderConfirmBean>> getOrderConfirm(@QueryMap Map<String, Object> options);

    //获取服务器时间
    @POST(RequestUrls.GET_SERVER_CURRENT_TIME_URL)
    Observable<HttpBean<Long>> getServiceTime(@QueryMap Map<String, Object> options);

    //获取用户可用优惠券张数
    @POST(RequestUrls.GET_COUPONS_AVAILABLE_COUNT_URL)
    Observable<HttpBean<Integer>> getUserCoupons(@QueryMap Map<String, Object> options);

    //获取用户收货地址列表
    @POST(RequestUrls.GET_USER_DSPT_LIST)
    Observable<HttpBean<UserAddressBean>> getUserAddressList(@QueryMap Map<String, Object> options);

    //设置用户默认收货地址
    @POST(RequestUrls.UPDATE_USERDSPT_DEFAULT)
    Observable<HttpBean<String>> getUserDefaultAddress(@QueryMap Map<String, Object> options);

    //删除收货地址
    @POST(RequestUrls.REQUEST_DELETE_USER_DSPT)
    Observable<HttpBean<String>> getUserDeleteAddress(@QueryMap Map<String, Object> options);

    //修改收货地址
    @POST(RequestUrls.REQUEST_UPDATE_USER_DSPT)
    Observable<HttpBean<String>> getUserUpdateAddress(@QueryMap Map<String, Object> options);

}
