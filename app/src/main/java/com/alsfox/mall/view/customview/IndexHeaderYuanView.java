package com.alsfox.mall.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alsfox.mall.R;
import com.alsfox.mall.bean.index.IndexDaohangInfoBean;

import java.util.List;

import static com.baidu.location.b.g.i;

/**
 * Created by 浩 on 2016/10/21.
 * 首页圆形图标
 */

public class IndexHeaderYuanView extends LinearLayout {

    private View root;
    private LinearLayout header_yuan_ly;

    public IndexHeaderYuanView(Context context) {
        super(context);
        initView();
    }

    public IndexHeaderYuanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public IndexHeaderYuanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        root = LayoutInflater.from(getContext()).inflate(R.layout.layout_header_yuan, this);
        header_yuan_ly = (LinearLayout) root.findViewById(R.id.header_yuan_ly);
    }

    public void getDataList(List<IndexDaohangInfoBean> indexDaohangInfoBeans) {
        if (header_yuan_ly == null) return;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        for (int i = 0; i < indexDaohangInfoBeans.size(); i++) {
            IndexHeaderYuanItemView indexHeaderYuanItemView = new IndexHeaderYuanItemView(getContext());
            indexHeaderYuanItemView.setLayoutParams(layoutParams);
            indexHeaderYuanItemView.setVerticalGravity(VERTICAL);
            indexHeaderYuanItemView.setText(indexDaohangInfoBeans.get(i).getNavName());
            indexHeaderYuanItemView.setImg(indexDaohangInfoBeans.get(i).getShowImgRes());
            indexHeaderYuanItemView.setOnClick(new OnClick(i));
            header_yuan_ly.addView(indexHeaderYuanItemView);
        }
    }

    public void getUrlDataList(List<IndexDaohangInfoBean> indexDaohangInfoBeans) {
        if (header_yuan_ly == null) return;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        for (int i = 0; i < indexDaohangInfoBeans.size(); i++) {
            IndexHeaderYuanItemView indexHeaderYuanItemView = new IndexHeaderYuanItemView(getContext());
            indexHeaderYuanItemView.setLayoutParams(layoutParams);
            indexHeaderYuanItemView.setVerticalGravity(VERTICAL);
            indexHeaderYuanItemView.setText(indexDaohangInfoBeans.get(i).getNavName());
            indexHeaderYuanItemView.setImg(indexDaohangInfoBeans.get(i).getShowImg());
            indexHeaderYuanItemView.setOnClick(new OnClick(i));
            header_yuan_ly.addView(indexHeaderYuanItemView);
        }
    }

    private class OnClick implements OnClickListener {
        private int poistion;

        OnClick(int poistion) {
            this.poistion = poistion;
        }

        @Override
        public void onClick(View v) {
            if (getOnHeaderViewClick() == null) return;
            getOnHeaderViewClick().onItemClickData(poistion);
        }
    }

    private OnHeaderViewClick onHeaderViewClick;

    public OnHeaderViewClick getOnHeaderViewClick() {
        return onHeaderViewClick;
    }

    public void setOnHeaderViewClick(OnHeaderViewClick onHeaderViewClick) {
        this.onHeaderViewClick = onHeaderViewClick;
    }

    public interface OnHeaderViewClick {
        void onItemClickData(int position);
    }
}
