package com.wokun.dset.store.dstore.dstorelist;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.home.DsyueshopActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.bean.DStoreHome;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreSearchListActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mImg_total_search;
    private ImageView mImg_sales_search;
    private ImageView mImg_search_search;

    @Override
    public int setActivityLayout() {

        return R.layout.activity_store_list;
    }

    @Override
    public void initView() {
        super.initView();
        mImg_total_search = findViewById(R.id.img_total_search);
        mImg_sales_search = findViewById(R.id.img_sales_search);
        mImg_search_search = findViewById(R.id.img_search_search);

        findViewById(R.id.linearlayout_total_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_sales_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_price_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_search_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearlayout_total_search:
                break;
            case R.id.linearlayout_sales_search:
                break;
            case R.id.linearlayout_price_search:
                break;
            case R.id.linearlayout_search_search:
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();
    }


    private void getStoreInfo() {

        String token = (String) SpCommonUtils.get(DStoreSearchListActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreSearchListActivity.this, Constants.USERID, "");

        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", StringUtil.getCurrentTime());
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.STORE_HOME_URL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("user_id", user_id)
                .params("token", token)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
//                        User user = gson.fromJson(userJson, User.class);
                        DStoreHome dStoreHome = (DStoreHome) JosnFrom.getInstance().getObj(response.body().toString(), DStoreHome.class);
                        if (dStoreHome != null && dStoreHome.getStatus().equals("0001")) {
//                            detailData(dStoreHome);
                        }


                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });


    }

}
