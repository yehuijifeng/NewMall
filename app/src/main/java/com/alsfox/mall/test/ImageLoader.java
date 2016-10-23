package com.alsfox.mall.test;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LuHao on 2016/10/22.
 * 图片缓存
 */

public class ImageLoader {
    //线程池，线程池数量为当前设别的cpu线程数量
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //多级缓存
    private DoubleCache doubleCache = new DoubleCache();

    public ImageLoader() {

    }

    public void dispalyImage(String url, ImageView imageView) {
        Bitmap bitmap = doubleCache.getBitMap(url);
        if (bitmap != null) {
            //如果缓存有，则取出来直接用
            imageView.setImageBitmap(bitmap);
        } else {
            //如果没有，则异步下载图片
            asyncImage(url, imageView);
        }
    }

    /**
     * 异步下载图片
     *
     * @param url
     * @param imageView
     */
    private void asyncImage(final String url, final ImageView imageView) {
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = ImageDownload.downloadImage(url);
                if (bitmap == null) return;
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                //存入缓存
                doubleCache.putBitMap(url, bitmap);
            }
        });
    }
}
