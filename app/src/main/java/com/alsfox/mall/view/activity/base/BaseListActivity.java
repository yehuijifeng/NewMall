package com.alsfox.mall.view.activity.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseListAdapter;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.http.StatusCode;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.baseview.BaseListView;
import com.alsfox.mall.view.baseview.FootView;
import com.alsfox.mall.view.baseview.HeaderView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Luhao on 2016/7/22.
 */
public abstract class BaseListActivity<T extends BasePresenter> extends BaseActivity<T> implements ListView.OnItemClickListener {

    protected BaseListView baseListView;
    public ListView listView;
    protected List<Object> data = new ArrayList<>();
    protected BaseListAdapter baseListAdapter;
    protected boolean isRefresh = true, isLoadMore = true;

    /**
     * 每一行item的数据
     *
     * @param position
     * @param baseViewHolder
     * @param itemType
     */
    public abstract void getItemData(int position, BaseViewHolder baseViewHolder, int itemType);

    /**
     * 返回一个提供重用的viewholder
     *
     * @param itemView
     * @param postion
     * @param itemType
     * @return
     */
    public abstract BaseViewHolder getViewHolder(View itemView, int postion, int itemType);

    /**
     * 返回一个item的视图
     *
     * @param position
     * @return
     */
    public abstract int getItemView(int position, int itemType);


    //判断itemView类型,默认0
    public int getItemViewType(int position) {
        return 0;
    }

    // 种类+1。这里尤其要注意，必须+1.具体为什么我也不清楚
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public void initView() {
        baseListView = (BaseListView) findViewById(R.id.default_list_view);
        listView = baseListView.listView;
        if (setHeaderView() > 0) {
            View view = View.inflate(this, setHeaderView(), null);
            if (view != null) {
                ViewGroup viewGroup = (ViewGroup) view.getRootView();
                viewGroup.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                listView.addHeaderView(view, null, getIsHeaderViewClick());
                getHeaderView(view);
            }
        }
        baseListAdapter = new BaseListAdapter(data, new OnAdapterStatus());
        listView.setAdapter(baseListAdapter);
        listView.setOnItemClickListener(this);
        listView.addFooterView(baseListView.footView, null, getIsFootViewClick());
        baseListView.setRefresh(isRefresh());
        baseListView.setLoadMore(isLoadMore());
        defaultRefresh();
        defaultLoadMore();
    }

    public class OnAdapterStatus implements BaseListAdapter.OnAdapterStatus {

        @Override
        public BaseViewHolder getViewHolders(View itemView, int position, int type) {
            return getViewHolder(itemView, position, type);
        }

        @Override
        public View getItemViews(int position, int type, ViewGroup parent) {
            return inflater.inflate(getItemView(position, type), null, false);
        }

        @Override
        public void getItemDatas(int position, BaseViewHolder baseViewHolder, int type) {
            getItemData(position, baseViewHolder, type);
        }

        @Override
        public int getItemViewTypes(int position) {
            return getItemViewType(position);
        }

        @Override
        public int getViewTypeCounts() {
            return getViewTypeCount();
        }

        @Override
        public int getcount() {
            return getCount();
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        if (success.getRequestCode() == StatusCode.REQUEST_SUCCESS) {
            baseListView.setOnExecuteScollSuccess();
            baseListView.footView.onFootPrepare(baseListView.isLoadComplete());
        }
        if (isRefresh())
            baseListView.closeRefreshView();
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        if (finals.getRequestCode() == StatusCode.NOT_MORE_DATA) {
            baseListView.footView.onFootViewAll();
        }
        if (isRefresh())
            baseListView.closeRefreshView();
    }

    /**
     * 传入头view
     */
    protected int setHeaderView() {
        return 0;
    }

    /**
     * 传出头view
     */
    protected void getHeaderView(View view) {
    }

    /**
     * listview的头view是否可点击
     * 默认，true
     */
    protected boolean getIsHeaderViewClick() {
        return false;
    }

    /**
     * 重写listview的尾view
     */
    protected void getAgainFootView(View footView) {
        listView.removeFooterView(baseListView.footView);
        listView.addFooterView(footView, null, getIsFootViewClick());
    }

    /**
     * listview的尾view是否可点击
     * 默认，true
     */
    protected boolean getIsFootViewClick() {
        return false;
    }

    /**
     * 获得listview中的数据
     */
    protected List getData() {
        return data;
    }

    /**
     * 重写listview中adapter所带的数据个数
     */
    public int getCount() {
        return data.size();
    }

    /**
     * 加载更多的事件，子类可以重写
     */
    public void loadMore() {

    }

    /**
     * 刷新数据
     */
    protected void notifyDataChange() {
        baseListAdapter.notifyDataSetChanged();
    }

    /**
     * 添加数据
     */
    protected void addAll(Collection datas) {
        if (datas == null) return;
        data.addAll(datas);
    }

    /**
     * 删除数据
     */
    protected void clearAll() {
        if (data == null) return;
        data.clear();
    }

    /**
     * 是否加载更多
     */
    protected boolean isLoadMore() {
        return isLoadMore;
    }

    protected void setLoadMore(boolean bl) {
        isLoadMore = bl;
        if (baseListView != null) baseListView.setLoadMore(bl);
    }

    /**
     * 是否下拉刷新
     */
    protected boolean isRefresh() {
        return isRefresh;
    }

    protected void setRefresh(boolean bl) {
        isRefresh = bl;
        if (baseListView != null) baseListView.setRefresh(bl);
    }

    /**
     * 下拉刷新监听事件
     */
    private void defaultRefresh() {
        baseListView.setRefreshListener(new HeaderView.RefreshListener() {
            @Override
            public void onRefreshPrepare(boolean bl, PtrFrameLayout frame) {
                //准备刷新
            }

            @Override
            public void onRefreshBegin(boolean bl, PtrFrameLayout frame) {
                //刷新中
                refresh();
            }

            @Override
            public void onRefreshComplete(boolean bl, PtrFrameLayout frame) {
                //刷新完成
            }
        });
    }

    /**
     * 下拉加载监听
     */
    private void defaultLoadMore() {
        baseListView.setLoadMoreListener(new FootView.LoadMoreListener() {
            @Override
            public void onLoadMorePrepare(boolean bl) {
                //准备加载
            }

            @Override
            public void onLoadMoreBegin(boolean bl) {
                //开始加载
                if (isLoadMore())
                    loadMore();
            }

            @Override
            public void onLoadMoreComplete(boolean bl) {
                //加载完全部数据

            }
        });
    }

    protected void loadSuccess() {
        notifyDataChange();
        closeLoading();
        if (isRefresh())
            baseListView.closeRefreshView();
    }

    protected void loadTextFinal(String errorStr) {
        showErrorLoadingByDefaultClick(errorStr);
        if (isRefresh())
            baseListView.closeRefreshView();
    }

    protected void loadTextFinal(String errorStr, View.OnClickListener onClickListener) {
        showErrorLoading(errorStr, onClickListener);
        if (isRefresh())
            baseListView.closeRefreshView();
    }

    protected void loadBtnFinal(String errorStr, String btnStr) {
        showErrorBtnLoadingByDefaultClick(errorStr, btnStr);
        if (isRefresh())
            baseListView.closeRefreshView();
    }

    protected void loadBtnFinal(String errorStr, String btnStr, View.OnClickListener onClickListener) {
        showErrorBtnLoading(errorStr, btnStr, onClickListener);
        if (isRefresh())
            baseListView.closeRefreshView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseListAdapter.closeAdapter();
    }
}
