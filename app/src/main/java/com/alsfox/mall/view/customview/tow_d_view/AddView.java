package com.alsfox.mall.view.customview.tow_d_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.alsfox.mall.utils.DisplayUtils;

import static android.R.attr.width;

/**
 * Created by 浩 on 2016/11/9.
 * 二维 加号
 */

public class AddView extends View {

    protected Paint paint;//油漆
    protected int HstartX, HstartY, HendX, HendY;//水平的线
    protected int SstartX, SstartY, SsendX, SsendY;//垂直的线
    protected int paintWidth;//初始化加号的粗细
    protected int paintColor = Color.BLACK;//画笔颜色黑色
    protected int padding;//默认padding

    protected Canvas canvas;
    protected int widthMeasureSpec;//
    protected int heightMeasureSpec;//暂存

    public AddView(Context context) {
        super(context);
        initView();
    }

    public AddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AddView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        paintWidth = DisplayUtils.dip2px(getContext(), 1);//初始化加号的粗细为2
        padding = DisplayUtils.dip2px(getContext(), 3);//默认padding
        paint = new Paint();//初始化画笔
        paint.setColor(paintColor);//设置颜色
        paint.setStrokeWidth(paintWidth);//设置粗细

    }

    public int getPadding() {
        return padding;
    }

    //让外界调用，修改padding的大小
    public void setPadding(int padding) {
        SsendY = HendX = width - padding;
        SstartY = HstartX = padding;
    }

    //让外界调用，修改加号颜色
    public void setPaintColor(int paintColor) {
        paint.setColor(paintColor);
    }

    //让外界调用，修改加号粗细
    public void setPaintWidth(int paintWidth) {
        paint.setStrokeWidth(paintWidth);
    }

    //设置当前view的宽度
    public void setWidth(int width) {
        //setWidth(width);
        //setMinimumHeight(width);
    }

    /**
     * 监听测量view的方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;

        //获得当前测量出来的view的宽高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width;
        if (widthMode == MeasureSpec.EXACTLY) {
            //  MeasureSpec.EXACTLY表示该view设置的确切的数值
            width = widthSize;
        } else {
            width = DisplayUtils.dip2px(getContext(), 60);//给定默认高度
        }
        SstartX = SsendX = HstartY = HendY = width / 2;//起始点坐标计算，为画图的中心点
        SsendY = HendX = width - getPadding();
        SstartY = HstartX = getPadding();
        //这样做是因为加号宽高是相等的，手动设置当前view的宽高
        setMeasuredDimension(width, width);
    }

    /**
     * 监听当前view的画图动作
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        super.onDraw(canvas);
        //水平的横线，画出来
        canvas.drawLine(HstartX, HstartY, HendX, HendY, paint);
        //垂直的横线，画出来
        canvas.drawLine(SstartX, SstartY, SsendX, SsendY, paint);
    }


}
