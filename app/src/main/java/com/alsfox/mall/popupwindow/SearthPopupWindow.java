package com.alsfox.mall.popupwindow;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alsfox.mall.R;


/**
 * Created by Luhao on 2016/9/11.
 * 选择商品或者商铺
 */
public class SearthPopupWindow extends PopupWindow implements View.OnClickListener {
    private TextView commodity_text, store_text;
    private View window_search;
    private OnDismiss onDismiss;

    public SearthPopupWindow(Context context) {
        super(context);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window_search = View.inflate(context, R.layout.window_search, null);
        commodity_text = (TextView) window_search.findViewById(R.id.commodity_text);
        store_text = (TextView) window_search.findViewById(R.id.store_text);
        commodity_text.setOnClickListener(this);
        store_text.setOnClickListener(this);
        /**
         * 设置此项为true时，会让其这个popupWindow处于焦点，其它控件(除主页面键)都会
         * 失去焦点，不可点击,仅仅适用于全屏幕遮挡的popupwindow
         */
        setFocusable(true);
        setAnimationStyle(R.style.AnimationPreviewForCommodityPop);
        /**
         * 如果pop不设置背景的话不会在点击视图以外消失
         */
        setBackgroundDrawable(new ColorDrawable(0x0000000));
        /**popupwindow以外可以触摸*/
        setOutsideTouchable(true);
        update();
        window_search.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        setContentView(window_search);

    }

    public void showAtLocation(View parent, OnDismiss onDismiss) {
        this.onDismiss = onDismiss;
        int[] location = new int[2];
        // 获得位置
        parent.getLocationOnScreen(location);
        int width = getContentView().getMeasuredWidth();
        showAtLocation(parent, Gravity.NO_GRAVITY, (location[0] + parent.getWidth() / 2) - width / 2,
                location[1] + parent.getWidth());
    }

    public interface OnDismiss {
        void OnDismissClick(int i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store_text://商铺
                onDismiss.OnDismissClick(2);
                dismiss();
                break;
            case R.id.commodity_text://商品
                onDismiss.OnDismissClick(1);
                dismiss();
                break;
        }
    }
}
