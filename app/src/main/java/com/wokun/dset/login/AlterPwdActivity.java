package com.wokun.dset.login;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * 忘记密码
 */

public class AlterPwdActivity extends BaseBindingActivity{
    @BindString(R.string.tysl_alter_password)String title;

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    private TimeCount timeCount;
    @BindView(R.id.action_send_verify_code)
    Button actionSendverifycode;

    @BindView(R.id.action_siv_show_pwd)
    SelectorImageView sivShowPwd;
    @Override
    public int createView() {
        return R.layout.activity_login_alter_password;
    }

    @Override
    public WidgetBar createToolBar() {
     return    mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        etPwd.setHint("新密码（6-20位字母，数字或符号）");
        etMobile.setHint("请输入手机号");
        etYzm.setHint("请输入收到的手机验证码");


    }


    /** 忘记密码*/
    @OnClick(R.id.action_submit)
    public void action_forget_password(View v) {
        if (R.id.action_submit == v.getId()) {
                 mforget();
        }}

    private void mforget() {
        String pwd = etPwd.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String yzm = etYzm.getText().toString().trim();
        String mobile2 = "+86"+mobile;
//        if (TextUtils.isEmpty(account)) {
//            showToast(getResources().getString(R.string.tip_account));
//            return;
//        }
        if (TextUtils.isEmpty(mobile)) {
            RxToast.showShort(getResources().getString(R.string.tip_phone));
            return;
        }
        if (TextUtils.isEmpty(yzm)) {
            RxToast.showShort(getResources().getString(R.string.tip_code));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            RxToast.showShort(getResources().getString(R.string.tip_login_pwd));
            return;
        }
        // TODO: 2018/7/2 区号、中英文
        Map params = new HashMap();
//        params.put("username", account);
        params.put("quhao","86");
        params.put("phone", mobile);
        params.put("code", yzm);
        params.put("password", pwd);
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        Log.e("mobile","!!!!"+mobile);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FORGET_PWD)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                . params("quhao", "86")
                . params("phone", mobile)
                . params("code", yzm)
                . params("password", pwd)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("忘记密码","忘记密码!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            //   finish();
                            startActivity(LoginActivity.class);
                        }
                    }
                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("忘记密码1",response+"!!!!");

                    }
                });

    }
    /**
     * 密码的显示与隐藏
     * */
    @OnClick(R.id.action_siv_show_pwd)
    public void actionSivShowPwd(View view){
        LoginMgr.getInstance().isShowPassword(etPwd,sivShowPwd);
    }



    /** 获取验证码*/
    @OnClick(R.id.action_send_verify_code)
    public void actionSendVerifyCode(View v){
        if(R.id.action_send_verify_code == v.getId()){
            String mobile = etMobile.getText().toString().trim();
            timeCount = new TimeCount(60000, 1000);
            LoginMgr.getInstance().sendVerifyCode(mobile);
            timeCount.start();
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
            //   actionSendverifycode.setTextColor(Color.parseColor("#056196"));
            actionSendverifycode.setTextColor(Color.parseColor(String.valueOf(R.color.colorWhite)));
        }
    }
}
