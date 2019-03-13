package com.wokun.dset.mainfragment;

import android.util.Log;

import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.home.LvealBean;
import com.wokun.dset.linshou.EcologyBean;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\14 0014.
 */

public class EcologyFragment extends BaseFragment{
      @BindView(R.id.bridge_web_view)
      BridgeWebView bridge_web_view;

    @Override
    public int createView() {
        return R.layout.activity_ecology;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        OkGo.<BaseResponse<EcologyBean>>post(Constants.BASE_URL + Constants.BUSINESS_NEW)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<EcologyBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<EcologyBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("user","进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            EcologyBean data = (EcologyBean) body.getData();
                            bridge_web_view.loadUrl(data.getUrl());
                        }else {
                            startActivity(LoginActivity.class);
                        }


                    }
                });

    }





}
