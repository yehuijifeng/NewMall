package com.alsfox.mall.view.activity.goods;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseListAdapter;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.shop.ShopInfoBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.goods.GoodsListPresenter;
import com.alsfox.mall.view.activity.base.BaseListActivity;
import com.alsfox.mall.view.activity.searth.SearthActivity;
import com.alsfox.mall.view.customview.goods.ItemGoodsListView;
import com.alsfox.mall.view.interfaces.goods.IGoodsListView;

import java.util.Map;


/**
 * Created by 浩 on 2016/11/2.
 * 商品列表
 */

public class GoodsListActivity extends BaseListActivity<GoodsListPresenter> implements IGoodsListView, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ImageView title_default_back;
    private CheckBox title_default_right_check;
    private LinearLayout search_ly;
    private RadioButton goods_list_new_text, goods_list_number_text, goods_list_comments_text, goods_list_price_text;
    private RadioGroup goods_tab_rg;

    private int showType;//当前排列方式,默认列表，0；方块，1；
    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 当前排序方式
     */
    private String currentSort = SORT_COMMODITY_PUB;

    /**
     * 按最新排序
     */
    public static final String SORT_COMMODITY_PUB = "pub";

    /**
     * 按销量排序
     */
    public static final String SORT_COMMODITY_SALE = "sale";

    /**
     * 按价格升序排序
     */
    public static final String SORT_COMMODITY_PRICE_ASC = "priceAsc";

    /**
     * 按价格降序排序
     */
    public static final String SORT_COMMODITY_PRICE_DESC = "priceDesc";

    /**
     * 按好评率排序
     */
    public static final String SORT_COMMODITY_HPLV = "hpLv";


    private int shopTypeId;//商品分类id

    @Override
    protected GoodsListPresenter initPresenter() {
        return new GoodsListPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_shop_list;
    }

    @Override
    protected String setTitleText() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        title_default_back = (ImageView) findViewById(R.id.title_default_back);
        title_default_right_check = (CheckBox) findViewById(R.id.title_default_right_check);
        search_ly = (LinearLayout) findViewById(R.id.search_ly);
        goods_tab_rg = (RadioGroup) findViewById(R.id.goods_tab_rg);
        goods_list_new_text = (RadioButton) findViewById(R.id.goods_list_new_text);
        goods_list_number_text = (RadioButton) findViewById(R.id.goods_list_number_text);
        goods_list_comments_text = (RadioButton) findViewById(R.id.goods_list_comments_text);
        goods_list_price_text = (RadioButton) findViewById(R.id.goods_list_price_text);
        title_default_back.setOnClickListener(this);
        search_ly.setOnClickListener(this);
        goods_list_new_text.setChecked(true);
        goods_tab_rg.setOnCheckedChangeListener(this);
        goods_list_price_text.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        shopTypeId = getInt(MallConstant.SHOPTYPEID, 0);
        showType = title_default_right_check.isChecked() ? 1 : 0;
        title_default_right_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showType = isChecked ? 1 : 0;
                baseListAdapter = new BaseListAdapter(data, new OnAdapterStatus());
                listView.setAdapter(baseListAdapter);
                notifyDataChange();
            }
        });
        getGoodsListRefesh();
    }


    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        if (showType == 0) {//列表
            return new GoodsListViewHolder(itemView);
        } else if (showType == 1) {//方块
            return new GoodsListTowViewHolder(itemView);
        } else {
            return null;
        }
    }

    @Override
    public int getItemView(int position, int itemType) {
        if (showType == 0) {//列表
            return R.layout.layout_searth_list;
        } else if (showType == 1) {//方块
            return R.layout.item_goods_list;
        } else {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        int count = data.size();
        if (showType == 0) {
            return count;
        } else {
            if (data.size() % 2 != 0) {
                count = data.size() / 2 + 1;
            } else {
                count = data.size() / 2;
            }
        }
        return count;
    }

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        if (showType == 0) {//列表
            ShopInfoBean shopInfoBean = (ShopInfoBean) data.get(position);
            GoodsListViewHolder goodsViewHolder = (GoodsListViewHolder) baseViewHolder;
            imageLoader.displayImage(shopInfoBean.getShopIcon(), goodsViewHolder.itemShopListView.searth_list_goods_img, MallAppliaction.getInstance().defaultOptions);
            goodsViewHolder.itemShopListView.searth_list_goods_name.setText(shopInfoBean.getShopName());
            goodsViewHolder.itemShopListView.searth_list_goods_price.setText("￥" + shopInfoBean.getShowPrice());
            goodsViewHolder.itemShopListView.searth_list_goods_num.setText("(" + shopInfoBean.getShopPjNum() + ")");
            goodsViewHolder.itemShopListView.searth_list_goods_score.setRating(shopInfoBean.getShopZhPj());
        } else if (showType == 1) {//方格
            GoodsListTowViewHolder goodsViewTowHolder = (GoodsListTowViewHolder) baseViewHolder;
            final ShopInfoBean shopInfoBean = (ShopInfoBean) data.get(position * 2);
            imageLoader.displayImage(shopInfoBean.getShopIcon(), goodsViewTowHolder.goods_list_left_icon_img, MallAppliaction.getInstance().defaultOptions);
            goodsViewTowHolder.goods_list_left_name_text.setText(shopInfoBean.getShopName());
            goodsViewTowHolder.goods_list_left_price_text.setText("￥" + shopInfoBean.getShowPrice());
            goodsViewTowHolder.goods_list_left_ly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getGoodsData(shopInfoBean.getShopId());
                }
            });
            if (position * 2 + 1 <= data.size() - 1) {
                goodsViewTowHolder.goods_list_right_ly.setVisibility(View.VISIBLE);
                final ShopInfoBean shopInfoBeanTow = (ShopInfoBean) data.get(position * 2 + 1);
                imageLoader.displayImage(shopInfoBeanTow.getShopIcon(), goodsViewTowHolder.goods_list_right_icon_img, MallAppliaction.getInstance().defaultOptions);
                goodsViewTowHolder.goods_list_right_name_text.setText(shopInfoBeanTow.getShopName());
                goodsViewTowHolder.goods_list_right_price_text.setText("￥" + shopInfoBeanTow.getShowPrice());
                goodsViewTowHolder.goods_list_right_ly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getGoodsData(shopInfoBeanTow.getShopId());
                    }
                });
            } else {
                goodsViewTowHolder.goods_list_right_ly.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转商品详情
        if (showType == 0) {
            getGoodsData(((ShopInfoBean) data.get(position)).getShopId());
        }
    }

    /**
     * 跳转商品详情
     *
     * @param shopId
     */
    private void getGoodsData(int shopId) {
        Bundle bundle = new Bundle();
        bundle.putInt(MallConstant.GOODSID, shopId);
        startActivity(GoodsContentActivity.class, bundle);
    }

    @Override
    protected void refresh() {
        super.refresh();
        pageNum = 1;
        getGoodsList();
    }

    @Override
    public void loadMore() {
        pageNum++;
        getGoodsList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_default_back://返回
                finish();
                break;
            case R.id.search_ly://搜索
                startActivity(SearthActivity.class);
                break;
            case R.id.goods_list_price_text:
                if (!SORT_COMMODITY_PRICE_ASC.equals(currentSort)) {
                    currentSort = SORT_COMMODITY_PRICE_ASC;
                    goods_list_price_text.setSelected(true);
                } else {
                    currentSort = SORT_COMMODITY_PRICE_DESC;
                    goods_list_price_text.setSelected(false);
                }
                getGoodsListRefesh();
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_GOODS_LIST://商品列表
                if (pageNum == 1) {
                    clearAll();

                }
                addAll(success.getHttpBean().getObjects());
                loadSuccess();
                break;

        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_GOODS_LIST://商品列表
                showLongToast(finals.getErrorMessage());
                break;
        }
    }

    /**
     * 刷新查询列表
     */
    private void getGoodsListRefesh() {
        showLoading();
        pageNum = 1;
        getGoodsList();
    }

    /**
     * 查询列表
     */
    private void getGoodsList() {
        Map<String, Object> params = RequestAction.GET_GOODS_LIST.params.getParams();
        params.put(MallConstant.SHOPINFO_TYPEID, shopTypeId);
        params.put(MallConstant.SHOPINFO_INDEX, currentSort);
        params.put(MallConstant.PAGE_NUM, pageNum);
        sendRequest(RequestAction.GET_GOODS_LIST);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.goods_list_price_text://根据价格排序
                //这里交给点击事件去处理
                break;
            case R.id.goods_list_new_text://根据最新排序
                currentSort = SORT_COMMODITY_PUB;
                getGoodsListRefesh();
                break;
            case R.id.goods_list_number_text://根据销量排序
                currentSort = SORT_COMMODITY_SALE;
                getGoodsListRefesh();
                break;
            case R.id.goods_list_comments_text://根据评价排序
                currentSort = SORT_COMMODITY_HPLV;
                getGoodsListRefesh();
                break;
        }
    }

    /**
     * 商品item
     */
    private class GoodsListViewHolder extends BaseViewHolder {
        private ItemGoodsListView itemShopListView;

        GoodsListViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            itemShopListView = new ItemGoodsListView(itemView);
        }
    }

    private class GoodsListTowViewHolder extends BaseViewHolder {
        private ImageView goods_list_left_icon_img, goods_list_right_icon_img;
        private TextView goods_list_left_name_text, goods_list_left_price_text, goods_list_right_name_text, goods_list_right_price_text;
        private LinearLayout goods_list_left_ly, goods_list_right_ly;

        GoodsListTowViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            goods_list_left_icon_img = (ImageView) itemView.findViewById(R.id.goods_list_left_icon_img);
            goods_list_right_icon_img = (ImageView) itemView.findViewById(R.id.goods_list_right_icon_img);
            goods_list_left_name_text = (TextView) itemView.findViewById(R.id.goods_list_left_name_text);
            goods_list_left_price_text = (TextView) itemView.findViewById(R.id.goods_list_left_price_text);
            goods_list_right_name_text = (TextView) itemView.findViewById(R.id.goods_list_right_name_text);
            goods_list_right_price_text = (TextView) itemView.findViewById(R.id.goods_list_right_price_text);
            goods_list_left_ly = (LinearLayout) itemView.findViewById(R.id.goods_list_left_ly);
            goods_list_right_ly = (LinearLayout) itemView.findViewById(R.id.goods_list_right_ly);
        }
    }

}
