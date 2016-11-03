package com.alsfox.mall.view.activity.goods;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.shop.ShopInfoBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.goods.GoodsContentPresenter;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.customview.goods.MyScrollView;
import com.alsfox.mall.view.customview.goods.ScrollViewContainer;
import com.alsfox.mall.view.customview.goods.YsnowScrollView;
import com.alsfox.mall.view.customview.goods.YsnowWebView;
import com.alsfox.mall.view.interfaces.goods.IGoodsContentView;
import com.take.turns.view.TakeTurnsView;

import java.util.Map;

import static com.alsfox.mall.http.request.RequestAction.GET_GOODS_CONTENT;

/**
 * Created by 浩 on 2016/11/3.
 * 商品详情
 */

public class GoodsContentActivity extends BaseActivity<GoodsContentPresenter> implements IGoodsContentView, View.OnClickListener {
    private TakeTurnsView goods_content_img_view;//轮番图
    private ScrollViewContainer goods_content_scroll;//最外层
    private MyScrollView goods_content_scroll_one;//第一层
    private YsnowScrollView goods_content_scroll_tow;//第二层
    private YsnowWebView goods_content_web;//web页面的图文详情
    private TextView goods_content_name_text;//商品名称
    private ImageView goods_content_share_img;//分享
    private TextView goods_content_price_text;//商品显示价格
    private TextView goods_content_lod_price_text;//商品折扣价格
    private TextView goods_content_buy_number_text;//几人购买
    private TextView goods_content_integral_text;//可获得积分
    private TextView goods_content_introduce_text;//商品简介
    private TextView goods_content_evaluate_number_text;//几人评价
    private TextView goods_content_zhpf_text;//综合评分
    private RatingBar goods_content_zhpf_bar;//评分星级
    private LinearLayout goods_content_evaluation_ly;//首个评价区
    private RatingBar goods_content_one_user_bar;//首个评价星级
    private TextView goods_content_one_user_time_text;//首个评价用户+时间
    private TextView goods_content_evaluation_text;//首个评价内容
    private TextView goods_content_more_evaluate_text;//查看更多评价
    private LinearLayout goods_content_go_cart_ly;//加入购物车
    private LinearLayout goods_content_collection_ly;//收藏商品
    private CheckBox goods_content_collection_cb;//收藏商品标识
    private Button btn_commodity_add_shopping_cart;//加入购物车
    private Button btn_commodity_purchase_immediately;//立即购买
    private ImageView goods_content_to_top_img;//回顶部的按钮


    private int goodsId;//商品id
    private ShopInfoBean shopInfoBean;//商品信息

    @Override
    protected GoodsContentPresenter initPresenter() {
        return new GoodsContentPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_goods_content;
    }

    @Override
    protected String setTitleText() {
        return getResources().getString(R.string.str_goods_details);
    }

    @Override
    protected void initView() {
        goods_content_img_view = (TakeTurnsView) findViewById(R.id.goods_content_img_view);//轮番图
        goods_content_scroll = (ScrollViewContainer) findViewById(R.id.goods_content_scroll);//最外层
        goods_content_scroll_one = (MyScrollView) findViewById(R.id.goods_content_scroll_one);//第一层
        goods_content_scroll_tow = (YsnowScrollView) findViewById(R.id.goods_content_scroll_tow);//第二层
        goods_content_web = (YsnowWebView) findViewById(R.id.goods_content_web);//web页面的图文详情
        goods_content_name_text = (TextView) findViewById(R.id.goods_content_name_text);//商品名称
        goods_content_share_img = (ImageView) findViewById(R.id.goods_content_share_img);//分享
        goods_content_price_text = (TextView) findViewById(R.id.goods_content_price_text);//商品显示价格
        goods_content_lod_price_text = (TextView) findViewById(R.id.goods_content_lod_price_text);//商品折扣价格
        goods_content_buy_number_text = (TextView) findViewById(R.id.goods_content_buy_number_text);//几人购买
        goods_content_integral_text = (TextView) findViewById(R.id.goods_content_integral_text);//可获得积分
        goods_content_introduce_text = (TextView) findViewById(R.id.goods_content_introduce_text);//商品简介
        goods_content_evaluate_number_text = (TextView) findViewById(R.id.goods_content_evaluate_number_text);//几人评价
        goods_content_zhpf_text = (TextView) findViewById(R.id.goods_content_zhpf_text);//综合评分
        goods_content_zhpf_bar = (RatingBar) findViewById(R.id.goods_content_zhpf_bar);//评分星级
        goods_content_evaluation_ly = (LinearLayout) findViewById(R.id.goods_content_evaluation_ly);//首个评价区
        goods_content_one_user_bar = (RatingBar) findViewById(R.id.goods_content_one_user_bar);//首个评价星级
        goods_content_one_user_time_text = (TextView) findViewById(R.id.goods_content_one_user_time_text);//首个评价用户+时间
        goods_content_evaluation_text = (TextView) findViewById(R.id.goods_content_evaluation_text);//首个评价内容
        goods_content_more_evaluate_text = (TextView) findViewById(R.id.goods_content_more_evaluate_text);//查看更多评价
        goods_content_go_cart_ly = (LinearLayout) findViewById(R.id.goods_content_go_cart_ly);//加入购物车
        goods_content_collection_ly = (LinearLayout) findViewById(R.id.goods_content_collection_ly);//收藏商品
        goods_content_collection_cb = (CheckBox) findViewById(R.id.goods_content_collection_cb);//收藏商品标识
        btn_commodity_add_shopping_cart = (Button) findViewById(R.id.btn_commodity_add_shopping_cart);//加入购物车
        btn_commodity_purchase_immediately = (Button) findViewById(R.id.btn_commodity_purchase_immediately);//立即购买
        goods_content_to_top_img = (ImageView) findViewById(R.id.goods_content_to_top_img);//回顶部


        btn_commodity_add_shopping_cart.setOnClickListener(this);
        btn_commodity_purchase_immediately.setOnClickListener(this);
        goods_content_collection_ly.setOnClickListener(this);
        goods_content_go_cart_ly.setOnClickListener(this);
        goods_content_more_evaluate_text.setOnClickListener(this);
        goods_content_to_top_img.setOnClickListener(this);
        goods_content_share_img.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        showLoading();
        goodsId = getInt(MallConstant.GOODSID, 0);
        getGoodsContentById();
    }

