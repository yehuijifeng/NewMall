package com.alsfox.mall.http.request;

/**
 * Created by Luhao on 2016/6/23.
 * 网络请求的url，统一管理
 */
public class RequestUrls {

    /**
     * 服务器地址、项目名
     */
    public static final String IP = "http://114.55.245.32/";//本地测试。基础商城
    public static final String ROOT_URL = IP + "";//本地测试。基础商城

    /**
     * 获取首页数据的URL
     */
    public static final String GET_INDEX_CONTENT_URL = ROOT_URL + "alsfoxShop/site/index/selectIndexContent.action";

    /**
     * 用户协议
     */
    public static final String SELECT_USER_XIEYI_URL = ROOT_URL + "alsfoxShop/site/about/selectUserAgreement.action";
    /**
     * 提现须知
     */
    public static final String SELECT_USER_TIXIAN_URL = ROOT_URL + "alsfoxShop/site/user/selectuserMoneyOutRules.action";

    /**
     * 获取商品分类的URL
     */
    public static final String GET_COMMODITY_CLASSIFY_URL = ROOT_URL + "alsfoxShop/site/shop/shoptype/selectShopTypeList.action";

    /**
     * 获取商品详情的URL
     */
    public static final String GET_COMMODITY_DETAILS_URL = ROOT_URL + "alsfoxShop/site/shop/shopinfo/selectShopInfo.action";

    /**
     * 获取注册验证码的URL
     */
    public static final String GET_REGISTER_ID_CODE_URL = ROOT_URL + "alsfoxShop/site/user/sendYzmForReg.action";

    /**
     * 获取商品列表的URL
     */
    public static final String GET_COMMODITY_LIST_URL = ROOT_URL + "alsfoxShop/site/shop/shopinfo/selectShopInfoList.action";

    /**
     * 请求注册的URL
     */
    public static final String REQUEST_USER_REGISTER_URL = ROOT_URL + "alsfoxShop/site/user/userRegist.action";

    /**
     * 请求商户注册的URL
     */
    public static final String REQUEST_MERCHANT_REGISTER_URL = ROOT_URL + "alsfoxShop/site/user/dealerRegist.action";


    /**
     * 请求登陆的URL
     */
    public static final String REQUEST_USER_LOGIN_URL = ROOT_URL + "alsfoxShop/site/user/userLogin.action";

    /**
     * 找回密码验证码发送请求接口URL
     */
    public static final String REQUEST_USER_SENDYZM_FOR_FIND_PWD = ROOT_URL + "alsfoxShop/site/user/sendYzmForFindPwd.action";

    /**
     * 找回密码请求接口URL
     */
    public static final String REQUEST_USER_FIND_USER_PWD = ROOT_URL + "alsfoxShop/site/user/findUserPwd.action";

    /**
     * 修改密码验证码发送请求接口URL
     */
    public static final String REQUEST_USER_SENDYZM_FOR_UPDATE_PWD = ROOT_URL + "alsfoxShop/site/user/sendYzmForUpdatePwd.action";

    /**
     * 修改密码请求接口URL
     */
    public static final String REQUEST_USER_UPDATE_USER_PWD = ROOT_URL + "alsfoxShop/site/user/updateUserPwd.action";

    /**
     * 添加我的收货地址请求接口URL
     */
    public static final String REQUEST_INSERT_USER_DSPT = ROOT_URL + "alsfoxShop/site/userdspt/insertUserDspt.action";

    /**
     * 修改我的收货地址请求接口URL
     */
    public static final String REQUEST_UPDATE_USER_DSPT = ROOT_URL + "alsfoxShop/site/userdspt/updateUserDspt.action";

    /**
     * 删除我的收货地址请求接口URL
     */
    public static final String REQUEST_DELETE_USER_DSPT = ROOT_URL + "alsfoxShop/site/userdspt/deleteUserDspt.action";

    /**
     * 查询我的收货地址请求接口URL
     */
    public static final String GET_USER_DSPT_LIST = ROOT_URL + "alsfoxShop/site/userdspt/selectUserDsptList.action";

    /**
     * 查询全国省份请求接口URL
     */
    public static final String GET_PROVINCE_LIST = ROOT_URL + "alsfoxShop/system/selectProvinceList.action";

    /**
     * 查询全国城市请求接口URL
     */
    public static final String GET_CITY_LIST = ROOT_URL + "alsfoxShop/system/selectCityList.action";

    /**
     * 查询全国地区请求接口URL
     */
    public static final String GET_AREA_LIST = ROOT_URL + "alsfoxShop/system/selectAreaList.action";

