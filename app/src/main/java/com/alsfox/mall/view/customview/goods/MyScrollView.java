package com.alsfox.mall.view.customview.goods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by 浩 on 2016/11/3.
 * 监听滑动
 */

public class MyScrollView extends ScrollView {

    private onScrollChangedListener onScrollChangedListener;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public interface onScrollChangedListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    public void setOnScrollChangedListener(MyScrollView.onScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }
}
