package com.alsfox.mall.view.customview;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alsfox.mall.R;
import com.alsfox.mall.view.customview.tow_d_view.AddView;
import com.alsfox.mall.view.customview.tow_d_view.RemoveView;


/**
 * Created by 浩 on 2016/11/9.
 * 加减号选择器
 */

public class CountEditText extends LinearLayout implements View.OnClickListener, TextWatcher {
    private View root;
    private AddView add_view;
    private RemoveView remove_view;
    private EditText count_edit;
    private int maxCount = Integer.MAX_VALUE;//最大范围
    private int count;//当前数值

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private OnChangeEditText onChangeEditText;

    public OnChangeEditText getOnChangeEditText() {
        return onChangeEditText;
    }

    /**
     * 数量回调接口
     *
     * @param onChangeEditText
     */
    public void setOnChangeEditText(OnChangeEditText onChangeEditText) {
        this.onChangeEditText = onChangeEditText;
    }

    /**
     * 不可添加数量
     *
     * @param bl
     */
    public void setInputBoxEnable(boolean bl) {
        if (bl)
            maxCount = 0;
    }

    /**
     * 设置加减号大小
     */
    public void setEditViewHeight(int height) {
        add_view.setWidth(height);
        remove_view.setWidth(height);
    }

    public void setEditContentLengh(int i){
        count_edit.setMinEms(i);
    }

    public interface OnChangeEditText {
        void onChangeEdit(int count);
    }

    public CountEditText(Context context) {
        super(context);
        initView();
    }

    public CountEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CountEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        root = View.inflate(getContext(), R.layout.layout_count_edit, this);
        add_view = (AddView) root.findViewById(R.id.add_view);
        remove_view = (RemoveView) root.findViewById(R.id.remove_view);
        count_edit = (EditText) root.findViewById(R.id.count_edit);
        add_view.setOnClickListener(this);
        remove_view.setOnClickListener(this);
        count_edit.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_view:
                count++;
                break;
            case R.id.remove_view:
                count--;
                break;
        }
        if (count < 0) count = 0;
        count_edit.setText(count + "");
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
        if (count > maxCount) {
            count = maxCount;
            count_edit.setText(count + "");
        }
    }

    /**
     * 文本改变之前
     *
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * 分本改变中
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /**
     * 文本改变后
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {
        String countStr = count_edit.getText().toString();
        if (TextUtils.isEmpty(countStr)) countStr = 1 + "";
        count = Integer.parseInt(countStr);
        if (count < 0) count = 0;
        if (count > maxCount) count = maxCount;
        if (getOnChangeEditText() != null)
            getOnChangeEditText().onChangeEdit(count);
    }


}
