package com.alsfox.mall.presenter.home;

import com.alsfox.mall.model.home.ClassifyModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.home.IClassifyView;

/**
 * Created by 浩 on 2016/10/22.
 * 分类
 */

public class ClassifyPresenter extends BasePresenter<IClassifyView,ClassifyModel> {
    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public ClassifyPresenter(IClassifyView mView) {
        super(mView);
        mModel=new ClassifyModel();
    }




}
