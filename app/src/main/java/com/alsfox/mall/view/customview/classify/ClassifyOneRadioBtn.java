package com.alsfox.mall.view.customview.classify;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.alsfox.mall.R;

/**
 * Created by 浩 on 2016/10/22.
 * 分类中一级分类的按钮
 */

public class ClassifyOneRadioBtn extends LinearLayout {

    private View root;
    private RadioButton classify_one_radio;

    public ClassifyOneRadioBtn(Context context) {
        super(context);
        initView();
    }

    public ClassifyOneRadioBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ClassifyOneRadioBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        root = LayoutInflater.from(getContext()).inflate(R.layout.item_classify_one, this);
        classify_one_radio = (RadioButton) root.findViewById(R.id.classify_one_radio);
    }

//    public void setClassifyRadioCheckListener(RadioGroup.OnCheckedChangeListener checkedChangeListener) {
//        if (classify_one_radio == null) return;
//        classify_one_radio.setOnCheckedChangeListener(checkedChangeListener);
//    }

    public void setClassifyRadioCheck(boolean bl) {
        if (classify_one_radio == null) return;
        classify_one_radio.setChecked(bl);
    }

    public void setClassifyRadioText(CharSequence str) {
        if (classify_one_radio == null) return;
        classify_one_radio.setText(str);
    }

}
