package com.alsfox.mall.view.fragment.home;

import android.view.View;
import android.widget.ImageView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.fragment.base.BaseFragment;
import com.take.turns.view.TakeTurnsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuHao on 2016/10/23.
 * 购物车
 */

public class ShoppingCartFragment extends BaseFragment {

    private TakeTurnsView test;

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
        test = (TakeTurnsView) parentView.findViewById(R.id.test);

    }

    @Override
    protected void initData() {
        List<String> urls = new ArrayList<>();
        urls.add("http://a2.att.hudong.com/43/81/300245751203132333810057722_950.jpg");
        urls.add("http://a4.att.hudong.com/57/83/300245751203132333832384935_950.jpg");
        urls.add("http://img1.3lian.com/img013/v1/29/d/38.jpg");
        urls.add("http://i-7.vcimg.com/trim/a75c438eb73de2f0547281cd6bb7a49359451/trim.jpg");
        urls.add("http://i-7.vcimg.com/trim/2d0a8187a8adb4077dde9df0b5b79a881174151/trim.jpg");
        test.setTakeTurnsHeight((int) (getWindowWidth() / 2.28));
        test.setSleepTime(4 * 1000);//轮番的循环时间
        test.setViewpagerScrollTime(300);//轮番滑动速率
        test.setImageUrls(urls);
        test.setUpdateUI(new TakeTurnsView.UpdateUI() {
            @Override
            public void onUpdateUI(int position, ImageView imageView, String imgUrl) {
                imageLoader.displayImage(imgUrl, imageView, MallAppliaction.getInstance().defaultOptions);
            }

            @Override
            public void onItemClick(int position, ImageView imageView) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        test.onResume();
    }
}
