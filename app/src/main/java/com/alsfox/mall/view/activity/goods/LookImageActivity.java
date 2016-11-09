package com.alsfox.mall.view.activity.goods;

import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.function.photoview.PhotoView;
import com.alsfox.mall.presenter.base.BasePresenter;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.baseview.MyViewPager;

import java.util.ArrayList;
import java.util.List;


public class LookImageActivity extends BaseActivity implements OnPageChangeListener {

    private MyViewPager viewPager;
    private PicAdapter picAdapter;
    private List<String> imgUrls = new ArrayList<>();
    private int position;
    private TextView mLabel;

    public static final String BUNDLE_KEY_IMAGEPOSITION = "imgPosition";
    public static final String BUNDLE_KEY_IMAGEURLS = "imgUrls";
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_picture_look;
    }

    @Override
    protected String setTitleText() {
        return null;
    }

    /**
     * 初始化控件(View)
     */
    @Override
    protected void initView() {
        mTitleView.setTitleText("图片列表");
        imgUrls = getStringArrayList(BUNDLE_KEY_IMAGEURLS);
        position = getInt(BUNDLE_KEY_IMAGEPOSITION, 0);
        mLabel = (TextView) findViewById(R.id.mLabel);
        viewPager = (MyViewPager) findViewById(R.id.viewPager);
        picAdapter = new PicAdapter();
        viewPager.setAdapter(picAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(position);
        mLabel.setText((position + 1) + "/" + imgUrls.size());
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

    }


    class PicAdapter extends PagerAdapter {

        private List<PhotoView> photoViews = new ArrayList<PhotoView>();

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /**
         * 销毁当前page的相隔2个及2个以上的item时调用
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("test", "销毁view位置:" + position);
            container.removeView((View) object); // 删除页卡
        }

        // 这个方法用来实例化页卡
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LookImageActivity.this.position = position;
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setScaleType(ImageView.ScaleType.CENTER);
            photoView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            String imgUrl = imgUrls.get(position);
            if ("http".equalsIgnoreCase(imgUrl.substring(0, 4))) {
                imageLoader.displayImage(imgUrl, photoView, MallAppliaction.getInstance().defaultOptions);
            } else {
                photoView.setImageBitmap(BitmapFactory.decodeFile(imgUrl));
            }
            container.addView(photoView, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            photoViews.add(photoView);
            return photoView; // 返回该view对象，作为key
        }

        @Override
        public int getCount() {
            return imgUrls.size();
        }
    }

    @Override
    public void onPageSelected(int position) {
        mLabel.setText((position + 1) + "/" + imgUrls.size());
        //setSwipeBackEnable(position == 0);
//        imgPath = imgUrls.get(position);
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
}