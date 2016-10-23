package com.alsfox.mall.test;

import android.graphics.Bitmap;

/**
 * Created by LuHao on 2016/10/22.
 * 图片缓存
 */

public interface ImageCache {

    /**获得缓存中的图片
     * @param url
     * @return
     */
      Bitmap getBitMap(String url);

    /**添加缓存
     * @param url
     * @param bitmap
     */
      void putBitMap(String url,Bitmap bitmap);
}
