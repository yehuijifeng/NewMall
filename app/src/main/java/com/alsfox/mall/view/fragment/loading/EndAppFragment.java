package com.alsfox.mall.view.fragment.loading;

import android.view.View;

import com.alsfox.mall.view.activity.index.LoadingActivity;

/**
 * Created by 浩 on 2016/10/27.
 * 第一次进入应用的最后一张
 */

public class EndAppFragment extends StartAppFragment {

    public EndAppFragment(int resId) {
        super(resId);
    }

    @Override
    protected void initData() {
        start_app_btn.setVisibility(View.VISIBLE);
        start_app_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoadingActivity.class);
                getActivity().finish();
            }
        });
    }
}
