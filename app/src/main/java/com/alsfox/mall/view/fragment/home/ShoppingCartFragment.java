package com.alsfox.mall.view.fragment.home;

import android.view.View;

import com.alsfox.mall.R;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.baseview.MyTitleView;
import com.alsfox.mall.view.fragment.base.BaseFragment;

/**
 * Created by LuHao on 2016/10/23.
 * 购物车
 */

public class ShoppingCartFragment extends BaseFragment {


    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView(View parentView) {
        mTitleView.setTitleMode(MyTitleView.TitleMode.NO_BACK_NORMAL);
        mTitleView.setTitleText(getResources().getString(R.string.str_shopping_cart));
    }

    @Override
    protected void initData() {

    }

}
