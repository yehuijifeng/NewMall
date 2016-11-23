package com.alsfox.mall.view.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alsfox.mall.BuildConfig;
import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.coupons.CouponsBean;
import com.alsfox.mall.bean.order.OrderConfirmBean;
import com.alsfox.mall.bean.order.OrderDetailBean;
import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.bean.user.UserAddressBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.order.OrderConfirmPresenter;
import com.alsfox.mall.view.activity.base.BaseListActivity;
import com.alsfox.mall.view.activity.user.UserAddressListActivity;
import com.alsfox.mall.view.customview.CountEditText;
import com.alsfox.mall.view.interfaces.order.IOrderConfirmView;
import com.bigkoo.pickerview.OptionsPopupWindow;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


/**
 * Created by 浩 on 2016/11/22.
 * 订单确认
 */

public class OrderConfirmActivity extends BaseListActivity<OrderConfirmPresenter> implements IOrderConfirmView, View.OnClickListener {

    private RelativeLayout rl_min_money;//满多少减多少
    private TextView tv_min_money;//至少满多少
    private TextView tv_add_money;//满减金额

    private RelativeLayout rl_add_youfei;//增加的邮费
    private TextView tv_add_youfei;//增加邮费要求

    private RelativeLayout rl_order_over_time;//选择收货时间
    private TextView tv_select_order_time;//时间显示
    private RelativeLayout rl_order_select_coupons;//选择优惠券
    private TextView tv_coupons_usable_count;//可使用优惠券张数

    private LinearLayout ll_my_score;//积分去
    private TextView tv_my_score;//我的积分
    private TextView tv_deductible_score;//最多抵扣积分
    private TextView tv_lab_score_explain;//1积分等于多少金额
    private EditText et_order_confirm_score;//输入抵扣金额
    private TextView tv_score_deductible_count;//抵扣金额
    private EditText et_order_confirm_msg;//留言

    private LinearLayout ll_user_addres;//点击去选择收货地址
    private TextView tv_user_name;//收件人姓名
    private TextView tv_user_tel;//收件人电话
    private TextView tv_user_address;//收件人地址

    private TextView total_money_text;//合计
    private Button btn_jiesuan;//结算

    public static final String SHOPPING_CART_CONTENT = "ShoppingCartContent";
    private List<ShoppingCartBean> shoppingCarts;
    private Gson gson;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private OptionsPopupWindow opw;//时间选择器
    private UserAddressBean userAddressBean;//用户收货地址
    //总金额
    private double totalMoney, toralCountMoney;

    //确认订单信息
    private OrderConfirmBean orderConfirmBean;

    //积分最大可抵扣金额
    private double maxJifenMoney;

    //优惠券抵扣金额
    private double youhuijuanMoney;

    //积分抵扣金额
    private double jifenMoney;

    //用户选择的送货时间段
    private String receiveTime;

    //用户选择的优惠券id
    private int record_id;
    //选择地址
    private final int CODE_GET_ADDRESS = 100;

    //选择优惠券
    private final int CODE_CHOOSE_COUPONS = CODE_GET_ADDRESS + 1;

    @Override
    protected boolean isLoadMore() {
        return false;
    }

