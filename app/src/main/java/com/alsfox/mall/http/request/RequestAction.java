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
