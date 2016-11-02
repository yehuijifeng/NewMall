package com.alsfox.mall.view.fragment.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.order.OrderCountBean;
import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.function.RxBus;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.home.UserContentPresenter;
import com.alsfox.mall.view.activity.user.UserInfoActivity;
import com.alsfox.mall.view.activity.user.UserLoginActivity;
import com.alsfox.mall.view.activity.user.UserRegisterActivity;
import com.alsfox.mall.view.baseview.MyTitleView;
import com.alsfox.mall.view.fragment.base.BaseFragment;
import com.alsfox.mall.view.interfaces.home.IUsercontentView;

import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by 浩 on 2016/10/25.
 * 个人中心
 */

public class UserContentFragment extends BaseFragment<UserContentPresenter> implements IUsercontentView, View.OnClickListener {

    private LinearLayout
            user_centent_header_ly,//个人中心头，设置背景图片
            user_centent_user_ly, //个人中心用户view
            user_centent_login_ly;//个人中心登录view

    private ImageView user_icon_img;//头像

    private TextView user_name_text;//用户名

    private Button
            user_login_btn,//登录
            user_registered_btn;//注册

    private FrameLayout
            user_pay_goods_fl,//待付款
            user_send_goods_fl,//待发货
            user_receive_goods_fl,//待收货
            user_evaluation_goods_fl,//待评价
            user_over_goods_fl;//已完成
    private TextView
            user_pay_goods_count_text,//待付款
            user_send_goods_count_text,//待发货
            user_receive_goods_count_text,//待收货
            user_evaluation_goods_count_text;//待评价

    private RelativeLayout
            user_all_order_rl,//全部订单
            user_integral_rl,//我的积分
            user_wallet_rl,//我的钱包
            user_coupons_rl,//我的优惠券
            user_service_rl,//售后服务
            user_addres_rl,//收货地址
            user_collection_rl,//我的收藏
            user_settings_rl;//设置

    private Subscription subscription;//用户登录的订阅

    @Override
    protected UserContentPresenter initPresenter() {
        return new UserContentPresenter(this);
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.fragment_user_content;
    }

