package com.alsfox.mall.function.imageloader;

import android.graphics.Bitmap;
import android.os.Handler;

import com.alsfox.mall.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * Created by yehuijifeng
 * on 2015/11/26.
 * imageloader的默认配置，单个图片的属性配置
 */

public class ImageOptions {
    /**
     * 防止被实例化
     */
    private ImageOptions() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 默认的图片显示方式
     */
    public static DisplayImageOptions defaultOptions() {
        /**
         *3.DisplayImageOptions实例对象的配置
         *以下的设置再调用displayImage()有效，使用loadImage()无效
         *显示image的选项
         */

        /**
         * DisplayImageOptions 创建的两种方式。
         */
        // 创建默认的DisplayImageOptions
        //DisplayImageOptions option = DisplayImageOptions.createSimple();

        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_default_img)//加载的时候显示的图片
                .showImageForEmptyUri(R.drawable.ic_default_img)  // empty空或者错误的情况下URI时显示的图片
                .showImageOnFail(R.drawable.ic_default_img)// 不是图片文件或者解码错误情况下的图片
                .resetViewBeforeLoading(false)  //设置图片在下载前是否重置，复位
                .delayBeforeLoading(0)//加载前延迟：1s
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中,default 不缓存至内存
                .considerExifParams(true) //考虑Exif参数
                .cacheOnDisk(true)//缓存到sd卡
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//图片比例尺类型：平铺
                .bitmapConfig(Bitmap.Config.ARGB_4444)//设置图片的解码类型,default防止内存溢出
                .resetViewBeforeLoading(true)//在加载前重置视图
                .displayer(new SimpleBitmapDisplayer()) // 正常显示， 可以设置动画，比如圆角或者渐变
                .handler(new Handler())// default
                .build();
//        //设置图片的解码配置
//        decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)
//
//        //设置额外的内容给ImageDownloader
//        extraForDownloader(Object extra)
//
//        //设置图片加入缓存前，对bitmap进行设置
//        preProcessor(BitmapProcessor preProcessor)
//
//        //设置显示前的图片，显示后这个图片一直保留在缓存中
//        postProcessor(BitmapProcessor postProcessor)
//
//        //设置图片以如何的编码方式显示
//        imageScaleType(ImageScaleType imageScaleType)

        /**
         *  设置图片的显示方式
         */
        //.displayer(new RoundedBitmapDisplayer(10))//设置圆角图片
        //.displayer(new FadeInBitmapDisplayer(3000))//设置图片渐显的时间
        //.displayer(new SimpleBitmapDisplayer())// 正常显示一张图片

        /**
         * 图片的缩放方式
         */
        //imageScaleType(ImageScaleType.XXX)
        //imageScaleType:
        //EXACTLY :图像将完全按比例缩小的目标大小
        //EXACTLY_STRETCHED:图片会缩放到目标大小完全
        //IN_SAMPLE_INT:图像将被二次采样的整数倍
        //IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
        //NONE:图片不会调整
        return options;

    }


    /**
     * 圆形图片
     */
    public static DisplayImageOptions roundOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_default_img)//加载的时候显示的图片
                .showImageForEmptyUri(R.drawable.ic_default_img)  // empty空或者错误的情况下URI时显示的图片
                .showImageOnFail(R.drawable.ic_default_img)// 不是图片文件或者解码错误情况下的图片
                .resetViewBeforeLoading(false)  //设置图片在下载前是否重置，复位
                .delayBeforeLoading(0)//加载前延迟：1s
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中,default 不缓存至内存
                .considerExifParams(true) //考虑Exif参数
                .cacheOnDisk(true)//缓存到sd卡
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//图片比例尺类型：平铺
                .bitmapConfig(Bitmap.Config.ARGB_4444)//设置图片的解码类型,default防止内存溢出
                .resetViewBeforeLoading(true)//在加载前重置视图
                .displayer(new CircleBitmapDisplayer())
                .handler(new Handler())// default
                .build();
        return options;
    }
}