    /**
     * 查询地区请求接口URL
     */
    public static final String SELECT_USER_AREA_LIST_URL = ROOT_URL + "alsfoxShop/system/selectaddressList.action";


    /**
     * 获取确认订单信息的URL
     */
    public static final String GET_ORDER_CONFIRM_INFO_URL = ROOT_URL + "alsfoxShop/site/order/confirmOrderInfo.action";

    /**
     * 设置默认收货地址请求接口URL
     */
    public static final String UPDATE_USERDSPT_DEFAULT = ROOT_URL + "alsfoxShop/site/userdspt/updateUserDsptDefault.action";

    /**
     * 获取商品图文详情的URL
     */
    public static final String GET_COMMODITY_IMG_AND_TEXT_URL = ROOT_URL + "alsfoxShop/site/shop/shopinfo/selectShopDesc.action";

    /**
     * 添加商品收藏的URL
     */
    public static final String ADD_COMMODITY_COLLECT_URL = ROOT_URL + "alsfoxShop/site/shopcollection/insertShopCollection.action";

    /**
     * 删除商品收藏的URL
     */
    public static final String DEL_COMMODITY_COLLECT_URL = ROOT_URL + "alsfoxShop/site/shopcollection/deleteShopCollection.action";

    /**
     * 获取商品收藏列表的URL
     */
    public static final String GET_COMMODITY_COLLECT_URL = ROOT_URL + "alsfoxShop/site/shopcollection/selectShopCollection.action";

    /**
     * 获取待支付订单列表的URL
     */
    public static final String GET_ORDER_LIST_WAIT_PAY_URL = ROOT_URL + "alsfoxShop/site/order/selectOrderInfoWaitPay.action";

    /**
     * 获取待发货订单列表的URL
     */
    public static final String GET_ORDER_LIST_WAIT_DELIVERY_URL = ROOT_URL + "alsfoxShop/site/order/selectOrderInfoWaitSend.action";

    /**
     * 获取待收货订单列表的URL
     */
    public static final String GET_ORDER_LIST_WAIT_RECEIVE_URL = ROOT_URL + "alsfoxShop/site/order/selectOrderInfoWaitTake.action";

    /**
     * 获取待评价订单列表的URL
     */
    public static final String GET_ORDER_LIST_WAIT_COMMENT_URL = ROOT_URL + "alsfoxShop/site/order/selectOrderInfoWaitComment.action";

    /**
     * 获取已完成订单列表的URL
     */
    public static final String GET_ORDER_LIST_COMPLETED_URL = ROOT_URL + "alsfoxShop/site/order/selectOrderInfoOver.action";

    /**
     * 获取loading页图片的URL
     */
    public static final String GET_LOADING_IMAGE_URL = ROOT_URL + "alsfoxShop/site/start/selectStartInfo.action";

    /**
     * 获取我的优惠券的URL
     */
    public static final String GET_MY_COUPONS_LIST_URL = ROOT_URL + "alsfoxShop/site/coupons/selectCouponsRecordInfoList.action";

    /**
     * 查询可用的优惠券的URL
     */
    public static final String GET_KEYONG_COUPONS_LIST_URL = ROOT_URL + "alsfoxShop/site/coupons/selectUserUsdCouponsList.action";

    /**
     * 用户取消订单的URL
     */
    public static final String REQUEST_ORDER_CANCEL_URL = ROOT_URL + "alsfoxShop/site/order/turnOrderInfo.action";

    /**
     * 获取用户订单数量的URL
     */
    public static final String GET_USER_ORDER_COUNT_URL = ROOT_URL + "alsfoxShop/site/order/selectOrderMarketNum.action";

    /**
     * 获取服务器当前时间的URL
     */
    public static final String GET_SERVER_CURRENT_TIME_URL = ROOT_URL + "alsfoxShop/site/order/getSystemCurrent.action";

    /**
     * 修改用户头像的URL
     */
    public static final String REQUEST_MODIFY_USER_HEAD_IMG_URL = ROOT_URL + "alsfoxShop/site/user/updateUserAvatar.action";

    /**
     * 生成订单的URL
     */
    public static final String REQUEST_CONFIRM_ORDER_URL = ROOT_URL + "alsfoxShop/site/order/insertShopOrderInfo.action";

    /**
     * 获取订单详情的URL
     */
    public static final String GET_ORDER_DETAIL_URL = ROOT_URL + "alsfoxShop/site/order/selectOrderInfo.action";