    @Override
    protected void initView(View parentView) {
        mTitleView.setTitleText(getResources().getString(R.string.str_user_centre));
        mTitleView.setTitleMode(MyTitleView.TitleMode.NO_BACK_IMAGE);
        mTitleView.setImageButtonDrawable(R.drawable.ic_user_center_edit);
        mTitleView.setImageButtonOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到个人中心
                if (isLoginTo()) {
                    startActivity(UserInfoActivity.class);
                }
            }
        });
        initContentView(parentView);
    }

    private void initContentView(View parentView) {
        user_centent_header_ly = (LinearLayout) parentView.findViewById(R.id.user_centent_header_ly);
        user_centent_user_ly = (LinearLayout) parentView.findViewById(R.id.user_centent_user_ly);
        user_centent_login_ly = (LinearLayout) parentView.findViewById(R.id.user_centent_login_ly);
        user_icon_img = (ImageView) parentView.findViewById(R.id.user_icon_img);
        user_name_text = (TextView) parentView.findViewById(R.id.user_name_text);
        user_login_btn = (Button) parentView.findViewById(R.id.user_login_btn);
        user_registered_btn = (Button) parentView.findViewById(R.id.user_registered_btn);
        user_pay_goods_fl = (FrameLayout) parentView.findViewById(R.id.user_pay_goods_fl);
        user_send_goods_fl = (FrameLayout) parentView.findViewById(R.id.user_send_goods_fl);
        user_receive_goods_fl = (FrameLayout) parentView.findViewById(R.id.user_receive_goods_fl);
        user_evaluation_goods_fl = (FrameLayout) parentView.findViewById(R.id.user_evaluation_goods_fl);
        user_over_goods_fl = (FrameLayout) parentView.findViewById(R.id.user_over_goods_fl);
        user_pay_goods_count_text = (TextView) parentView.findViewById(R.id.user_pay_goods_count_text);
        user_send_goods_count_text = (TextView) parentView.findViewById(R.id.user_send_goods_count_text);
        user_receive_goods_count_text = (TextView) parentView.findViewById(R.id.user_receive_goods_count_text);
        user_evaluation_goods_count_text = (TextView) parentView.findViewById(R.id.user_evaluation_goods_count_text);
        user_all_order_rl = (RelativeLayout) parentView.findViewById(R.id.user_all_order_rl);
        user_integral_rl = (RelativeLayout) parentView.findViewById(R.id.user_integral_rl);
        user_wallet_rl = (RelativeLayout) parentView.findViewById(R.id.user_wallet_rl);
        user_coupons_rl = (RelativeLayout) parentView.findViewById(R.id.user_coupons_rl);
        user_service_rl = (RelativeLayout) parentView.findViewById(R.id.user_service_rl);
        user_addres_rl = (RelativeLayout) parentView.findViewById(R.id.user_addres_rl);
        user_collection_rl = (RelativeLayout) parentView.findViewById(R.id.user_collection_rl);
        user_settings_rl = (RelativeLayout) parentView.findViewById(R.id.user_settings_rl);

        user_icon_img.setOnClickListener(this);
        user_login_btn.setOnClickListener(this);
        user_registered_btn.setOnClickListener(this);
        user_pay_goods_fl.setOnClickListener(this);
        user_send_goods_fl.setOnClickListener(this);
        user_receive_goods_fl.setOnClickListener(this);
        user_evaluation_goods_fl.setOnClickListener(this);
        user_over_goods_fl.setOnClickListener(this);
        user_all_order_rl.setOnClickListener(this);
        user_integral_rl.setOnClickListener(this);
        user_wallet_rl.setOnClickListener(this);
        user_coupons_rl.setOnClickListener(this);
        user_service_rl.setOnClickListener(this);
        user_addres_rl.setOnClickListener(this);
        user_collection_rl.setOnClickListener(this);
        user_settings_rl.setOnClickListener(this);

    }


    @Override
    protected void initData() {
        ViewGroup.LayoutParams layoutParams = user_centent_header_ly.getLayoutParams();
        layoutParams.height = (int) (getWindowWidth() / 2.28);
        user_centent_header_ly.setLayoutParams(layoutParams);
        showUserInfoView(MallAppliaction.getInstance().userBean);
    }

    @Override
    protected void onVisible() {
        super.onVisible();

    }

    @Override
    public void onResume() {
        super.onResume();
        subscription = RxBus.getDefault()
                .register(UserBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserBean>() {
                    @Override
                    public void call(UserBean userBean) {
                        showUserInfoView(userBean);
                    }
                });
        getUserOrderCount();
    }

    private void showUserInfoView(UserBean userBean) {
        //这里更换ui布局
        if (userBean != null) {
            user_centent_user_ly.setVisibility(View.VISIBLE); //个人中心用户view
            user_centent_login_ly.setVisibility(View.GONE);//个人中心登录view
            imageLoader.displayImage(userBean.getUserAvatar(), user_icon_img, MallAppliaction.getInstance().roundOptions);
            user_name_text.setText(userBean.getUserName());
        } else {
            user_centent_user_ly.setVisibility(View.GONE); //个人中心用户view
            user_centent_login_ly.setVisibility(View.VISIBLE);//个人中心登录view
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed())
            //如果订阅取消订阅
            subscription.unsubscribe();
    }

    private void getUserOrderCount() {
        if (MallAppliaction.getInstance().userBean == null) return;
        Map<String, Object> params = RequestAction.GET_USER_ORDER_COUNT.params.getParams();
        params.put(MallConstant.SHOPINFO_USERID, MallAppliaction.getInstance().userBean.getUserId());
        sendRequest(RequestAction.GET_USER_ORDER_COUNT);
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        switch (success.getRequestAction()) {
            case GET_USER_ORDER_COUNT:
                OrderCountBean orderCountBean = (OrderCountBean) success.getHttpBean().getObject();
                if (orderCountBean.getWaitPayNum() > 0) {
                    user_pay_goods_count_text.setVisibility(View.VISIBLE);
                    user_pay_goods_count_text.setText(orderCountBean.getWaitPayNum());//待付款
                } else {
                    user_pay_goods_count_text.setVisibility(View.INVISIBLE);
                }
                if (orderCountBean.getWaitSendNum() > 0) {
                    user_send_goods_count_text.setVisibility(View.VISIBLE);
                    user_send_goods_count_text.setText(orderCountBean.getWaitSendNum());//待发货
                } else {
                    user_send_goods_count_text.setVisibility(View.INVISIBLE);
                }
                if (orderCountBean.getWaitTakeNum() > 0) {
                    user_receive_goods_count_text.setVisibility(View.VISIBLE);
                    user_receive_goods_count_text.setText(orderCountBean.getWaitTakeNum());//待收货
                } else {
                    user_receive_goods_count_text.setVisibility(View.INVISIBLE);
                }
                if (orderCountBean.getWaitCommentNum() > 0) {
                    user_evaluation_goods_count_text.setVisibility(View.VISIBLE);
                    user_evaluation_goods_count_text.setText(orderCountBean.getWaitCommentNum());//待评价
                } else {
                    user_evaluation_goods_count_text.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_icon_img://点击头像，进入个人中心
                if (isLoginTo()) {
                    startActivity(UserInfoActivity.class);
                }
                break;
            case R.id.user_login_btn://登录
                startActivity(UserLoginActivity.class);
                break;
            case R.id.user_registered_btn://注册
                startActivity(UserRegisterActivity.class);
                break;
            case R.id.user_pay_goods_fl://待支付
                break;
            case R.id.user_send_goods_fl://待发货
                break;
            case R.id.user_receive_goods_fl://待收货
                break;
            case R.id.user_evaluation_goods_fl://待评价
                break;
            case R.id.user_over_goods_fl://已完成
                break;
            case R.id.user_all_order_rl://全部订单
                break;
            case R.id.user_integral_rl://我的积分
                break;
            case R.id.user_wallet_rl://我的余额
                break;
            case R.id.user_coupons_rl://我的优惠券
                break;
            case R.id.user_service_rl://售后服务
                break;
            case R.id.user_addres_rl://收货地址
                break;
            case R.id.user_collection_rl://我的收藏
                break;
            case R.id.user_settings_rl://设置
                break;
        }
    }
}
