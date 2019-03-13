package com.wokun.dset.ucenter.quanyi.dashishop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.PayDetailsBean;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class PaydetailsActivity extends BaseBindingActivity {
    @BindView(R.id.detail_id)
    TextView detail_id;
    @BindView(R.id.detail_uid)
    TextView detail_uid;
    @BindView(R.id.detail_count)
    TextView detail_count;
    @BindView(R.id.detail_money)
    TextView detail_money;
    @BindView(R.id.detail_phone)
    TextView detail_phone;
    @BindView(R.id.detail_name)
    TextView detail_name;

    @BindView(R.id.detail_bank)
    TextView detail_bank;
    @BindView(R.id.detail_state)
    TextView detail_state;
    @BindView(R.id.detail_gua_time)
    TextView detail_gua_time;
    @BindView(R.id.detail_xia_time)
    TextView detail_xia_time;

//PayDetailsBean
    @Override
    public int createView() {
        return R.layout.activity_paydetails;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("付款信息");
    }

    @Override
    public void init() {
        String id = getIntent().getStringExtra("id");
        loadDataMessage(id);
    }

    private void loadDataMessage(String id) {
        Map params = new HashMap();
        params.put("id", id);
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        OkGo.<BaseResponse<PayDetailsBean>>post(Constants.BASE_URL + Constants.SINGLEORDRT)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<PayDetailsBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<PayDetailsBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("user","进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            PayDetailsBean data = (PayDetailsBean) body.getData();
                            if(data == null)return;
                            detail_id.setText(data.getId());
                            detail_uid.setText(data.getIn_userid());
                            detail_count.setText(data.getNumber());
                            detail_money.setText(data.getAmount());
                            detail_phone.setText(data.getPhone());
                            detail_name.setText(data.getRealname());
                            detail_bank.setText(data.getBank_num());
                            detail_state.setText(data.getStatus_description());
                            detail_gua_time.setText(data.getCreated_at());
                            detail_xia_time.setText(data.getUpdated_at());



                        }
                    }
                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("user",response+"!!!!");
                    }
                });





    }
}
