package com.alsfox.mall.view.activity.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.ActivityCollector;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.http.StatusCode;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.activity.user.UserLoginActivity;
import com.alsfox.mall.view.baseview.LoadingView;
import com.alsfox.mall.view.baseview.MyTitleView;
import com.android.skin.base.BaseSkinActivity;
import com.android.skin.manager.SkinManager;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Luhao on 2016/6/21.
 * 所有activity的基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends BaseSkinActivity {

    //必须实例化presenter对象
    protected abstract T initPresenter();

    //必须传入activity的view
    protected abstract int setContentView();

    //初始化title
    protected abstract String setTitleText();

    //初始化view
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    protected T presenter;

    private BaseHelper baseHelper;

    /**
     * imageloader工具类的初始化
     */
    protected ImageLoader imageLoader;

    /**
     * 父布局填充
     */
    public LayoutInflater inflater;

    /**
     * 根布局
     */
    protected ViewGroup root, rootGroup;

    /**
     * activity的title
     */
    protected MyTitleView mTitleView;

    /**
     * activity的laodingview，该loadingview包含在titleview中
     */
    private LoadingView loadingView;

    /**
     * 获得titlebar的高度
     */
    public static int titleBarHeight;

    /**
     * 是否全屏幕
     */
    private boolean isPullWindow;

    protected boolean isDefaultRxBus = true;//是否使用默认的RxBus生命周期

    public boolean isDefaultRxBus() {
        return isDefaultRxBus;
    }

    public void setDefaultRxBus(boolean defaultRxBus) {
        isDefaultRxBus = defaultRxBus;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止横竖屏
        setPullWindow();//设置全屏
        setContentView(setContentView());
//        //沉浸式title
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        initialize();
        initView();
        initData();
    }

    public boolean isLogin() {
        return MallAppliaction.getInstance().userBean != null;
    }

    public boolean isLoginTo() {
        if (MallAppliaction.getInstance().userBean == null) {
            startActivity(UserLoginActivity.class);
            return false;
        }
        return true;
    }

    public boolean isPullWindow() {
        return isPullWindow;
    }

    public void setPullWindow(boolean pullWindow) {
        isPullWindow = pullWindow;
    }

    /**
     * 如果需要将某个activity设置成全屏，则在setContentView之前重写该方法
     */
    protected void setPullWindow() {
        if (!isPullWindow()) return;
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null && isDefaultRxBus()) {
            presenter.onResume();
            presenter.subscription = presenter.observable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResponseAction>() {
                        @Override
                        public void call(ResponseAction responseAction) {
                            if (responseAction instanceof ResponseSuccessAction) {
                                onRequestSuccess((ResponseSuccessAction) responseAction);
                            } else if (responseAction instanceof ResponseFinalAction) {
                                onRequestFinal((ResponseFinalAction) responseAction);
                            }
                        }
                    });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null && isDefaultRxBus()) {
            presenter.onPause();
        }
    }


    /**
     * 在baseactivity内部的初始化工作
     */
    private void initialize() {
        presenter = initPresenter();
        ActivityCollector.addActivity(this);
        baseHelper = new BaseHelper(this);
        rootGroup = baseHelper.getRootGroup();
        root = baseHelper.getRoot();
        inflater = baseHelper.getInflater();
        //在activity创建的时候添加换肤的监听
        //目的就是为了当我们继承这个抽象类的时候，其他地方如果用到了ViewGroup的话，我们也不用再次获取了
        SkinManager.getInstance().register(this, rootGroup);
        mTitleView = (MyTitleView) findViewById(R.id.default_title_view);
        if (mTitleView != null) {
            loadingView = mTitleView.getLoadingView();
            if (setCustomToolbar() != null) {
                onCreateCustomToolBar(setCustomToolbar());
            } else {
                mTitleView.setTitleText(setTitleText());
            }
        } else {
            loadingView = (LoadingView) findViewById(R.id.default_loading_view);
        }
        imageLoader = ImageLoader.getInstance();
    }

    /**
     * 当窗口焦点改变的时候，我们去计算titlebar的高度，以便于给自定义的titleview设置高度
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            getWindowTop();
//            mTitleView.setTitleBarHeight(titleBarHeight);
//        }
    }

    /**
     * 获得titlevar的高度
     *
     * @return
     */
    private int getWindowTop() {
        if (titleBarHeight > 0) {
            return titleBarHeight;
        } else {
            Rect frame = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;
            int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
            //statusBarHeight是上面所求的状态栏的高度
            titleBarHeight = contentTop - statusBarHeight;
            return titleBarHeight;
        }
    }

    /**
     * 隐藏软键盘的方法
     *
     * @param view
     */
    protected void hideSoftInputFromWindow(View view) {
        baseHelper.hideSoftInputFromWindow(view);
    }

    protected void showSoftInputFromWindow(View view) {
        baseHelper.showSoftInputFromWindow(view);
    }

    /**
     * 去设置网络
     */
    protected void toSetNetWork() {
        Intent wifiSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(wifiSettingsIntent);
    }

    /**
     * 发送网络请求
     *
     * @param requesteAction
     */
    protected void sendRequest(RequestAction requesteAction) {
        baseHelper.sendRequest(requesteAction);
    }

    /**
     * 刷新的事件，子类可以重写
     */
    protected void refresh() {

    }

    /**
     * 隐藏标题
     */
    protected void goneTitleView() {
        mTitleView.goneTitleView();
    }

    /**
     * loading遮罩层的加载
     */
    protected void showLoading() {
        if (loadingView == null) return;
        loadingView.showLoading(getResources().getString(R.string.header_hint_loading));
    }

    protected void showLoading(String str) {
        if (loadingView == null) return;
        loadingView.showLoading(str);
    }


    protected void showErrorLoading(String str, View.OnClickListener onClickListener) {
        if (loadingView == null) return;
        loadingView.closeLoadingView();
        loadingView.showErrorPrompt(str);
        loadingView.setErrorClickListener(onClickListener);
    }

    /**
     * 显示错误信息，并且显示按钮
     *
     * @param errorStr
     * @param btnStr
     * @param btnclick
     * @param errorclick
     */
    protected void showErrorLoadingAndBtn(String errorStr, String btnStr, View.OnClickListener btnclick, View.OnClickListener errorclick) {
        if (loadingView == null) return;
        loadingView.showErrorPromptAndBtn(errorStr, btnStr, btnclick);
        loadingView.setErrorClickListener(errorclick);

    }

    /**
     * 显示错误信息+图片，无点击事件
     *
     * @param str
     */
    protected void showErrorLoadingByNoClick(String str) {
        showErrorLoading(str, null);
    }

    protected void showErrorLoadingByDefaultClick(String str) {
        showErrorLoading(str, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }

    /**
     * 显示错误按钮+信息
     *
     * @param str
     * @param btnStr
     * @param onClickListener
     */
    protected void showErrorBtnLoading(String str, String btnStr, View.OnClickListener onClickListener) {
        if (loadingView == null) return;
        loadingView.showErrorBtnPrompt(str);
        loadingView.setErrorBtnClickListener(btnStr, onClickListener);
    }

    protected void showErrorBtnLoadingByDefaultClick(String str, String btnStr) {
        showErrorBtnLoading(str, btnStr, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }

    protected void closeLoading() {
        if (loadingView == null) return;
        loadingView.closeLoadingView();
    }

    /**
     * 获取屏幕尺寸的对象
     *
     * @return
     */
    protected DisplayMetrics getDisplayMerics() {
        if (baseHelper != null)
            return baseHelper.outMetrics;
        return null;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    protected int getWindowHeight() {
        return baseHelper.getWindowHeight();
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    protected int getWindowWidth() {
        return baseHelper.getWindowWidth();
    }

    /**
     * 供子类调用的title,自定义toolbar
     */
    protected View setCustomToolbar() {
        return null;
    }

    /**
     * @param view 创建自定义的toolbar
     */
    protected void onCreateCustomToolBar(View view) {
        mTitleView.setNewView(view);
    }


    /**
     * 跳转activity
     *
     * @param cla
     */
    protected void startActivity(Class cla) {
        startActivity(cla, null);
    }

    protected void startActivity(Class<?> cls, Bundle bundle) {
        baseHelper.startActivity(cls, bundle);
    }

    /**
     * 显式跳转Activity的方法(带返回结果，不带参数)
     *
     * @param cls         要跳转的Activity的类
     * @param requestCode 跳转Activity的请求码
     */
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        baseHelper.startActivityForResult(cls, requestCode);
    }

    /**
     * 显式跳转Activity的方法(带返回结果，带Bundle)
     *
     * @param cls         要跳转的Activity的类
     * @param bundle      装载了各种参数的Bundle
     * @param requestCode 跳转Activity的请求码
     */
    protected void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        baseHelper.startActivityForResult(cls, bundle, requestCode);
    }


    /**
     * 获取上一个Activity传过来的Bundle
     */
    protected Bundle getBundle() {
        return baseHelper.getBundle();
    }

    /**
     * 获取上一个Activity传过来的字符串集合
     */
    protected List<String> getStringArrayList(String key) {
        return baseHelper.getStringArrayList(key);
    }

    /**
     * 获取上一个Activity传过来的int值
     *
     * @param key          键
     * @param defaultValue 默认值
     */
    protected int getInt(String key, int defaultValue) {
        return baseHelper.getInt(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的String值
     */
    protected String getString(String key, String defaultValue) {
        return baseHelper.getString(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的布尔值
     */
    protected boolean getBoolean(String key, boolean defaultValue) {
        return baseHelper.getBoolean(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的double值
     */
    protected double getDouble(String key, double defaultValue) {
        return baseHelper.getDouble(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的float值
     */
    protected float getFloat(String key, float defaultValue) {
        return baseHelper.getFloat(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的实现了Parcelable接口的对象
     */
    protected Parcelable getParcelable(String key) {
        return baseHelper.getParcelable(key);
    }

    /**
     * 获取上一个Activity传过来的实现了Parcelable接口的对象的集合
     */
    protected ArrayList<? extends Parcelable> getParcelableList(String key) {
        return baseHelper.getParcelableList(key);
    }


    /**
     * Intent intent = new Intent();
     * intent.putExtra("user_address", addressInfoVo);
     * setResult(RESULT_OK, intent);
     * finish();
     */

    protected void setIsClick(boolean bl) {
        baseHelper.setIsClick(bl);
    }

    /**
     * 提供给子类继承
     * 网路请求返回结果
     *
     * @param success
     */
    protected void onRequestSuccess(ResponseSuccessAction success) {

    }

    protected void onRequestFinal(ResponseFinalAction finals) {
        if (finals.getRequestCode() == StatusCode.NETWORK_ERROR) {
            closeLoading();
            //无网络链接
            showErrorLoadingAndBtn(finals.getErrorMessage() + getResources().getString(R.string.refresh_window), getResources().getString(R.string.settings_network), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toSetNetWork();//按钮是去设置网络
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLoading();
                    refresh();//点击空白处是刷新
                }
            });
        }
    }

    /**
     * 弹出时间短暂的Toast
     */
    protected void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出时间较长的Toast
     */
    protected void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        if (baseHelper != null)
            baseHelper.releaseActivity();
        baseHelper = null;
        if (presenter != null)
            presenter.onDestory();
        presenter = null;
    }
}
