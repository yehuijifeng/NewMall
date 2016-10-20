package com.alsfox.mall.view.fragment.home;

import android.view.View;
import android.widget.AdapterView;

import com.alsfox.mall.R;
import com.alsfox.mall.base.BaseViewHolder;
import com.alsfox.mall.bean.index.IndexInfoBean;
import com.alsfox.mall.bean.index.IndexMokuaiContentInfoBean;
import com.alsfox.mall.bean.index.IndexMokuaiInfoBean;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.model.home.IndexModel;
import com.alsfox.mall.presenter.home.IndexPresenter;
import com.alsfox.mall.view.customview.SearchTitleView;
import com.alsfox.mall.view.fragment.base.BaseListFragment;
import com.alsfox.mall.view.interfaces.home.IIndexView;

import java.util.List;


/**
 * Created by 浩 on 2016/10/19.
 * 首页
 */

public class IndexFragment extends BaseListFragment<IndexPresenter> implements IIndexView {

    private SearchTitleView search_title_view;

    private List<IndexMokuaiInfoBean> indexData;

    @Override
    protected IndexPresenter initPresenter() {
        return new IndexPresenter(this, getWindowWidth());
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        search_title_view = (SearchTitleView) parentView.findViewById(R.id.search_title_view);

    }

    @Override
    protected void initData() {
        showLoading("正在加载首页数据……");
        presenter.getIndexData();
        search_title_view.setSearchClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShortToast("进入搜索页面");
            }
        });
    }


    @Override
    protected void refresh() {
        showLoading();
        presenter.getIndexData();
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_INDE_DATA:
                IndexInfoBean indexInfoBean = (IndexInfoBean) success.getHttpBean().getObject();
                indexData = indexInfoBean.getIndexMoudleList();
                clearAll();
                addAll(indexData);
                loadSuccess();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_INDE_DATA:
                loadSuccess();
                showLongToast(finals.getErrorMessage());
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * 0：一排三列；
     * 1：├型；
     * 2：田型；
     * 3：┤型；
     * 4：┬型；
     * 5：┴型；
     * 6：一型；
     */
    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        IndexMokuaiInfoBean indexMokuaiInfoBean = indexData.get(position);
        presenter.getItemData(position, baseViewHolder, itemType, indexMokuaiInfoBean);
    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        BaseViewHolder baseViewHolder = null;
        switch (itemType) {
            case IndexModel.INDEX_ITEM_ONE:
                baseViewHolder = presenter.getOneViewHolder(itemView);
                break;
            case IndexModel.INDEX_ITEM_TOW:
                baseViewHolder = presenter.getTowViewHolder(itemView);
                break;
            case IndexModel.INDEX_ITEM_THREE:
                baseViewHolder = presenter.getThreeViewHolder(itemView);
                break;
            case IndexModel.INDEX_ITEM_FOUR:
                baseViewHolder = presenter.getFourViewHolder(itemView);
                break;
            case IndexModel.INDEX_ITEM_FIVE:
                baseViewHolder = presenter.getFiveViewHolder(itemView);
                break;
            case IndexModel.INDEX_ITEM_SEX:
                baseViewHolder = presenter.getSexViewHolder(itemView);
                break;
            case IndexModel.INDEX_ITEM_SEVEN:
                baseViewHolder = presenter.getSevenViewHolder(itemView);
                break;
        }
        return baseViewHolder;
    }

    @Override
    protected boolean isLoadMore() {
        return false;
    }

    @Override
    public int getViewTypeCount() {
        //有七种不同样式的item
        return 7;
    }

    @Override
    public int getItemView(int position, int itemType) {
        int redId = 0;
        switch (itemType) {
            case IndexModel.INDEX_ITEM_ONE:
                redId = R.layout.index_item_one;
                break;
            case IndexModel.INDEX_ITEM_TOW:
                redId = R.layout.index_item_tow;
                break;
            case IndexModel.INDEX_ITEM_THREE:
                redId = R.layout.index_item_three;
                break;
            case IndexModel.INDEX_ITEM_FOUR:
                redId = R.layout.index_item_four;
                break;
            case IndexModel.INDEX_ITEM_FIVE:
                redId = R.layout.index_item_five;
                break;
            case IndexModel.INDEX_ITEM_SEX:
                redId = R.layout.index_item_sex;
                break;
            case IndexModel.INDEX_ITEM_SEVEN:
                redId = R.layout.index_item_seven;
                break;
        }
        return redId;
    }

    @Override
    public int getItemViewType(int position) {
        IndexMokuaiInfoBean indexMokuaiInfoBean = indexData.get(position);
        return indexMokuaiInfoBean.getMoudleType();
    }

    /**
     * 点击每一张图片的事件
     *
     * @param v
     * @param moduleContent
     */
    @Override
    public void onItemImgClick(View v, IndexMokuaiContentInfoBean moduleContent) {

    }
}
