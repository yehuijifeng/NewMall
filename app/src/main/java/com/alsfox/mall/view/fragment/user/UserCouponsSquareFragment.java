package com.alsfox.mall.view.fragment.user;

import android.view.View;
import android.widget.AdapterView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.coupons.CouponsBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.StatusCode;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;

import java.util.Map;

/**
 * Created by 浩 on 2016/11/24.
 * 优惠券广场
 */

public class UserCouponsSquareFragment extends UserCouponsMyFragment {

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        super.getItemData(position, baseViewHolder, itemType);
        CouponsBean couponsInfo = (CouponsBean) data.get(position);
        ViewHolder viewHolder = (ViewHolder) baseViewHolder;
        viewHolder.btnAddCoupons.setVisibility(View.VISIBLE);
        if (couponsInfo.getIsTask() == 0) {
            viewHolder.btnAddCoupons.setOnClickListener(new AddCouponsClickListener(couponsInfo));
            viewHolder.btnAddCoupons.setEnabled(true);
            viewHolder.btnAddCoupons.setText("领取");
        } else {
            viewHolder.btnAddCoupons.setVisibility(View.GONE);
            viewHolder.yilingqu_image.setVisibility(View.VISIBLE);
            viewHolder.tvCouponsName.setTextColor(getResources().getColor(R.color.mall_white));
            viewHolder.tv_coupons_condition_of_use.setTextColor(getResources().getColor(R.color.mall_white));
            viewHolder.tvCouponsMoney.setTextColor(getResources().getColor(R.color.mall_white));
            viewHolder.tvCouponsValidTime.setTextColor(getResources().getColor(R.color.mall_white));
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

    }

    /**
     * 请求优惠券广场
     */
    protected void requestMyCoupons() {
        Map<String, Object> params = RequestAction.GET_KEYONG_COUPONS_LIST.params.getParams();
        params.put(MallConstant.PAGE_NUM, currentPageNum);
        params.put(MallConstant.PARAM_KEY_COUPONS_RECORD_INFO_USER_ID, MallAppliaction.getInstance().userBean.getUserId());
        sendRequest(RequestAction.GET_KEYONG_COUPONS_LIST);
    }


    class AddCouponsClickListener implements View.OnClickListener {

        private CouponsBean couponsInfo;

        AddCouponsClickListener(CouponsBean couponsInfo) {
            this.couponsInfo = couponsInfo;
        }

        @Override
        public void onClick(View v) {
            Map<String, Object> params = RequestAction.REQUEST_ADD_COUPONS.params.getParams();
            params.put(MallConstant.PARAM_KEY_COUPONS_RECORD_INFO_USER_ID, MallAppliaction.getInstance().userBean.getUserId());
            params.put(MallConstant.PARAM_KEY_COUPONS_RECORD_INFO_COUPONS_ID, couponsInfo.getCouponsId());
            sendRequest(RequestAction.REQUEST_ADD_COUPONS);
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case REQUEST_ADD_COUPONS:
                showLongToast(success.getHttpBean().getObject().toString());
                refresh();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case REQUEST_ADD_COUPONS:
                if (finals.getRequestCode() != StatusCode.NOT_MORE_DATA || currentPageNum == 1) {
                    showLongToast(finals.getErrorMessage());
                }
                break;
        }
    }

}
