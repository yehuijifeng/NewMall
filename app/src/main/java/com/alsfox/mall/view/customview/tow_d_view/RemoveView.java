package com.alsfox.mall.view.customview.tow_d_view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by 浩 on 2016/11/9.
 * 减号
 */

public class RemoveView extends AddView {
    public RemoveView(Context context) {
        super(context);
    }

    public RemoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RemoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //水平的横线，减号不需要垂直的横线了
        canvas.drawLine(HstartX, HstartY, HendX, HendY, paint);
    }
}
