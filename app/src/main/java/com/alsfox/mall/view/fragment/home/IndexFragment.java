package com.alsfox.mall.view.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.index.IndexBean;
import com.alsfox.mall.bean.index.IndexDaohangBean;
import com.alsfox.mall.bean.index.IndexFlashShopBean;
import com.alsfox.mall.bean.index.IndexLunfanBean;
import com.alsfox.mall.bean.index.IndexMokuaiBean;
import com.alsfox.mall.bean.index.IndexMokuaiContentBean;
import com.alsfox.mall.bean.index.IndexQianggouBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.model.home.IndexModel;
import com.alsfox.mall.presenter.home.IndexPresenter;
import com.alsfox.mall.utils.DisplayUtils;
import com.alsfox.mall.utils.TimeThreadUtil;
import com.alsfox.mall.view.activity.goods.GoodsContentActivity;
import com.alsfox.mall.view.activity.goods.GoodsListActivity;
import com.alsfox.mall.view.activity.searth.SearthActivity;
import com.alsfox.mall.view.customview.SearchTitleView;
import com.alsfox.mall.view.customview.index.IndexHeaderYuanView;
import com.alsfox.mall.view.fragment.base.BaseListFragment;
import com.alsfox.mall.view.interfaces.home.IIndexView;
import com.take.turns.view.TakeTurnsView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 浩 on 2016/10/19.
 * 首页
 */

public class IndexFragment extends BaseListFragment<IndexPresenter> implements IIndexView, View.OnClickListener {

    private SearchTitleView search_title_view;

    private List<IndexMokuaiBean> indexData;

    //头view的控件
    private TakeTurnsView index_header_lunfan;
    private IndexHeaderYuanView index_header_ly, index_header_ly_tow;
    //限时抢购view
    private LinearLayout index_header_qianggou, flash_frame_ly;
    private TextView flash_text_d, flash_text_h, flash_text_m, flash_text_s;//天，时分秒
    private TextView flash_go_text;//更多
    private TextView after_day_text;//天数
    private MyCountDownTimer myCountDownTimer;//计时器
    private List<IndexFlashShopBean> indexQianggouInfoBeanCache = new ArrayList<>();

    @Override
    protected IndexPresenter initPresenter() {
        return new IndexPresenter(this, getWindowWidth());
    }

    @Override
    protected int setFragmentViewContent() {
        return R.layout.fragment_index;
    }

    @Override
    protected int setHeaderView() {
        return R.layout.layout_index_header;
    }

    @Override
    protected void getHeaderView(View view) {
        index_header_lunfan = (TakeTurnsView) view.findViewById(R.id.index_header_lunfan);
        index_header_ly = (IndexHeaderYuanView) view.findViewById(R.id.index_header_ly);
        index_header_ly_tow = (IndexHeaderYuanView) view.findViewById(R.id.index_header_ly_tow);
        index_header_qianggou = (LinearLayout) view.findViewById(R.id.index_header_qianggou);
        flash_frame_ly = (LinearLayout) view.findViewById(R.id.flash_frame_ly);
        flash_text_d = (TextView) view.findViewById(R.id.flash_text_d);
        flash_text_h = (TextView) view.findViewById(R.id.flash_text_h);
        flash_text_m = (TextView) view.findViewById(R.id.flash_text_m);
        flash_text_s = (TextView) view.findViewById(R.id.flash_text_s);
        flash_go_text = (TextView) view.findViewById(R.id.flash_go_text);
        after_day_text = (TextView) view.findViewById(R.id.after_day_text);
        index_header_qianggou.setOnClickListener(this);
        flash_go_text.setOnClickListener(this);
    }

