package com.alsfox.mall.view.activity.searth;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.merchant.MerchantInfoBean;
import com.alsfox.mall.bean.searth.HotWordBean;
import com.alsfox.mall.bean.shop.ShopInfoBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.StatusCode;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.popupwindow.SearthPopupWindow;
import com.alsfox.mall.presenter.searth.SearthPresenter;
import com.alsfox.mall.utils.DisplayUtils;
import com.alsfox.mall.view.activity.base.BaseListActivity;
import com.alsfox.mall.view.customview.FlowLayout;
import com.alsfox.mall.view.customview.shop.ItemShopListView;
import com.alsfox.mall.view.interfaces.searth.ISearthView;

import java.util.List;
import java.util.Map;

import static com.alsfox.mall.http.request.RequestAction.GET_SEARCH_GOODS_LIST;
import static com.alsfox.mall.http.request.RequestAction.GET_SEARCH_HOT_WORDS;
import static com.alsfox.mall.http.request.RequestAction.GET_SEARCH_MERCHANT_LIST;

/**
 * Created by 浩 on 2016/10/28.
 * 搜索商品和店铺
 */

public class SearthActivity extends BaseListActivity<SearthPresenter> implements ISearthView, View.OnClickListener {

    private ImageView title_default_back, search_img;
    private TextView search_text;//选择是搜索商品还是搜索店铺
    private EditText title_searth_edit;//输入搜索内容
    private Button title_searth_btn;//点击搜索
    private FlowLayout title_flow_layout;//流布局

    private int searthType = 1;//1,商品；2，店铺

    private SearthPopupWindow searthPopupWindow;//选择类型的popupwindow

    private String searthName;//搜索的关键字
    private int pageNum = 1;//分页

    @Override
    protected SearthPresenter initPresenter() {
        return new SearthPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_searth;
    }