    /**
     * 获取版本信息的URL
     */
    public static final String GET_VERSION_INFO_URL = ROOT_URL + "alsfoxShop/site/version/selectVersionInfo.action";

    /**
     * 确认收货的URL
     */
    public static final String REQUEST_CONFIRM_RECEIVE_URL = ROOT_URL + "alsfoxShop/site/order/takeOrderInfo.action";

    /**
     * 获取优惠券广场的URL
     */
    public static final String GET_COUPONS_SQUARE_URL = ROOT_URL + "alsfoxShop/site/coupons/selectCouponsInfoList.action";

    /**
     * 领取优惠券的URL
     */
    public static final String REQUEST_ADD_COUPONS_URL = ROOT_URL + "alsfoxShop/site/coupons/insertCouponsRecordInfo.action";

    /**
     * 获取优惠券可用张数的URL
     */
    public static final String GET_COUPONS_AVAILABLE_COUNT_URL = ROOT_URL + "alsfoxShop/site/coupons/selectUserUsdCouponsCount.action";

    /**
     * 获取可用优惠券列表的URL
     */
    public static final String GET_COUPONS_AVAILABLE_LIST_URL = ROOT_URL + "alsfoxShop/site/coupons/selectUserUsdCouponsList.action";

    /**
     * 获取用户积分记录的URL
     */
    public static final String GET_USER_INTEGRAL_RECORD_URL = ROOT_URL + "alsfoxShop/site/integral/selectUserIntegralRecord.action";

    /**
     * 获取用户余额记录的URL
     */
    public static final String GET_USER_BALANCE_RECORD_URL = ROOT_URL + "alsfoxShop/site/integral/selectUserMoneyRecord.action";

    /**
     * 钱包支付的URL
     */
    public static final String GET_BALANCE_ACCOUNT_URL = ROOT_URL + "alsfoxShop/site/order/insertOrderPayInfoForMoney.action";

    /**
     * 修改支付方式的URL
     */
    public static final String GET_UPDATE_PAY_TYPE_URL = ROOT_URL + "alsfoxShop/site/order/updateOrderInfoPayType.action";


    /**
     * 获取商品评价的URL
     */
    public static final String GET_COMMODITY_COMMENT_URL = ROOT_URL + "alsfoxShop/site/shopcomment/selectShopCommentList.action";

    /**
     * 提交商品评价的URL
     */
    public static final String SUBMIT_COMMODITY_COMMENT_URL = ROOT_URL + "alsfoxShop/site/shopcomment/insertShopComment.action";

    /**
     * 获取公告列表的URL
     */
    public static final String GET_NOTICE_LIST_URL = ROOT_URL + "alsfoxShop/site/index/selectInformationList.action";

    /**
     * 获取公告详情的URL
     */
    public static final String GET_NOTICE_DETAIL_URL = ROOT_URL + "alsfoxShop/site/index/selectInformationInfo.action";

    /**
     * 获取热门搜索关键字的URL
     */
    public static final String GET_SEARCH_HOT_WORDS_URL = ROOT_URL + "alsfoxShop/site/hot/selectHotWordList.action";

    /**
     * 获取商品搜索列表
     */
    public static final String GET_SEARCH_COMMODITY_LIST_URL = ROOT_URL + "alsfoxShop/site/shop/shopinfo/selectShopInfoList.action";

    /**
     * 提交意见反馈的URL
     */
    public static final String SUBMIT_USER_FEEDBACK_URL = ROOT_URL + "alsfoxShop/site/feedback/insertFeedBackInfo.action";

    /**
     * 关于我们的URL
     */
    public static final String REQUEST_ABOUT_US_URL = ROOT_URL + "alsfoxShop/site/about/selectAboutConfigInfo.action";

    /**
     * 支付宝异步回调URL
     */
    public static final String ALIPAY_ASYNC_CALLBACK_URL = ROOT_URL + "alsfoxShop/site/order/notifyUrl.action";

    /**
     * 商品分享的URL
     */
    public static final String COMMODITY_SHARE_URL = ROOT_URL + "alsfoxShop/site/shop/shopinfo/shopShare.action";

    /**
     * 获取客服电话的URL
     */
    public static final String GET_SERVICE_PHONE_URL = ROOT_URL + "alsfoxShop/site/about/selectKefuConfigInfo.action";

    /**
     * 设置支付密码验证码发送验证码请求接口URL
     */
    public static final String REQUEST_USER_PAY_INSERT_PWD_SMS = ROOT_URL + "alsfoxShop/site/user/sendYzmForSetPayPwd.action";

