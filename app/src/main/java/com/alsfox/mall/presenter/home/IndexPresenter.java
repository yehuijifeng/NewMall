package com.alsfox.mall.presenter.home;

import android.view.View;

import com.alsfox.mall.base.BaseViewHolder;
import com.alsfox.mall.bean.index.IndexMokuaiContentInfoBean;
import com.alsfox.mall.bean.index.IndexMokuaiInfoBean;
import com.alsfox.mall.model.home.IndexModel;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.interfaces.home.IIndexView;

/**
 * Created by 浩 on 2016/10/19.
 * 首页presenter
 */

public class IndexPresenter extends BasePresenter<IIndexView> {

    private IndexModel indexModel;

    /**
     * 每个继承基类的presenter都要去实现构造方法，并传入view层
     *
     * @param mView
     */
    public IndexPresenter(IIndexView mView, int windowWidth) {
        super(mView);
        indexModel = new IndexModel(windowWidth);
    }

    /**
     * 加载首页数据
     */
    public void getIndexData() {
        indexModel.getIndexData();
    }

    /**
     * listview中item
     *
     * @param position
     * @param baseViewHolder
     * @param itemType
     * @param indexMokuaiInfoBean
     */
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType, IndexMokuaiInfoBean indexMokuaiInfoBean) {
        indexModel.setOnItemImgClickInterface(new IndexModel.OnItemImgClickInterface() {
            @Override
            public void onItemImgClick(View v, IndexMokuaiContentInfoBean moduleContent) {
                mView.onItemImgClick(v, moduleContent);
            }

        });
        indexModel.getItemData(position, baseViewHolder, itemType, indexMokuaiInfoBean);

    }

    public BaseViewHolder getOneViewHolder(View itemView) {
        return indexModel.getOneViewHolder(itemView);
    }

    public BaseViewHolder getTowViewHolder(View itemView) {
        return indexModel.getTowViewHolder(itemView);
    }

    public BaseViewHolder getThreeViewHolder(View itemView) {
        return indexModel.getThreeViewHolder(itemView);
    }

    public BaseViewHolder getFourViewHolder(View itemView) {
        return indexModel.getFourViewHolder(itemView);
    }

    public BaseViewHolder getFiveViewHolder(View itemView) {
        return indexModel.getFiveViewHolder(itemView);
    }

    public BaseViewHolder getSexViewHolder(View itemView) {
        return indexModel.getSexViewHolder(itemView);
    }

    public BaseViewHolder getSevenViewHolder(View itemView) {
        return indexModel.getSevenViewHolder(itemView);
    }
}
