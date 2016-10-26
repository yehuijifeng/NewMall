package com.alsfox.mall.view.customview.user;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alsfox.mall.R;
import com.alsfox.mall.utils.DisplayUtils;

public class UserPwdInputBox extends LinearLayout implements CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener {

    private LinearLayout ll_pwd_input_box;

    private EditText et_user_login_pwd;

    private CheckBox cb_user_login_display_pwd;

    private int drawablePadding;

    private Drawable drawableLeft;
    private Drawable drawableRight;
    private Drawable drawableTop;
    private Drawable drawableBottom;

    private Context context;

    public UserPwdInputBox(Context context) {
        this(context, null);
    }

    public UserPwdInputBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserPwdInputBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomPwdInputBox, defStyleAttr, 0);
        drawablePadding = typedArray.getDimensionPixelSize(R.styleable.CustomPwdInputBox_drawablePadding, 0);
        drawableLeft = typedArray.getDrawable(R.styleable.CustomPwdInputBox_drawableLeft);
        drawableRight = typedArray.getDrawable(R.styleable.CustomPwdInputBox_drawableRight);
        drawableTop = typedArray.getDrawable(R.styleable.CustomPwdInputBox_drawableTop);
        drawableBottom = typedArray.getDrawable(R.styleable.CustomPwdInputBox_drawableBottom);
        typedArray.recycle();
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, drawableLeft.getIntrinsicWidth(), drawableLeft.getIntrinsicHeight());
        }
        if (drawableRight != null) {
            drawableRight.setBounds(0, 0, drawableRight.getIntrinsicWidth(), drawableRight.getIntrinsicHeight());
        }
        if (drawableTop != null) {
            drawableTop.setBounds(0, 0, drawableTop.getIntrinsicWidth(), drawableTop.getIntrinsicHeight());
        }
        if (drawableBottom != null) {
            drawableBottom.setBounds(0, 0, drawableBottom.getIntrinsicWidth(), drawableBottom.getIntrinsicHeight());
        }
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View parent = LayoutInflater.from(context).inflate(R.layout.layout_pwd_input_box, this, false);
        ll_pwd_input_box = (LinearLayout) parent.findViewById(R.id.ll_pwd_input_box);
        et_user_login_pwd = (EditText) parent.findViewById(R.id.et_user_login_pwd);
        cb_user_login_display_pwd = (CheckBox) parent.findViewById(R.id.cb_user_login_display_pwd);
        cb_user_login_display_pwd.setOnCheckedChangeListener(this);
        et_user_login_pwd.setOnFocusChangeListener(this);
        et_user_login_pwd.setCompoundDrawablePadding(drawablePadding);
        et_user_login_pwd.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
        addView(parent);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, et_user_login_pwd.getMeasuredHeight());
        params.rightMargin = DisplayUtils.dip2px(getContext(), 5);
        params.gravity = Gravity.CENTER_VERTICAL;
        cb_user_login_display_pwd.setLayoutParams(params);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //隐藏键盘
        imm.hideSoftInputFromWindow(et_user_login_pwd.getWindowToken(), 0);
        if (isChecked) {
            et_user_login_pwd.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            et_user_login_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        //显示键盘
        imm.showSoftInput(et_user_login_pwd, 0);//editText为需要点击的文本框
        et_user_login_pwd.setSelection(et_user_login_pwd.getText().length());
    }

    public EditText getInputBox() {
        return et_user_login_pwd;
    }

    public String getText() {
        return et_user_login_pwd.getText().toString().trim();
    }

    public void setText(CharSequence text) {
        et_user_login_pwd.setText(text);
    }

    public void setHint(CharSequence hint) {
        et_user_login_pwd.setHint(hint);
    }

    public String getHint() {
        return et_user_login_pwd.getHint().toString();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            ll_pwd_input_box.setBackgroundResource(R.drawable.shape_general_input_box_focused);
        } else {
            ll_pwd_input_box.setBackgroundResource(R.drawable.shape_general_input_box_normal);
        }
    }
}