    /**
     * 设置支付密码验证码发送请求接口URL
     */
    public static final String REQUEST_USER_PAY_INSERT_PWD = ROOT_URL + "alsfoxShop/site/user/updateUserInfoForpayPwd.action";

    /**
     * 修改支付密码验证码发送验证码请求接口URL
     */
    public static final String REQUEST_USER_PAY_UPDATE_PWD_SMS = ROOT_URL + "alsfoxShop/site/user/sendYzmForUpdatePayPwd.action";

    /**
     * 修改支付密码验证码发送请求接口URL
     */
    public static final String REQUEST_USER_PAY_UPDATE_PWD = ROOT_URL + "alsfoxShop/site/user/updateUserPayPwd.action";

    /**
     * 充值的URL
     */
    public static final String INSERT_USER_MONEY_URL = ROOT_URL + "alsfoxShop/site/user/insertUserMoneyInInfo.action";

    /**
     * 查询用户是否设置支付密码的URL
     */
    public static final String SELECT_USER_PAY_PASSWORD_URL = ROOT_URL + "alsfoxShop/site/user/selectUserPayPwdIsExist.action";

    /**
     * 设置支付密码的URL
     */
    public static final String UPDATE_USER_PAY_PASSWORD_URL = ROOT_URL + "alsfoxShop/site/user/updateUserInfoForpayPwd.action";

    /**
     * 提现的URL
     */
    public static final String GET_WITHDRAW_DEPOSIT_URL = ROOT_URL + "alsfoxShop/site/user/insertUserMoneyOut.action";

    /**
     * 查询收款账号
     */
    public static final String SELECT_USER_PAY_MONEY_URL = ROOT_URL + "alsfoxShop/site/useraccount/selectUserAccountList.action";

    /**
     * 修改收款账号
     */
    public static final String UPDATE_USER_PAY_MONEY_URL = ROOT_URL + "alsfoxShop/site/useraccount/updateUserAccount.action";

    /**
     * 删除收款账号
     */
    public static final String DELETE_USER_PAY_MONEY_URL = ROOT_URL + "alsfoxShop/site/useraccount/delUserAccount.action";

    /**
     * 添加收款账号
     */
    public static final String INSERT_USER_PAY_MONEY_URL = ROOT_URL + "alsfoxShop/site/useraccount/insertUserAccount.action";

    /**
     * 商品申请退款
     */
    public static final String GET_USER_EXIT_MONEY_URL = ROOT_URL + "alsfoxShop/site/orderservice/applyOrderServiceTuikuan.action";

    /**
     * 用户退款列表
     */
    public static final String GET_USER_EXIT_MONEY_LIST_URL = ROOT_URL + "alsfoxShop/site/orderservice/selectUserTuikuanList.action";

    /**
     * 用户退货列表
     */
    public static final String GET_USER_EXIT_GOODS_LIST_URL = ROOT_URL + "alsfoxShop/site/orderservice/selectUserTuihuoList.action";

    /**
     * 用户换货列表
     */
    public static final String GET_USER_EXCHANG_GOODS_LIST_URL = ROOT_URL + "alsfoxShop/site/orderservice/selectUserHuanhuoList.action";

    /**
     * 申请退货
     */
    public static final String GET_EXIT_GOODS_URL = ROOT_URL + "alsfoxShop/site/orderservice/applyOrderServiceTuihuo.action";

    /**
     * 申请换货
     */
    public static final String GET_EXCHANG_GOODS_URL = ROOT_URL + "alsfoxShop/site/orderservice/applyOrderServiceHuanhuo.action";

    /**
     * 查询售后处理订单信息
     */
    public static final String GET_AFTER_SERVICE_ORDER_LIST_URL = ROOT_URL + "alsfoxShop/site/order/selectOrderInfoListService.action";

    /**
     * 查询售后服务详情
     */
    public static final String GET_AFTER_SERVICE_DETAILS_URL = ROOT_URL + "alsfoxShop/site/orderservice/selectOrderServiceInfoForDetail.action";

    /**
     * 用户退货请求
     */
    public static final String GET_ORDER_SERVICE_FAHUO_URL = ROOT_URL + "alsfoxShop/site/orderservice/sendOrderServiceTuihuoByUser.action";

    /**
     * 用户换货请求
     */
    public static final String GET_ORDER_SERVICE_HUANHUO_URL = ROOT_URL + "alsfoxShop/site/orderservice/sendOrderServiceHuanhuoByUser.action";

