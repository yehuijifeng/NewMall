package com.alsfox.mall.popupwindow;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.PopupWindow;

/**
 * Created by 权兴
 * on 2015/9/2 17:09.
 */
public class CommoditySpecPopupWindow extends PopupWindow implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

//    private View popView;
//
//    private Context context;
//
//    private ShopInfoVo shopInfo;
//
//    private List<ShopSpecVo> shopSpecs;
//
//    private ShopSpecVo currentShopSpec;
//
//    private FrameLayout flCommodityPopParent;
//    private RelativeLayout llCommodityPopParent;
//    //private LinearLayout ll_commodity_spec;
//    //private View line_commodity_spec;
//    private TextView tvCommodityPrice, tv_commodity_jifen;
//    private TextView tvCommodityStock;
//    //private RadioGroup crgCommoditySpec;
//    //private CustomRadioGroup crg_commodity_spec_tow;
//    private CommodityCountEditText ccetCommodityCountChange;
//    private ImageView ivCommodityIcon;
//    private Button btn_commodity_pop_confirm;
//    private GridView grid_one;
//    private MyAdapter myAdapter;
//    private ImageView commodity_not_data;
//    private FrameLayout iv_commodity_fl;
//
//    public static final int ACTION_BUY = 0;
//    public static final int ACTION_SHOPPING_CART = 1;
//    public static final String ACTION_SHOPPING_BACK_PARCELABLE = "addCartAnimByCan";
//    private int action;
//
//    private void assignViews(View popView) {
//        flCommodityPopParent = (FrameLayout) popView.findViewById(R.id.fl_commodity_pop_parent);
//        llCommodityPopParent = (RelativeLayout) popView.findViewById(R.id.ll_commodity_pop_parent);
//        //ll_commodity_spec = (LinearLayout) popView.findViewById(R.id.ll_commodity_spec);
//        //line_commodity_spec = popView.findViewById(R.id.line_commodity_spec);
//        grid_one = (GridView) popView.findViewById(R.id.grid_one);
//        tvCommodityPrice = (TextView) popView.findViewById(R.id.tv_commodity_price);
//        tvCommodityStock = (TextView) popView.findViewById(R.id.tv_commodity_stock);
//
//        tv_commodity_jifen = (TextView) popView.findViewById(R.id.tv_commodity_jifen);
//        //crgCommoditySpec = (RadioGroup) popView.findViewById(R.id.crg_commodity_spec);
//        //crg_commodity_spec_tow=(CustomRadioGroup) popView.findViewById(R.id.crg_commodity_spec_tow);
//        ccetCommodityCountChange = (CommodityCountEditText) popView.findViewById(R.id.ccet_commodity_count_change);
//        ccetCommodityCountChange.setInputBoxEnable(true);
//        ivCommodityIcon = (ImageView) popView.findViewById(R.id.iv_commodity_icon);
//        iv_commodity_fl = (FrameLayout) popView.findViewById(R.id.iv_commodity_fl);
//        commodity_not_data = (ImageView) popView.findViewById(R.id.commodity_not_data);
//        btn_commodity_pop_confirm = (Button) popView.findViewById(R.id.btn_commodity_pop_confirm);
//        btn_commodity_pop_confirm.setOnClickListener(this);
//        ImageLoader.getInstance().displayImage(shopInfo.getShopIcon(), ivCommodityIcon, MallApplication.options);
//        initSpec();
//    }
//
//    private class MyAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return 1;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return shopSpecs.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View v;
//            AutoRadioGroup rl_spec;
//            v = LayoutInflater.from(context).inflate(R.layout.item_window_spec, null);
//            rl_spec = (AutoRadioGroup) v.findViewById(R.id.rl_spec);
//            for (int i = 0; i < shopSpecs.size(); i++) {
//                ShopSpecVo shopSpec = shopSpecs.get(i);
//                View tempbuju = LayoutInflater.from(context).inflate(R.layout.rb_spec_item_tow, null);
//                RadioButton specButton = (RadioButton) tempbuju.findViewById(R.id.rb_temp);
//                specButton.setPadding(35, 35, 35, 35);
//                specButton.setId(i);
//                specButton.setText(shopSpec.getSpecName());
//                specButton.setOnCheckedChangeListener(CommoditySpecPopupWindow.this);
//                if (i == radioBtnId) {
//                    specButton.setChecked(true);
//                } else {
//                    specButton.setChecked(false);
//                }
//                if (shopInfo.getIsTimeout() == 0) {//限时抢购商品不能加数量
//                    ccetCommodityCountChange.setInputBoxEnable(false);
//                } else if (shopInfo.getTypeId() == -2) {//报销商品不能加数量
//                    ccetCommodityCountChange.setInputBoxEnable(false);
//                }
//                rl_spec.addView(tempbuju);
//            }
//            return v;
//        }
//    }
//
//    private void initSpec() {
//        if (shopSpecs == null || shopSpecs.size() < 1) {
//            int shopStock = shopInfo.getShopStock();
//            //ll_commodity_spec.setVisibility(View.GONE);
//            //line_commodity_spec.setVisibility(View.GONE);
//            tvCommodityPrice.setText("￥" + shopInfo.getShopPrice());
//            //tvCommodityPrice.setText(SpecUtils.getSpecInterval(shopInfo));
//            tv_commodity_jifen.setText(SpecUtils.getIntegralInterval(shopInfo));
//            tvCommodityStock.setText("库存" + shopStock);
//            if (shopInfo.getTypeId() == -2)
//                ccetCommodityCountChange.setCommodityMaxCount(1);
//            else
//                ccetCommodityCountChange.setCommodityMaxCount(shopInfo.getShopStock() > shopInfo.getTodayBuyMaxMun() ? shopInfo.getTodayBuyMaxMun() : shopInfo.getShopStock());
//            ccetCommodityCountChange.setCommodityCountInterface(new CommodityCountEditText.OnCommodityCount() {
//                @Override
//                public void onCommodityCounts(int number) {
//                    if (number >= shopInfo.getTodayBuyMaxMun())
//                        Toast.makeText(context, "超过最大购买数量", Toast.LENGTH_LONG).show();
//                }
//            });
//            if (shopStock < 1) {
//                btn_commodity_pop_confirm.setEnabled(false);
//                ccetCommodityCountChange.setInputBoxEnable(false);
//                btn_commodity_pop_confirm.setText("库存不足");
//            } else {
//                ccetCommodityCountChange.setInputBoxEnable(true);
//                btn_commodity_pop_confirm.setEnabled(true);
//                btn_commodity_pop_confirm.setText("确定");
//            }
//            if (shopInfo.getIsTimeout() == 0) {//限时抢购商品不能加数量
//                ccetCommodityCountChange.setInputBoxEnable(false);
//            } else if (shopInfo.getTypeId() == -2) {//报销商品不能加数量
//                ccetCommodityCountChange.setInputBoxEnable(false);
//            }
//            grid_one.setVisibility(View.GONE);
//            return;
//        }
//        grid_one.setVisibility(View.VISIBLE);
//        myAdapter = new MyAdapter();
//        grid_one.setAdapter(myAdapter);
//    }
//
//    /**
//     * <p>Create a new empty, non focusable popup window of dimension (0,0).</p> <p/> <p>The popup
//     * does provide a background.</p>
//     *
//     * @param context
//     */
//    public CommoditySpecPopupWindow(Context context, ShopInfoVo shopInfoVo) {
//        super(context);
//        this.context = context;
//        this.shopInfo = shopInfoVo;
//        if (shopInfo != null) {
//            shopSpecs = shopInfo.getShopSpecList();
//        }
//        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        popView = View.inflate(context, R.layout.layout_commodity_detail_popview, null);
//        assignViews(popView);
//        if (shopInfo != null) {
//            if (shopInfo.getShopStock() > 0) {
//                commodity_not_data.setVisibility(View.GONE);
//            } else
//                commodity_not_data.setVisibility(View.VISIBLE);
//        }
//        FrameLayout fl_commodity_pop_parent = (FrameLayout) popView.findViewById(R.id.fl_commodity_pop_parent);
//        fl_commodity_pop_parent.setOnClickListener(this);
//        setFocusable(true);
//        setAnimationStyle(R.style.AnimationPreviewForCommodityPop);
//        setBackgroundDrawable(new ColorDrawable(0xFFFFFF));
//        setContentView(popView);
//        setOutsideTouchable(true);
//        update();
//    }
//
//    @Override
//    public void showAtLocation(View parent, int gravity, int x, int y) {
//        super.showAtLocation(parent, gravity, x, y);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(0, 0);
//                params.width = context.getResources().getDimensionPixelSize(R.dimen.image_width_xxxlarge);
//                params.height = context.getResources().getDimensionPixelSize(R.dimen.image_height_xxxlarge);
//                params.gravity = Gravity.BOTTOM;
//                params.leftMargin = context.getResources().getDimensionPixelSize(R.dimen.default_margin);
//                params.bottomMargin = llCommodityPopParent.getHeight() - params.height / 2;
//                //ivCommodityIcon.setLayoutParams(params);
//                //commodity_not_data.setLayoutParams(params);
//                iv_commodity_fl.setLayoutParams(params);
//            }
//        }, 10);
//    }
//
//    public void showAtLocation(View parent, int gravity, int x, int y, int action) {
//        this.showAtLocation(parent, gravity, x, y);
//        this.action = action;
//    }
//
//    private Handler handler = new Handler();
//
//    /**
//     * Called when a view has been clicked.
//     *
//     * @param v The view that was clicked.
//     */
//    @Override
//    public void onClick(View v) {
//        if (currentShopSpec == null && (shopSpecs != null && shopSpecs.size() > 0)) {
//            ToastUtil.showTextToast(context, "请选择商品规格", Toast.LENGTH_SHORT);
//            return;
//        }
//        switch (v.getId()) {
//            case R.id.fl_commodity_pop_parent:
//                dismiss();
//                break;
//            case R.id.btn_commodity_pop_confirm:
//                if (action == ACTION_BUY) {
//                    if (MallApplication.user == null) {
//                        context.startActivity(new Intent(context, UserLoginActivity.class));
//                        return;
//                    }
//                    toConfirmOrder(newShoppingCart());
//                } else if (action == ACTION_SHOPPING_CART) {
//                    addToShoppingCart(newShoppingCart());
//                }
//                dismiss();
//                break;
//        }
//    }
//
//    private ShoppingCartVo newShoppingCart() {
//        ShoppingCartVo shoppingCart = new ShoppingCartVo();
//        if (shopSpecs != null && shopSpecs.size() > 0) {
//            shoppingCart.setSpecId(currentShopSpec.getSpecId());
//            shoppingCart.setIsSpec(0);
//            if (shopInfo.getTypeId() != -2)
//                shoppingCart.setPrice(currentShopSpec.getSpecPrice());
//            else
//                shoppingCart.setPrice(currentShopSpec.getSpecPrice() + shopInfo.getDelPrice());
//            shoppingCart.setDiKouPrice(currentShopSpec.getDikouPrice());
//            shoppingCart.setShopStock(currentShopSpec.getSpecNum() > shopInfo.getTodayBuyMaxMun() ? shopInfo.getTodayBuyMaxMun() : currentShopSpec.getSpecNum());
//        } else {
//            shoppingCart.setIsSpec(-1);
//            shoppingCart.setPrice(shopInfo.getShopPrice());
//            shoppingCart.setDiKouPrice(shopInfo.getDikouPrice());
//            shoppingCart.setShopStock(shopInfo.getShopStock() > shopInfo.getTodayBuyMaxMun() ? shopInfo.getTodayBuyMaxMun() : shopInfo.getShopStock());
//        }
//        if (action == ACTION_BUY) {
//            shoppingCart.setUserId(MallApplication.user.getUserId());
//        }
//        //shoppingCart.setIsTimeOut(shopInfo.getIsTimeout());
//        shoppingCart.setMerchant(shopInfo.getDianpuInfo().getDianpuName());
//        shoppingCart.setMerchantId(shopInfo.getDianpuInfo().getDianpuId());
//        shoppingCart.setShopId(shopInfo.getShopId());
//        shoppingCart.setShopIcon(shopInfo.getShopIcon());
//        shoppingCart.setShopNum(ccetCommodityCountChange.getCommodityCount());
//        shoppingCart.setShopName(shopInfo.getShopName());
//        return shoppingCart;
//    }
//
//    private void addToShoppingCart(ShoppingCartVo shoppingCart) {
//        shoppingCart.setIsChecked(false);
//        ShoppingCartDBUtils.saveOne(shoppingCart);
//        if (MallApplication.APP_TYPE != MallApplication.APP_MALL_CAN) {
//            EventBus.getDefault().post(shoppingCart);
//            ToastUtil.showTextToast(context, "商品已添加到购物车", Toast.LENGTH_SHORT);
//            ShowShoppingNumberUtil.showNumber();
//        } else {
//            Intent intent = new Intent(ACTION_SHOPPING_BACK_PARCELABLE);
//            intent.putExtra("newShoppingCart", newShoppingCart());
//            //intent.putExtra("newShoppingSpec", currentShopSpec);
//            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
//            localBroadcastManager.sendBroadcast(intent);
//        }
//
//    }
//
//    /**
//     * @param shoppingCart 跳转确认订单
//     */
//    private void toConfirmOrder(ShoppingCartVo shoppingCart) {
//        ArrayList<ShoppingCartVo> shoppingCarts = new ArrayList<>();
//        shoppingCarts.add(shoppingCart);
//        Bundle bundle = new Bundle();
//        bundle.putInt("OrderByIsBaoxiao", shopInfo.getTypeId());
//        bundle.putInt("OrderByIsTimeOut", shopInfo.getIsTimeout());
//        bundle.putParcelableArrayList("shoppingCarts", shoppingCarts);
//        Intent intent = new Intent();
//        intent.setClass(context, OrderConfirmActivity.class);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
//    }
//
//    private int radioBtnId = 0;
//
//    /**
//     * Called when the checked state of a compound button has changed.
//     *
//     * @param buttonView The compound button view whose state has changed.
//     * @param isChecked  The new checked state of buttonView.
//     */
//
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked) {
//            radioBtnId = buttonView.getId();
//            currentShopSpec = shopSpecs.get(buttonView.getId());
//            buttonView.setTextColor(Color.WHITE);
//            int specNum = currentShopSpec.getSpecNum();
//            if (shopInfo.getTypeId() != -2)
//                tvCommodityPrice.setText("￥" + currentShopSpec.getSpecPrice());
//            else
//                tvCommodityPrice.setText("￥" + (currentShopSpec.getSpecPrice() + shopInfo.getDelPrice()));
//            //tvCommodityPrice.setText(SpecUtils.getSpecInterval(currentShopSpec));
//            tv_commodity_jifen.setText(SpecUtils.getIntegralInterval(currentShopSpec));
//            tvCommodityStock.setText("库存" + specNum);
//            if (ccetCommodityCountChange != null) {
//                ccetCommodityCountChange.setCommodityMaxCount(currentShopSpec.getSpecNum() > shopInfo.getTodayBuyMaxMun() ? shopInfo.getTodayBuyMaxMun() : currentShopSpec.getSpecNum());
//                ccetCommodityCountChange.setCommodityCount(1);
//            }
//            if (specNum < 1) {
//
//                btn_commodity_pop_confirm.setEnabled(false);
//                btn_commodity_pop_confirm.setText("库存不足");
//            } else {
//                if (ccetCommodityCountChange != null) {
//                    ccetCommodityCountChange.setInputBoxEnable(true);
//                }
//                btn_commodity_pop_confirm.setEnabled(true);
//                btn_commodity_pop_confirm.setText("确定");
//            }
//            myAdapter.notifyDataSetChanged();
//        } else {
//            buttonView.setTextColor(context.getResources().getColor(R.color.text_general_color));
//        }
//        if (shopInfo.getIsTimeout() == 0) {//限时抢购商品不能加数量
//            ccetCommodityCountChange.setInputBoxEnable(false);
//        }
//    }
}