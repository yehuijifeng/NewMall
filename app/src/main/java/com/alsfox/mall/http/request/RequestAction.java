package com.alsfox.mall.http.request;


import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by Luhao on 2016/6/23.
 * 网络请求的主要枚举
 */
public enum RequestAction {

    //获得首页数据
    GET_INDEX_DATA(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getIndexData(params.getParams());
        }
    },

    //获得分类数据
    GET_CLASSIFY_DATA(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getClassifyData(params.getParams());
        }
    },

    //获取用户订单数量
    GET_USER_ORDER_COUNT(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserOderCount(params.getParams());
        }
    },

    //用户登录
    GET_USER_LOGIN(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserLogin(params.getParams());
        }
    },

    //loading页
    GET_APP_LOADING(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getAppLoading(params.getParams());
        }
    },

    //版本号
    GET_APP_VERSION(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getAppVersion(params.getParams());
        }
    },

    //获取热门搜索关键字的URL
    GET_SEARCH_HOT_WORDS(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getSearthHot(params.getParams());
        }
    },

    //获取商品搜索列表
    GET_SEARCH_GOODS_LIST(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getSearthGoodsList(params.getParams());
        }
    },

    //获取店铺列表
    GET_SEARCH_MERCHANT_LIST(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getSearthMerchantList(params.getParams());
        }
    },

    //通用下载文件
    GET_DOWN_APK(new RequestParams()) {
        @Override
        public void getRequest() {
        }
    },

    //上传用户头像
    GET_UPDATE_USER_ICON(new RequestParams()) {
        @Override
        public void getRequest() {
        }
    },
    //获得验证码
    GET_USER_REGISTER_CODE(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserRegisterCode(params.getParams());
        }
    },

    //用户注册
    GET_USER_REGISTER(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserRegister(params.getParams());
        }
    },

    //商品列表
    GET_GOODS_LIST(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getGoodsList(params.getParams());
        }
    },

    //商品详情
    GET_GOODS_CONTENT(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getGoodsContent(params.getParams());
        }
    },

    //添加商品收藏
    GET_ADD_GOODS_COMMODITY(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getAddCollectionGoods(params.getParams());
        }
    },

    //删除商品收藏
    GET_DEL_GOODS_COMMODITY(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getDeleteCollectionGoods(params.getParams());
        }
    },

    //注册信鸽
    GET_IS_LOGOUT(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getIsLoginOut(params.getParams());
        }
    },

    //获取确认订单信息
    GET_ORDER_CONFIRM_INFO(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getOrderConfirm(params.getParams());
        }
    },

    //获取服务器时间
    GET_SERVER_TIME(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getServiceTime(params.getParams());
        }
    },

    //获取用户优惠券张数
    GET_USER_COUPONS(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserCoupons(params.getParams());
        }
    },

    //获取用户收货地址列表
    SELECT_USER_DSPT_LIST(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserAddressList(params.getParams());
        }
    },

    //设置用户默认收货地址
    SET_USERDSPT_DEFAULT(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserDefaultAddress(params.getParams());
        }
    },

    //删除收货地址
    DELETE_USER_DSPT(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserDeleteAddress(params.getParams());
        }
    },

    //修改我的收货地址请求
    UPDATE_USER_DSPT(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserUpdateAddress(params.getParams());
        }
    },

    //获取我的优惠券
    GET_MY_COUPONS_LIST(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserCouponsMy(params.getParams());
        }
    },

    //优惠券广场
    GET_KEYONG_COUPONS_LIST(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserCouponsSquare(params.getParams());
        }
    },

    //显示可用优惠券
    GET_USE_COUPONS_LIST(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserCouponsUse(params.getParams());
        }
    },

    //领取优惠券
    REQUEST_ADD_COUPONS(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getUserCouponsAdd(params.getParams());
        }
    },

    //获取支付方式
    SELECT_PAY_TYPE(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getOrderPayType(params.getParams());
        }
    },

    //生成订单
    REQUEST_CONFIRM_ORDER(new RequestParams()) {
        @Override
        public void getRequest() {
            observable = RetrofitManage.getInstance().getService().getConfirmOrder(params.getParams());
        }
    },

    ;
    public Call<ResponseBody> call;//下载文件专用，okhttp类型的回调
    public Observable observable;//普通网络请求使用，网络请求的操作实例
    public RequestParams params;//请求参数实例

    RequestAction(RequestParams paramss) {
        params = paramss;
    }

    //发送请求，生成预请求对象
    public abstract void getRequest();


}
