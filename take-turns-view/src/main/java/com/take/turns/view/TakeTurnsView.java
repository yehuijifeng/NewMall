package com.take.turns.view;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.take.turns.R;
import com.take.turns.adapter.MyAdapter;
import com.take.turns.contants.Contant;
import com.take.turns.utils.DisplayUtil;
import com.take.turns.viewpager.NoScrollViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 浩 on 2016/8/30.
 * 轮番图
 */
public class TakeTurnsView extends LinearLayout {
    private View root;//根布局
    public NoScrollViewPager take_turns_view_pager;//改造后的viewpager
    private RadioGroup take_turns_radio_group;//存放轮番图下标的radiogroup
    /**
     * viewpager的适配器
     */
    private MyAdapter pagerAdapter;

    private Handler mHandler;

    private int sleepTime = 3000;

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * 资源id
     */
    private List<String> imageDataUrls;

    /**
     * 控制viewpager的滑动速度
     */
    private FixedSpeedScroller scroller;

    private int fixedTime = 200;

    private UpdateUI updateUI;

    public UpdateUI getUpdateUI() {
        return updateUI;
    }

    //private ImageLoader imageLoader;

    public void setUpdateUI(UpdateUI updateUI) {
        this.updateUI = updateUI;
    }

    public List<String> getImageUrls() {
        return imageDataUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        setImageUrls(imageUrls, 0);
    }

    public void setImageUrls(List<String> imageUrls, int drawableId) {
        //为null则不赋值
        if (imageUrls == null || imageUrls.isEmpty()) return;
        if (imageDataUrls != null && imageDataUrls.equals(imageUrls)) return;
        //更新数据
        imageDataUrls = imageUrls;
        //若数据长度一样，则不变，若长度不一样，重新赋值
        if (tips == null || tips.length != imageUrls.size()) {
            // 将点点加入到ViewGroup中
            tips = new RadioButton[imageUrls.size()];
            getRadioButton(drawableId);
        }
        //检测数据需不需要更新，只关心数据长度，若长度一样则只更新内容
        if (!checkData()) {
            getImageViews();
        }

        //take_turns_view_pager.removeAllViews();

        //适配器在有了数据以后才创建
        if (pagerAdapter == null) {
            pagerAdapter = new MyAdapter();
            pagerAdapter.setmImageViews(imageViews);
            take_turns_view_pager.setInfinateAdapter(mHandler, pagerAdapter);
            take_turns_view_pager.setCurrentItem(imageViews.size() * 100 + 1, true);
            take_turns_view_pager.setCurrentItem(imageViews.size() * 100, true);
            //更新
            //pagerAdapter.notifyDataSetChanged();
        } else
            //更新适配器数据
            pagerAdapter.setmImageViews(imageViews);

        //更新
        //pagerAdapter.notifyDataSetChanged();

        //设置当前点点的位置
        tips[take_turns_view_pager.getCurrentItem() < imageDataUrls.size() ? take_turns_view_pager.getCurrentItem() : (take_turns_view_pager.getCurrentItem() % imageDataUrls.size()) == 0 ? (take_turns_view_pager.getCurrentItem() % imageDataUrls.size()) : (take_turns_view_pager.getCurrentItem() % imageDataUrls.size()) - 1].setChecked(true);

    }


    /**
     * 显示点点的布局
     *
     * @param drawableId
     */
    private void getRadioButton(int drawableId) {
        //添加之前清除点点
        take_turns_radio_group.removeAllViews();

        //radiobutton的布局
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置边距
        int margs = DisplayUtil.dip2px(getContext(), 2);
        layoutParams.setMargins(margs, margs, margs, margs);

        //radiobutton的样式
        drawableId = drawableId == 0 ? R.drawable.bg_page_item_tag : drawableId;

        for (int i = 0; i < tips.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setButtonDrawable(drawableId);
            radioButton.setLayoutParams(layoutParams);
            tips[i] = radioButton;
            take_turns_radio_group.addView(radioButton);
        }
    }