    /**
     * 添加限时抢购数据
     *
     * @param indexQianggouInfoBean
     */
    private void getFlashSale(IndexQianggouBean indexQianggouInfoBean) {
        //判断是否有限时抢购，没有则隐藏
        if (indexQianggouInfoBean == null || indexQianggouInfoBean.getShopInfoList().isEmpty()) {
            index_header_qianggou.setVisibility(View.GONE);
            return;
        } else {
            index_header_qianggou.setVisibility(View.VISIBLE);
        }
        if (myCountDownTimer == null) {
            myCountDownTimer = new MyCountDownTimer(TimeThreadUtil.timeMS(indexQianggouInfoBean.getEndTime(), indexQianggouInfoBean.getNowTime()), 1000);
            myCountDownTimer.start();
        }
        //判断限时抢购数据缓存是否为null
        if (indexQianggouInfoBeanCache.size() < 1)
            indexQianggouInfoBeanCache.addAll(indexQianggouInfoBean.getShopInfoList());
        else if (!compare(indexQianggouInfoBean.getShopInfoList(), indexQianggouInfoBeanCache))
            return;
        else {
            indexQianggouInfoBeanCache.clear();
            indexQianggouInfoBeanCache.addAll(indexQianggouInfoBean.getShopInfoList());
            myCountDownTimer.cancel();
            myCountDownTimer = new MyCountDownTimer(TimeThreadUtil.timeMS(indexQianggouInfoBean.getEndTime(), indexQianggouInfoBean.getNowTime()), 1000);
            myCountDownTimer.start();
        }
        flash_frame_ly.removeAllViews();
        for (IndexFlashShopBean indexFlashShopInfoBean : indexQianggouInfoBean.getShopInfoList()) {

            flash_frame_ly.addView(getFlashSaleView(indexFlashShopInfoBean.getShopIcon(), "￥" + indexFlashShopInfoBean.getShowPrice()));
        }
        if (indexQianggouInfoBean.getShopInfoList().size() < 3) {
            ViewGroup.LayoutParams layoutParams = flash_frame_ly.getLayoutParams();
            layoutParams.height = (int) (getWindowWidth() / 2);
            flash_frame_ly.setLayoutParams(layoutParams);
        } else {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            flash_frame_ly.setLayoutParams(layoutParams);
        }

    }

