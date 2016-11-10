package com.alsfox.mall.view.fragment.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.bean.shoppingcart.ShoppingCartBean;
import com.alsfox.mall.presenter.home.ShoppingCartPresenter;
import com.alsfox.mall.view.activity.base.BaseViewPagerActivity;
import com.alsfox.mall.view.activity.index.HomeActivity;
import com.alsfox.mall.view.baseview.MyTitleView;
import com.alsfox.mall.view.baseview.dialog.PromptDialog;
import com.alsfox.mall.view.customview.CountEditText;
import com.alsfox.mall.view.fragment.base.BaseListFragment;
import com.alsfox.mall.view.interfaces.home.IShoppingCartvView;

import java.util.List;

/**
 * Created by LuHao on 2016/10/23.
 * 购物车
 */

public class ShoppingCartFragment extends BaseListFragment<ShoppingCartPresenter> implements IShoppingCartvView {

    public static final String KEY_SHOPPING_CART_TYPE = "shopping_cart_type";

    public static final int SHOPPING_CART_BY_ACTIVITY = 1000;

    public static final int SHOPPING_CART_BY_FRAGMENT = 1001;

    private int shopCartType;

    private List<ShoppingCartBean> shoppingCartBeens;

    private PromptDialog promptDialog;

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
        ShoppingViewHolder shoppingViewHolder = (ShoppingViewHolder) baseViewHolder;
        final ShoppingCartBean shoppingCartBean = shoppingCartBeens.get(position);
        shoppingViewHolder.shopping_cb.setChecked(shoppingCartBean.isChecked());
        imageLoader.displayImage(shoppingCartBean.getShopIcon(), shoppingViewHolder.shopping_img);
        shoppingViewHolder.shopping_name_text.setText(shoppingCartBean.getShopName());
        if (shoppingCartBean.getSpecName() != null)
            shoppingViewHolder.shopping_spec_text.setText(shoppingCartBean.getSpecName());
        else {
            shoppingViewHolder.shopping_spec_text.setText("");
        }
        shoppingViewHolder.shopping_count_edit.setCount(shoppingCartBean.getShopNum());
        shoppingViewHolder.shopping_count_edit.setMaxCount(shoppingCartBean.getShopStock());
        shoppingViewHolder.shopping_price_text.setText("￥" + shoppingCartBean.getPrice());
        shoppingViewHolder.shopping_count_edit.setOnChangeEditText(new CountEditText.OnChangeEditText() {
            @Override
            public void onChangeEdit(int count) {
                //修改商品数量
                shoppingCartBean.setShopNum(count);
                presenter.updateShoppingCart(shoppingCartBean);
            }
        });
        shoppingViewHolder.shopping_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //修改选中状态
                shoppingCartBean.setChecked(isChecked);
                presenter.updateShoppingCart(shoppingCartBean);
            }
        });
        shoppingViewHolder.shopping_delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除商品
                promptDialog.showPromptDialog("你确定删除该商品吗？", new PromptDialog.OnPromptClickListener() {
                    @Override
                    public void onDetermine() {
                        presenter.deleteShoppingCart(shoppingCartBean);
                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
        });
    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        return new ShoppingViewHolder(itemView);
    }

    @Override
    public int getItemView(int position, int itemType) {
        return R.layout.item_shopping_cart;
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        mTitleView.setTitleText(getResources().getString(R.string.str_shopping_cart));
        promptDialog = new PromptDialog(getActivity());
        shopCartType = getInt(KEY_SHOPPING_CART_TYPE, SHOPPING_CART_BY_FRAGMENT);
        if (shopCartType == SHOPPING_CART_BY_ACTIVITY) {
            mTitleView.setTitleMode(MyTitleView.TitleMode.NORMAL);
            initData();
        } else {
            mTitleView.setTitleMode(MyTitleView.TitleMode.NO_BACK_NORMAL);
        }

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
        baseListView.setOnExecuteScollSuccess();
        baseListView.footView.onFootPrepare(baseListView.isLoadComplete());
        baseListView.closeRefreshView();
        baseListView.footView.onFootViewAll();
        if (shoppingCartBeenList == null || shoppingCartBeenList.isEmpty()) {
            showErrorBtnLoading(getResources().getString(R.string.str_shoping_card_empty), getResources().getString(R.string.str_guangguang), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shopCartType == SHOPPING_CART_BY_ACTIVITY) {
                        startActivity(HomeActivity.class);
                    } else {
                        ((BaseViewPagerActivity) getActivity()).setPageNumber(0);
                    }

                }
            });
        } else {
            closeLoading();
            this.shoppingCartBeens = shoppingCartBeenList;
            addAll(shoppingCartBeens);
            notifyDataChange();
        }
    }

    @Override
    public void deleteShoppingCart(int i) {
        if (i > 0)
            presenter.queryShoppingCart();

    }

    @Override
    public void updateShoppingCart(int i) {
        //if (i > 0)
        //notifyDataChange();
    }

    @Override
    public void insterShoppingCart(int i) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class ShoppingViewHolder extends BaseViewHolder {
        CheckBox shopping_cb;
        ImageView shopping_img;
        TextView shopping_name_text;
        TextView shopping_spec_text;
        TextView shopping_price_text;
        CountEditText shopping_count_edit;
        ImageView shopping_delete_img;

        public ShoppingViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            shopping_cb = (CheckBox) itemView.findViewById(R.id.shopping_cb);
            shopping_img = (ImageView) itemView.findViewById(R.id.shopping_img);
            shopping_name_text = (TextView) itemView.findViewById(R.id.shopping_name_text);
            shopping_spec_text = (TextView) itemView.findViewById(R.id.shopping_spec_text);
            shopping_price_text = (TextView) itemView.findViewById(R.id.shopping_price_text);
            shopping_count_edit = (CountEditText) itemView.findViewById(R.id.shopping_count_edit);
            shopping_delete_img = (ImageView) itemView.findViewById(R.id.shopping_delete_img);
        }
    }
}
