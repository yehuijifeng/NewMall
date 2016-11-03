package com.alsfox.mall.presenter.home;

import android.view.View;

import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.bean.index.IndexBean;
import com.alsfox.mall.bean.index.IndexMokuaiContentBean;
import com.alsfox.mall.bean.index.IndexMokuaiBean;
import com.alsfox.mall.model.home.IndexModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.home.IIndexView;

/**
 * Created by 浩 on 2016/10/19.
 * 首页presenter
 */

public class IndexPresenter extends BasePresenter<IIndexView,IndexModel> {


    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public IndexPresenter(IIndexView mView, int windowWidth) {
        super(mView);
        mModel = new IndexModel(windowWidth);
    }

    /**
     * 加载首页数据
     */
    public void getIndexData() {
        mModel.getIndexData();
    }

    /**
     * 将首页数据添加到数据库
     *
     * @param indexData
     * @return
     */
    public int setIndexDataByDb(IndexBean indexData) {
        return mModel.setIndexDataByDb(indexData);
    }

    /**
     * 从数据库查询出首页数据
     *
     * @return
     */
    public void getIndexDataByDb() {
        mView.getIndexDataByDb(mModel.getIndexDataByDb());
    }

    /**
     * listview中item
     *
     * @param position
     * @param baseViewHolder
     * @param itemType
     * @param indexMokuaiInfoBean
     */
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType, IndexMokuaiBean indexMokuaiInfoBean) {
        mModel.setOnItemImgClickInterface(new IndexModel.OnItemImgClickInterface() {
            @Override
            public void onItemImgClick(View v, IndexMokuaiContentBean moduleContent) {
                mView.onItemImgClick(v, moduleContent);
            }

        });
        mModel.getItemData(position, baseViewHolder, itemType, indexMokuaiInfoBean);

    }

    public BaseViewHolder getOneViewHolder(View itemView) {
        return mModel.getOneViewHolder(itemView);
    }

    public BaseViewHolder getTowViewHolder(View itemView) {
        return mModel.getTowViewHolder(itemView);
    }

    public BaseViewHolder getThreeViewHolder(View itemView) {
        return mModel.getThreeViewHolder(itemView);
    }

    public BaseViewHolder getFourViewHolder(View itemView) {
        return mModel.getFourViewHolder(itemView);
    }

    public BaseViewHolder getFiveViewHolder(View itemView) {
        return mModel.getFiveViewHolder(itemView);
    }

    public BaseViewHolder getSexViewHolder(View itemView) {
        return mModel.getSexViewHolder(itemView);
    }

    public BaseViewHolder getSevenViewHolder(View itemView) {
        return mModel.getSevenViewHolder(itemView);
    }
}