    /**
     * 队列比较
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean compare(List a, List b) {
        if (a.size() != b.size())
            return false;
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

    /**
     * 动态添加限时抢购模块
     *
     * @param imgUrl
     * @param money
     */
    private View getFlashSaleView(String imgUrl, CharSequence money) {
        //frameLayout
        FrameLayout frameLayout = new FrameLayout(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        layoutParams.rightMargin = DisplayUtils.dip2px(getActivity(), 3);
        frameLayout.setLayoutParams(layoutParams);
        //imageview
        ImageView imageView = new ImageView(getActivity());
        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams1.gravity = Gravity.CENTER_HORIZONTAL;
        imageView.setLayoutParams(layoutParams1);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageLoader.displayImage(imgUrl, imageView, MallAppliaction.getInstance().defaultOptions);
        //textview
        TextView textView = new TextView(getActivity());
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.gravity = Gravity.BOTTOM;
        textView.setLayoutParams(layoutParams2);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setBackgroundResource(R.color.transparent_flash);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setText(money);
        //添加view
        frameLayout.addView(imageView);
        frameLayout.addView(textView);
        return frameLayout;
    }

    /**
     * 计时器
     * 继承 CountDownTimer 防范
     * <p>
     * 重写 父类的方法 onTick() 、 onFinish()
     */

    private class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          <p>
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            flash_text_d.setText("00");
            flash_text_h.setText("00");
            flash_text_m.setText("00");
            flash_text_s.setText("00");
            refresh();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long time[] = TimeThreadUtil.timeDown(millisUntilFinished);
            if (time[0] == 0) {
                after_day_text.setVisibility(View.GONE);
                flash_text_d.setVisibility(View.GONE);
            } else {
                after_day_text.setVisibility(View.VISIBLE);
                flash_text_d.setVisibility(View.VISIBLE);
                flash_text_d.setText(TimeThreadUtil.integerByStr(time[0]));
            }
            flash_text_h.setText(TimeThreadUtil.integerByStr(time[1]));
            flash_text_m.setText(TimeThreadUtil.integerByStr(time[2]));
            flash_text_s.setText(TimeThreadUtil.integerByStr(time[3]));
        }
    }

    private int[] headerStrs = new int[]{R.string.str_notice, R.string.str_logistics_query, R.string.str_user_order, R.string.str_user_collect};//圆形图标的四个默认按钮
    private int[] headerIcons = new int[]{R.drawable.ic_notice, R.drawable.ic_wuliu, R.drawable.ic_my_order, R.drawable.ic_collection};//圆形图标的四个默认按钮图片资源

    /**
     * 圆形图标默认数据
     */
    private void getDaohangList() {
        List<IndexDaohangBean> indexDaohangBeans = new ArrayList<>();
        for (int i = 0; i < headerStrs.length; i++) {
            IndexDaohangBean indexDaohangBean = new IndexDaohangBean();
            indexDaohangBean.setIndexs(i);
            indexDaohangBean.setNavName(getResources().getString(headerStrs[i]));
            indexDaohangBean.setShowImgRes(headerIcons[i]);
            indexDaohangBeans.add(indexDaohangBean);
        }
        if (index_header_ly == null) return;
        index_header_ly.setOnHeaderViewClick(new IndexHeaderYuanView.OnHeaderViewClick() {
            @Override
            public void onItemClickData(int position) {
                headerItemClick(position);
            }
        });
        index_header_ly.getDataList(indexDaohangBeans);
    }

    /**
     * 圆形图标本地点击事件
     */
    private void headerItemClick(int position) {
        switch (position) {
            case 0://公告
                //startActivity(NoticeListActivity.class);
                break;
            case 1://物流信息
//                if (isLogin()) {
//                    Bundle bundle=new Bundle();
//                    bundle.putInt("currentItem", 2);
//                    startActivity(OrderListActivity.class, bundle);
//                }
                break;
            case 2://订单列表
//                if (isLogin()) {
//                    startActivity(OrderListActivity.class);
//                }
                break;
            case 3://我的收藏
//                if (isLogin()) {
//                    startActivity(UserCommodityCollectActivity.class);
//                }
                break;
        }
    }

    /**
     * 圆形图标网络点击事件
     *
     * @param indexDaohangInfoBeans
     */
    private void getHeaderDaoHangUrlList(final List<IndexDaohangBean> indexDaohangInfoBeans) {
        if (index_header_ly_tow == null) return;
        index_header_ly_tow.setOnHeaderViewClick(new IndexHeaderYuanView.OnHeaderViewClick() {
            @Override
            public void onItemClickData(int position) {
                IndexDaohangBean indexDaohangInfoBean = indexDaohangInfoBeans.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt(MallConstant.SHOPTYPEID, indexDaohangInfoBean.getShopTypeId());
                startActivity(GoodsListActivity.class, bundle);
            }
        });
        index_header_ly_tow.getUrlDataList(indexDaohangInfoBeans);
    }

    private float downY;

    /**
     * 轮番图
     *
     * @param indexLunfanContentList
     */
    private void getLunFanView(final List<IndexLunfanBean> indexLunfanContentList) {
        if (indexLunfanContentList.isEmpty()) return;
        List<String> imageUrls = new ArrayList<>();
        for (IndexLunfanBean indexLunfanInfoBean : indexLunfanContentList) {
            imageUrls.add(indexLunfanInfoBean.getShowImg());
        }
        index_header_lunfan.setTakeTurnsHeight((int) (getWindowWidth() / 2.28));
        index_header_lunfan.setSleepTime(4 * 1000);//轮番的循环时间
        index_header_lunfan.setViewpagerScrollTime(300);//轮番滑动速率
        index_header_lunfan.setImageUrls(imageUrls);
        index_header_lunfan.setUpdateUI(new TakeTurnsView.UpdateUI() {
            @Override
            public void onUpdateUI(int position, ImageView imageView, String imgUrl) {
                imageLoader.displayImage(imgUrl, imageView, MallAppliaction.getInstance().defaultOptions);
            }

            @Override
            public void onItemClick(int position, ImageView imageView) {
                getHeaderStartActivity(indexLunfanContentList.get(position).getLunfanType(), indexLunfanContentList.get(position).getFkId());
            }
        });
        index_header_lunfan.setTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = Math.abs(event.getRawY()) - downY;
                        if (moveY > 10f) {
                            setRefresh(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        setRefresh(true);
                        return false;
                }
                return false;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (index_header_lunfan != null)
            index_header_lunfan.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (index_header_lunfan != null)
            index_header_lunfan.onPause();
    }


    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        search_title_view = (SearchTitleView) parentView.findViewById(R.id.search_title_view);
        search_title_view.setSearchIconGone(false);

    }

    @Override
    protected void initData() {
        showLoading("正在加载首页数据……");
        presenter.getIndexData();
        getDaohangList();//默认圆形图标数据
        search_title_view.setSearchClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SearthActivity.class);
            }
        });
    }


    @Override
    protected void refresh() {
        presenter.getIndexData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
            myCountDownTimer = null;
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_INDEX_DATA:
                final IndexBean indexInfoBean = (IndexBean) success.getHttpBean().getObject();
                indexData = new ArrayList<>(indexInfoBean.getIndexMoudleList());
                clearAll();
                getLunFanView(new ArrayList<>(indexInfoBean.getIndexLunfanContentList()));//轮番图
                getHeaderDaoHangUrlList(new ArrayList<>(indexInfoBean.getIndexNavList()));//圆形图片
                getFlashSale(indexInfoBean.getShopTimeOut());//限时抢购
                addAll(indexData);
                loadSuccess();

                //缓存数据库
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.setIndexDataByDb(indexInfoBean);
                    }
                }).start();
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        //super.onRequestFinal(finals);
        switch (finals.getRequestAction()) {
            case GET_INDEX_DATA:
                loadSuccess();
                presenter.getIndexDataByDb();
                showLongToast(finals.getErrorMessage());
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        IndexMokuaiBean indexMokuaiInfoBean = indexData.get(position);
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
        IndexMokuaiBean indexMokuaiInfoBean = indexData.get(position);
        return indexMokuaiInfoBean.getMoudleType();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.index_header_qianggou://点击进入限时抢购

                break;
            case R.id.flash_go_text://点击进入限时抢购

                break;
        }
    }

    /**
     * 点击每一张图片的事件
     *
     * @param v
     * @param moduleContent
     */
    @Override
    public void onItemImgClick(View v, IndexMokuaiContentBean moduleContent) {
        getHeaderStartActivity(moduleContent.getContentType(), moduleContent.getFkId());
    }

    /**
     * 拿到数据库的缓存
     *
     * @param indexBean
     */
    @Override
    public void getIndexDataByDb(IndexBean indexBean) {
        if (indexBean == null) {
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
        } else {
            indexData = new ArrayList<>(indexBean.getIndexMoudleList());
            clearAll();
            getLunFanView(new ArrayList<>(indexBean.getIndexLunfanContentList()));//轮番图
            getHeaderDaoHangUrlList(new ArrayList<>(indexBean.getIndexNavList()));//圆形图片
            getFlashSale(indexBean.getShopTimeOut());//限时抢购
            addAll(indexData);
            loadSuccess();
        }
    }


    /**
     * 轮番图和首页图片的点击事件
     *
     * @param type
     * @param fkId
     */
    private void getHeaderStartActivity(int type, int fkId) {
        Bundle bundle = new Bundle();
        Intent intent = null;
        switch (type) {
            case 0://商品信息
                bundle.putInt(MallConstant.GOODSID, fkId);
                intent = new Intent(getActivity(), GoodsContentActivity.class);
                break;
            case 1://商品分类
                bundle.putInt(MallConstant.SHOPINFO_TYPEID, fkId);
                //intent = new Intent(getActivity(), CommodityListActivity.class);
                showShortToast("商品分类");
                break;
            case 2://公告
                bundle.putInt(MallConstant.INFORMATION_INFORMATIONID, fkId);
                //intent = new Intent(getActivity(), CommodityListActivity.class);
                showShortToast("公告");
                break;
        }
        if (intent == null) return;
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
