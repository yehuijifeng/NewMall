package com.alsfox.mall.view.customview.index;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by 浩 on 2016/10/21.
 * 圆形图标的item
 */

public class IndexHeaderYuanItemView extends LinearLayout {
    private View root;
    private TextView item_header_text;
    private ImageView item_header_img;
    private ImageLoader imageLoader;

    public IndexHeaderYuanItemView(Context context) {
        super(context);
        initView();
    }

    public IndexHeaderYuanItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public IndexHeaderYuanItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        imageLoader = ImageLoader.getInstance();
        root = LayoutInflater.from(getContext()).inflate(R.layout.item_header_yuan_item, this);
        item_header_text = (TextView) root.findViewById(R.id.item_header_text);
        item_header_img = (ImageView) root.findViewById(R.id.item_header_img);
    }

    public void setOnClick(OnClickListener onClickListener) {
        root.setOnClickListener(onClickListener);
    }

    public void setText(CharSequence str) {
        if (item_header_text != null) {
            item_header_text.setText(str);
        }
    }

    public void setImg(String url) {
        if (item_header_img != null) {
            imageLoader.displayImage(url, item_header_img, MallAppliaction.getInstance().roundOptions);
        }
    }

    public void setImg(int resId) {
        if (item_header_img != null) {
            imageLoader.displayImage("drawable://" + resId, item_header_img, MallAppliaction.getInstance().roundOptions);
        }
    }
}
