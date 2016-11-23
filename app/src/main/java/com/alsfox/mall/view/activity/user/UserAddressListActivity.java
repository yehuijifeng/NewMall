package com.alsfox.mall.view.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alsfox.mall.R;
import com.alsfox.mall.adapter.BaseViewHolder;
import com.alsfox.mall.appliaction.MallAppliaction;
import com.alsfox.mall.bean.user.UserAddressBean;
import com.alsfox.mall.constances.MallConstant;
import com.alsfox.mall.http.SignUtils;
import com.alsfox.mall.http.StatusCode;
import com.alsfox.mall.http.request.RequestAction;
import com.alsfox.mall.http.response.ResponseFinalAction;
import com.alsfox.mall.http.response.ResponseSuccessAction;
import com.alsfox.mall.presenter.user.UserAddressPresenter;
import com.alsfox.mall.view.activity.base.BaseListActivity;
import com.alsfox.mall.view.baseview.dialog.PromptDialog;
import com.alsfox.mall.view.interfaces.user.IUserAddressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alsfox.mall.http.request.RequestAction.SELECT_USER_DSPT_LIST;

/**
 * Created by 浩 on 2016/11/23.
 * 用户收货地址
 */

public class UserAddressListActivity extends BaseListActivity<UserAddressPresenter> implements IUserAddressView, View.OnClickListener {

    private Button add_address_btn;//新增收货地址
    private int pageNum = 1;
    private PromptDialog promptDialog;
    private List<RadioButton> radioButtons = new ArrayList<>();
    private UserAddressBean delAddressInfoVo;//删除的地址信息
    private final int CODE_ADD_ADDRESS = 1000;//新增收货地址返回码
    private final int CODE_MODIFY_ADDRESS = CODE_ADD_ADDRESS + 1;//编辑地址返回码

    @Override
    public BaseViewHolder getViewHolder(View itemView, int postion, int itemType) {
        return new AddressViewHolder(itemView);
    }

    @Override
    public int getItemView(int position, int itemType) {
        return R.layout.item_user_address;
    }

    @Override
    protected UserAddressPresenter initPresenter() {
        return new UserAddressPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_user_address;
    }

    @Override
    protected String setTitleText() {
        return "收货地址管理";
    }

    @Override
    public void initView() {
        super.initView();
        add_address_btn = (Button) findViewById(R.id.add_address_btn);
        add_address_btn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        promptDialog = new PromptDialog(this);
        showLoading("正在查询收货地址，请稍后……");
        refresh();
    }

    @Override
    protected void refresh() {
        pageNum = 1;
        getAddressList();
    }

    @Override
    public void loadMore() {
        pageNum++;
        getAddressList();
    }

    /**
     * 查询收货地址
     */
    private void getAddressList() {
        Map<String, Object> params = SELECT_USER_DSPT_LIST.params.getParams();
        params.put(MallConstant.PARAM_KEY_USERDSPT_USERID, MallAppliaction.getInstance().userBean.getUserId());//用户ID
        params.put(MallConstant.PAGE_NUM, pageNum);//当前页码
        sendRequest(SELECT_USER_DSPT_LIST);
    }


    @Override
    public void getItemData(int position, BaseViewHolder baseViewHolder, int itemType) {
        UserAddressBean addressInfoVo = (UserAddressBean) data.get(position);
        AddressViewHolder viewHolder = (AddressViewHolder) baseViewHolder;
        radioButtons.add(viewHolder.rb_addres_default);
        viewHolder.tv_user_name.setText(addressInfoVo.getDsptName());
        viewHolder.tv_user_tel.setText(addressInfoVo.getDsptPhone());
        viewHolder.tv_user_address.setText(addressInfoVo.getDsptArea() + addressInfoVo.getDsptAddress());
        if (addressInfoVo.getIsDefault() == 1) {
            viewHolder.rb_addres_default.setChecked(true);
        } else {
            viewHolder.rb_addres_default.setChecked(false);
        }
        viewHolder.tv_user_address_del.setOnClickListener(new OnClickAndOnChecked(addressInfoVo));
        viewHolder.tv_user_address_edit.setOnClickListener(new OnClickAndOnChecked(addressInfoVo));
        viewHolder.rb_addres_default.setOnCheckedChangeListener(new OnClickAndOnChecked(addressInfoVo));
    }


    @Override
    protected void onRequestSuccess(ResponseSuccessAction success) {
        super.onRequestSuccess(success);
        switch (success.getRequestAction()) {
            case SELECT_USER_DSPT_LIST:
                if (pageNum == 1) {
                    clearAll();
                }
                List<UserAddressBean> addressInfoVos = success.getHttpBean().getObjects();
                addAll(addressInfoVos);
                loadSuccess();
                break;
            case SET_USERDSPT_DEFAULT://设为默认地址
                showShortToast(success.getHttpBean().getObject().toString());
                break;
            case DELETE_USER_DSPT://删除地址
                if (delAddressInfoVo != null) {
                    data.remove(delAddressInfoVo);
                    notifyDataChange();
                }
                showShortToast(success.getHttpBean().getObject().toString());
                break;
        }
    }

