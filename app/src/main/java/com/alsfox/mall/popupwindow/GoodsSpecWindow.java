package com.alsfox.mall.popupwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.shop.ShopInfoBean;
import com.alsfox.mall.bean.shop.ShopSpecBean;
import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.db.shoppingcart.ShoppingCartDao;
import com.alsfox.mall.utils.DisplayUtils;
import com.alsfox.mall.utils.SpecUtils;
import com.alsfox.mall.view.activity.order.OrderConfirmActivity;
import com.alsfox.mall.view.activity.user.UserLoginActivity;
import com.alsfox.mall.view.customview.CountEditByWindowText;
import com.alsfox.mall.view.customview.FlowLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 权兴
 * on 2015/9/2 17:09.
 */
public class GoodsSpecWindow extends PopupWindow implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private Context context;

    private ShopInfoBean shopInfo;//商品信息

    private List<ShopSpecBean> shopSpecs;//规格列表

    private ShopSpecBean currentShopSpec;//选中的规格

    private View popView;
    private FrameLayout fl_popupwindow_root;//最外层view
    private RelativeLayout rl_popupwindow_root;//包含内容的view
    private TextView tv_goods_price;//价格
    private TextView tv_goods_stock;//库存
    private TextView tv_goods_integral;//可获得积分
    private FlowLayout flow_layout;//显示规格用视图
    private RelativeLayout line_tow;//规格最外层view
    private CountEditByWindowText count_edit_view;//数量的加减
    private Button btn_goods_commit;//确定
    private ImageView iv_goods_icon;//商品图片


    public static final int ACTION_BUY_ORDER = 0;
    public static final int ACTION_BUY_SHOPPING_CART = 1;
    public static final String ACTION_SHOPPING_BACK_PARCELABLE = "addCartAnimByCan";
    private int action;
    private ShoppingCartDao shoppingCartDao;

    private Handler handler = new Handler();

    private void assignViews(View popView) {
        fl_popupwindow_root = (FrameLayout) popView.findViewById(R.id.fl_popupwindow_root);
        rl_popupwindow_root = (RelativeLayout) popView.findViewById(R.id.rl_popupwindow_root);
        tv_goods_price = (TextView) popView.findViewById(R.id.tv_goods_price);
        tv_goods_stock = (TextView) popView.findViewById(R.id.tv_goods_stock);
        tv_goods_integral = (TextView) popView.findViewById(R.id.tv_goods_integral);
        flow_layout = (FlowLayout) popView.findViewById(R.id.flow_layout);
        line_tow = (RelativeLayout) popView.findViewById(R.id.line_tow);
        count_edit_view = (CountEditByWindowText) popView.findViewById(R.id.count_edit_view);
        count_edit_view.setEditContentLengh(2);
        btn_goods_commit = (Button) popView.findViewById(R.id.btn_goods_commit);
        iv_goods_icon = (ImageView) popView.findViewById(R.id.iv_goods_icon);
        btn_goods_commit.setOnClickListener(this);
        fl_popupwindow_root.setOnClickListener(this);
        ImageLoader.getInstance().displayImage(shopInfo.getShopIcon(), iv_goods_icon, MallAppliaction.getInstance().defaultOptions);
    }


    private void initSpec() {
        count_edit_view.setOnChangeEditText(new CountEditByWindowText.OnChangeEditText() {
            @Override
            public void onChangeEdit(int count) {
//                    if (count >= shopInfo.getTodayBuyMaxMun())
//                        Toast.makeText(context, "超过最大购买数量", Toast.LENGTH_LONG).show();
            }
        });
        if (shopInfo != null) {
            if (shopInfo.getIsGuige() == 0) {//有规格
                shopSpecs = shopInfo.getShopSpecList();
                line_tow.setVisibility(View.VISIBLE);
                showSpecItem(shopSpecs);
            } else {//无规格
                line_tow.setVisibility(View.GONE);
                int shopStock = shopInfo.getShopStock();//库存
                tv_goods_price.setText(SpecUtils.getPriceInterval(shopInfo));
                tv_goods_integral.setText(SpecUtils.getIntegralInterval(shopInfo));
                tv_goods_stock.setText("库存" + shopInfo.getShopStock());
                if (shopStock < 1) {
                    btn_goods_commit.setEnabled(false);
                    btn_goods_commit.setText("库存不足");
                    count_edit_view.setInputBoxEnable(false);
                } else {
                    count_edit_view.setInputBoxEnable(true);
                    btn_goods_commit.setEnabled(true);
                    btn_goods_commit.setText("确定");
                }
                if (shopInfo.getIsTimeout() == 0) {//限时抢购商品不能加数量
                    count_edit_view.setInputBoxEnable(false);
                } else if (shopInfo.getTypeId() == -2) {//报销商品不能加数量
                    count_edit_view.setInputBoxEnable(false);
                }
            }
        }
    }

    /**
     * <p>Create a new empty, non focusable popup window of dimension (0,0).</p> <p/> <p>The popup
     * does provide a background.</p>
     *
     * @param context
     */
    public GoodsSpecWindow(Context context, ShopInfoBean shopInfoVo) {
        super(context);
        this.context = context;
        this.shopInfo = shopInfoVo;
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popView = View.inflate(context, R.layout.window_goods_spec, null);
        assignViews(popView);
        initSpec();
        setFocusable(true);
        setAnimationStyle(R.style.AnimationPreviewForCommodityPop);
        setBackgroundDrawable(new ColorDrawable(0xFFFFFF));
        setContentView(popView);
        setOutsideTouchable(true);
        update();
        shoppingCartDao = new ShoppingCartDao();
    }

    /**
     * 显示出所有的规格item
     *
     * @param shopSpecs
     */
    private void showSpecItem(List<ShopSpecBean> shopSpecs) {
        if (shopSpecs == null || shopSpecs.size() < 1) return;
        int padding = DisplayUtils.dip2px(context, 10);

        for (int i = 0; i < shopSpecs.size(); i++) {
            ShopSpecBean shopSpecBean = shopSpecs.get(i);
            final TextView specItemText = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            specItemText.setId(i);
            specItemText.setLayoutParams(layoutParams);
            specItemText.setBackgroundResource(R.drawable.bg_goods_spec_window_text);
            specItemText.setPadding(padding, padding, padding, padding);
            specItemText.setTextSize(context.getResources().getInteger(R.integer.goods_spec_text_size));
            specItemText.setText(shopSpecBean.getSpecName());
            specItemText.setTextColor(context.getResources().getColor(R.color.viewpager_tab_text_color));
            specItemText.setOnClickListener(new FlowItemClick());
            if (i == 0) {
                specItemText.setSelected(true);
                specItemText.setTextColor(context.getResources().getColor(R.color.white));
                currentShopSpec = shopSpecBean;
                tv_goods_price.setText(SpecUtils.getPriceInterval(shopSpecBean));
                tv_goods_integral.setText(SpecUtils.getIntegralInterval(shopSpecBean));
                tv_goods_stock.setText("库存" + shopSpecBean.getSpecNum());
                count_edit_view.setMaxCount(shopSpecBean.getSpecNum());
            }

            flow_layout.addView(specItemText);
            flow_layout.setHorizontalSpacing(padding);
        }
    }

    /**
     * 规格点击事件
     */
    private class FlowItemClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            v.setSelected(true);
            ((TextView) v).setTextColor(context.getResources().getColor(R.color.white));
            currentShopSpec = shopSpecs.get(v.getId());
            tv_goods_price.setText(SpecUtils.getPriceInterval(currentShopSpec));
            tv_goods_integral.setText(SpecUtils.getIntegralInterval(currentShopSpec));
            tv_goods_stock.setText("库存" + currentShopSpec.getSpecNum());
            for (int i = 0; i < flow_layout.getChildCount(); i++) {
                if (flow_layout.getChildAt(i).getId() != v.getId()) {
                    count_edit_view.setMaxCount(currentShopSpec.getSpecNum());
                    flow_layout.getChildAt(i).setSelected(false);
                    ((TextView) flow_layout.getChildAt(i)).setTextColor(context.getResources().getColor(R.color.viewpager_tab_text_color));
                }
            }
        }
    }


    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(0, 0);
                params.width = context.getResources().getDimensionPixelSize(R.dimen.searth_image_height);
                params.height = context.getResources().getDimensionPixelSize(R.dimen.searth_image_height);
                params.gravity = Gravity.BOTTOM;
                params.leftMargin = context.getResources().getDimensionPixelSize(R.dimen.padding_and_margin_x);
                params.bottomMargin = rl_popupwindow_root.getHeight() - params.height / 2;
                iv_goods_icon.setLayoutParams(params);
            }
        }, 10);
    }

    public void showAtLocation(View parent, int gravity, int x, int y, int action) {
        this.showAtLocation(parent, gravity, x, y);
        this.action = action;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_popupwindow_root:
                dismiss();
                break;
            case R.id.btn_goods_commit:
                if (currentShopSpec == null && shopInfo.getIsGuige() == 0) {
                    Toast.makeText(context, "请选择商品规格", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (action == ACTION_BUY_ORDER) {//去下单
                    if (MallAppliaction.getInstance().userBean == null) {
                        context.startActivity(new Intent(context, UserLoginActivity.class));
                        return;
                    }
                    toOrder(newShoppingCart());
                } else if (action == ACTION_BUY_SHOPPING_CART) {//去购物车
                    toShoppingCart(newShoppingCart());
                }
                dismiss();
                break;
        }
    }

    /**
     * 创建一个商品的购物车订单对象
     *
     * @return
     */
    private ShoppingCartBean newShoppingCart() {
        ShoppingCartBean shoppingCart = new ShoppingCartBean();
        if (currentShopSpec != null && shopSpecs.size() > 0 && shopInfo.getIsGuige() == 0) {
            shoppingCart.setSpecId(currentShopSpec.getSpecId());
            shoppingCart.setIsSpec(0);
            shoppingCart.setDiKouPrice(currentShopSpec.getDikouPrice());
            shoppingCart.setShopStock(currentShopSpec.getSpecNum());
            shoppingCart.setSpecName(currentShopSpec.getSpecName());
            if (shopInfo.getTypeId() != -2)//报销商品
                shoppingCart.setPrice(currentShopSpec.getSpecPrice());
            else
                shoppingCart.setPrice(currentShopSpec.getSpecPrice());
        } else {
            shoppingCart.setIsSpec(-1);
            shoppingCart.setPrice(shopInfo.getShopPrice());
            shoppingCart.setDiKouPrice(shopInfo.getDikouPrice());
            shoppingCart.setShopStock(shopInfo.getShopStock());
        }
        if (action == ACTION_BUY_ORDER && MallAppliaction.getInstance().userBean != null) {
            shoppingCart.setUserId(MallAppliaction.getInstance().userBean.getUserId());
        }
        shoppingCart.setShopType(shopInfo.getTypeId());
        if (shopInfo.getDianpuInfo() != null) {
            shoppingCart.setMerchant(shopInfo.getDianpuInfo().getDianpuName());
            shoppingCart.setMerchantId(shopInfo.getDianpuInfo().getDianpuId());
        }
        shoppingCart.setIsTimeout(shopInfo.getIsTimeout());
        shoppingCart.setShopId(shopInfo.getShopId());
        shoppingCart.setShopIcon(shopInfo.getShopIcon());
        shoppingCart.setShopNum(count_edit_view.getCount());
        shoppingCart.setShopName(shopInfo.getShopName());
        return shoppingCart;
    }

    private void toShoppingCart(ShoppingCartBean shoppingCart) {
        shoppingCart.setChecked(true);
        int i = shoppingCartDao.insert(shoppingCart);
        if (i < 1) {
            Toast.makeText(context, "商品添加购物车失败！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(context, "商品已添加购物车！", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (MallAppliaction.getInstance().APP_TYPE != MallAppliaction.getInstance().APP_MALL_CAN) {
//            Toast.makeText(context, "商品已添加到购物车", Toast.LENGTH_SHORT).show();
//            //ShowShoppingNumberUtil.showNumber();
//        } else {
//            Intent intent = new Intent(ACTION_SHOPPING_BACK_PARCELABLE);
//            intent.putExtra("newShoppingCart", newShoppingCart());
//            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
//            localBroadcastManager.sendBroadcast(intent);
//        }

    }

    /**
     * @param shoppingCart 跳转确认订单
     */
    private void toOrder(ShoppingCartBean shoppingCart) {
        ArrayList<ShoppingCartBean> shoppingCarts = new ArrayList<>();
        shoppingCarts.add(shoppingCart);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(OrderConfirmActivity.SHOPPING_CART_CONTENT, shoppingCarts);
        Intent intent = new Intent(context, OrderConfirmActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private int radioBtnId = 0;

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            radioBtnId = buttonView.getId();
            currentShopSpec = shopSpecs.get(buttonView.getId());
            buttonView.setTextColor(Color.WHITE);
            int specNum = currentShopSpec.getSpecNum();
//            if (shopInfo.getTypeId() != -2)
//                tvCommodityPrice.setText("￥" + currentShopSpec.getSpecPrice());
//            else
//                tvCommodityPrice.setText("￥" + (currentShopSpec.getSpecPrice() + shopInfo.getDelPrice()));
//            tv_commodity_jifen.setText(SpecUtils.getIntegralInterval(currentShopSpec));
//            tvCommodityStock.setText("库存" + specNum);
            //count_edit_view.setMaxCount(currentShopSpec.getSpecNum() > shopInfo.getTodayBuyMaxMun() ? shopInfo.getTodayBuyMaxMun() : currentShopSpec.getSpecNum());
            count_edit_view.setCount(1);
            if (specNum < 1) {
                btn_goods_commit.setEnabled(false);
                btn_goods_commit.setText("库存不足");
            } else {
                count_edit_view.setInputBoxEnable(true);
                count_edit_view.setMaxCount(specNum);
                btn_goods_commit.setEnabled(true);
                btn_goods_commit.setText("确定");
            }
        } else {
            //buttonView.setTextColor(context.getResources().getColor(R.color.text_general_color));
        }
        if (shopInfo.getIsTimeout() == 0) {//限时抢购商品不能加数量
            count_edit_view.setInputBoxEnable(false);
        }
    }
}