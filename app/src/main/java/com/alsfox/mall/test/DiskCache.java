package com.alsfox.mall.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LuHao on 2016/10/22.
 * 本地缓存
 */

public class DiskCache implements ImageCache {

    private final String bitMapPath = Environment.getExternalStorageDirectory() + "/" + "image_loader_cache";

    @Override
    public Bitmap getBitMap(String url) {
        return BitmapFactory.decodeFile(bitMapPath + url);
    }

    @Override
    public void putBitMap(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(bitMapPath + url);
            //保存格式为ong，100表示100%压缩，文件流
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
