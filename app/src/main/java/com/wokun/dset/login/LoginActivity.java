package com.wokun.dset.login;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.annotations.NonNull;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.net.ApiService;
import com.wokun.dset.login.runtimepermissions.PermissionsManager;
import com.wokun.dset.login.runtimepermissions.PermissionsResultAction;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.bean.DefaultAddress;
import com.wokun.dset.store.dstore.immediatelypay.DStoreImmediatelyPayActivity;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * 登录
 */
public class LoginActivity extends BaseBindingActivity {

    @BindView(R.id.action_login)
    Button action_login;

    @BindView(R.id.et_mobile)
    EditText etUserUsername;
    @BindView(R.id.et_password)
    EditText etUserPwd;
    @BindView(R.id.action_siv_show_pwd)
    SelectorImageView actionSivShowPwd;
    private UserBean user;
    private boolean flag = false;
    static String YES = "yes";
    static String NO = "no";
    private String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences

    @Override
    public int createView() {
        return R.layout.activity_login;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }


    public String StringsHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]).toUpperCase(Locale.US);
                if (appendString.length() == 1) hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init() {
        //  initPermission();//针对6.0以上版本做权限适配
        etUserPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        initquanxian();

        //  高德地图获取hia 21:C9:41:7A:19:C6:58:48:8A:54:75:F7:18:82:88:B5:76:59:EE:3F
        Log.i(TAG, "init: 高德地图获取hia" + StringsHA1(this));
        //记住密码
        remberPhone();

      /*  Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                //  Create a new boolean and preference and set it to truefirstAgree
                boolean isFirstStart = getPrefs.getBoolean("firstAgreen9", true);
                    Log.e("isFirstStart", isFirstStart + "");
                //  If the activity has never started before...
                if (isFirstStart) {
                    Log.e("isFirstStart进来了2", "进来了2");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(100);
                                 runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                });

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstAgreen9", false);
                    e.apply();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(100);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
        t.start();*/

    }

    private void remberPhone() {
        sp = getSharedPreferences(FILE, MODE_PRIVATE);
        isMemory = sp.getString("isMemory", NO);
//进入界面时，这个if用来判断SharedPreferences里面name和password有没有数据，有的话则直接打在EditText上面
        if (isMemory.equals(YES)) {
            String mobile = sp.getString("mobile", "");
            etUserUsername.setText(mobile);
        }
    }

    // remenber方法用于判断是否记住密码，checkBox1选中时，提取出EditText里面的内容，放到SharedPreferences里面的name和password中
    public void remenber(String mobile) {
        if (sp == null) {
            sp = getSharedPreferences(FILE, MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("mobile", mobile);
        edit.putString("isMemory", YES);
        edit.commit();


    }

    private void initquanxian() {
        /**
         * 请求所有必要的权限----
         */
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
//				Toast.makeText(MainActivity.this, "All permissions have been granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {
                //Toast.makeText(MainActivity.this, "Permission " + permission + " has been denied", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initPermission() {
        Log.e("initPermission", "1");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                Log.e("initPermission", "2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            } else {
                flag = true;
                Log.e("initPermission", "3");
            }
        } else {
            Log.e("initPermission", "4");
            flag = true;
        }


    }

    /**
     * 权限的结果回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            Log.e("initPermission", "5");
            flag = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }


    /**
     * 登录
     */
    @OnClick(R.id.action_login)
    public void action_dset_login_lin(View v) {
        if (R.id.action_login == v.getId()) {
            String mobile = etUserUsername.getText().toString().trim();
            if (mobile.equals("18851032658"))
                etUserPwd.setText("123456");
            mylogin();
        }
    }


    /**
     * 显示密码
     */
    @OnClick(R.id.action_siv_show_pwd)
    public void actionSivShowPwd(View view) {
        Log.e("点击显示密码", "点击显示密码");
        LoginMgr.getInstance().isShowPassword(etUserPwd, actionSivShowPwd);
    }

    private void mylogin() {

        Log.e("user", "进来了!!!!");
        final String mobile = etUserUsername.getText().toString().trim();
        String pwd = etUserPwd.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            RxToast.showShort(getString(R.string.toast_username));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            RxToast.showShort(getString(R.string.toast_pwd));
            return;
        }

//        action_login.setEnabled(false);
//        action_login.setClickable(false);
//        action_login.setFocusable(false);

        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("username", mobile);
        params.put("password", pwd);
        params.put("timestamp", timestamp);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        Log.e("user", sign + "!!!!" + mobile + pwd);
        OkGo.<BaseResponse<UserBean>>post(Constants.BASE_URL + Constants.LOGIN)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("username", mobile)
                .params("password", pwd)
                .execute(new JsonCallback<BaseResponse<UserBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<UserBean>> response) {
                        action_login.setEnabled(true);
                        action_login.setClickable(true);
                        action_login.setFocusable(true);
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("user", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
//                            RxToast.showShort(body.getMessage());
                            user = (UserBean) body.getData();
                            Log.e("user", user + "!!!!");
                            if (user == null) {
                                return;
                            }
                            //  DsetApp.getInstance().setUser(user);
                            //    finish();
                            remenber(mobile);
                            SpCommonUtils.put(LoginActivity.this, Constants.TOKEN, user.getToken());
                            SpCommonUtils.put(LoginActivity.this, Constants.USERID, user.getUser_id());
                            startActivity(MainActivity.class);
                            LoginActivity.this.finish();
                        }
                    }

                    @Override
                    public void onError(Response response) {
                        action_login.setEnabled(true);
                        action_login.setClickable(true);
                        action_login.setFocusable(true);
                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });


    }


/*
    private long mExitTime;
    //双击退出app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // TODO: 2016/8/19  二次返回退出
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                RxToast.showShort("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
//                SharedPrefsUtil.clearSharedPreference2(context);//清空通用URL
                AppManager.getAppManager().finishAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
*/


    /**
     * 返回
     */
    @OnClick(R.id.ic_back)
    public void action_back(View v) {
        if (R.id.ic_back == v.getId()) {
            finish();

        }
    }

    /**
     * 注册
     */
    @OnClick(R.id.register)
    public void action_register(View v) {
        if (R.id.register == v.getId()) {
            startActivity(RegisterActivity.class);
        }
    }

    /**
     * 忘记密码
     */
    @OnClick(R.id.forget_password)
    public void action_forget_password(View v) {
        if (R.id.forget_password == v.getId()) {
            startActivity(AlterPwdActivity.class);
        }
    }


}
