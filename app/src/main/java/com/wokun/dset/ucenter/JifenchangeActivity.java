package com.wokun.dset.ucenter;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.hudongshop.ServiceZhihiuiActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.AssetsAddBean;
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class JifenchangeActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_jifen)String title;
    @BindView(R.id.ucenter_dsjifen)TextView ucenter_dsjifen;
    @BindView(R.id.ucenter_dsyue)TextView ucenter_dsyue;
    @BindView(R.id.edt_yue_count)EditText edt_yue_count;
    @BindView(R.id.jifen_xiane)TextView jifen_xiane;
    @BindView(R.id.jifen_bili)TextView jifen_bili;


    @BindView(R.id.swipeRefreshLayout)SwipeRefreshLayout swipeRefreshLayout;


    private  String  inputContent;
    private  String  pass;
    @Override
    public int createView() {
        return R.layout.activity_jifenchange;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title)
                .setMenu("兑换记录",null)
                .setMenuTextColor(Color.parseColor("#ffffff"))
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(DuihuanDetailsActivity.class);
                    }
                },null);
    }

    private void showCustomizeDialog() {
        final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(JifenchangeActivity.this);
        final View dialogView = LayoutInflater.from(JifenchangeActivity.this)
                .inflate(R.layout.dialog_jifen_success,null);
        customizeDialog.setView(dialogView);
        customizeDialog.setCancelable(true).create();
        customizeDialog.show();
    }

    @Override
    public void init() {
        loadDataMessage();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //    RxToast.showShort("我刷新了");
                loadDataMessage();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadDataMessage() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来dsa2211","进来来dsa22");
        OkGo.<BaseResponse<AssetsAddBean>>post(Constants.BASE_URL + Constants.ASSETS_ADD)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new DialogCallback<BaseResponse<AssetsAddBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AssetsAddBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            AssetsAddBean assetsAddBean = (AssetsAddBean) body.getData();
                            ucenter_dsjifen.setText(assetsAddBean.getHcg_wa());
                            jifen_xiane.setText(assetsAddBean.getFt_limit());
                            ucenter_dsyue.setText(assetsAddBean.getCash_wa());
                            jifen_bili.setText(assetsAddBean.getRatio());
                        }
                    }
                });

    }


    /** 立即转账*/
    @OnClick(R.id.ucenter_zhuan)
    public void action_ucenter_zhuan(View v){
        if(R.id.ucenter_zhuan == v.getId()){
            showpayDialog();
        }
    }

    private void showpayDialog() {

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

    private void loadData(String pass) {
        String edtyuecount = edt_yue_count.getText().toString().trim();
          if(TextUtils.isEmpty(edtyuecount))
          {
              RxToast.showShort("请输入兑换数量");
              return;
          }
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("number", edtyuecount);
        params.put("password", pass);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来dsa2211","进来来dsa22");
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ASSETS_ADD_CHECK)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("number", edtyuecount)
                .params("password", pass)
                .execute(new DialogCallback<BaseResponse<Object>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            showCustomizeDialog();
                        }
                    }
                });













    }


}
