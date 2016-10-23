package com.alsfox.mall.test;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


/**
 * Created by LuHao on 2016/10/22.
 * 内存缓存
 */

public class MemoryCache implements ImageCache {

    /**
     * 图片缓存
     */
    private LruCache<String, Bitmap> mMemoryCache;


    public MemoryCache() {
        //计算可用最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        //取四分之一用作缓存
        final int cacheSize = maxMemory / 4;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //经实测发现：getByteCount() = getRowBytes() * getHeight()，
                // 也就是说位图所占用的内存空间数等于位图的每一行所占用的空间数乘以位图的行数。
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap getBitMap(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void putBitMap(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
    }
}
