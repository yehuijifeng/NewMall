package com.alsfox.mall.view.fragment.loading;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alsfox.mall.R;
import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.presenter.app.AppPresenter;
import com.alsfox.mall.view.fragment.base.BaseFragment;
import com.alsfox.mall.view.interfaces.app.IAppView;

/**
 * Created by 浩 on 2016/10/27.
 * 第一次进入应用
 */

public class StartAppFragment extends BaseFragment<AppPresenter> implements IAppView {

    protected ImageView start_app_img;
    protected Button start_app_btn;
    private int resId;

    public StartAppFragment(int resId) {
        this.resId = resId;
    }

    @Override
    protected AppPresenter initPresenter() {
        return new AppPresenter(this);
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.fragment_start_app;
    }

    @Override
    protected void initView(View parentView) {
        start_app_img = (ImageView) parentView.findViewById(R.id.start_app_img);
        start_app_btn = (Button) parentView.findViewById(R.id.start_app_btn);
        start_app_img.setImageResource(resId);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void isDownloadApp(boolean bl, AppVersionBean appVersionBean) {

    }
}
