package com.alsfox.mall.view.interfaces.app;

import com.alsfox.mall.bean.app.AppVersionBean;
import com.alsfox.mall.view.interfaces.base.IBaseView;

/**
 * Created by 浩 on 2016/10/27.
 * loading页面
 */

public interface IAppView extends IBaseView {

    /**
     * 是否需要升级app
     *
     * @param bl
     */
    void isDownloadApp(boolean bl,AppVersionBean appVersionBean);
}
