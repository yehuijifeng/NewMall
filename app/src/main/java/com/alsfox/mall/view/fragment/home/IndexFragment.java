package com.alsfox.mall.view.fragment.home;

import android.view.View;
import android.widget.AdapterView;

import com.alsfox.mall.R;
import com.alsfox.mall.base.BaseViewHolder;
import com.alsfox.mall.presenter.home.IndexPresenter;
import com.alsfox.mall.view.fragment.base.BaseListFragment;
import com.alsfox.mall.view.interfaces.home.IIndexView;

/**
 * Created by 浩 on 2016/10/19.
 *
 */

public class IndexFragment extends BaseListFragment<IndexPresenter> implements IIndexView {

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {

    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        return null;
    }

    @Override
    public int getItemView(int position, int itemType) {
        return 0;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected IndexPresenter initPresenter() {
        return new IndexPresenter(this);
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
    }

    @Override
    protected void initData() {

    }


}
