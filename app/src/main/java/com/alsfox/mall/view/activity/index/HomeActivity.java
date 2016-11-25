package com.alsfox.mall.view.activity.index;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.ActivityCollector;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.utils.LogUtils;
import com.alsfox.mall.view.activity.base.BaseViewPagerActivity;
import com.alsfox.mall.view.fragment.home.ClassifyFragment;
import com.alsfox.mall.view.fragment.home.IndexFragment;
import com.alsfox.mall.view.fragment.home.ShoppingCartFragment;
import com.alsfox.mall.view.fragment.home.UserContentFragment;
import com.alsfox.mall.xinge.XingeUtils;
import com.tencent.android.tpush.XGIOperateCallback;

import java.util.Map;

/**
 * Created by 浩 on 2016/10/19.
 * 商城首页
 */

public class HomeActivity extends BaseViewPagerActivity {

    private long exitTime = 0;//计算用户点击返回键的时间
    private boolean isXGRegister, isRunXG;//信鸽是否注册，信鸽是否已经在运行过程中
    private int runXGNum;

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
    protected void initData() {
        super.initData();
        isXGRegister = false;
        isRunXG = false;
        runXGNum = 0;
        getXG();
    }

    @Override
    protected boolean isShowBar() {
        return false;
    }

    @Override
    protected View setTabView(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.layout_index_viewpager_tab_item, container, false);
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

    /**
     * 去信鸽注册
     */
    public synchronized void getXG() {
        if (MallAppliaction.getInstance().userBean == null || isXGRegister || isRunXG || runXGNum > 3)
            return;
        isRunXG = true;
        //XingeUtils.enableDebug(this, true);
        XingeUtils.registerPush(this, "user_" + MallAppliaction.getInstance().userBean.getUserId(), new XGIOperateCallback() {
            @Override
            public void onSuccess(final Object data, int arg1) {
                LogUtils.i("注册成功，设备token为：" + data);
                isXGRegister = true;
                isRunXG = false;
                getClient(data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                LogUtils.i("注册失败，错误码：" + errCode + ",错误信息：" + msg);
                isXGRegister = false;
                isRunXG = false;
                runXGNum++;
                getXG();
            }
        });
    }

    /**
     * 将信鸽的token提交到服务器
     *
     * @param data
     */
    private void getClient(Object data) {
        Map<String, Object> params = RequestAction.GET_IS_LOGOUT.params.getParams();
        params.put("userInfo.userId", MallAppliaction.getInstance().userBean.getUserId());
        params.put("userInfo.signLoginMark", data);
        sendRequest(RequestAction.GET_IS_LOGOUT);
    }

    /**
     * @see android.support.v4.app.FragmentActivity#onKeyDown(int, android.view.KeyEvent)
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showShortToast("再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
                ActivityCollector.finishAll();
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
