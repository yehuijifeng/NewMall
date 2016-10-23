package com.alsfox.mall.test;

import android.graphics.Bitmap;

/**
 * Created by LuHao on 2016/10/22.
 * 多级缓存，这里只涉及到内存和sd卡
 */

public class DoubleCache implements ImageCache {

    ImageCache memoryCache = new MemoryCache();
    ImageCache diskCache = new DiskCache();

    @Override
    public Bitmap getBitMap(String url) {
        Bitmap bitmap = memoryCache.getBitMap(url);
        if (bitmap == null) {
            bitmap = diskCache.getBitMap(url);
        }
        return bitmap;
    }

    @Override
    public void putBitMap(String url, Bitmap bitmap) {
        memoryCache.putBitMap(url, bitmap);
        diskCache.putBitMap(url, bitmap);
    }
}
