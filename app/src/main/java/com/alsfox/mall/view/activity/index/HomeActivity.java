package com.alsfox.mall.view.activity.index;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.activity.base.BaseViewPagerActivity;
import com.alsfox.mall.view.fragment.home.ClassifyFragment;
import com.alsfox.mall.view.fragment.home.IndexFragment;
import com.alsfox.mall.view.fragment.home.ShoppingCartFragment;
import com.alsfox.mall.view.fragment.home.UserContentFragment;

/**
 * Created by 浩 on 2016/10/19.
 * 商城首页
 */

public class HomeActivity extends BaseViewPagerActivity {

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected String setTitleText() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        mViewList.add(new IndexFragment());
        mViewList.add(new ClassifyFragment());
        mViewList.add(new ShoppingCartFragment());
        mViewList.add(new UserContentFragment());
        setPageNumber(0);
    }

    @Override
    protected boolean isShowBar() {
        return false;
    }

    @Override
    protected View setTabView(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.base_viewpager_tab_item, container, false);
        TextView tabText = (TextView) view.findViewById(R.id.viewpager_tab_text);
        ImageView tabImg = (ImageView) view.findViewById(R.id.viewpager_tab_img);
        tabImg.setVisibility(View.VISIBLE);
        switch (position) {
            case 0:
                tabText.setText(getResources().getString(R.string.str_home));
                tabImg.setImageResource(R.drawable.selector_icon_home);
                break;
            case 1:
                tabText.setText(getResources().getString(R.string.str_classification));
                tabImg.setImageResource(R.drawable.selector_icon_notepad);
                break;
            case 2:
                tabText.setText(getResources().getString(R.string.str_shopping_cart));
                tabImg.setImageResource(R.drawable.selector_icon_cart);
                break;
            case 3:
                tabText.setText(getResources().getString(R.string.str_user));
                tabImg.setImageResource(R.drawable.selector_icon_profile);
                break;
        }
        return view;
    }
}
