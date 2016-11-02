package com.alsfox.mall.view.activity.goods;


import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.shop.ShopInfoBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.goods.ShopListPresenter;
import com.alsfox.mall.view.activity.base.BaseListActivity;
import com.alsfox.mall.view.activity.searth.SearthActivity;
import com.alsfox.mall.view.customview.shop.ItemShopListView;
import com.alsfox.mall.view.interfaces.goods.IShopListView;

import java.util.List;
import java.util.Map;

/**
 * Created by 浩 on 2016/11/2.
 * 商品列表
 */

public class GoodsListActivity extends BaseListActivity<ShopListPresenter> implements IShopListView, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ImageView title_default_back;
    private CheckBox title_default_right_check;
    private LinearLayout search_ly;
    private RadioButton goods_list_new_text, goods_list_number_text, goods_list_comments_text, goods_list_price_text;
    //private TintableImageView goods_list_price_img;
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
    protected ShopListPresenter initPresenter() {
        return new ShopListPresenter(this);
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
        //goods_list_price_img = (TintableImageView) findViewById(R.id.goods_list_price_img);
        title_default_back.setOnClickListener(this);
        search_ly.setOnClickListener(this);
        goods_list_new_text.setChecked(true);
        goods_tab_rg.setOnCheckedChangeListener(this);
        goods_list_new_text.setOnClickListener(this);
        goods_list_number_text.setOnClickListener(this);
        goods_list_comments_text.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        shopTypeId = getInt(MallConstant.SHOPTYPEID, 0);
        showType = title_default_right_check.isChecked() ? 1 : 0;
        title_default_right_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showType = isChecked ? 1 : 0;
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
            return null;
            //return R.layout.layout_searth_merchant;
        } else {
            return null;
        }
    }

    @Override
    public int getItemView(int position, int itemType) {
        if (showType == 0) {//列表
            return R.layout.layout_searth_list;
        } else if (showType == 1) {//方块
            return 0;
            //return R.layout.layout_searth_merchant;
        } else {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        ShopInfoBean shopInfoBean = (ShopInfoBean) data.get(position);
        if (showType == 0) {//列表
            GoodsListViewHolder goodsViewHolder = (GoodsListViewHolder) baseViewHolder;
            imageLoader.displayImage(shopInfoBean.getShopIcon(), goodsViewHolder.itemShopListView.searth_list_goods_img, MallAppliaction.getInstance().defaultOptions);
            goodsViewHolder.itemShopListView.searth_list_goods_name.setText(shopInfoBean.getShopName());
            goodsViewHolder.itemShopListView.searth_list_goods_price.setText("￥" + shopInfoBean.getShowPrice());
            goodsViewHolder.itemShopListView.searth_list_goods_num.setText("(" + shopInfoBean.getShopPjNum() + ")");
            goodsViewHolder.itemShopListView.searth_list_goods_score.setRating(shopInfoBean.getShopZhPj());
        } else if (showType == 1) {//方格

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转商品详情
    }

    @Override
    protected void refresh() {
        super.refresh();
        pageNum = 1;
        getGoodsList();
    }

    @Override
    public void loadMore() {
        super.loadMore();
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
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_GOODS_LIST://商品列表
                if (pageNum == 1)
                    clearAll();
                List<ShopInfoBean> shopInfoBeans = success.getHttpBean().getObjects();
                addAll(shopInfoBeans);
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
                if (!SORT_COMMODITY_PRICE_ASC.equals(currentSort)) {
                    currentSort = SORT_COMMODITY_PRICE_ASC;
                    //goods_list_price_img.setImageResource(R.drawable.ic_sort_asc);
                } else {
                    currentSort = SORT_COMMODITY_PRICE_DESC;
                    //goods_list_price_img.setImageResource(R.drawable.ic_sort_desc);
                }
                getGoodsListRefesh();
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
        private ItemShopListView itemShopListView;

        GoodsListViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            itemShopListView = new ItemShopListView(itemView);
        }
    }
}
