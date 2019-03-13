package com.wokun.dset.ucenter.renamepassword;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.login.AlterPwdActivity;
import com.wokun.dset.login.LoginMgr;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 重置支付密码
 * */
public class ForgetpwdActivity extends BaseBindingActivity {
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.action_send_verify_code)
    Button actionSendverifycode;
    private String phoneNum;
    private TimeCount timeCount;
    @Override
    public int createView() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    public WidgetBar createToolBar() {
        return null;
    }

    @Override
    public void init() {
        phoneNum = getIntent().getStringExtra("phoneNum");


    }
    /** 获取验证码*/
    @OnClick(R.id.action_send_verify_code)
    public void actionSendVerifyCode(View v){
        if(R.id.action_send_verify_code == v.getId()){
            String phoneNum2 = phoneNum.substring(2);
            Log.e("phoneNum",phoneNum2);
            timeCount = new  TimeCount(60000, 1000);
            LoginMgr.getInstance().sendVerifyCode(phoneNum2);
            timeCount.start();
        }
    }


    /** 重置*/
    @OnClick(R.id.txt_next4)
    public void action_txt_next4(View v){
        if(R.id.txt_next4 == v.getId()){
           // startActivity(NamepasswordActivity2.class);
            String mobilecode = etMobile.getText().toString().trim();
            if(!TextUtils.isEmpty(mobilecode)){
                Intent intent = new Intent(ForgetpwdActivity.this, NamepasswordActivity22.class);
                intent.putExtra("mobilecode",mobilecode);
                intent.putExtra("phoneNum",phoneNum);
                startActivity(intent);
            }else{
                RxToast.showShort("请输入验证码");
            }

        }
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
           actionSendverifycode.setTextColor(Color.parseColor("#ffffff"));
         //   actionSendverifycode.setTextColor(Color.parseColor(String.valueOf(R.color.colorWhite)));
        }
    }


}