    /**
     * 将数据添加进inmageview中
     */
    private void getImageViews() {
        //清理数据
        imageViews.clear();
        //图片布局
        ViewGroup.LayoutParams imageLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        take_turns_view_pager.setNoScroll(false);
        // 将图片装载到数组中
        if (imageDataUrls.size() == 1) {
            for (int i = 0; i < 2; i++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setId(0);
                imageView.setOnClickListener(new OnItemClickListener(0));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(imageLayoutParams);
                imageViews.add(imageView);
            }
            take_turns_view_pager.setNoScroll(true);
        } else if (imageDataUrls.size() == 2 || imageDataUrls.size() == 3) {

            for (int i = 0; i < imageDataUrls.size() * 2; i++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setId(i > (imageDataUrls.size() - 1) ? i - imageDataUrls.size() : i);
                imageView.setOnClickListener(new OnItemClickListener(i > (imageDataUrls.size() - 1) ? i - imageDataUrls.size() : i));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(imageLayoutParams);
                imageViews.add(imageView);
            }
        } else {
            for (int i = 0; i < imageDataUrls.size(); i++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setId(i);
                imageView.setOnClickListener(new OnItemClickListener(i));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(imageLayoutParams);
                imageViews.add(imageView);
            }
        }
    }

    //每一个imageviewd点击事件
    private class OnItemClickListener implements OnClickListener {
        private int viewId;

        OnItemClickListener(int viewId) {
            this.viewId = viewId;
        }

        @Override
        public void onClick(View v) {
            if (getUpdateUI() != null) {
                getUpdateUI().onItemClick(viewId, (ImageView) v);
            }
        }
    }

    private boolean checkData() {
        if (imageViews == null || imageViews.isEmpty()) return false;
        // 将图片装载到数组中
        if (imageDataUrls.size() == 1) {
            if (imageViews.size() == 2) {
//                for (ImageView imageView : imageViews) {
//                    imageLoader.displayImage(imageDataUrls.get(0), imageView, displayImageOptions);
//                }
                return true;
            }
            return false;
        } else if (imageDataUrls.size() == 2 || imageDataUrls.size() == 3) {
            if (imageViews.size() == imageDataUrls.size() * 2) {
//                for (int i = 0; i < imageViews.size(); i++) {
//                    imageLoader.displayImage(imageDataUrls.get((i > (imageDataUrls.size() - 1)) ? i - imageDataUrls.size() : i), imageViews.get(i), displayImageOptions);
//                }
                return true;
            }
            return false;
        } else {
            if (imageViews.size() == imageDataUrls.size()) {
//                for (int i = 0; i < imageViews.size(); i++) {
//                    imageLoader.displayImage(imageDataUrls.get(i), imageViews.get(i), displayImageOptions);
//                }
                return true;
            }
            return false;
        }
    }

    /**
     * view控件集合
     */
    private List<ImageView> imageViews;

    /**
     * 游标集合
     */
    private RadioButton[] tips;

    public TakeTurnsView(Context context) {
        super(context);
        initView();
    }

    public TakeTurnsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TakeTurnsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //imageLoader = ImageLoader.getInstance();
        root = LayoutInflater.from(getContext()).inflate(R.layout.base_take_truns, this);
        take_turns_view_pager = (NoScrollViewPager) root.findViewById(R.id.take_turns_view_pager);
        take_turns_radio_group = (RadioGroup) root.findViewById(R.id.take_turns_radio_group);
        imageViews = new ArrayList<>();
        take_turns_view_pager.addOnPageChangeListener(new MyOnPageChangeListener());
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        if (imageDataUrls == null || imageDataUrls.size() <= 1) return;
                        take_turns_view_pager.setCurrentItem(take_turns_view_pager.getCurrentItem() + 1, true);
                        if (Contant.isRun && !Contant.isDown) {
                            this.sendEmptyMessageDelayed(0, sleepTime);
                        }
                        break;

