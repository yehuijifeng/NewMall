package com.alsfox.mall.view.fragment.home;

import android.view.View;
import android.widget.AdapterView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.presenter.home.ShoppingCartPresenter;
import com.alsfox.mall.view.activity.base.BaseViewPagerActivity;
import com.alsfox.mall.view.baseview.MyTitleView;
import com.alsfox.mall.view.fragment.base.BaseListFragment;
import com.alsfox.mall.view.interfaces.home.IShoppingCartvView;

import java.util.List;

/**
 * Created by LuHao on 2016/10/23.
 * 购物车
 */

public class ShoppingCartFragment extends BaseListFragment<ShoppingCartPresenter> implements IShoppingCartvView {

    private List<ShoppingCartBean> shoppingCartBeens;

    @Override
    protected ShoppingCartPresenter initPresenter() {
        return new ShoppingCartPresenter(this);
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {

    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        return null;
    }

    @Override
    public int getItemView(int position, int itemType) {
        return R.layout.item_shopping_cart;
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        mTitleView.setTitleMode(MyTitleView.TitleMode.NO_BACK_NORMAL);
        mTitleView.setTitleText(getResources().getString(R.string.str_shopping_cart));
    }

    @Override
    protected void initData() {
        showLoading("正在加载购物车……");
        presenter.queryShoppingCart();
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        refresh();
    }

    @Override
    protected void refresh() {
        if (presenter != null) {
            clearAll();
            presenter.queryShoppingCart();
        }
    }

    @Override
    public void queryShoppingCart(List<ShoppingCartBean> shoppingCartBeenList) {
        if (shoppingCartBeenList == null || shoppingCartBeenList.isEmpty()) {
            showErrorBtnLoading(getResources().getString(R.string.str_shoping_card_empty), getResources().getString(R.string.str_guangguang), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseViewPagerActivity) getActivity()).setPageNumber(0);
                }
            });
        } else {
            this.shoppingCartBeens = shoppingCartBeenList;
            addAll(shoppingCartBeens);
        }
    }

    @Override
    public void deleteShoppingCart(int i) {
        if (i > 0)
            notifyDataChange();
    }

    @Override
    public void updateShoppingCart(int i) {
        if (i > 0)
            notifyDataChange();
    }

    @Override
    public void insterShoppingCart(int i) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
