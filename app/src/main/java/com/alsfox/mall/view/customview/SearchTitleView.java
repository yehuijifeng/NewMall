package com.alsfox.mall.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alsfox.mall.R;

/**
 * Created by 浩 on 2016/10/20.
 * 首页带搜索的title
 */

public class SearchTitleView extends LinearLayout {
    private View root;
    private LinearLayout search_ly;
    private ImageView search_img;

    public SearchTitleView(Context context) {
        super(context);
        initView();
    }

    public SearchTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SearchTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        root = LayoutInflater.from(getContext()).inflate(R.layout.layout_serach_title, this);
        search_ly = (LinearLayout) root.findViewById(R.id.search_ly);
        search_img = (ImageView) root.findViewById(R.id.search_img);
    }

    public void setSearchClick(OnClickListener onClickListener) {
        if (search_ly == null) return;
        search_ly.setOnClickListener(onClickListener);
    }

    public void setSearchIcon(int resId) {
        if (search_img == null) return;
        search_img.setImageResource(resId);
    }

    public void setSearchIconClick(OnClickListener onClickListener) {
        if (search_img == null) return;
        search_img.setOnClickListener(onClickListener);
    }

    public void setSearchIconGone(Boolean bl) {
        if (search_img == null) return;
        search_img.setVisibility(bl ? GONE : VISIBLE);
    }
}
