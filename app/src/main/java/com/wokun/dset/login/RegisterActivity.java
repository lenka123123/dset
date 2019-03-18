package com.wokun.dset.login;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
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

public class RegisterActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_register)String title;
    @BindView(R.id.et_mobile)
    EditText etUserUsername;

    @BindView(R.id.et_invite_code)
    EditText etRegisterRefereeCode;
    @BindView(R.id.et_yzm)
    EditText et_yzm;
    @BindView(R.id.et_loginpassword)
    EditText et_loginpassword;
    @BindView(R.id.et_zhifupwd)
    EditText et_zhifupwd;
    @BindView(R.id.action_send_verify_code)
    Button actionSendverifycode;



      private TimeCount timeCount;
      private  String mphone ;

    @BindView(R.id.action_siv_show_pwd)
    SelectorImageView sivShowPwd;
    @Override
    public int createView() {
        return R.layout.activity_register;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        et_loginpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

  etUserUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
               //
                // toast("您输入的数据为："+s.toString());
                      if(TextUtils.isEmpty(charSequence)){
                      //    RxToast.showShort("手机账号不能为空");
                      }else {
                         findViewById(R.id.action_send_verify_code) .setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                     timeCount = new TimeCount(60000, 1000);
                                     timeCount.start();
                                     mphone = charSequence.toString().trim();
                                     LoginMgr.getInstance().sendVerifyCode(mphone);
                             }
                         });


                      }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    /** 注册*/
    @OnClick(R.id.action_register)
    public void action_register(View v) {
        if (R.id.action_register == v.getId()) {
            mregister();
        }
    }

    /** 注册协议*/
    @OnClick(R.id.action_tysl_xy)
    public void   action_tysl_xyr(View v) {
        if (R.id.action_tysl_xy == v.getId()) {
           startActivity(RegisterAboutActivity.class);
        }
    }

    private void mregister() {
     //   startActivity(LoginActivity.class);
    //    String username = etRegisterUserName.getText().toString();
         mphone = etUserUsername.getText().toString();
        String code = et_yzm.getText().toString();
        String loginPwd = et_loginpassword.getText().toString();
        String businessPwd = et_zhifupwd.getText().toString();
        String refereeCode = etRegisterRefereeCode.getText().toString();
      /*  if (TextUtils.isEmpty(username)) {
            RxToast.showShort(getResources().getString(R.string.tip_username));
            return;
        }*/
        if (TextUtils.isEmpty(mphone)) {
            RxToast.showShort(getResources().getString(R.string.tip_phone));
            return;
        }
        if (TextUtils.isEmpty(code)) {
            RxToast.showShort(getResources().getString(R.string.tip_code));
            return;
        }
        if (TextUtils.isEmpty(loginPwd)) {
            RxToast.showShort(getResources().getString(R.string.tip_login_pwd));
            return;
        }

        if (TextUtils.isEmpty(businessPwd)) {
            RxToast.showShort(getResources().getString(R.string.tip_pay_pwd));
            return;
        }

        if (TextUtils.isEmpty(refereeCode)) {
            RxToast.showShort(getResources().getString(R.string.tip_referee));
            return;
        }

        Map params = new HashMap();
        params.put("invite_code", refereeCode);
        params.put("password", loginPwd);
        params.put("traspass", businessPwd);
        params.put("phone", mphone);
        params.put("code", code);
        params.put("quhao", "86");
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.REGISTER)
                 .tag(this)
                 .params("sign", sign)
                 .params("timestamp", StringUtil.getCurrentTime())
                 . params("invite_code", refereeCode)
                 .  params("password", loginPwd)
                 . params("traspass", businessPwd)
                  . params("phone", mphone)
                  . params("code", code)
                  . params("quhao", "86")
                  .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("user1","进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
//                            RxToast.showShort(body.getMessage());
                         //   finish();
                            startActivity(LoginActivity.class);
                        }
                    }
                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("user",response+"!!!!");

                    }
                });



    }
    /**
     * 密码的显示与隐藏
     * */
    @OnClick(R.id.action_siv_show_pwd)
    public void actionSivShowPwd(View view){
        LoginMgr.getInstance().isShowPassword(et_loginpassword,sivShowPwd);
    }

 /*   *//** 发送验证码*//*
    @OnClick(R.id.action_send_verify_code)
    public void   action_send_verify_code(View v) {

        timeCount = new TimeCount(60000, 1000);
        LoginMgr.getInstance().sendVerifyCode(mphone);
        timeCount.start();


        if (R.id.action_send_verify_code == v.getId()) {
           mphone = etUserUsername.getText().toString().trim();
            LoginMgr.getInstance().sendVerifyCode(mphone);
        }
    }*/

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
          //  actionSendverifycode.setTextColor(Color.parseColor(String.valueOf(R.color.colorWhite)));
        }
    }






}
