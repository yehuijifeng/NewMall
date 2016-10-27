package com.alsfox.mall.model.home;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.index.IndexBean;
import com.alsfox.mall.bean.index.IndexMokuaiBean;
import com.alsfox.mall.bean.index.IndexMokuaiContentBean;
import com.alsfox.mall.db.index.IndexDao;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.request.RetrofitManage;
import com.alsfox.mall.model.base.BaseModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浩 on 2016/10/19.
 * 首页model
 */

public class IndexModel extends BaseModel {
    public static final int INDEX_ITEM_ONE = 0;//0:一排三;
    public static final int INDEX_ITEM_TOW = INDEX_ITEM_ONE + 1;//1：├型；
    public static final int INDEX_ITEM_THREE = INDEX_ITEM_TOW + 1;//2：田型；
    public static final int INDEX_ITEM_FOUR = INDEX_ITEM_THREE + 1;//3：┤型；
    public static final int INDEX_ITEM_FIVE = INDEX_ITEM_FOUR + 1;//4：┬型；
    public static final int INDEX_ITEM_SEX = INDEX_ITEM_FIVE + 1;//5：┴型；
    public static final int INDEX_ITEM_SEVEN = INDEX_ITEM_SEX + 1;//6：一型；

    private int windowWidth;//当前手机屏幕的宽度
    private ImageLoader imageLoader = ImageLoader.getInstance();//imageloader图片加载
    private IndexDao indexDao;//操作数据库

    public IndexModel(int windowWidth) {
        this.windowWidth = windowWidth;
        indexDao = new IndexDao();
    }

    /**
     * 获取首页数据
     */
    public void getIndexData() {
        RetrofitManage.getInstance().sendRequest(RequestAction.GET_INDEX_DATA);
    }

    /**
     * 将首页数据添加到数据库
     *
     * @param indexData
     * @return
     */
    public int setIndexDataByDb(IndexBean indexData) {
        return indexDao.insert(indexData);
    }

    /**
     * 从数据库查询出首页数据
     *
     * @return
     */
    public IndexBean getIndexDataByDb() {
        return indexDao.select();
    }

