package com.alsfox.mall.view.activity.index;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.alsfox.mall.R;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.presenter.app.AppPresenter;
import com.alsfox.mall.view.activity.base.BaseViewPagerActivity;
import com.alsfox.mall.view.fragment.loading.EndAppFragment;
import com.alsfox.mall.view.fragment.loading.StartAppFragment;
import com.alsfox.mall.view.interfaces.app.IAppView;

/**
 * Created by 浩 on 2016/10/27.
 * 第一次进入应用
 */

public class StartAppActivity extends BaseViewPagerActivity<AppPresenter> implements IAppView {

    private RadioGroup start_app_radio_group;

    @Override
    protected boolean isShowBar() {
        return false;
    }

    @Override
    protected AppPresenter initPresenter() {
        return new AppPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_start_app;
    }

    @Override
    protected String setTitleText() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        start_app_radio_group = (RadioGroup) findViewById(R.id.start_app_radio_group);
        mViewList.add(new StartAppFragment(R.drawable.start_one));
        mViewList.add(new StartAppFragment(R.drawable.start_tow));
        mViewList.add(new EndAppFragment(R.drawable.start_three));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected View setTabView(ViewGroup container, int position) {

        return null;
    }

    @Override
    public void isDownloadApp(boolean bl, AppVersionBean appVersionBean) {

    }
}
