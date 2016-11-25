package com.alsfox.mall.view.fragment.user;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.coupons.CouponsBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.SignUtils;
import com.alsfox.mall.http.StatusCode;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.user.UserCouponsPresenter;
import com.alsfox.mall.view.fragment.base.BaseListFragment;
import com.alsfox.mall.view.interfaces.user.IUserCouponsView;

import java.util.List;
import java.util.Map;

import static com.alsfox.mall.http.request.RequestAction.GET_MY_COUPONS_LIST;


/**
 * Created by 浩 on 2016/11/24.
 * 我的优惠券
 */

public class UserCouponsMyFragment extends BaseListFragment<UserCouponsPresenter> implements IUserCouponsView {

    protected int currentPageNum = 1;

    private int coupons_action;

    protected double total;

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        ViewHolder viewHolder = (ViewHolder) baseViewHolder;
        CouponsBean couponsInfo = (CouponsBean) data.get(position);
        viewHolder.tvCouponsName.setText(couponsInfo.getCouponsName());
        viewHolder.tv_coupons_condition_of_use.setText("满" + couponsInfo.getUseTerm() + "元可用");
        viewHolder.tvCouponsMoney.setText("￥" + couponsInfo.getMoney());
        viewHolder.tvCouponsValidTime.setText(couponsInfo.getStartTime() + "——" + couponsInfo.getEndTime());
        viewHolder.yilingqu_image.setVisibility(View.GONE);
        int status = couponsInfo.getStatus();
        if (status == -2 || status == -3) {
            viewHolder.tvCouponsName.setTextColor(getResources().getColor(R.color.mall_white));
            viewHolder.tv_coupons_condition_of_use.setTextColor(getResources().getColor(R.color.mall_white));
            viewHolder.tvCouponsMoney.setTextColor(getResources().getColor(R.color.mall_white));
            viewHolder.tvCouponsValidTime.setTextColor(getResources().getColor(R.color.mall_white));
            viewHolder.ivCouponsMark.setVisibility(View.VISIBLE);
            if (status == -2) {
                viewHolder.ivCouponsMark.setImageResource(R.drawable.ic_coupons_overdue);
            } else {
                viewHolder.ivCouponsMark.setImageResource(R.drawable.ic_coupons_used);
            }
        } else {
            viewHolder.tvCouponsName.setTextColor(getResources().getColor(R.color.black));
            viewHolder.tv_coupons_condition_of_use.setTextColor(getResources().getColor(R.color.black));
            viewHolder.tvCouponsMoney.setTextColor(getResources().getColor(R.color.mall_main));
            viewHolder.tvCouponsValidTime.setTextColor(getResources().getColor(R.color.black));
            viewHolder.ivCouponsMark.setVisibility(View.GONE);
        }
    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemView(int position, int itemType) {
        return R.layout.item_user_coupons_my;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (coupons_action != MallConstant.ACTION_COUPONS_LIST_GET) return;
        Intent intent = new Intent();
        intent.putExtra(MallConstant.COUPONS_CONTENT, (CouponsBean) data.get(position));
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    protected UserCouponsPresenter initPresenter() {
        return new UserCouponsPresenter(this);
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.layout_empty_not_title_list;
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        baseListView.setBackgroundResource(R.color.mall_white);
    }

    @Override
    protected void initData() {
        coupons_action = getInt(MallConstant.BUNDLE_KEY_COUPONS_ACTION, MallConstant.ACTION_COUPONS_LIST_SELECT);
        total = getDouble(MallConstant.BUNDLE_KEY_ORDER_TOTAL, 0.0);
        reLoad();
    }

    @Override
    protected void refresh() {
        currentPageNum = 1;
        requestMyCoupons();
    }

    private void reLoad() {
        showLoading("正在加载优惠券");
        requestMyCoupons();
    }

    @Override
    public void loadMore() {
        currentPageNum++;
        requestMyCoupons();
    }

    /**
     * 请求优惠券,请求可用优惠券
     */
    protected void requestMyCoupons() {
        Map<String, Object> params = SignUtils.getParameters();
        params.put(MallConstant.PAGE_NUM, currentPageNum);
        params.put(MallConstant.PARAM_KEY_COUPONS_RECORD_INFO_USER_ID, MallAppliaction.getInstance().userBean.getUserId());
        //请求可用优惠券
        if (coupons_action == MallConstant.ACTION_COUPONS_LIST_GET) {
            params.put(MallConstant.PARAM_KEY_COUPONS_RECORD_INFO_TOTAL_PRICE, total);
            RequestAction.GET_USE_COUPONS_LIST.params.setParams(params);
            sendRequest(RequestAction.GET_USE_COUPONS_LIST);
        } else {
            GET_MY_COUPONS_LIST.params.setParams(params);
            sendRequest(RequestAction.GET_MY_COUPONS_LIST);
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_MY_COUPONS_LIST:
            case GET_USE_COUPONS_LIST:
            case GET_KEYONG_COUPONS_LIST:
                if (currentPageNum == 1) {
                    clearAll();
                }
                List<CouponsBean> couponsInfos = success.getHttpBean().getObjects();
                addAll(couponsInfos);
                loadSuccess();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_MY_COUPONS_LIST:
            case GET_KEYONG_COUPONS_LIST:
                if (finals.getRequestCode() != StatusCode.NOT_MORE_DATA || currentPageNum == 1) {
                    if (coupons_action != MallConstant.ACTION_COUPONS_LIST_GET)
                        showErrorLoadingByDefaultClick(finals.getErrorMessage());
                    else
                        showErrorLoadingByDefaultClick("没有可用的优惠券");
                }
                break;
        }
    }

    protected class ViewHolder extends BaseViewHolder {

        RelativeLayout favorableLayout;
        TextView tvCouponsMoney;
        TextView tvCouponsName;
        ImageView ivCouponsMark, yilingqu_image;
        Button btnAddCoupons;
        View favorableView;
        TextView tvCouponsValidTime;
        TextView tv_coupons_condition_of_use;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            favorableLayout = (RelativeLayout) itemView.findViewById(R.id.favorableLayout);
            tvCouponsMoney = (TextView) itemView.findViewById(R.id.tv_coupons_money);
            tvCouponsName = (TextView) itemView.findViewById(R.id.tv_coupons_name);
            ivCouponsMark = (ImageView) itemView.findViewById(R.id.iv_coupons_mark);
            btnAddCoupons = (Button) itemView.findViewById(R.id.btn_add_coupons);
            favorableView = itemView.findViewById(R.id.favorableView);
            yilingqu_image = (ImageView) itemView.findViewById(R.id.yilingqu_image);
            tvCouponsValidTime = (TextView) itemView.findViewById(R.id.tv_coupons_valid_time);
            tv_coupons_condition_of_use = (TextView) itemView.findViewById(R.id.tv_coupons_condition_of_use);
        }
    }
}
