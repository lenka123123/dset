package com.wokun.dset.ucenter.renamepassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.login.RegisterActivity;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.GetMobileBean;
import com.wokun.dset.utils.StringUtil;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
    重置支付密码
*
* */
public class RenamepaypwdActvitity extends BaseBindingActivity {

    @BindView(R.id.my_phone)
    TextView myPhone;


    private String phoneNum;

    @Override
    public int createView() {
        return R.layout.activity_renamepaypwd_actvitity;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("重置支付密码");
    }

    @Override
    public void init() {
        loadData();

    }

    private void loadData() {

        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
         String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<GetMobileBean>>post(Constants.BASE_URL + Constants.GET_MOBILE)
                .tag(this)
                .params("sign",sign)
                .params("timestamp",StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<GetMobileBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<GetMobileBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMessage());
                        if(body.getStatus().equals("0001")){
                            GetMobileBean    getMobileBean = (GetMobileBean) body.getData();
                            phoneNum = getMobileBean.getPhone();
                            myPhone.setText("您正在为"+phoneNum+"重置密码");
                            RxToast.showShort(body.getMessage());

                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<GetMobileBean>> response) {
                        super.onError(response);

                        RxToast.showShort("文件上传出错");
                    }
                });






    }

    /** 重置没有忘记密码*/
    @OnClick(R.id.re_name_get)
    public void action_re_name_get(View v){
        if(R.id.re_name_get == v.getId()){
            Intent intent = new Intent(RenamepaypwdActvitity.this, NamepasswordActivity.class);
            intent.putExtra("phoneNum",phoneNum);
            startActivity(intent);
        }
    }

    /** 重置忘记密码*/
    @OnClick(R.id.re_name_forget)
    public void action_re_name_forget(View v){
        if(R.id.re_name_forget == v.getId()){
            Intent intent = new Intent(RenamepaypwdActvitity.this, ForgetpwdActivity.class);
            intent.putExtra("phoneNum",phoneNum);
            startActivity(intent);
        }
    }



}
