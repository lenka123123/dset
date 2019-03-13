package com.wokun.dset.ucenter;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.hudongshop.ServiceZhihiuiActivity;
import com.wokun.dset.login.LoginMgr;


import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class EditPwdActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_edit_password)
    String title;

    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;              //旧密码
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;              //新密码
    @BindView(R.id.et_re_new_pwd)
    EditText etReNewPwd;            //再次输入新密码
    @BindView(R.id.action_submit)
    Button actionSubmit;            //确认按钮mobie_phone
    @BindView(R.id.mobie_phone)
    TextView mobie_phone;
    @BindView(R.id.et_image_error1)
    SelectorImageView et_image_error1;
    @BindView(R.id.et_image_error3)
    SelectorImageView et_image_error3;
    @BindView(R.id.action_send_gaisjcode)
    Button actionSendverifycode;


    private String mobile1;
    private   String mobile;

    private TimeCount timeCount;

    @Override
    public int createView() {
        return R.layout.activity_edit_pwd;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        mobile1 = intent.getStringExtra("changemobile");
        Log.e("mymobile",mobile1);
         mobile = mobile1.substring(2);
        Log.e("mymobile",mobile);
        mobie_phone.setText(mobile);
        etOldPwd.setHint("请输入新密码");
        etNewPwd.setHint("请确认新密码");
        etReNewPwd.setHint("请输入验证码");
        actionSubmit.setText("确认");
    }
    /** 修改密码*/
    @OnClick(R.id.action_submit)
    public void action_submit(View v){
      if(!etOldPwd.getText().toString().trim().equals(etNewPwd.getText().toString().trim()))
        {
            RxToast.showShort("密码不一致");
            return;
        }
        if(R.id.action_submit == v.getId()){
            String pwd = etOldPwd.getText().toString().trim();
            String yzm = etNewPwd.getText().toString().trim();
            showCustomizeDialog();
          LoginMgr.getInstance().alterPassword(this,mobile,pwd,yzm);
        }
    }

    private void showCustomizeDialog() {

        final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(EditPwdActivity.this);
        final View dialogView = LayoutInflater.from(EditPwdActivity.this)
                .inflate(R.layout.dialog_changepwd,null);
        TextView del_sure = (TextView) dialogView.findViewById(R.id.del_sure);
        customizeDialog.setView(dialogView);
        final AlertDialog s = customizeDialog.show();
        del_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.dismiss();
            }
        });
    }

    /**
     * 密码的显示与隐藏
     * */
    @OnClick(R.id.et_image_error1)
    public void actionSivShowPwd(View view){
       LoginMgr.getInstance().isShowPassword(etOldPwd,et_image_error1);
    }

    @OnClick(R.id.et_image_error3)
    public void actionSivShowPwd3(View view){
       LoginMgr.getInstance().isShowPassword(etNewPwd,et_image_error3);
    }
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            actionSendverifycode.setSelected(false);//灰色边框
            actionSendverifycode.setClickable(false);
            actionSendverifycode.setText( millisUntilFinished / 1000 +"秒后重发");
        }

        @Override
        public void onFinish() {
            actionSendverifycode.setSelected(true);//蓝色边框
            actionSendverifycode.setClickable(true);
            actionSendverifycode.setText("重获验证码");
            //   actionSendverifycode.setTextColor(Color.parseColor("#056196"));
            actionSendverifycode.setTextColor(Color.parseColor(String.valueOf(R.color.colorWhite)));
        }
    }








    /** 获取验证码*/
    @OnClick(R.id.action_send_gaisjcode)
    public void actionSendVerifyCode(View v){
        if(R.id.action_send_gaisjcode == v.getId()){
            //String mobile = etMobile.getText().toString().trim();
            timeCount = new TimeCount(60000, 1000);
            LoginMgr.getInstance().sendVerifyCode(mobile);
            timeCount.start();
        }
    }
}