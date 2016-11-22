package com.alsfox.mall.view.activity.goods;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.shop.ShopImageBean;
import com.alsfox.mall.bean.shop.ShopInfoBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.SignUtils;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.request.RequestUrls;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.popupwindow.GoodsSpecWindow;
import com.alsfox.mall.presenter.goods.GoodsContentPresenter;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.activity.shoppingcart.ShoppingCartActivity;
import com.alsfox.mall.view.customview.goods.MyScrollView;
import com.alsfox.mall.view.customview.goods.ScrollViewContainer;
import com.alsfox.mall.view.customview.goods.YsnowScrollView;
import com.alsfox.mall.view.customview.goods.YsnowWebView;
import com.alsfox.mall.view.fragment.home.ShoppingCartFragment;
import com.alsfox.mall.view.interfaces.goods.IGoodsContentView;
import com.take.turns.view.TakeTurnsView;

import java.util.ArrayList;
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
    private LinearLayout goods_content_bottom_ly;//底部view

    private float downY;//轮番图滑动y轴
    private int goodsId;//商品id
    private ShopInfoBean shopInfoBean;//商品信息
    private ArrayList<String> imageUrls = new ArrayList<>();
    private GoodsSpecWindow goodsSpecWindow;

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
        goods_content_scroll_one.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                //setSwipeBackEnable(DragEvent.ACTION_DRAG_ENDED == event.getAction());
                return false;
            }
        });
        goods_content_scroll_tow.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                //setSwipeBackEnable(DragEvent.ACTION_DRAG_ENDED == event.getAction());
                return false;
            }
        });
        goods_content_web = (YsnowWebView) findViewById(R.id.goods_content_web);//web页面的图文详情
        goods_content_name_text = (TextView) findViewById(R.id.goods_content_name_text);//商品名称
        goods_content_share_img = (ImageView) findViewById(R.id.goods_content_share_img);//分享
        goods_content_price_text = (TextView) findViewById(R.id.goods_content_price_text);//商品显示价格
        goods_content_lod_price_text = (TextView) findViewById(R.id.goods_content_lod_price_text);//商品折扣价格
        goods_content_lod_price_text.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中间加横线
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
        goods_content_bottom_ly = (LinearLayout) findViewById(R.id.goods_content_bottom_ly);//收藏商品
        btn_commodity_add_shopping_cart = (Button) findViewById(R.id.btn_commodity_add_shopping_cart);//加入购物车
        btn_commodity_purchase_immediately = (Button) findViewById(R.id.btn_commodity_purchase_immediately);//立即购买
        goods_content_to_top_img = (ImageView) findViewById(R.id.goods_content_to_top_img);//回顶部

        goods_content_collection_cb.setOnClickListener(this);
        btn_commodity_add_shopping_cart.setOnClickListener(this);
        btn_commodity_purchase_immediately.setOnClickListener(this);
        //goods_content_collection_ly.setOnClickListener(this);
        goods_content_go_cart_ly.setOnClickListener(this);
        goods_content_more_evaluate_text.setOnClickListener(this);
        goods_content_to_top_img.setOnClickListener(this);
        goods_content_share_img.setOnClickListener(this);

        //最外层scroll设置
        goods_content_scroll.setOutMetrics(getDisplayMerics());
        goods_content_scroll.setLl_commodity_detail_bottom(goods_content_collection_ly);
        goods_content_scroll.setOnLoadSecondPageListener(new LoadPageListener());

        //轮番图设置
        goods_content_img_view.setTakeTurnsHeight(getWindowWidth());
        goods_content_img_view.setSleepTime(4 * 1000);//轮番的循环时间
        goods_content_img_view.setViewpagerScrollTime(300);//轮番滑动速率
