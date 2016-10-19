package com.take.turns.viewpager;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.take.turns.adapter.MyAdapter;
import com.take.turns.contants.Contant;


/**
 * 传入onScroll kongzhi dangqian viewpager
 * shi
 */
public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = false;
    private Handler handler;


    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    /**
     * ontouch 事件是否消耗；传入false则表示继续往上传递，该view不消耗
     *
     * @param arg0
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    /**
     * ontouch 事件是否拦截；传入false则表示该view不拦截
     *
     * @param arg0
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    /**
     * 事件分发
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (noScroll)
            return super.dispatchTouchEvent(ev);
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            Contant.isRun = false;
            Contant.isDown = true;
            if (handler != null)
                handler.removeCallbacksAndMessages(null);
        } else if (action == MotionEvent.ACTION_UP) {
            Contant.isRun = true;
            Contant.isDown = false;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessage(1);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setInfinateAdapter(Handler handler, MyAdapter adapter) {
        this.handler = handler;
        setAdapter(adapter);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

}
