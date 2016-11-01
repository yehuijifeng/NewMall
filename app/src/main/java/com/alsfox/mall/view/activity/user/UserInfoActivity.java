package com.alsfox.mall.view.activity.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.constances.AppConstant;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.download.OnProgressListener;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.request.RetrofitManage;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.user.UserInfoPresenter;
import com.alsfox.mall.utils.BitmapUtil;
import com.alsfox.mall.utils.DateUtils;
import com.alsfox.mall.utils.PickLocalImageUtils;
import com.alsfox.mall.view.activity.base.BaseActivity;
import com.alsfox.mall.view.activity.function.ImageCropActivity;
import com.alsfox.mall.view.baseview.dialog.ListDialog;
import com.alsfox.mall.view.baseview.dialog.LoadingDialog;
import com.alsfox.mall.view.interfaces.user.IUserInfoView;

import java.io.File;
import java.util.Map;


/**
 * Created by 浩 on 2016/11/1.
 * 个人中心
 */

public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements View.OnClickListener, IUserInfoView {

    private ImageView user_info_icon_img;
    private RelativeLayout user_info_update_pwd_rl, user_info_update_icon_rl;
    private TextView user_info_phone_text;
    private String cropImagePath, imageFileName;//图片路径
    private ListDialog listDialog;
    private LoadingDialog loadingDialog;

    @Override
    protected UserInfoPresenter initPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_user_info;
    }

    @Override
    protected String setTitleText() {
        return getResources().getString(R.string.str_compile);
    }

    @Override
    protected void initView() {
        user_info_icon_img = (ImageView) findViewById(R.id.user_info_icon_img);
        user_info_update_pwd_rl = (RelativeLayout) findViewById(R.id.user_info_update_pwd_rl);
        user_info_update_icon_rl = (RelativeLayout) findViewById(R.id.user_info_update_icon_rl);
        user_info_phone_text = (TextView) findViewById(R.id.user_info_phone_text);
        listDialog = new ListDialog(this);
        loadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void initData() {
        if (MallAppliaction.getInstance().userBean != null) {
            imageLoader.displayImage(MallAppliaction.getInstance().userBean.getUserAvatar(), user_info_icon_img, MallAppliaction.getInstance().roundOptions);
            user_info_phone_text.setText(MallAppliaction.getInstance().userBean.getUserName());
            user_info_update_pwd_rl.setOnClickListener(this);
            user_info_update_icon_rl.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_info_update_pwd_rl://修改 密码

                break;
            case R.id.user_info_update_icon_rl://更换用户头像
                listDialog.showListDialog(getResources().getStringArray(R.array.select_icon_type), new ListDialog.OnListItemClickListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onItems(int item, String itemName) {
                        if (item == 0) {//相册
                            PickLocalImageUtils.toAlbum(UserInfoActivity.this);
                        } else if (item == 1) {//相机
                            imageFileName = DateUtils.format(System.currentTimeMillis(), "'IMG'_yyyyMMddHHmmss") + ".jpg";
                            PickLocalImageUtils.toCamera(UserInfoActivity.this, imageFileName);
                        }
                    }
                });
                break;
        }
    }

    private String content = "正在上传，请稍后……";

    private void requestModifyHeadImg(String cropImagePath) {
        try {
            Map<String, Object> params = RequestAction.GET_UPDATE_USER_ICON.params.getParams();
            params.put(MallConstant.USERINFO_ID, MallAppliaction.getInstance().userBean.getUserId());
            loadingDialog.showLoadingDialog(content);
            RetrofitManage.getInstance().uploadFile(RequestAction.GET_UPDATE_USER_ICON, new File[]{new File(cropImagePath)}, new OnProgressListener() {
                @Override
                public void onProgress(double progress, double total, long bytesRead) {
                    content = "已上传：" + ((progress / total) * 100) + "%";
                    if (progress == total) {
                        loadingDialog.dismissLoadingDialog();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String imagePath;
            switch (requestCode) {
                case PickLocalImageUtils.CODE_FOR_ALBUM:
                    if (data == null) return;
                    Uri uri = data.getData();
                    imagePath = PickLocalImageUtils.getPath(uri, getContentResolver());
                    PickLocalImageUtils.toCrop(this, imagePath);
                    break;
                case PickLocalImageUtils.CODE_FOR_CAMERA:
                    imagePath = AppConstant.SAVE_IMG_PATH + imageFileName;
                    PickLocalImageUtils.toCrop(this, imagePath);
                    break;
                case PickLocalImageUtils.CODE_FOR_CROP:
                    if (data == null) return;
                    cropImagePath = data.getStringExtra(ImageCropActivity.KEY_SAVE_IMAGE_PATH);
                    Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFile(cropImagePath, 100, 100);
                    user_info_icon_img.setImageBitmap(bitmap);
                    BitmapUtil.saveBitmap(bitmap, cropImagePath);
                    requestModifyHeadImg(cropImagePath);
                    break;
            }
        }
    }

    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case GET_UPDATE_USER_ICON:
                String imgUrl = success.getHttpBean().getObject().toString().replace("%2F", "/");
                imageLoader.displayImage(MallAppliaction.getInstance().userBean.getUserAvatar(), user_info_icon_img, MallAppliaction.getInstance().roundOptions);
                presenter.insertUserIcon(imgUrl);
                break;

        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction finals) {
        switch (finals.getRequestAction()) {
            case GET_UPDATE_USER_ICON:
                showLongToast(finals.getErrorMessage());
                break;

        }
    }
}
