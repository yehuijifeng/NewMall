package com.alsfox.mall.view.customview.goods;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alsfox.mall.R;
import com.alsfox.mall.utils.DisplayUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 包含两个ScrollView的容器 更多详解见博客http://dwtedx.com
 *
 * @author chenjing
 */
public class ScrollViewContainer extends RelativeLayout {

    /**
     * 自动上滑
     */
    public static final int AUTO_UP = 0;
    /**
     * 自动下滑
     */
    public static final int AUTO_DOWN = 1;
    /**
     * 动画完成
     */
    public static final int DONE = 2;
    /**
     * 动画速度
     */
    public static final float SPEED = 30f;

    private boolean isMeasured = false;

    /**
     * 用于计算手滑动的速度
     */
    private VelocityTracker vt;

    private int mViewHeight;
    private int mViewWidth;

    private View topView;
    private View bottomView;

    private boolean canPullDown;
    private boolean canPullUp;
    private int state = DONE;

    private boolean isFirstPage = true;

    /**
     * 记录当前展示的是哪个view，0是topView，1是bottomView
     */
    private int mCurrentViewIndex = 0;

    /**
     * 手滑动距离，这个是控制布局的主要变量
     */
    private float mMoveLen;
    private MyTimer mTimer;
    private float mLastY;
    /**
     * 用于控制是否变动布局的另一个条件，mEvents==0时布局可以拖拽了，mEvents==-1时可以舍弃将要到来的第一个move事件， 这点是去除多点拖动剧变的关键
     */
    private int mEvents;

    private DisplayMetrics outMetrics;

    private LinearLayout ll_commodity_detail_bottom;

