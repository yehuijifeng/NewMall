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
    protected View root;
    private AddView add_view;//加号
    private RemoveView remove_view;//减号
    private EditText count_edit;//输入数量框
    private int maxCount = Integer.MAX_VALUE;//最大范围
    private int count = 1;//当前数值
    private OnChangeEditText onChangeEditText;//数量改变的回调接口

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        count_edit.setText(count + "");
    }

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
        if (!bl) {
            maxCount = 0;
            count_edit.setText(0 + "");
        }
    }

    /**
     * 是否可以手动输入数量
     *
     * @param bl
     */
    public void setInputEditText(boolean bl) {
        count_edit.setEnabled(bl);
        count_edit.setTextColor(getResources().getColor(R.color.black));
    }

    /**
     * 初始化的输入框长度
     *
     * @param i
     */
    public void setEditContentLengh(int i) {
        count_edit.setMinEms(i);
    }

    /**
     * 添加最大输入量
     *
     * @param maxCount
     */
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
        if (count > maxCount) {
            count = maxCount;
            count_edit.setText(count + "");
        }
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

    protected void getRoot() {
        root = View.inflate(getContext(), R.layout.layout_count_edit, this);
    }

    private void initView() {
        getRoot();
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
        if (count < 1) count = 1;
        count_edit.setText(count + "");
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
        if (TextUtils.isEmpty(countStr)) {
            countStr = 1 + "";
            count_edit.setText(countStr);
        }
        count = Integer.parseInt(countStr);
        if (count < 1) count = 1;
        if (count > maxCount) count = maxCount;
        if (getOnChangeEditText() != null)
            getOnChangeEditText().onChangeEdit(count);
    }


}
