package com.wokun.dset.ucenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.GetMobileBean;
import com.wokun.dset.ucenter.myyijian.ui.MyyijianActivity;
import com.wokun.dset.utils.DataCleanManager;
import com.wokun.dset.utils.StringUtil;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;
/**
 *
 * 设置页面
 *
 * */

//设置页面
public class SettingsActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_settings)String title;
    @BindView(R.id.clear) TextView clear;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.phone_num)
    TextView phone_num;
     private  String phoneNum;

    @Override
    public int createView() {
        return R.layout.activity_settings;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<GetMobileBean>>post(Constants.BASE_URL + Constants.GET_MOBILE)
                .tag(this)
                .params("sign",sign)
                .params("timestamp",StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<GetMobileBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<GetMobileBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMessage());
                        if(body.getStatus().equals("0001")){
                            GetMobileBean    getMobileBean = (GetMobileBean) body.getData();
                              phoneNum = getMobileBean.getPhone();
                             phone_num.setText(phoneNum+"");

                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<GetMobileBean>> response) {
                        super.onError(response);

                        RxToast.showShort("文件上传出错");
                    }
                });







     /*   Intent intent = getIntent();
        changemobile = intent.getStringExtra("changemobile");*/
       /* if (switchButton.isChecked()) {//接受消息
            JPushInterface.resumePush(this);
        } else {//拒绝消息
            if(!JPushInterface.isPushStopped(this)){
                JPushInterface.stopPush(this);
            }
        }
        try {
            clear.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    /**修改密码*/
    @OnClick(R.id.action_edit_password)
    public void actionEditPassword(View v) {
       Intent intent = new Intent(SettingsActivity.this, EditPwdActivity.class);
        intent.putExtra("changemobile",phoneNum);

      startActivity(intent);
    }

/*    *//**修改绑定手机*//*
    @OnClick(R.id.action_edit_mobile)
    public void actionEditMobile(View v) {
        Intent intent = new Intent(SettingsActivity.this, NewEditMobileActivity.class);

        startActivity(intent);
    }*/





    /**意见反馈*/
    @OnClick(R.id.setting_fankui)
    public void actionsettingfankui(View v) {

        startActivity(MyyijianActivity.class);
    }

    /**关于我们*/
    @OnClick(R.id.action_about_us)
    public void actionAboutUs(View v) {
       startActivity(AboutUsActivity.class);
    }




    /**检查更新*/
    @OnClick(R.id.action_check_update)
    public void actionCheckUpdate(View v) {

    }

    /**清空缓存*/
    @OnClick(R.id.action_clear_cache)
    public void actionClearCache(View v) {
        DataCleanManager.clearAllCache(this);
        try {
            clear.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**退出登录*/
    @OnClick(R.id.action_logout)
    public void action_logout(View v) {
      LoginMgr.getInstance().logout(this);
      startActivity(LoginActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