    private onLoadPageListener loadSecondPageListener;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (mMoveLen != 0) {
                if (state == AUTO_UP) {
                    mMoveLen -= SPEED;
                    if (mMoveLen <= -mViewHeight) {
                        mMoveLen = -mViewHeight;
                        state = DONE;
                        mCurrentViewIndex = 1;
                    }
                    if (loadSecondPageListener != null && isFirstPage) {
                        loadSecondPageListener.onLoadSecondPage();
                        isFirstPage = false;
                    }
                } else if (state == AUTO_DOWN) {
                    mMoveLen += SPEED;
                    if (mMoveLen >= 0) {
                        mMoveLen = 0;
                        state = DONE;
                        mCurrentViewIndex = 0;
                    }
                    if (loadSecondPageListener != null && !isFirstPage) {
                        loadSecondPageListener.onLoadFirstPage();
                        isFirstPage = true;
                    }
                } else {
                    mTimer.cancel();
                }
            }
            requestLayout();
        }

    };

    public interface onLoadPageListener {
        void onLoadFirstPage();

        void onLoadSecondPage();

        void onMoving(MotionEvent ev);

        void onCancel(MotionEvent ev);
    }

    public ScrollViewContainer(Context context) {
        super(context);
        init(context);
    }

    public ScrollViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollViewContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    public void setOnLoadSecondPageListener(onLoadPageListener loadSecondPageListener) {
        this.loadSecondPageListener = loadSecondPageListener;
    }

    private void init(Context context) {
        mTimer = new MyTimer(handler);
    }

    public void setOutMetrics(DisplayMetrics outMetrics) {
        this.outMetrics = outMetrics;
    }

    public void setLl_commodity_detail_bottom(LinearLayout ll_commodity_detail_bottom) {
        this.ll_commodity_detail_bottom = ll_commodity_detail_bottom;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (vt == null)
                    vt = VelocityTracker.obtain();
                else
                    vt.clear();
                mLastY = ev.getY();
                vt.addMovement(ev);
                mEvents = 0;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                // 多一只手指按下或抬起时舍弃将要到来的第一个事件move，防止多点拖拽的bug
                mEvents = -1;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getY() - mLastY) > 50) {
                    if (loadSecondPageListener != null) {
                        loadSecondPageListener.onMoving(ev);
                    }
                }
                vt.addMovement(ev);
                if (canPullUp && mCurrentViewIndex == 0 && mEvents == 0) {
                    mMoveLen += (ev.getY() - mLastY);
                    // 防止上下越界
                    if (mMoveLen > 0) {
                        mMoveLen = 0;
                        mCurrentViewIndex = 0;
                    } else if (mMoveLen < -mViewHeight) {
                        mMoveLen = -mViewHeight;
                        mCurrentViewIndex = 1;
                    }
                    if (mMoveLen < -8) {
                        // 防止事件冲突
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                    }
                } else if (canPullDown && mCurrentViewIndex == 1 && mEvents == 0) {
                    mMoveLen += (ev.getY() - mLastY);
                    // 防止上下越界
                    if (mMoveLen < -mViewHeight) {
                        mMoveLen = -mViewHeight;
                        mCurrentViewIndex = 1;
                    } else if (mMoveLen > 0) {
                        mMoveLen = 0;
                        mCurrentViewIndex = 0;
                    }
                    if (mMoveLen > 8 - mViewHeight) {
                        // 防止事件冲突
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                    }
                } else
                    mEvents++;
                mLastY = ev.getY();
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                if (loadSecondPageListener != null) {
                    loadSecondPageListener.onCancel(ev);
                }
                mLastY = ev.getY();
                vt.addMovement(ev);
                vt.computeCurrentVelocity(700);
                // 获取Y方向的速度
                float mYV = vt.getYVelocity();
                vt.clear();
                vt.recycle();
                vt = null;
                if (mMoveLen == 0 || mMoveLen == -mViewHeight)
                    break;
                if (Math.abs(mYV) < 500) {
                    // 速度小于一定值的时候当作静止释放，这时候两个View往哪移动取决于滑动的距离
                    if (mMoveLen <= -mViewHeight / 2) {
                        state = AUTO_UP;
                    } else if (mMoveLen > -mViewHeight / 2) {
                        state = AUTO_DOWN;
                    }
                } else {
                    // 抬起手指时速度方向决定两个View往哪移动
                    if (mYV < 0)
                        state = AUTO_UP;
                    else
                        state = AUTO_DOWN;
                }
                mTimer.schedule(2);
                break;

        }
        super.dispatchTouchEvent(ev);
        return true;
    }

    public void toTop() {
        state = AUTO_DOWN;
        mTimer.schedule(2);
        topView.scrollTo(10, 10);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        topView.layout(0, (int) mMoveLen, mViewWidth, topView.getMeasuredHeight() + (int) mMoveLen);
        if (outMetrics == null || ll_commodity_detail_bottom == null) return;
        bottomView.layout(0, topView.getMeasuredHeight() + (int) mMoveLen + (int) (outMetrics.heightPixels * 0.1) + (int) getResources().getDimension(R.dimen.dialog_button_height), mViewWidth, topView.getMeasuredHeight() + (int) mMoveLen
                + outMetrics.heightPixels - ll_commodity_detail_bottom.getMeasuredHeight() - DisplayUtils.dip2px(getContext(), -20));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isMeasured) {
            isMeasured = true;

            mViewHeight = getMeasuredHeight();
            mViewWidth = getMeasuredWidth();

            topView = getChildAt(0);
            bottomView = getChildAt(1);

            bottomView.setOnTouchListener(bottomViewTouchListener);
            topView.setOnTouchListener(topViewTouchListener);
        }
    }

    private OnTouchListener topViewTouchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ScrollView sv = (ScrollView) v;
            canPullUp = sv.getScrollY() == (sv.getChildAt(0).getMeasuredHeight() - sv
                    .getMeasuredHeight()) && mCurrentViewIndex == 0;
            return false;
        }
    };
    private OnTouchListener bottomViewTouchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ScrollView sv = (ScrollView) v;
            canPullDown = sv.getScrollY() == 0 && mCurrentViewIndex == 1;
            return false;
        }
    };

    class MyTimer {
        private Handler handler;
        private Timer timer;
        private MyTask mTask;

        public MyTimer(Handler handler) {
            this.handler = handler;
            timer = new Timer();
        }

        public void schedule(long period) {
            if (mTask != null) {
                mTask.cancel();
                mTask = null;
            }
            mTask = new MyTask(handler);
            timer.schedule(mTask, 0, period);
        }

        public void cancel() {
            if (mTask != null) {
                mTask.cancel();
                mTask = null;
            }
        }

        class MyTask extends TimerTask {
            private Handler handler;

            public MyTask(Handler handler) {
                this.handler = handler;
            }

            @Override
            public void run() {
                handler.obtainMessage().sendToTarget();
            }

        }
    }

}