package com.wokun.dset.ucenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.response.BaseResponse4;
import com.wokun.dset.ucenter.bean.RollOutBean;
import com.wokun.dset.ucenter.bean.ZhuanzhangBean;
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.DsetApp.getContext;
import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class ZhuanzhangActivity extends BaseBindingActivity {

    @BindView(R.id.txt_zhuanzhang)
    TextView txtZhuanzhang;
    @BindView(R.id.zhuanzhang_yue2)
    TextView zhuanzhangyue2;

    @BindView(R.id.zhuanzhang_phone)
    EditText zhuanzhang_phone;
    @BindView(R.id.zhuanzhang_uid)
    EditText zhuanzhang_uid;
    @BindView(R.id.zhuanzhang_yue)
    EditText zhuanzhang_yue;

    private  String  inputContent;
    private  String  pass;

    private  String zhuanzhangUid;
    private String zhuanzhangPhone;
    private String zhuanzhangYue;
    @Override
    public int createView() {
        return R.layout.activity_zhuanzhang;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("转账")
                .setMenu("转账记录",null)
                .setMenuTextColor(Color.parseColor("#ffffff"))
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(ZhuanzhangDetailsActivity.class);
                    }
                },null);
    }

    @Override
    public void init() {

      if(getIntent() !=null){
          String uid = getIntent().getStringExtra("uid");
          String last_phone = getIntent().getStringExtra("last_phone");
          zhuanzhang_uid.setText(uid);
          zhuanzhang_phone.setText(last_phone);
          loadDataMessage();
      }
        loadDataMessage();

    }

    private void loadDataMessage() {

        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来dsa2211","进来来dsa22");
        OkGo.<BaseResponse<RollOutBean>>post(Constants.BASE_URL + Constants.ROLL_OUT)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new DialogCallback<BaseResponse<RollOutBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<RollOutBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            RollOutBean data = (RollOutBean) body.getData();
                            if(data == null)return;
                              zhuanzhangyue2.setText("金票："+data.getCash_wa());

                        }
                    }
                });

    }

    /** 提交转账*/
    @OnClick(R.id.txt_zhuanzhang)
    public void action_zhuanzhang(View v){
        if(R.id.txt_zhuanzhang == v.getId()){
          Log.e("我进来了提交转账","我进来了");

            showpayDialog();

        }
    }

    private void showpayDialog() {
        Log.e("我进来了提交转账","我进来了2");
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.pop_layout3, null);
        customizeDialog.setView(dialogView);
        final AlertDialog myDialog = customizeDialog.show();
        ImageView delete_pop = (ImageView) dialogView.findViewById(R.id.delete_pop);
        Button actionSubmit = (Button) dialogView.findViewById(R.id.btnCommit);
        //    EditInputView edit1 = (EditInputView)dialogView.findViewById(R.id.edit1);
        final VerificationCodeView icv = (VerificationCodeView) dialogView.findViewById(R.id.icv);
        delete_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });


        icv.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                inputContent = icv.getInputContent();
                if (inputContent.length() == 6) {
                    pass = inputContent;
                }
                Log.i("icv_input", icv.getInputContent());
            }

            @Override
            public void deleteContent() {
                Log.i("icv_delete", icv.getInputContent());
            }
        });
        actionSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();

                loadData(pass);
            }
        });





    }

    private void loadData(final String pass) {
         zhuanzhangPhone = zhuanzhang_phone.getText().toString().trim();
         zhuanzhangYue = zhuanzhang_yue.getText().toString().trim();
         zhuanzhangUid = zhuanzhang_uid.getText().toString().trim();
        if(TextUtils.isEmpty(zhuanzhangPhone))
        {
            RxToast.showShort("对方手机后四位不能为空");
            return;
        }
        if(TextUtils.isEmpty(zhuanzhangYue))
        {
            RxToast.showShort("转账金额不能为空");
            return;
        }
        if(TextUtils.isEmpty(zhuanzhangUid))
        {
            RxToast.showShort("转账的UID不能为空");
            return;
        }
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("inAccount", zhuanzhangUid);
        params.put("last_phone", zhuanzhangPhone);
        params.put("out_num", zhuanzhangYue);
        params.put("password", pass);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来dsa22121","进来来dsa22");
        OkGo.<BaseResponse4<ZhuanzhangBean>>post(Constants.BASE_URL + Constants.ROLL_OUT_CHECK)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("inAccount", zhuanzhangUid)
                .params("last_phone", zhuanzhangPhone)
                .params("out_num", zhuanzhangYue)
                .params("password", pass)
                .execute(new JsonCallback<BaseResponse4<ZhuanzhangBean>>() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onSuccess(Response<BaseResponse4<ZhuanzhangBean>> response) {
                        BaseResponse4 body = response.body();
                        if(body == null)return;


                        Log.e("转账状态1",""+body.getStatus());
                        Log.e("转账状态2",""+body.getMessage());

                        if (body.getStatus()==0001) {
                            RxToast.showShort(body.getMessage());
                            finish();
                        }
                     else   if(body.getStatus()==0005){
                            ZhuanzhangBean data =(ZhuanzhangBean) body.getData();
                            Log.e("转账状态3",  data.toString());
                            jiediandialog(body.getMessage(), data ,pass);
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse4<ZhuanzhangBean>> response) {
                        super.onError(response);

                        Log.e("转账状态4",  response.message()+"&&&"+response.toString());

                    }
                });





    }

    private void jiediandialog(String mymsg, ZhuanzhangBean data, final String password ) {//pop_zhuanzhang

        Log.e("景来了","景来了");
        final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(ZhuanzhangActivity.this);
        final View dialog = LayoutInflater.from(ZhuanzhangActivity.this)
                .inflate(R.layout.pop_zhuanzhang,null);

        ImageView delete_pop = (ImageView) dialog.findViewById(R.id.delete_pop);

        TextView zhuanzhuang_context = (TextView) dialog.findViewById(R.id.zhuanzhuang_context);
        TextView benci_zhuanzhang = (TextView) dialog.findViewById(R.id.benci_zhuanzhang);
        TextView shouxu_zhuanzhang = (TextView) dialog.findViewById(R.id.shouxu_zhuanzhang);
        TextView shiji_zhuanzhang = (TextView) dialog.findViewById(R.id.shiji_zhuanzhang);
        TextView zhuanzhang_cancel = (TextView) dialog.findViewById(R.id.zhuanzhang_cancel);
        TextView sure_zhuanzhang = (TextView) dialog.findViewById(R.id.sure_zhuanzhang);

        zhuanzhuang_context.setText(mymsg);
        benci_zhuanzhang.setText(data.getOut_num()+"");
        shouxu_zhuanzhang.setText(data.getShouxu()+"");
        shiji_zhuanzhang.setText(data.getPay_num()+"");
        customizeDialog.setView(dialog);
        final AlertDialog s = customizeDialog.show();
        delete_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.dismiss();
            }
        });
        zhuanzhang_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.dismiss();
            }
        });
        sure_zhuanzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("进来了进来了2","进来了");
                Map params = new HashMap();
                params.put("timestamp", StringUtil.getCurrentTime());
                params.put("inAccount", zhuanzhangUid);
                params.put("last_phone", zhuanzhangPhone);
                params.put("out_num", zhuanzhangYue);
                params.put("password", password);
                Map<String, String> removeMap = removeEmptyData(params);
                Map<String, String> resultMap = sortMapByKey(removeMap);
                String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
                OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.NODE_TURNOUT)
                        .tag(this)
                        .params("sign", sign)
                        .params("timestamp", StringUtil.getCurrentTime())
                        .params("inAccount", zhuanzhangUid)
                        .params("last_phone", zhuanzhangPhone)
                        .params("out_num", zhuanzhangYue)
                        .params("password", pass)
                        .execute(new JsonCallback<BaseResponse<Object>>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(Response<BaseResponse<Object>> response) {
                                BaseResponse body = response.body();
                                if(body == null)return;
                                if (body.getStatus().equals("0001")) {
                                    RxToast.showShort(body.getMessage());
                                    Log.e("进来了进来了","进来了");
                                finish();
                                }

                            }

                            @Override
                            public void onError(Response<BaseResponse<Object>> response) {
                                super.onError(response);

                             ;

                            }
                        });
            }
        });








    }

}