    /**
     * 用户换货确认收货
     */
    public static final String GET_ORDER_SERVICE_HUANHUO_QUEREN_SHOUHUO_URL = ROOT_URL + "alsfoxShop/site/orderservice/updateOrderServiceInfoForHuanhuoTake.action";

    /**
     * 显示抢购
     */
    public static final String GET_FLASH_SALES_LIST_URL = ROOT_URL + "alsfoxShop/site/shop/timeout/selectShopTimeOutList.action";

    /**
     * 获取微信预支付订单的URL
     */
    public static final String GET_WEIXIN_PAY_URL = ROOT_URL + "alsfoxShop/site/order/WXPayOrderInfo.action";

    /**
     * 获取支付方式
     */
    public static final String SELECT_PAY_TYPE_URL = ROOT_URL + "alsfoxShop/site/order/selectorderPayTypeInfoList.action";

    /**
     * 获取店铺列表
     */
    public static final String GET_SHOP_LIST_URL = ROOT_URL + "alsfoxShop/site/dianpu/selectDianpuInfoList.action";

    /**
     * 获取店铺分类
     */
    public static final String GET_SHOP_CLASSIFY_URL = ROOT_URL + "alsfoxShop/site/dianpu/selectDianpuTypeAndDist.action";

    /**
     * 获取店铺详情
     */
    public static final String GET_SHOP_DETAIL_URL = ROOT_URL + "alsfoxShop/site/dianpu/selectDianpuInfo.action";

    /**
     * 获取店铺商品列表
     */
    public static final String GET_SHOP_COMMODITIES_URL = ROOT_URL + "alsfoxShop/site/dianpu/selectShopInfoList.action";

    /**
     * 获取店铺图文详情的URL
     */
    public static final String GET_MERCHANT_PT_DETAIL_URL = ROOT_URL + "alsfoxShop/site/dianpu/selectDianpuDesc.action";

    /**
     * 分享店铺
     */
    public static final String MERCHANT_SHARE_URL = ROOT_URL + "alsfoxShop/site/dianpu/selectDianpuInfoForShare.action";

    /**
     * 收藏店铺
     */
    public static final String COLLECT_MERCHANT_URL = ROOT_URL + "alsfoxShop/site/dianpu/insertDianpuCollection.action";

    /**
     * 取消收藏店铺
     */
    public static final String COLLECT_MERCHANT_CANCEL_URL = ROOT_URL + "alsfoxShop/site/dianpu/deletedianpuCollection.action";

    /**
     * 获取用户收藏的店铺列表
     */
    public static final String GET_COLLECT_MERCHANT_LIST_URL = ROOT_URL + "alsfoxShop/site/dianpu/selectDianpuCollection.action";

    /**
     * 查询滚动文字请求接口
     */
    public static final String SELECT_ROLL_TEXT_URL = ROOT_URL + "alsfoxShop/site/about/selectGundongConfig.action";

    /**
     * 报销申请页面
     */
    public static final String GET_ACCOUNT_APPLY_URL = ROOT_URL + "alsfoxShop/site/baoxiao/selectBaoxiaoForConfig.action";

    /**
     * 提交报销申请
     */
    public static final String GET_ACTION_ACCOUNT_APPLY_URL = ROOT_URL + "alsfoxShop/site/baoxiao/insertBaoxiaoInfo.action";

    /**
     * 查看报销进度详情
     */
    public static final String SELECT_ACCOUNT_SCHEDULE_URL = ROOT_URL + "alsfoxShop/site/baoxiao/selectBaoxiaoInfo.action";

    /**
     * 分享会id
     */
    public static final String GET_UMENG_BACK_URL = ROOT_URL + "alsfoxShop/site/about/updateUserIntegralForShare.action";

    /**
     * 查看报销手续费百分比
     */
    public static final String GET_ACTION_ACCOUNT_SXF_URL = ROOT_URL + "alsfoxShop/site/baoxiao/baoxiaoMgrMessgageRemind.action";

    /**
     * 查看我的团队
     */
    public static final String SELECT_USER_TEAM_URL = ROOT_URL + "alsfoxShop/site/user/whereIsMyTeam.action";

    /**
     * 查看是否审核通过实名认证
     */
    public static final String SELECT_USER_AUTHENTICATION_URL = ROOT_URL + "alsfoxShop/site/user/selectUserAuthenticationInfo.action";

    /**
     * 提交审核实名认证
     */
    public static final String CHANGE_USER_AUTHENTICATION_URL = ROOT_URL + "alsfoxShop/site/user/changeUserAuthenticationInfo.action";

}