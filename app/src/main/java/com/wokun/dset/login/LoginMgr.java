package com.wokun.dset.login;


import android.app.Activity;
import android.content.Intent;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.MD5Util;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.DsetApp;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.PostPictureBean;
import com.wokun.dset.utils.MD5.Md5Encrypt;
import com.wokun.dset.utils.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;


public class LoginMgr {

    private LoginMgr(){

    }


    private static class LoginMgrHolder{
        private static LoginMgr instance = new LoginMgr();
    }

    public static LoginMgr getInstance(){
        return LoginMgrHolder.instance;
    }


    public String getSign(Map<String, String> removeMap, Map<String, String> resultMap, Map params){
        StringBuffer buffer = new StringBuffer();
        for (Iterator<String> it = resultMap.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            buffer.append(key + "=" + params.get(key) + "&");
        }
        String b = buffer.substring(0, buffer.length()-1) + Constants.KEY;
//		String loge = buffer.append("key"+"="+mkey).toString();
        Log.e("lgl","params:"+b);
        return Md5Encrypt.md5(b);
    }


    /**
     * 取消订单
     * */
    public void cancelDindan(String id) {

        Map params = new HashMap();
        params.put("id",id);
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.CANCEL_DINDAN)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                . params("id", id)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("取消订单","取消订单!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());

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
     * 注册账号
     * @param mobile 手机号码
     * @param password 密码
     * @param code 验证码
     * */
/*    public void register(String mobile, String password, String code,String inviteCode) {
        if(TextUtils.isEmpty(mobile)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(password)){
            RxToast.showShort(Constants.NULL_PWD_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(code)){
            RxToast.showShort(Constants.NULL_CODE_MESSAGE);
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.REGISTER_URL)
                .tag(this)
                .params(Constants.CODE, code)
                .params(Constants.MOBILE, mobile)
                .params(Constants.INVITE_CODE, inviteCode)
                .params(Constants.PASSWORD, MD5Util.encrypt(password))
                .params(Constants.LOGIN_TYPE, Constants.ANDROID)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMsg());
                    }
                });
    }*/

    /**
     * 获取验证码
     * @param mobile 手机号码
     * */
    /**
     * 获取验证码
     */
   public void sendVerifyCode(String mobile) {
        if(TextUtils.isEmpty(mobile)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
       Map params = new HashMap();
       params.put("quhao", "86");
       params.put("phone", mobile);
       // params.put("method", "intsendmsg");
       params.put("timestamp", StringUtil.getCurrentTime());
       Map<String, String> removeMap = removeEmptyData(params);
       Map<String, String> resultMap = sortMapByKey(removeMap);

       String sign= LoginMgr.getInstance().getSign(removeMap, resultMap, params);
       Log.e("lgl","sign:"+sign);

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.GET_CODE)
                .tag(this)
                . params("quhao", "86")
                . params("phone", mobile)
                . params("sign", sign)
                . params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMessage());
                        Log.e("lgl","sign:"+body.getMessage());
                    }
                });









    }

    /**
     * 忘记密码
     * @param mobile 手机号码
     * @param newPwd 新密码
     * @param code 验证码
     * */
    public void alterPassword(final Activity activity, String mobile, String newPwd, String code) {
        if(TextUtils.isEmpty(mobile)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(newPwd)){
            RxToast.showShort(Constants.NULL_PWD_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(code)){
            RxToast.showShort(Constants.NULL_CODE_MESSAGE);
            return;
        }
        Map params = new HashMap();
//        params.put("username", account);
        params.put("quhao","86");
        params.put("phone", mobile);
        params.put("code", code);
        params.put("password", newPwd);
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
                . params("code", code)
                . params("password", newPwd)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("忘记密码","忘记密码!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            //   finish();

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
     * 是否显示密码
     * @param etPassword EditText
     * @param sivIsShowPwd SelectorImageView
     * */
    public void isShowPassword(EditText etPassword, SelectorImageView sivIsShowPwd){
        if (sivIsShowPwd.isChecked()) {
            sivIsShowPwd.toggle(false);
            //设置EditText文本为隐藏的
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            sivIsShowPwd.toggle(true);
            //设置EditText文本为可见的
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        etPassword.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = etPassword.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    /**
     * 登录
     * @param mobile 手机号码
     * */












    /**
     * 退出登录
     * @param activity Activity
     * */

    public void logout(final Activity activity) {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign= LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        Log.e("lgl","sign:"+sign);

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.LOGOUT)//
                .tag(activity)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            DsetApp.getInstance().clear();
                            activity.setResult(Constants.SETTING_RESULT_CODE);
                            activity.finish();

                        }
                   //     Toast.makeText(activity, body.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
    }






}
