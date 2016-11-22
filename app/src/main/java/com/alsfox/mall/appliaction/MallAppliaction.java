package com.alsfox.mall.appliaction;

import com.alsfox.mall.bean.app.BaiduMapBean;
import com.alsfox.mall.bean.user.UserBean;
import com.alsfox.mall.db.base.BaseDBHelper;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 浩 on 2016/10/19.
 * 商城appliaction
 */

public class MallAppliaction extends MvpAppliaction {

    private static MallAppliaction instance;

    public static MallAppliaction getInstance() {
        return instance;
    }

    public BaseDBHelper dBHelper;//操作数据库
    public UserBean userBean;//用户信息
    public BaiduMapBean baiduMapBean = new BaiduMapBean();//百度定位

    public static final int APP_MALL = 0;
    public static final int APP_MALL_CAN = APP_MALL + 1;
    public static final int APP_MALL_MERCHANT = APP_MALL_CAN + 1;
    /**
     * app类型：0，标准版；1，餐饮版，2，多商户
     */
    public static final int APP_TYPE = APP_MALL;
    //public static final int APP_TYPE=APP_MALL_CAN;
    //public static final int APP_TYPE=APP_MALL_MERCHANT;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        //子线程
                        if (dBHelper == null)
                            dBHelper = BaseDBHelper.getInstance(MallAppliaction.this);//获得数据库帮助类并创建数据库

                        //执行subscribe中的区域代码
                        subscriber.onNext(null);
                    }
                })
                .subscribeOn(Schedulers.newThread())//使用subscribeOn()指定观察者代码运行的线程；它把以上的代码放在的非ui线程
                .observeOn(AndroidSchedulers.mainThread())//使结果在android主线程运行
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        //主线程

                    }
                });
    }

}