//        goods_content_img_view.setTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        downY = event.getRawY();
//                        return true;
//                    case MotionEvent.ACTION_MOVE:
//                        float moveY = Math.abs(event.getRawY()) - downY;
//                        if (moveY > 10f) {
//                            //setRefresh(false);
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        //setRefresh(true);
//                        return false;
//                }
//                return false;
//            }
//        });

    }

    /**
     * 最完成scroll的监听事件
     */
    private class LoadPageListener implements ScrollViewContainer.onLoadPageListener {
        private boolean isFirstLoad = true;

        LoadPageListener() {

        }

        @Override
        public void onLoadFirstPage() {
            goods_content_to_top_img.setVisibility(View.GONE);
        }

        @Override
        public void onLoadSecondPage() {
            if (isFirstLoad) {
                Map<String, Object> parameters = SignUtils.getParameters();
                parameters.put(MallConstant.SHOPINFO_SHOPID, goodsId);
                goods_content_web.loadUrl(RequestUrls.GET_COMMODITY_IMG_AND_TEXT_URL + SignUtils.getParams(parameters));
                isFirstLoad = false;
            }
            goods_content_to_top_img.setVisibility(View.VISIBLE);
        }

        @Override
        public void onMoving(MotionEvent ev) {
            //setSwipeBackEnable(false);
        }

        @Override
        public void onCancel(MotionEvent ev) {
            //setSwipeBackEnable(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (goods_content_img_view != null)
            goods_content_img_view.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (goods_content_img_view != null)
            goods_content_img_view.onPause();
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
//        goods_content_scroll;//最外层
//        goods_content_scroll_one;//第一层
//        goods_content_scroll_tow;//第二层
//        goods_content_web;//web页面的图文详情
        for (ShopImageBean shopImageBean : shopInfoBean.getShopImgList()) {
            imageUrls.add(shopImageBean.getImgUrl());
        }

        goods_content_img_view.setImageUrls(imageUrls);
        goods_content_img_view.setUpdateUI(new TakeTurnsView.UpdateUI() {
            @Override
            public void onUpdateUI(int position, ImageView imageView, String imgUrl) {
                imageLoader.displayImage(imgUrl, imageView, MallAppliaction.getInstance().defaultOptions);
            }

            @Override
            public void onItemClick(int position, ImageView imageView) {
                imageView.setOnClickListener(new ImageClickListener(position));
            }
        });
        goodsSpecWindow = new GoodsSpecWindow(this, shopInfoBean);
        goods_content_name_text.setText(shopInfoBean.getShopName());  //商品名称
        goods_content_price_text.setText("￥" + shopInfoBean.getShowPrice());  //商品显示价格
        goods_content_lod_price_text.setText("" + shopInfoBean.getDelPrice());//商品折扣价格
        goods_content_buy_number_text.setText(shopInfoBean.getShopSaleNum() + "人购买");//几人购买
        goods_content_integral_text.setText("可获得" + shopInfoBean.getGetIntegral() + "积分");//可获得积分
        goods_content_introduce_text.setText(shopInfoBean.getShopIntr());//商品简介
        goods_content_evaluate_number_text.setText("(" + shopInfoBean.getShopPjNum() + ")条评价");//几人评价
        goods_content_zhpf_text.setText("综合评分:" + shopInfoBean.getShopZhPj());//综合评分
        if (shopInfoBean.getIsCollection() == 0) {
            goods_content_collection_cb.setChecked(false);
        } else {
            goods_content_collection_cb.setChecked(true);
        }
        //评分星级
        if (shopInfoBean.getShopComment() == null) {
            goods_content_evaluation_ly.setVisibility(View.GONE);
            goods_content_more_evaluate_text.setVisibility(View.GONE);
            goods_content_zhpf_text.setText("综合评分:5.0");//综合评分
        } else {
            goods_content_more_evaluate_text.setVisibility(View.VISIBLE);
            goods_content_evaluation_ly.setVisibility(View.VISIBLE);
            goods_content_one_user_time_text.setText(shopInfoBean.getShopComment().getUserName() + " " + shopInfoBean.getShopComment().getCreateTime());//首个评价用户+时间
            goods_content_evaluation_text.setText(shopInfoBean.getShopComment().getCommentCon());//首个评价内容
        }

    }

    /**
     * 详情图片点击事件
     */
    class ImageClickListener implements View.OnClickListener {

        private int position;

        public ImageClickListener(int position) {
            this.position = position;
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(LookImageActivity.BUNDLE_KEY_IMAGEURLS, imageUrls);
            bundle.putInt(LookImageActivity.BUNDLE_KEY_IMAGEPOSITION, position);
            startActivity(LookImageActivity.class, bundle);
        }
    }

    @Override
    protected void refresh() {
        showLoading();
        getGoodsContentById();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.btn_commodity_add_shopping_cart://添加购物车
                if (shopInfoBean == null || goodsSpecWindow == null) return;
                goodsSpecWindow.showAtLocation(root, Gravity.BOTTOM, 0, getWindowHeight(), GoodsSpecWindow.ACTION_BUY_SHOPPING_CART);
                break;
            case R.id.btn_commodity_purchase_immediately://立即购买
                if (shopInfoBean == null || goodsSpecWindow == null) return;
                goodsSpecWindow.showAtLocation(root, Gravity.BOTTOM, 0, getWindowHeight(), GoodsSpecWindow.ACTION_BUY_ORDER);
                break;
            case R.id.goods_content_collection_ly://收藏
            case R.id.goods_content_collection_cb://收藏
                getCollection();
                break;
            case R.id.goods_content_go_cart_ly://去购物车
                bundle.putInt(ShoppingCartFragment.KEY_SHOPPING_CART_TYPE, ShoppingCartFragment.SHOPPING_CART_BY_ACTIVITY);
                startActivity(ShoppingCartActivity.class, bundle);
                break;
            case R.id.goods_content_more_evaluate_text://查看更多评论
                break;
            case R.id.goods_content_to_top_img://回顶部
                goods_content_scroll.toTop();
                break;
            case R.id.goods_content_share_img://分享
                break;
        }
    }

    /**
     * 收藏商品
     */
    private void getCollection() {
        if (isLoginTo()) {
            Map<String, Object> params = SignUtils.getParameters();
            params.put(MallConstant.GOODS_CONTENT_USERID, MallAppliaction.getInstance().userBean.getUserId());
            params.put(MallConstant.GOODS_CONTENT_SHOPID, goodsId);
            if (!goods_content_collection_cb.isChecked()) {
                RequestAction.GET_DEL_GOODS_COMMODITY.params.setParams(params);
                sendRequest(RequestAction.GET_DEL_GOODS_COMMODITY);
            } else {
                RequestAction.GET_ADD_GOODS_COMMODITY.params.setParams(params);
                sendRequest(RequestAction.GET_ADD_GOODS_COMMODITY);
            }
        } else {
            goods_content_collection_cb.setChecked(false);
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_GOODS_CONTENT:
                shopInfoBean = (ShopInfoBean) success.getHttpBean().getObject();
                showGoodsContent(shopInfoBean);
                closeLoading();
                break;
            case GET_DEL_GOODS_COMMODITY://删除收藏
                showLongToast(success.getHttpBean().getObject().toString());
                goods_content_collection_cb.setChecked(false);
                break;
            case GET_ADD_GOODS_COMMODITY://添加收藏
                showLongToast(success.getHttpBean().getObject().toString());
                goods_content_collection_cb.setChecked(true);
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        switch (finals.getRequestAction()) {
            case GET_GOODS_CONTENT:
                showErrorLoadingByDefaultClick(finals.getErrorMessage());
                break;
            case GET_DEL_GOODS_COMMODITY://删除收藏
                showLongToast(finals.getErrorMessage());
                break;
            case GET_ADD_GOODS_COMMODITY://添加收藏
                showLongToast(finals.getErrorMessage());
                break;
        }
    }


}
