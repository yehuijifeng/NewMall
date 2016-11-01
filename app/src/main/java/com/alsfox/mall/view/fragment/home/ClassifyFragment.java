package com.alsfox.mall.view.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.classify.ShopTypeBean;
import com.alsfox.mall.bean.classify.ShopTypeTowBean;
import com.alsfox.mall.db.classify.ClassifyDao;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.home.ClassifyPresenter;
import com.alsfox.mall.view.activity.searth.SearthActivity;
import com.alsfox.mall.view.customview.SearchTitleView;
import com.alsfox.mall.view.fragment.base.BaseGridFragment;
import com.alsfox.mall.view.interfaces.home.IClassifyView;

import java.util.ArrayList;
import java.util.List;

import static com.alsfox.mall.http.request.RequestAction.GET_CLASSIFY_DATA;

/**
 * Created by 浩 on 2016/10/22.
 * 分类
 */

public class ClassifyFragment extends BaseGridFragment<ClassifyPresenter> implements IClassifyView, View.OnClickListener {

    private SearchTitleView search_title_view;
    private RadioGroup classify_one_radio_group;
    private List<ShopTypeBean> shopTypeBeens;
    private ClassifyDao classifyDao;

    @Override
    protected ClassifyPresenter initPresenter() {
        return new ClassifyPresenter(this);
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        search_title_view = (SearchTitleView) parentView.findViewById(R.id.search_title_view);
        classify_one_radio_group = (RadioGroup) parentView.findViewById(R.id.classify_one_radio_group);
    }

    @Override
    protected void initData() {
        search_title_view.setSearchIconGone(true);//隐藏小图标
        showLoading("正在加载分类……");
        getClassifyOne();
        classifyDao = new ClassifyDao();
        search_title_view.setSearchClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SearthActivity.class);
            }
        });
    }

    @Override
    public boolean isLoadMore() {
        return false;
    }

    @Override
    public boolean isRefresh() {
        return false;
    }

    @Override
    protected void refresh() {
        getClassifyOne();
    }

    private void getClassifyOne() {
        sendRequest(GET_CLASSIFY_DATA);
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_CLASSIFY_DATA:
                shopTypeBeens = success.getHttpBean().getObjects();
                setClassifyOneData(shopTypeBeens);
                closeLoading();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        classifyDao.inster(shopTypeBeens);
                    }
                }).start();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        switch (finals.getRequestAction()) {
            case GET_CLASSIFY_DATA:
                closeLoading();
                showLongToast(finals.getErrorMessage());
                shopTypeBeens = classifyDao.queryClassify();
                if (shopTypeBeens != null) {
                    setClassifyOneData(shopTypeBeens);
                } else {
                    //无网络链接
                    showErrorLoadingAndBtn(getResources().getString(R.string.refresh_window), getResources().getString(R.string.settings_network), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toSetNetWork();//按钮是去设置网络
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showLoading();
                            refresh();//点击空白处是刷新
                        }
                    });
                }
                break;
        }
    }

    /**
     * 一级分类
     *
     * @param shopTypeBeens
     */
    private void setClassifyOneData(List<ShopTypeBean> shopTypeBeens) {
        if (shopTypeBeens.isEmpty()) return;
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        classify_one_radio_group.setOnCheckedChangeListener(new OnCheckListener());
        for (int i = 0; i < shopTypeBeens.size(); i++) {
            ShopTypeBean shopTypeBean = shopTypeBeens.get(i);
            RadioButton radioButton = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.item_classify_one, null);
            radioButton.setId(i);//当前分类id
            radioButton.setText(shopTypeBean.getTypeName());
            radioButton.setLayoutParams(layoutParams);
            classify_one_radio_group.addView(radioButton);
            if (i == 0) {
                radioButton.setChecked(true);
            }
        }
    }

    /**
     * 一级分类点击事件
     */
    private class OnCheckListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (shopTypeBeens == null || shopTypeBeens.isEmpty()) return;
            ShopTypeBean shopTypeBean = shopTypeBeens.get(checkedId);
            if (shopTypeBean != null && shopTypeBean.getSonShopTypeList() != null)
                setClassifyTowData(new ArrayList<>(shopTypeBean.getSonShopTypeList()));
            else {
                clearAll();
                notifyDataChange();
            }
        }
    }

    @Override
    public int getNumColumns() {
        return 3;
    }

    /**
     * 二级分类
     *
     * @param shopTypeBeens
     */
    private void setClassifyTowData(List<ShopTypeTowBean> shopTypeBeens) {
        clearAll();
        addAll(shopTypeBeens);
        loadSuccess();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
//        ShopTypeBean shopTypeBean = (ShopTypeBean) data.get(position);
//        Bundle bundle = new Bundle();
//        //分类id，查询分类列表
//        bundle.putInt(MallConstant.SHOPINFO_TYPEID, shopTypeBean.getTypeId());
//        startActivity(CommodityListActivity.class, bundle);
    }

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        ClassifyTowViewHolder viewHolder = (ClassifyTowViewHolder) baseViewHolder;
        ShopTypeTowBean shopTypeBean = (ShopTypeTowBean) data.get(position);
        viewHolder.classify_tow_text.setText(shopTypeBean.getTypeName());
        imageLoader.displayImage(shopTypeBean.getTypeIcon(), viewHolder.classify_tow_img, MallAppliaction.getInstance().defaultOptions);
    }

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        return new ClassifyTowViewHolder(itemView);
    }

    @Override
    public int getItemView(int position, int itemType) {
        return R.layout.item_classify_tow;
    }

    private class ClassifyTowViewHolder extends BaseViewHolder {

        private ImageView classify_tow_img;
        private TextView classify_tow_text;

        public ClassifyTowViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            classify_tow_img = (ImageView) itemView.findViewById(R.id.classify_tow_img);
            classify_tow_text = (TextView) itemView.findViewById(R.id.classify_tow_text);
        }
    }
}
