package com.alsfox.mall.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.alsfox.mall.constances.AppConstant;
import com.alsfox.mall.view.activity.function.ImageCropActivity;

import java.io.File;

/**
 * Created by 浩 on 2015/9/17 12:42.
 * 选择本地图片工具类
 */
public class PickLocalImageUtils {

    public static final int CODE_FOR_ALBUM = 2000;

    public static final int CODE_FOR_CAMERA = CODE_FOR_ALBUM + 1;

    public static final int CODE_FOR_CROP = CODE_FOR_CAMERA + 1;

    /**
     * 去相册
     *
     * @param activity
     */
    public static void toAlbum(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setType("image/*");
        activity.startActivityForResult(intent, CODE_FOR_ALBUM);
    }

    /**
     * 去相机
     *
     * @param activity
     * @param imageFileName
     */
    public static void toCamera(Activity activity, String imageFileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                AppConstant.SAVE_IMG_PATH, imageFileName)));
        activity.startActivityForResult(intent, CODE_FOR_CAMERA);
    }

    /**
     * 去剪切图片
     *
     * @param activity
     * @param imagePath
     * @param crop_width
     * @param crop_height
     * @param aspectRatioX
     * @param aspectRatioY
     * @param saveImagePath
     */
    public static void toCrop(Activity activity, String imagePath, int crop_width, int crop_height, int aspectRatioX, int aspectRatioY, String saveImagePath) {
        Intent intent = new Intent(activity, ImageCropActivity.class);
        intent.putExtra(ImageCropActivity.KEY_IMAGE_PATH, imagePath);
        intent.putExtra(ImageCropActivity.KEY_CROP_WIDTH, crop_width);
        intent.putExtra(ImageCropActivity.KEY_CROP_HEIGHT, crop_height);
        intent.putExtra(ImageCropActivity.ASPECT_RATIO_X, aspectRatioX);
        intent.putExtra(ImageCropActivity.ASPECT_RATIO_Y, aspectRatioY);
        if (!TextUtils.isEmpty(saveImagePath))
            intent.putExtra(ImageCropActivity.KEY_SAVE_IMAGE_PATH, saveImagePath);
        activity.startActivityForResult(intent, CODE_FOR_CROP);
    }

    /**
     * 去剪切图片
     *
     * @param activity
     * @param imagePath
     */
    public static void toCrop(Activity activity, String imagePath) {
        toCrop(activity, imagePath, ImageCropActivity.DEFAULT_CROP_WIDTH, ImageCropActivity.DEFAULT_CROP_HEIGHT, ImageCropActivity.DEFAULT_ASPECT_RATIO_VALUES, ImageCropActivity.DEFAULT_ASPECT_RATIO_VALUES, null);
    }


    /**
     * 获得相册选中图片路径
     *
     * @param uri
     * @param resolver
     * @return
     */
    public static String getPath(Uri uri, ContentResolver resolver) {
        String path;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = resolver.query(uri, projection, null, null,
                null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(column_index);
        } else {
            path = uri.getPath();
        }
        return path;
    }
}