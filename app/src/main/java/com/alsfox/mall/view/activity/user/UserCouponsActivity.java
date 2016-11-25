package com.alsfox.mall.view.activity.user;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.activity.base.BaseViewPagerActivity;
import com.alsfox.mall.view.fragment.user.UserCouponsMyFragment;
import com.alsfox.mall.view.fragment.user.UserCouponsSquareFragment;

/**
 * Created by 浩 on 2016/11/24.
 * 用户优惠券
 */

public class UserCouponsActivity extends BaseViewPagerActivity {

    @Override
    protected View setTabView(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.base_viewpager_tab_item, container, false);
        TextView tabText = (TextView) view.findViewById(R.id.viewpager_tab_text);
        switch (position) {
            case 0:
                tabText.setText(getResources().getString(R.string.str_user));
                break;
            case 1:
                tabText.setText(getResources().getString(R.string.str_square));
                break;
        }
        return view;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_user_coupons;
    }

    @Override
    protected String setTitleText() {
        return getResources().getString(R.string.str_coupons);
    }

    @Override
    protected void initView() {
        super.initView();
        mViewList.add(new UserCouponsMyFragment());
        mViewList.add(new UserCouponsSquareFragment());
        setPageNumber(0);
    }
}