                    case 1:
                        if (Contant.isRun && !Contant.isDown) {
                            this.sendEmptyMessageDelayed(0, sleepTime);
                        }
                        break;
                }
            }
        };

        getViewpagerScrollTime();
    }

    /**
     * 设置viewpager的滑动速度
     */
    private void getViewpagerScrollTime() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            scroller = new FixedSpeedScroller(take_turns_view_pager.getContext(),
                    new AccelerateInterpolator());
            field.set(take_turns_view_pager, scroller);
            //经测试，200ms是最佳视觉效果
            scroller.setmDuration(fixedTime);
        } catch (Exception e) {
            //LogUtils.e(TAG, "", e);
        }
    }

    /**
     * 外界调用，赋值
     *
     * @param time
     */
    public void setViewpagerScrollTime(int time) {
        try {
            fixedTime = time;
            //经测试，200ms是最佳视觉效果
            scroller.setmDuration(fixedTime);
        } catch (Exception e) {
            //LogUtils.e(TAG, "", e);
        }
    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * Indicates that the pager is in an idle, settled state. The current
         * page is fully in view and no animation is in progress.
         */
        public static final int SCROLL_STATE_IDLE = 0;

        /**
         * Indicates that the pager is currently being dragged by the user.
         */

        public static final int SCROLL_STATE_DRAGGING = 1;

        /**
         * Indicates that the pager is in the process of settling to a final
         * position.
         */
        public static final int SCROLL_STATE_SETTLING = 2;

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case SCROLL_STATE_IDLE:
                    // System.out
                    // .println("===========>>>"
                    // + " onPageScrollStateChanged --->>> SCROLL_STATE_IDLE");
                    break;

                case SCROLL_STATE_DRAGGING:
                    // System.out
                    // .println("===========>>>"
                    // + " onPageScrollStateChanged --->>> SCROLL_STATE_DRAGGING");
                    break;
                case SCROLL_STATE_SETTLING:
                    // System.out
                    // .println("===========>>>"
                    // + " onPageScrollStateChanged --->>> SCROLL_STATE_SETTLING");
                    break;
            }

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (imageViews.size() < 1) return;
            int j = setImageBackground(position % imageViews.size());
            if (getUpdateUI() != null)
                getUpdateUI().onUpdateUI(j, imageViews.get(j), imageDataUrls.get(j));
            //imageLoader.displayImage(imageDataUrls.get(j), imageViews.get(j), displayImageOptions);
        }


        /**
         * 设置选中的tip的背景
         *
         * @param selectItems
         */
        private int setImageBackground(int selectItems) {
            int j = 0;
            if (imageDataUrls.size() == 1) {// 说明只有一个图片.默认全部选中
                for (int i = 0; i < tips.length; i++) {
                    tips[i].setChecked(true);
                    j = i;
                }
            } else if (imageDataUrls.size() == 2 || imageDataUrls.size() == 3) {
                if (selectItems < imageViews.size() / 2) {
                    for (int i = 0; i < tips.length; i++) {
                        if (i == selectItems) {
                            tips[i].setChecked(true);
                            j = i;
                        }
                    }
                } else {
                    for (int i = 0; i < tips.length; i++) {
                        if (i == selectItems % imageDataUrls.size()) {
                            tips[i].setChecked(true);
                            j = i;
                        }
                    }
                }
            } else {
                for (int i = 0; i < tips.length; i++) {
                    if (i == selectItems) {
                        tips[i].setChecked(true);
                        j = i;
                    }
                }
            }
            return j;
        }
    }

    public void setTakeTurnsHeight(int height) {
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        layoutParams.height = height;
        root.setLayoutParams(layoutParams);
    }

    /**
     * 轮番图当前显示的图片接口
     */
    public interface UpdateUI {
        //当前ui显示出来的位置
        void onUpdateUI(int position, ImageView imageView, String imgUrl);

        //点击了当前页面
        void onItemClick(int position, ImageView imageView);
    }


    public void setTouchListener(OnTouchListener touchListener) {
        if (take_turns_view_pager == null) return;
        take_turns_view_pager.setOnTouchListener(touchListener);
    }

    //跟随activity的生命周期
    public void onPause() {
        Contant.isRun = false;
        mHandler.removeCallbacksAndMessages(null);
    }

    //跟随activity的生命周期
    public void onResume() {
        Contant.isRun = true;
        mHandler.sendEmptyMessageDelayed(0, sleepTime);
    }
}