    @Override
    protected String setTitleText() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        title_default_back = (ImageView) findViewById(R.id.title_default_back);//返回上个页面
        search_img = (ImageView) findViewById(R.id.search_img);//选择是搜索商品还是搜索店铺
        search_text = (TextView) findViewById(R.id.search_text); //选择是搜索商品还是搜索店铺
        title_searth_edit = (EditText) findViewById(R.id.title_searth_edit); //输入搜索内容
        title_searth_btn = (Button) findViewById(R.id.title_searth_btn); //点击搜索
        title_flow_layout = (FlowLayout) findViewById(R.id.title_flow_layout); //流布局
        search_img.setOnClickListener(this);
        search_text.setOnClickListener(this);
        title_searth_btn.setOnClickListener(this);
        title_default_back.setOnClickListener(this);
        searthPopupWindow = new SearthPopupWindow(this);
    }

    @Override
    protected void initData() {
        setRefresh(false);
        getsearthHot();
    }

    @Override
    protected void refresh() {
        super.refresh();
        pageNum = 1;
        getSearthData();
    }

    @Override
    public void loadMore() {
        super.loadMore();
        pageNum++;
        getSearthData();
    }

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        if (searthType == 1) {//商品
            ShopInfoBean shopInfoBean = (ShopInfoBean) data.get(position);
            GoodsViewHolder goodsViewHolder = (GoodsViewHolder) baseViewHolder;
            imageLoader.displayImage(shopInfoBean.getShopIcon(), goodsViewHolder.itemShopListView.searth_list_goods_img, MallAppliaction.getInstance().defaultOptions);
            goodsViewHolder.itemShopListView.searth_list_goods_name.setText(shopInfoBean.getShopName());
            goodsViewHolder.itemShopListView.searth_list_goods_price.setText("￥" + shopInfoBean.getShowPrice());
            goodsViewHolder.itemShopListView.searth_list_goods_num.setText("(" + shopInfoBean.getShopPjNum() + ")");
            goodsViewHolder.itemShopListView.searth_list_goods_score.setRating(shopInfoBean.getShopZhPj());
        } else if (searthType == 2) {//商铺

        }
    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        if (searthType == 1) {//商品
            return new GoodsViewHolder(itemView);
        } else if (searthType == 2) {//商铺
            return new ShopsViewHolder(itemView);
        } else {
            return null;
        }
    }

    @Override
    public int getItemView(int position, int itemType) {
        if (searthType == 1) {//商品
            return R.layout.layout_searth_list;
        } else if (searthType == 2) {//商铺
            return 0;
            //return R.layout.layout_searth_merchant;
        } else {
            return 0;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_img://选择搜索类型
            case R.id.search_text://选择搜索类型
                search_img.setRotation(180);//图片旋转
                searthPopupWindow.showAtLocation(search_text, new SearthPopupWindow.OnDismiss() {
                    @Override
                    public void OnDismissClick(int i) {
                        searthType = i;
                        search_text.setText(searthType == 1 ? getResources().getString(R.string.str_goods) : getResources().getString(R.string.str_shops));
                        search_img.setRotation(0);//图片旋转
                    }
                });
                searthPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        search_img.setRotation(0);//图片旋转
                    }
                });
                break;
            case R.id.title_searth_btn://点击进行搜索
                searthName = title_searth_edit.getText().toString().trim();
                pageNum = 1;
                getSearthDataView();
                break;
            case R.id.title_default_back://返回
                finish();
                break;
        }
    }

    /**
     * 搜索热门关键词
     */
    private void getsearthHot() {
        sendRequest(GET_SEARCH_HOT_WORDS);
    }


    /**
     * 搜索
     */
    private void getSearthDataView() {
        if (TextUtils.isEmpty(searthName)) return;
        showLoading("正在搜索，请稍后……");
        getSearthData();
    }

    private void getSearthData() {
        if (searthType == 1) {//商品
            getSearchGoodsList();
        } else if (searthType == 2) {//店铺
            getSearchMerchantList();
        }
    }

    /**
     * 商品
     */
    private void getSearchGoodsList() {
        Map<String, Object> parameters = GET_SEARCH_GOODS_LIST.params.getParams();
        parameters.put(MallConstant.PAGE_NUM, pageNum);
        parameters.put(MallConstant.SHOPINFO_SHOPNAME, searthName);
        sendRequest(GET_SEARCH_GOODS_LIST);
    }

    /**
     * 店铺
     */
    private void getSearchMerchantList() {
        Map<String, Object> parameters = GET_SEARCH_MERCHANT_LIST.params.getParams();
        parameters.put(MallConstant.PAGE_NUM, pageNum);
        parameters.put("dianpuInfo.dianpuName", searthName);
        parameters.put("dianpuInfo.dianpuLon", MallAppliaction.getInstance().baiduMapBean.getLontitude());
        parameters.put("dianpuInfo.dianpuLat", MallAppliaction.getInstance().baiduMapBean.getLatitude());
        sendRequest(GET_SEARCH_MERCHANT_LIST);
    }


    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_SEARCH_HOT_WORDS://关键词搜索
                List<HotWordBean> hotWords = success.getHttpBean().getObjects();
                initSearchHotWords(hotWords);
                break;
            case GET_SEARCH_GOODS_LIST://商品
                List<ShopInfoBean> shopInfoBeans = success.getHttpBean().getObjects();
                if (pageNum == 1)
                    clearAll();
                addAll(shopInfoBeans);
                loadSuccess();
                setRefresh(true);
                title_flow_layout.removeAllViews();
                break;
            case GET_SEARCH_MERCHANT_LIST://商铺
                List<MerchantInfoBean> merchantInfoBeens = success.getHttpBean().getObjects();
                if (pageNum == 1)
                    clearAll();
                addAll(merchantInfoBeens);
                loadSuccess();
                setRefresh(true);
                title_flow_layout.removeAllViews();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_SEARCH_HOT_WORDS:
                break;
            case GET_SEARCH_GOODS_LIST://商品
            case GET_SEARCH_MERCHANT_LIST://商铺
                if (finals.getRequestCode() == StatusCode.NOT_MORE_DATA) {
                    if (pageNum == 1) {
                        showErrorLoadingByDefaultClick(finals.getErrorMessage() + " 点击刷新");
                    } else {

                    }
                }
                break;
        }
    }

    /**
     * 热门标签添加到流布局中
     *
     * @param hotWordBeeans
     */
    private void initSearchHotWords(List<HotWordBean> hotWordBeeans) {
        if (hotWordBeeans == null || hotWordBeeans.size() < 1) return;
        for (HotWordBean hotWord : hotWordBeeans) {
            final TextView tv_hot_word = new TextView(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv_hot_word.setLayoutParams(layoutParams);
            tv_hot_word.setBackgroundResource(R.drawable.bg_search_ly);
            tv_hot_word.setPadding(DisplayUtils.dip2px(this, 5), DisplayUtils.dip2px(this, 5), DisplayUtils.dip2px(this, 5), DisplayUtils.dip2px(this, 5));
            tv_hot_word.setTextSize(DisplayUtils.sp2px(this, 5));
            tv_hot_word.setText(hotWord.getHotName());
            tv_hot_word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    title_searth_edit.setText(tv_hot_word.getText());
                    searthName = tv_hot_word.getText().toString().trim();
                    getSearthDataView();
                }
            });
            title_flow_layout.addView(tv_hot_word);
        }
    }

    /**
     * 商品item
     */
    private class GoodsViewHolder extends BaseViewHolder {
        private ItemShopListView itemShopListView;

        public GoodsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            itemShopListView = new ItemShopListView(itemView);
        }
    }

    /**
     * 商铺item
     */
    private class ShopsViewHolder extends BaseViewHolder {

        public ShopsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {

        }
    }
}