    @Override
    protected void onRequestFinal(ResponseFinalAction failure) {
        super.onRequestFinal(failure);
        switch (failure.getRequestAction()) {
            case SELECT_USER_DSPT_LIST:
                if (failure.getRequestCode() != StatusCode.NOT_MORE_DATA) {
                    showErrorLoadingByDefaultClick(failure.getErrorMessage());
                }
                break;
            case SET_USERDSPT_DEFAULT:
                showShortToast(failure.getErrorMessage());
                break;
            case DELETE_USER_DSPT:
                showShortToast(failure.getErrorMessage());
                break;
        }
    }

    /**
     * itema中设置为默认，编辑，删除的点击事件
     */
    class OnClickAndOnChecked implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private UserAddressBean addressInfoVo;

        OnClickAndOnChecked(UserAddressBean addressInfoVo) {
            this.addressInfoVo = addressInfoVo;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_user_address_edit://编辑
                    Bundle bundle = new Bundle();
                    bundle.putInt(MallConstant.PARAM_KEY_USERDSPT_DSPTID, addressInfoVo.getDsptId());
                    bundle.putString(MallConstant.PARAM_KEY_USERDSPT_DSPTNAME, addressInfoVo.getDsptName());
                    bundle.putString(MallConstant.PARAM_KEY_USERDSPT_DSPTPHONE, addressInfoVo.getDsptPhone());
                    bundle.putString(MallConstant.PARAM_KEY_USERDSPT_DSPTAREA, addressInfoVo.getDsptArea());
                    bundle.putString(MallConstant.PARAM_KEY_USERDSPT_DSPTADDRESS, addressInfoVo.getDsptAddress());
                    //startActivityForResult(ModifyDeliveryAddressActivity.class, bundle, CODE_MODIFY_ADDRESS);
                    break;
                case R.id.tv_user_address_del://删除
                    promptDialog.showPromptDialog("提示", "您确定要删除吗?", new PromptDialog.OnPromptClickListener() {
                        @Override
                        public void onDetermine() {
                            delAddressInfoVo = addressInfoVo;
                            setDefaultAddressOrDelAddressRequest(RequestAction.DELETE_USER_DSPT);
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                    break;
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                for (RadioButton radioButton : radioButtons) {
                    if (!compoundButton.equals(radioButton)) {
                        radioButton.setChecked(false);
                    }
                }
                //默认地址
                setDefaultAddressOrDelAddressRequest(RequestAction.SET_USERDSPT_DEFAULT);
            }
        }


        /**
         * 设为默认地址、删除地址
         */
        private void setDefaultAddressOrDelAddressRequest(RequestAction action) {
            Map<String, Object> params = SignUtils.getParameters();
            params.put(MallConstant.PARAM_KEY_USERDSPT_USERID, MallAppliaction.getInstance().userBean.getUserId());//用户ID
            params.put(MallConstant.PARAM_KEY_USERDSPT_DSPTID, addressInfoVo.getDsptId());//收货地址ID
            action.params.setParams(params);
            sendRequest(action);
        }

    }

    /**
     * 编辑收货地址,添加收货地址
     * 成功后的回调方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CODE_ADD_ADDRESS) {//添加
                showLoading("正在查询收货地址，请稍后……");
                refresh();
            } else if (requestCode == CODE_MODIFY_ADDRESS) {//编辑
                showLoading("正在查询收货地址，请稍后……");
                refresh();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_btn://新增收货地址
                //startActivityForResult(NewAddDeliveryActivity.class, CODE_ADD_ADDRESS);
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra(MallConstant.USER_ADDRESS, (UserAddressBean) data.get(position));
        setResult(RESULT_OK, intent);
        finish();
    }

    private class AddressViewHolder extends BaseViewHolder {

        private TextView tv_user_name, tv_user_tel, tv_user_address, tv_user_address_edit, tv_user_address_del;
        private RadioButton rb_addres_default;

        public AddressViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            tv_user_name = (TextView) itemView.findViewById(R.id.tv_user_name);
            tv_user_tel = (TextView) itemView.findViewById(R.id.tv_user_tel);
            tv_user_address = (TextView) itemView.findViewById(R.id.tv_user_address);
            tv_user_address_edit = (TextView) itemView.findViewById(R.id.tv_user_address_edit);
            tv_user_address_del = (TextView) itemView.findViewById(R.id.tv_user_address_del);
            rb_addres_default = (RadioButton) itemView.findViewById(R.id.rb_addres_default);
        }
    }
}
