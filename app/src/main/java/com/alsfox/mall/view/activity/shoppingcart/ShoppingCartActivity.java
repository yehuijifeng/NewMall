package com.alsfox.mall.view.activity.shoppingcart;

import com.alsfox.mall.R;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.utils.FragmentUtils;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.fragment.home.ShoppingCartFragment;

/**
 * Created by 浩 on 2016/11/8.
 * 购物车
 */

public class ShoppingCartActivity extends BaseActivity {

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    protected String setTitleText() {
        return null;
    }

    @Override
    protected void initView() {

        FragmentUtils.addFragment(this, new ShoppingCartFragment(), R.id.rl_shopping_cart_parent);
    }

    @Override
    protected void initData() {

    }
}
