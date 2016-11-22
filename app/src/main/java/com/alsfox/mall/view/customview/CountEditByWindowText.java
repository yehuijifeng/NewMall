package com.alsfox.mall.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.alsfox.mall.R;


/**
 * Created by 浩 on 2016/11/9.
 * 加减号选择器,popupwindow专用
 */

public class CountEditByWindowText extends CountEditText {


    public CountEditByWindowText(Context context) {
        super(context);
    }

    public CountEditByWindowText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountEditByWindowText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void getRoot() {
        root = View.inflate(getContext(), R.layout.layout_count_by_window_edit, this);
    }
}