    @Override
    protected boolean isRefresh() {
        return false;
    }

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        final OrderDetailBean orderDetail = (OrderDetailBean) data.get(position);
        OrderConfirmViewHolder viewHolder = (OrderConfirmViewHolder) baseViewHolder;
        double shopPrice = orderDetail.getShopPrice();
        imageLoader.displayImage(orderDetail.getShopImg(), viewHolder.order_confirm_img, MallAppliaction.getInstance().defaultOptions);
        viewHolder.order_confirm_count_edit.setMaxCount(orderDetail.getYuStock());
        viewHolder.order_confirm_count_edit.setCount(orderDetail.getShopNum());
        viewHolder.order_confirm_count_edit.setInputBoxEnable(false);
        viewHolder.order_confirm_name_text.setText(orderDetail.getShopName());
        viewHolder.order_confirm_price_text.setText("￥" + shopPrice);
        viewHolder.order_confirm_spec_text.setText(orderDetail.getShopSpecName());
        viewHolder.order_confirm_count_edit.setOnChangeEditText(new CountEditText.OnChangeEditText() {
            @Override
            public void onChangeEdit(int count) {
                calculatePrice(orderDetail, count);
            }
        });
    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        return new OrderConfirmViewHolder(itemView);
    }

    @Override
    public int getItemView(int position, int itemType) {
        return R.layout.item_order_confirm;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected OrderConfirmPresenter initPresenter() {
        return new OrderConfirmPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_order_confirm;
    }

    @Override
    protected String setTitleText() {
        return getResources().getString(R.string.str_confirm_order);
    }


    @Override
    protected int setHeaderView() {
        return R.layout.layout_order_confirm_header;
    }

    @Override
    protected void getHeaderView(View view) {
        ll_user_addres = (LinearLayout) view.findViewById(R.id.ll_user_addres);//点击去选择收货地址
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);//收件人姓名
        tv_user_tel = (TextView) view.findViewById(R.id.tv_user_tel);//收件人电话
        tv_user_address = (TextView) view.findViewById(R.id.tv_user_address);//收件人地址
        ll_user_addres.setOnClickListener(this);
    }

    /**
     * 底部layout
     *
     * @return
     */
    private View getFootView() {
        View footView = inflater.inflate(R.layout.layout_order_confirm_foot, null);
        rl_min_money = (RelativeLayout) footView.findViewById(R.id.rl_min_money);//满多少减多少
        tv_min_money = (TextView) footView.findViewById(R.id.tv_min_money);//至少满多少
        tv_add_money = (TextView) footView.findViewById(R.id.tv_add_money);//满减金额
        rl_add_youfei = (RelativeLayout) footView.findViewById(R.id.rl_add_youfei);//增加的邮费
        tv_add_youfei = (TextView) footView.findViewById(R.id.tv_add_youfei);//增加邮费要求
        rl_order_over_time = (RelativeLayout) footView.findViewById(R.id.rl_order_over_time);//选择收货时间
        tv_select_order_time = (TextView) footView.findViewById(R.id.tv_select_order_time);//留言
        rl_order_select_coupons = (RelativeLayout) footView.findViewById(R.id.rl_order_select_coupons);//选择优惠券
        tv_coupons_usable_count = (TextView) footView.findViewById(R.id.tv_coupons_usable_count);
        ll_my_score = (LinearLayout) footView.findViewById(R.id.ll_my_score);//积分区域
        tv_my_score = (TextView) footView.findViewById(R.id.tv_my_score);//我的积分
        tv_deductible_score = (TextView) footView.findViewById(R.id.tv_deductible_score);//最多抵扣积分
        tv_lab_score_explain = (TextView) footView.findViewById(R.id.tv_lab_score_explain);//1积分等于多少金额
        et_order_confirm_score = (EditText) footView.findViewById(R.id.et_order_confirm_score);//输入抵扣金额
        tv_score_deductible_count = (TextView) footView.findViewById(R.id.tv_score_deductible_count);//抵扣金额
        et_order_confirm_msg = (EditText) footView.findViewById(R.id.et_order_confirm_msg);//留言
        rl_order_over_time.setOnClickListener(this);
        rl_order_select_coupons.setOnClickListener(this);
        et_order_confirm_score.addTextChangedListener(new JifenTextChanged());
        rl_order_select_coupons.setOnTouchListener(new EditTextOnTouchListener());
        et_order_confirm_msg.setOnTouchListener(new EditTextOnTouchListener());
        return footView;
    }

    /**
     * 让listview中的edittext能够输入内容获取焦点
     */
    private class EditTextOnTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //ViewGroup.FOCUS_AFTER_DESCENDANTS:表示item的子控件优先于item获得焦点
            ((ViewGroup) v.getParent()).setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
            return false;
        }
    }

    @Override
    public void initView() {
        super.initView();
        total_money_text = (TextView) findViewById(R.id.total_money_text);
        btn_jiesuan = (Button) findViewById(R.id.btn_jiesuan);
        btn_jiesuan.setOnClickListener(this);
        baseListView.listView.addFooterView(getFootView());
        gson = new Gson();
        opw = new OptionsPopupWindow(this);
    }

    @Override
    protected void initData() {
        shoppingCarts = (List<ShoppingCartBean>) getParcelableList(SHOPPING_CART_CONTENT);
        if (shoppingCarts == null || shoppingCarts.isEmpty()) {
            showLongToast("请选择商品后再下单");
            finish();
        } else if (MallAppliaction.getInstance().userBean != null) {
            for (ShoppingCartBean shoppingCart : shoppingCarts) {
                shoppingCart.setUserId(MallAppliaction.getInstance().userBean.getUserId());
            }
            reLoad();
        }
    }

    /**
     * 加载确认订单信息
     */
    public void reLoad() {
        showLoading("正在加载订单……");
        for (ShoppingCartBean shoppingCart : shoppingCarts) {
            int shopNum = shoppingCart.getShopNum();
            totalMoney += (shopNum * shoppingCart.getPrice());
        }
        Map<String, Object> params = RequestAction.GET_ORDER_CONFIRM_INFO.params.getParams();
        String jsonStr = gson.toJson(shoppingCarts);
        params.put(MallConstant.PARAM_KEY_ORDER_CONFIRM_JSON, jsonStr);
        sendRequest(RequestAction.GET_ORDER_CONFIRM_INFO);
    }

    /**
     * 重新计算当前价格
     *
     * @param orderDetailBean
     * @param count
     */
    private void calculatePrice(OrderDetailBean orderDetailBean, int count) {
        //如果商品数量超过当前最大库存，则改变
        if (count > orderDetailBean.getYuStock()) count = orderDetailBean.getYuStock();
        //如果数量大于当前商品购买数量，说明用户加了数量
        if (orderDetailBean.getShopNum() < count) {
            //加
            totalMoney += orderDetailBean.getShopPrice();
            maxJifenMoney += orderDetailBean.getShopDikou();
        } else if (orderDetailBean.getShopNum() > count) {
            //减
            totalMoney -= orderDetailBean.getShopPrice();
            maxJifenMoney -= orderDetailBean.getShopDikou();
            if (!TextUtils.isEmpty(et_order_confirm_score.getText())) {
                //如果用户输入的积分数量大于了当前的积分抵扣价格，则控制
                int integral = Integer.valueOf(et_order_confirm_score.getText().toString());
                if (integral > (maxJifenMoney * 100)) {
                    et_order_confirm_score.setText((int) (maxJifenMoney * 100) + "");
                    et_order_confirm_score.setSelection(et_order_confirm_score.length());
                }
            }
        }
        getUserCoupns();
        orderDetailBean.setShopNum(count);
        calculateJifen();
    }

    /**
     * 显示确认订单的信息
     */
    private void loadSuccessData() {
        List<OrderDetailBean> orderDetails = orderConfirmBean.getOrderDetailList();
        for (OrderDetailBean orderDetail : orderDetails) {
            //最大抵扣金额
            maxJifenMoney += (orderDetail.getShopNum() * orderDetail.getShopDikou());
        }
        //用户默认收货地址
        showUserAddress(orderConfirmBean.getUserDspt());
        //计算积分
        calculateJifen();

    }

    /**
     * 计算积分
     */
    private void calculateJifen() {
        //积分区
        //总金额-积分抵扣-优惠券抵扣
        BigDecimal bigDecimal = new BigDecimal(totalMoney - youhuijuanMoney - jifenMoney);
        //保留两位小数
        double total = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //如果满足的最大满减金额，则减去优惠金额
        if (orderConfirmBean.getOrderPayMin() != null && orderConfirmBean.getOrderPayMin().getStatus() == 0) {
            if (total >= orderConfirmBean.getOrderPayMin().getTotalPay())
                total -= orderConfirmBean.getOrderPayMin().getChangeMoney();
            rl_min_money.setVisibility(View.VISIBLE);
            tv_add_money.setText("满" + orderConfirmBean.getOrderPayMin().getTotalPay() + "元，减" + orderConfirmBean.getOrderPayMin().getChangeMoney() + "元");
            tv_min_money.setText(orderConfirmBean.getOrderPayMin().getRemark());
        } else {
            rl_min_money.setVisibility(View.GONE);
        }
        //如果小于指定金额，则加上差价
        if (orderConfirmBean.getOrderPayAdd() != null && orderConfirmBean.getOrderPayAdd().getStatus() == 0) {
            rl_add_youfei.setVisibility(View.VISIBLE);
            if (total < orderConfirmBean.getOrderPayAdd().getTotalPay()) {
                total += orderConfirmBean.getOrderPayAdd().getChangeMoney();
                tv_add_youfei.setText("未满" + orderConfirmBean.getOrderPayAdd().getTotalPay() + "加" + orderConfirmBean.getOrderPayAdd().getChangeMoney() + "元配送费");
            } else {
                tv_add_youfei.setText("已达到包邮价格");
            }
        } else {
            rl_add_youfei.setVisibility(View.GONE);
        }
        toralCountMoney = total;//这个总价是实际支付价格计算过邮费，满减，优惠券，积分抵扣以后的价格
        total_money_text.setText("合计:" + decimalFormat.format(toralCountMoney));
        //积分系统，0，开启，-1关闭
        if (orderConfirmBean.getInteger() == 0) {
            ll_my_score.setVisibility(View.VISIBLE);
            tv_my_score.setText(MallAppliaction.getInstance().userBean.getUserIntegral() + "");
            tv_lab_score_explain.setText("1积分=" + orderConfirmBean.getIntegerVsmoney() + "元");
            if (maxJifenMoney > 0) {
                bigDecimal = new BigDecimal(maxJifenMoney);
                double deductibleAmount = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                tv_deductible_score.setText(deductibleAmount + "元");
                et_order_confirm_score.setEnabled(true);
                et_order_confirm_score.setHint("最大使用" + ((int) (deductibleAmount / orderConfirmBean.getIntegerVsmoney())) + "积分");
            } else {
                et_order_confirm_score.setEnabled(false);
                et_order_confirm_score.setHint("不支持积分抵扣");
            }
        } else {
            ll_my_score.setVisibility(View.GONE);
        }
    }

    /**
     * 显示用户收货地址
     */
    private void showUserAddress(UserAddressBean userAddressBean) {
        //默认收货地址
        if (userAddressBean != null) {
            this.userAddressBean = userAddressBean;
            tv_user_name.setText(userAddressBean.getDsptName());
            tv_user_tel.setText(userAddressBean.getDsptPhone());
            tv_user_address.setText(userAddressBean.getDsptArea() + orderConfirmBean.getUserDspt().getDsptAddress());
        }
    }

    /**
     * 获取服务器时间
     */
    private void getServerTime() {
        sendRequest(RequestAction.GET_SERVER_TIME);
    }

    /**
     * 获取用户可用优惠券张数
     */
    private void getUserCoupns() {
        Map<String, Object> params = RequestAction.GET_USER_COUPONS.params.getParams();
        params.put(MallConstant.PARAM_KEY_COUPONS_RECORD_INFO_USER_ID, MallAppliaction.getInstance().userBean.getUserId());
        params.put(MallConstant.PARAM_KEY_COUPONS_RECORD_INFO_TOTAL_PRICE, totalMoney);
        sendRequest(RequestAction.GET_USER_COUPONS);
    }

    /**
     * 打开时间选择器
     */
    private void openTimePicker(Long timeStamp) {
        final ArrayList<String> day = new ArrayList<>();
        final ArrayList<ArrayList<String>> hour = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if (hours <= 18) {
            day.add("今天");
        }
        day.add("明天");
        day.add("后天");

        if (BuildConfig.DEBUG) Log.i("currentHour", hours + "");
        for (int i = 0; i < day.size(); i++) {
            if (i == 0 && (hours >= 8 && hours <= 18)) {
                ArrayList<String> day_hour = new ArrayList<>();
                for (int j = hours; j < 19; j++) {
                    String time;
                    if (hours == 18) {
                        time = "2小时后送达";
                    } else {
                        int jj = j + 2;
                        time = jj + ":00-" + (jj + 2) + ":00";
                    }
                    day_hour.add(time);
                }
                hour.add(day_hour);
            } else {
                ArrayList<String> day_hour = new ArrayList<>();
                for (int j = 8; j < 19; j++) {
                    String time;
                    int jj = j + 2;
                    if (j >= 18) continue;
                    time = jj + ":00-" + (jj + 2) + ":00";
                    day_hour.add(time);
                }
                hour.add(day_hour);
            }
        }
        opw.setPicker(day, hour, true);
        opw.setCyclic(true);
        opw.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2) {
                ArrayList<String> strings = hour.get(i);
                String str = strings.get(i1);
                if ("2小时后送达".equals(str)) {
                    tv_select_order_time.setText(str);
                    receiveTime = str;
                    return;
                }
                receiveTime = day.get(i) + str + "送达";
                tv_select_order_time.setText(receiveTime);
            }
        });
        opw.showAtLocation(root, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 结算，生成订单
     */
    private void confirmOrder() {
        if (userAddressBean == null) {
            showShortToast("请选择收货地址");
            return;
        }
        //sendRequest(RequestAction.SELECT_PAY_TYPE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_jiesuan://结算，生成订单
                confirmOrder();
                break;
            case R.id.ll_user_addres://选择收货地址
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("isGetAddress", true);
                startActivityForResult(UserAddressListActivity.class, CODE_GET_ADDRESS);
                break;
            case R.id.rl_order_over_time://选择收货时间
                getServerTime();
                break;
            case R.id.rl_order_select_coupons://选择优惠券
                Bundle bundle1 = new Bundle();
                bundle1.putInt(MallConstant.BUNDLE_KEY_COUPONS_ACTION, MallConstant.ACTION_COUPONS_LIST_GET);
                bundle1.putDouble(MallConstant.BUNDLE_KEY_ORDER_TOTAL, totalMoney);
                //startActivityForResult(UserCouponsActivity.class, bundle1, CODE_CHOOSE_COUPONS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_GET_ADDRESS:
                    userAddressBean = data.getParcelableExtra(MallConstant.USER_ADDRESS);
                    showUserAddress(userAddressBean);
                    break;
                case CODE_CHOOSE_COUPONS:
                    CouponsBean couponsInfo = data.getParcelableExtra("coupons");
                    tv_coupons_usable_count.setText(couponsInfo.getCouponsName() + ",可抵扣" + couponsInfo.getMoney() + "元");
                    record_id = couponsInfo.getRecordId();
                    youhuijuanMoney = couponsInfo.getMoney();
                    calculateJifen();
                    break;
            }
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_ORDER_CONFIRM_INFO://获取确认订单信息
                orderConfirmBean = (OrderConfirmBean) success.getHttpBean().getObject();
                addAll(orderConfirmBean.getOrderDetailList());
                loadSuccess();
                loadSuccessData();
                break;
            case GET_SERVER_TIME://获取服务器时间
                Long time = (Long) success.getHttpBean().getObject();
                openTimePicker(time);
                break;
            case GET_USER_COUPONS://获取用户可用优惠券张数
                if (youhuijuanMoney <= 0.0) {
                    tv_coupons_usable_count.setText(success.getHttpBean().getObject() + "张可用");
                }
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_ORDER_CONFIRM_INFO://获取确认订单信息

                break;
        }
    }

    /**
     * 积分换算金额事件
     */
    private class JifenTextChanged implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s)) {
                //用户输入的积分
                int score = Integer.valueOf(s.toString());
                //如果超过最大积分，则变成最大积分
                if (score > orderConfirmBean.getMyInteger()) {
                    score = orderConfirmBean.getMyInteger();
                    et_order_confirm_score.setText(score + "");
                    et_order_confirm_score.setSelection(et_order_confirm_score.length());
                }
                if (score > maxJifenMoney * orderConfirmBean.getIntegerVsmoney()) {
                    score = (int) (maxJifenMoney * orderConfirmBean.getIntegerVsmoney());
                    et_order_confirm_score.setText(score + "");
                    et_order_confirm_score.setSelection(et_order_confirm_score.length());
                }
                //用户输入的积分可抵扣的金额
                jifenMoney = (double) score * orderConfirmBean.getIntegerVsmoney();
                BigDecimal b = new BigDecimal(jifenMoney);
                //缩进两位小数点
                jifenMoney = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                if (jifenMoney > maxJifenMoney) {
                    jifenMoney = maxJifenMoney;
                }
                tv_score_deductible_count.setText(decimalFormat.format(jifenMoney));
            } else {
                tv_score_deductible_count.setText(0.0 + "");
                jifenMoney = 0.0;
            }
            calculateJifen();
        }
    }

    private class OrderConfirmViewHolder extends BaseViewHolder {
        private ImageView order_confirm_img;
        private TextView order_confirm_name_text;
        private TextView order_confirm_spec_text;
        private TextView order_confirm_price_text;
        private CountEditText order_confirm_count_edit;

        public OrderConfirmViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            order_confirm_img = (ImageView) itemView.findViewById(R.id.order_confirm_img);
            order_confirm_name_text = (TextView) itemView.findViewById(R.id.order_confirm_name_text);
            order_confirm_spec_text = (TextView) itemView.findViewById(R.id.order_confirm_spec_text);
            order_confirm_price_text = (TextView) itemView.findViewById(R.id.order_confirm_price_text);
            order_confirm_count_edit = (CountEditText) itemView.findViewById(R.id.order_confirm_count_edit);
        }
    }
}
