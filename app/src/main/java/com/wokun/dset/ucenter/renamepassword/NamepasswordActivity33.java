package com.wokun.dset.ucenter.renamepassword;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

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
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * 重置支付密码
 */
public class NamepasswordActivity33 extends BaseBindingActivity {
    private String mobilecode;
    private String inputContent;
    private String phoneNum;
    private String newpwd;
    private String newpwdagain;
    @BindView(R.id.icv)
    VerificationCodeView icv;

    @Override
    public int createView() {
        return R.layout.activity_namepassword3;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("重置支付密码")
                .setMenu("完成", null)
                .setMenuTextColor(Color.parseColor("#ffffff"))
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(RenamepaypwdActvitity.class);
                    }
                }, null);
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        mobilecode = intent.getStringExtra("mobilecode");
        newpwd = intent.getStringExtra("newpwd");
        phoneNum = intent.getStringExtra("phoneNum");

        icv.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                inputContent = icv.getInputContent();
                if (inputContent.length() == 6) {
                    newpwdagain = inputContent;
                }
                Log.i("icv_input", icv.getInputContent());
            }

            @Override
            public void deleteContent() {
                Log.i("icv_delete", icv.getInputContent());
            }
        });
    }

    /**
     * 重置
     */
    @OnClick(R.id.txt_next3)
    public void action_txt_next3(View v) {
        if (R.id.txt_next3 == v.getId()) {
            if (newpwdagain.equals(newpwd)) {
                commit();
             /*   Intent intent=new Intent(NamepasswordActivity3.this, ChangpwdsuccessActivity.class);
                startActivity(intent);*/
            } else {
                RxToast.showShort("原密码与新密码不同");
            }
        }
    }

    private void commit() {
      /*  String phoneNum2 = phoneNum.substring(2);
        Log.e("phoneNum",phoneNum2);*/
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("strphone", phoneNum);
        params.put("code", mobilecode);
        params.put("traspass", newpwdagain);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        Log.e("修改忘记密码信息", "修改密码信息：" + phoneNum + mobilecode + newpwdagain);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FORGET_PAY_PWD)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("strphone", phoneNum)
                .params("code", mobilecode)
                .params("traspass", newpwdagain)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("修改忘记密码信息", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            showpayDialog();
                        } else if (body.getStatus().equals("0002")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);
                        } else if (body.getStatus().equals("0003")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);

                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("首页3", response + "!!!!");
                        // DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });


    }

    private void showpayDialog() {

        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.activity_changpwdsuccess, null);
        customizeDialog.setView(dialogView);
        customizeDialog.setCancelable(true).create();
        final AlertDialog myDialog = customizeDialog.show();
        myDialog.dismiss();

    }

}
