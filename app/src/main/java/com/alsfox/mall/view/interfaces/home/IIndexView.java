package com.alsfox.mall.view.interfaces.home;

import android.view.View;

import com.alsfox.mall.bean.index.IndexMokuaiContentBean;
import com.alsfox.mall.view.interfaces.base.IBaseView;

/**
 * Created by 浩 on 2016/10/19.
 *
 */

public interface IIndexView extends IBaseView {

    /**
     * 首页每一个图片的点击事件
     *
     * @param v
     * @param moduleContent
     */
    void onItemImgClick(View v, IndexMokuaiContentBean moduleContent);

}