    /**
     * listview 的item会调用该方法
     *
     * @param position
     * @param baseViewHolder
     * @param itemType
     * @param indexMokuaiInfoBean
     */
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType, IndexMokuaiBean indexMokuaiInfoBean) {
        if (indexMokuaiInfoBean == null || indexMokuaiInfoBean.getIndexMoudleContentList() == null || indexMokuaiInfoBean.getIndexMoudleContentList().isEmpty())
            return;
        List<IndexMokuaiContentBean> mokuaiContentInfoBeens = new ArrayList<>(indexMokuaiInfoBean.getIndexMoudleContentList());
        if (mokuaiContentInfoBeens.isEmpty()) return;

        switch (itemType) {
            case IndexModel.INDEX_ITEM_ONE://三行一排
                OneViewHolder oneViewHolder = (OneViewHolder) baseViewHolder;
                if (indexMokuaiInfoBean.getIsShowName() == -1 || TextUtils.isEmpty(indexMokuaiInfoBean.getMoudleName())) {//不显示模块名称
                    oneViewHolder.index_item_title.setVisibility(View.GONE);
                } else {
                    oneViewHolder.index_item_title.setVisibility(View.VISIBLE);
                    oneViewHolder.index_item_text.setText(indexMokuaiInfoBean.getMoudleName());
                }
                for (int i = 0; i < mokuaiContentInfoBeens.size(); i++) {
                    IndexMokuaiContentBean moduleContent = mokuaiContentInfoBeens.get(i);
                    ImageView imageView;
                    if (i == 0) {
                        imageView = oneViewHolder.one_img;
                    } else if (i == 1) {
                        imageView = oneViewHolder.tow_img;
                    } else if (i == 2) {
                        imageView = oneViewHolder.three_img;
                    } else {
                        continue;
                    }
                    imageLoader.displayImage(moduleContent.getShowImg(), imageView, MallAppliaction.getInstance().defaultOptions);
                    imageView.setOnClickListener(new ItemClickListener(moduleContent));
                }
                break;
            case IndexModel.INDEX_ITEM_TOW://1：├型；
                TowViewHolder towViewHolder = (TowViewHolder) baseViewHolder;
                if (indexMokuaiInfoBean.getIsShowName() == -1 || TextUtils.isEmpty(indexMokuaiInfoBean.getMoudleName())) {//不显示模块名称
                    towViewHolder.index_item_title.setVisibility(View.GONE);
                } else {
                    towViewHolder.index_item_title.setVisibility(View.VISIBLE);
                    towViewHolder.index_item_text.setText(indexMokuaiInfoBean.getMoudleName());
                }
                for (int i = 0; i < mokuaiContentInfoBeens.size(); i++) {
                    IndexMokuaiContentBean moduleContent = mokuaiContentInfoBeens.get(i);
                    ImageView imageView;
                    if (i == 0) {
                        imageView = towViewHolder.one_img;
                    } else if (i == 1) {
                        imageView = towViewHolder.tow_img;
                    } else if (i == 2) {
                        imageView = towViewHolder.three_img;
                    } else {
                        continue;
                    }
                    imageLoader.displayImage(moduleContent.getShowImg(), imageView, MallAppliaction.getInstance().defaultOptions);
                    imageView.setOnClickListener(new ItemClickListener(moduleContent));
                }
                break;
            case IndexModel.INDEX_ITEM_THREE://2：田型；
                ThreeViewHolder threeViewHolder = (ThreeViewHolder) baseViewHolder;
                if (indexMokuaiInfoBean.getIsShowName() == -1 || TextUtils.isEmpty(indexMokuaiInfoBean.getMoudleName())) {//不显示模块名称
                    threeViewHolder.index_item_title.setVisibility(View.GONE);
                } else {
                    threeViewHolder.index_item_title.setVisibility(View.VISIBLE);
                    threeViewHolder.index_item_text.setText(indexMokuaiInfoBean.getMoudleName());
                }
                for (int i = 0; i < mokuaiContentInfoBeens.size(); i++) {
                    IndexMokuaiContentBean moduleContent = mokuaiContentInfoBeens.get(i);
                    ImageView imageView;
                    if (i == 0) {
                        imageView = threeViewHolder.one_img;
                    } else if (i == 1) {
                        imageView = threeViewHolder.tow_img;
                    } else if (i == 2) {
                        imageView = threeViewHolder.three_img;
                    } else if (i == 3) {
                        imageView = threeViewHolder.four_img;
                    } else {
                        continue;
                    }
                    imageLoader.displayImage(moduleContent.getShowImg(), imageView, MallAppliaction.getInstance().defaultOptions);
                    imageView.setOnClickListener(new ItemClickListener(moduleContent));
                }
                break;
            case IndexModel.INDEX_ITEM_FOUR://3：┤型；
                FourViewHolder fourViewHolder = (FourViewHolder) baseViewHolder;
                if (indexMokuaiInfoBean.getIsShowName() == -1 || TextUtils.isEmpty(indexMokuaiInfoBean.getMoudleName())) {//不显示模块名称
                    fourViewHolder.index_item_title.setVisibility(View.GONE);
                } else {
                    fourViewHolder.index_item_title.setVisibility(View.VISIBLE);
                    fourViewHolder.index_item_text.setText(indexMokuaiInfoBean.getMoudleName());
                }
                for (int i = 0; i < mokuaiContentInfoBeens.size(); i++) {
                    IndexMokuaiContentBean moduleContent = mokuaiContentInfoBeens.get(i);
                    ImageView imageView;
                    if (i == 0) {
                        imageView = fourViewHolder.one_img;
                    } else if (i == 1) {
                        imageView = fourViewHolder.tow_img;
                    } else if (i == 2) {
                        imageView = fourViewHolder.three_img;
                    } else {
                        continue;
                    }
                    imageLoader.displayImage(moduleContent.getShowImg(), imageView, MallAppliaction.getInstance().defaultOptions);
                    imageView.setOnClickListener(new ItemClickListener(moduleContent));
                }
                break;
            case IndexModel.INDEX_ITEM_FIVE://4：┬型；
                FiveViewHolder fiveViewHolder = (FiveViewHolder) baseViewHolder;
                if (indexMokuaiInfoBean.getIsShowName() == -1 || TextUtils.isEmpty(indexMokuaiInfoBean.getMoudleName())) {//不显示模块名称
                    fiveViewHolder.index_item_title.setVisibility(View.GONE);
                } else {
                    fiveViewHolder.index_item_title.setVisibility(View.VISIBLE);
                    fiveViewHolder.index_item_text.setText(indexMokuaiInfoBean.getMoudleName());
                }
                for (int i = 0; i < mokuaiContentInfoBeens.size(); i++) {
                    IndexMokuaiContentBean moduleContent = mokuaiContentInfoBeens.get(i);
                    ImageView imageView;
                    if (i == 0) {
                        imageView = fiveViewHolder.one_img;
                    } else if (i == 1) {
                        imageView = fiveViewHolder.tow_img;
                    } else if (i == 2) {
                        imageView = fiveViewHolder.three_img;
                    } else {
                        continue;
                    }
                    imageLoader.displayImage(moduleContent.getShowImg(), imageView, MallAppliaction.getInstance().defaultOptions);
                    imageView.setOnClickListener(new ItemClickListener(moduleContent));
                }
                break;
            case IndexModel.INDEX_ITEM_SEX://5：┴型；
                SexViewHolder sexViewHolder = (SexViewHolder) baseViewHolder;
                if (indexMokuaiInfoBean.getIsShowName() == -1 || TextUtils.isEmpty(indexMokuaiInfoBean.getMoudleName())) {//不显示模块名称
                    sexViewHolder.index_item_title.setVisibility(View.GONE);
                } else {
                    sexViewHolder.index_item_title.setVisibility(View.VISIBLE);
                    sexViewHolder.index_item_text.setText(indexMokuaiInfoBean.getMoudleName());
                }
                for (int i = 0; i < mokuaiContentInfoBeens.size(); i++) {
                    IndexMokuaiContentBean moduleContent = mokuaiContentInfoBeens.get(i);
                    ImageView imageView;
                    if (i == 0) {
                        imageView = sexViewHolder.one_img;
                    } else if (i == 1) {
                        imageView = sexViewHolder.tow_img;
                    } else if (i == 2) {
                        imageView = sexViewHolder.three_img;
                    } else {
                        continue;
                    }
                    imageLoader.displayImage(moduleContent.getShowImg(), imageView, MallAppliaction.getInstance().defaultOptions);
                    imageView.setOnClickListener(new ItemClickListener(moduleContent));
                }
                break;
            case IndexModel.INDEX_ITEM_SEVEN://6：一型；
                SevenViewHolder sevenViewHolder = (SevenViewHolder) baseViewHolder;
                if (indexMokuaiInfoBean.getIsShowName() == -1 || TextUtils.isEmpty(indexMokuaiInfoBean.getMoudleName())) {//不显示模块名称
                    sevenViewHolder.index_item_title.setVisibility(View.GONE);
                } else {
                    sevenViewHolder.index_item_title.setVisibility(View.VISIBLE);
                    sevenViewHolder.index_item_text.setText(indexMokuaiInfoBean.getMoudleName());
                }
                for (int i = 0; i < mokuaiContentInfoBeens.size(); i++) {
                    IndexMokuaiContentBean moduleContent = mokuaiContentInfoBeens.get(i);
                    ImageView imageView;
                    if (i == 0) {
                        imageView = sevenViewHolder.one_img;
                    } else {
                        continue;
                    }
                    imageLoader.displayImage(moduleContent.getShowImg(), imageView, MallAppliaction.getInstance().defaultOptions);
                    imageView.setOnClickListener(new ItemClickListener(moduleContent));
                }
                break;
        }
    }

    /**
     * 图片点击事件接口对象
     */
    private OnItemImgClickInterface onItemImgClickInterface;

    public OnItemImgClickInterface getOnItemImgClickInterface() {
        return onItemImgClickInterface;
    }

    /**
     * 对外开放的图片点击事件接口
     *
     * @param onItemImgClickInterface
     */
    public void setOnItemImgClickInterface(OnItemImgClickInterface onItemImgClickInterface) {
        this.onItemImgClickInterface = onItemImgClickInterface;
    }

    /**
     * 图片点击事件接口
     */
    public interface OnItemImgClickInterface {
        void onItemImgClick(View v, IndexMokuaiContentBean moduleContent);
    }

    /**
     * 每个图片的点击事件
     */
    private class ItemClickListener implements View.OnClickListener {
        IndexMokuaiContentBean moduleContent;

        ItemClickListener(IndexMokuaiContentBean moduleContent) {
            this.moduleContent = moduleContent;
        }

        @Override
        public void onClick(View v) {
            if (getOnItemImgClickInterface() == null) return;
            getOnItemImgClickInterface().onItemImgClick(v, moduleContent);
        }
    }

    /**
     * 各种viewholder
     */
    public BaseViewHolder getOneViewHolder(View itemView) {
        return new OneViewHolder(itemView);
    }

    public BaseViewHolder getTowViewHolder(View itemView) {
        return new TowViewHolder(itemView);
    }

    public BaseViewHolder getThreeViewHolder(View itemView) {
        return new ThreeViewHolder(itemView);
    }

    public BaseViewHolder getFourViewHolder(View itemView) {
        return new FourViewHolder(itemView);
    }

    public BaseViewHolder getFiveViewHolder(View itemView) {
        return new FiveViewHolder(itemView);
    }

    public BaseViewHolder getSexViewHolder(View itemView) {
        return new SexViewHolder(itemView);
    }

    public BaseViewHolder getSevenViewHolder(View itemView) {
        return new SevenViewHolder(itemView);
    }

    /**
     * 改变尺寸，三比一，横向
     *
     * @param view
     */
    private void setItemHeightThree(View view) {
        if (view == null) return;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int height = windowWidth / 3;
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }

    /**
     * 斜土型
     *
     * @param view
     */
    private void setItemHeightTow(View view) {
        if (view == null) return;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (windowWidth / 2.28);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 正土型
     *
     * @param view
     * @param viewtow
     */
    private void setItemHeightOne(View view, View viewtow) {
        if (view == null) return;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = windowWidth / 2;
        view.setLayoutParams(layoutParams);
        layoutParams = viewtow.getLayoutParams();
        layoutParams.height = windowWidth / 3;
        viewtow.setLayoutParams(layoutParams);
    }

    /**
     * 正方形单个view，针对田型
     *
     * @param view
     */
    private void setItemHeightFour(View view) {
        if (view == null) return;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = windowWidth / 2;
        view.setLayoutParams(layoutParams);
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

    //0：一排三列；
    private class OneViewHolder extends BaseViewHolder {

        LinearLayout index_item_title;
        LinearLayout index_item_one_ly;
        ImageView one_img, tow_img, three_img;
        TextView index_item_text;

        public OneViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void initItemView(View itemView) {
            index_item_title = (LinearLayout) itemView.findViewById(R.id.index_item_title);
            index_item_one_ly = (LinearLayout) itemView.findViewById(R.id.index_item_one_ly);
            one_img = (ImageView) itemView.findViewById(R.id.one_img);
            tow_img = (ImageView) itemView.findViewById(R.id.tow_img);
            three_img = (ImageView) itemView.findViewById(R.id.three_img);
            index_item_text = (TextView) itemView.findViewById(R.id.index_item_text);
            setItemHeightThree(index_item_one_ly);
        }
    }

    //1：├型；
    private class TowViewHolder extends BaseViewHolder {

        LinearLayout index_item_title;
        LinearLayout index_item_tow_ly;
        ImageView one_img, tow_img, three_img;
        TextView index_item_text;

        public TowViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            index_item_title = (LinearLayout) itemView.findViewById(R.id.index_item_title);
            index_item_tow_ly = (LinearLayout) itemView.findViewById(R.id.index_item_tow_ly);
            one_img = (ImageView) itemView.findViewById(R.id.one_img);
            tow_img = (ImageView) itemView.findViewById(R.id.tow_img);
            three_img = (ImageView) itemView.findViewById(R.id.three_img);
            index_item_text = (TextView) itemView.findViewById(R.id.index_item_text);
            setItemHeightTow(index_item_tow_ly);
        }
    }

    //2：田型；
    private class ThreeViewHolder extends BaseViewHolder {

        LinearLayout index_item_title;
        LinearLayout index_item_three_ly;
        ImageView one_img, tow_img, three_img, four_img;
        TextView index_item_text;

        public ThreeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            index_item_title = (LinearLayout) itemView.findViewById(R.id.index_item_title);
            index_item_three_ly = (LinearLayout) itemView.findViewById(R.id.index_item_three_ly);
            one_img = (ImageView) itemView.findViewById(R.id.one_img);
            tow_img = (ImageView) itemView.findViewById(R.id.tow_img);
            three_img = (ImageView) itemView.findViewById(R.id.three_img);
            four_img = (ImageView) itemView.findViewById(R.id.four_img);
            index_item_text = (TextView) itemView.findViewById(R.id.index_item_text);
            setItemHeightFour(one_img);
            setItemHeightFour(tow_img);
            setItemHeightFour(three_img);
            setItemHeightFour(four_img);
        }
    }

    //3：┤型；
    private class FourViewHolder extends BaseViewHolder {

        LinearLayout index_item_title;
        LinearLayout index_item_four_ly;
        ImageView one_img, tow_img, three_img;
        TextView index_item_text;

        public FourViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            index_item_title = (LinearLayout) itemView.findViewById(R.id.index_item_title);
            index_item_four_ly = (LinearLayout) itemView.findViewById(R.id.index_item_four_ly);
            one_img = (ImageView) itemView.findViewById(R.id.one_img);
            tow_img = (ImageView) itemView.findViewById(R.id.tow_img);
            three_img = (ImageView) itemView.findViewById(R.id.three_img);
            index_item_text = (TextView) itemView.findViewById(R.id.index_item_text);
            setItemHeightTow(index_item_four_ly);
        }
    }

    //4：┬型；
    private class FiveViewHolder extends BaseViewHolder {

        LinearLayout index_item_title;
        LinearLayout index_item_five_ly;
        ImageView one_img, tow_img, three_img;
        TextView index_item_text;

        public FiveViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            index_item_title = (LinearLayout) itemView.findViewById(R.id.index_item_title);
            index_item_five_ly = (LinearLayout) itemView.findViewById(R.id.index_item_five_ly);
            one_img = (ImageView) itemView.findViewById(R.id.one_img);
            tow_img = (ImageView) itemView.findViewById(R.id.tow_img);
            three_img = (ImageView) itemView.findViewById(R.id.three_img);
            index_item_text = (TextView) itemView.findViewById(R.id.index_item_text);
            setItemHeightOne(three_img, one_img);
        }
    }

    //5：┴型；
    private class SexViewHolder extends BaseViewHolder {

        LinearLayout index_item_title;
        LinearLayout index_item_sex_ly;
        ImageView one_img, tow_img, three_img;
        TextView index_item_text;

        public SexViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            index_item_title = (LinearLayout) itemView.findViewById(R.id.index_item_title);
            index_item_sex_ly = (LinearLayout) itemView.findViewById(R.id.index_item_sex_ly);
            one_img = (ImageView) itemView.findViewById(R.id.one_img);
            tow_img = (ImageView) itemView.findViewById(R.id.tow_img);
            three_img = (ImageView) itemView.findViewById(R.id.three_img);
            index_item_text = (TextView) itemView.findViewById(R.id.index_item_text);
            setItemHeightOne(one_img, three_img);
        }
    }

    //6：一型；
    private class SevenViewHolder extends BaseViewHolder {

        LinearLayout index_item_title;
        LinearLayout index_item_seven_ly;
        ImageView one_img;
        TextView index_item_text;

        public SevenViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void initItemView(View itemView) {
            index_item_title = (LinearLayout) itemView.findViewById(R.id.index_item_title);
            index_item_seven_ly = (LinearLayout) itemView.findViewById(R.id.index_item_seven_ly);
            one_img = (ImageView) itemView.findViewById(R.id.one_img);
            index_item_text = (TextView) itemView.findViewById(R.id.index_item_text);
            setItemHeightThree(index_item_seven_ly);
        }
    }

}