    /**
     * 获取商品详情
     */
    private void getGoodsContentById() {
        Map<String, Object> params = GET_GOODS_CONTENT.params.getParams();
        if (MallAppliaction.getInstance().userBean != null)
            params.put(MallConstant.SHOPINFO_USERID, MallAppliaction.getInstance().userBean.getUserId());
        params.put(MallConstant.SHOPINFO_SHOPID, goodsId);
        sendRequest(GET_GOODS_CONTENT);
    }

    /**
     * 显示商品详情信息
     *
     * @param shopInfoBean
     */
    private void showGoodsContent(ShopInfoBean shopInfoBean) {
        if (shopInfoBean == null) return;
//        goods_content_img_view;//轮番图
//        goods_content_scroll;//最外层
//        goods_content_scroll_one;//第一层
//        goods_content_scroll_tow;//第二层
//        goods_content_web;//web页面的图文详情
        goods_content_name_text.setText(shopInfoBean.getShopName());  //商品名称
        goods_content_price_text.setText("￥" + shopInfoBean.getShowPrice());  //商品显示价格
        goods_content_lod_price_text.setText("" + shopInfoBean.getDelPrice());//商品折扣价格
        goods_content_buy_number_text.setText(shopInfoBean.getShopSaleNum() + "人购买");//几人购买
        goods_content_integral_text.setText("可获得" + shopInfoBean.getGetIntegral() + "积分");//可获得积分
        goods_content_introduce_text.setText(shopInfoBean.getShopIntr());//商品简介
        goods_content_evaluate_number_text.setText("(" + shopInfoBean.getShopPjNum() + ")评价");//几人评价
        goods_content_zhpf_text.setText("综合评分:" + shopInfoBean.getShopZhPj());//综合评分
        //goods_content_zhpf_bar;//评分星级
        if (shopInfoBean.getShopComment() == null) {
            goods_content_evaluation_ly.setVisibility(View.GONE);
        } else {
            goods_content_evaluation_ly.setVisibility(View.VISIBLE);
            //goods_content_one_user_bar;//首个评价星级
            goods_content_one_user_time_text.setText(shopInfoBean.getShopComment().getUserName() + " " + shopInfoBean.getShopComment().getCreateTime());//首个评价用户+时间
            goods_content_evaluation_text.setText(shopInfoBean.getShopComment().getCommentCon());//首个评价内容
        }

    }

    @Override
    protected void refresh() {
        showLoading();
        getGoodsContentById();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commodity_add_shopping_cart://添加购物车

                break;
            case R.id.btn_commodity_purchase_immediately://立即购买

                break;
            case R.id.goods_content_collection_ly://收藏

                break;
            case R.id.goods_content_go_cart_ly://去购物车

                break;
            case R.id.goods_content_more_evaluate_text://查看更多评论
                break;
            case R.id.goods_content_to_top_img://回顶部
                break;
            case R.id.goods_content_share_img://分享
                break;
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_GOODS_CONTENT:
                shopInfoBean = (ShopInfoBean) success.getHttpBean().getObject();
                closeLoading();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_GOODS_CONTENT:
                showErrorLoadingByDefaultClick(finals.getErrorMessage());
                break;
        }
    }


}
