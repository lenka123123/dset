package com.wokun.dset.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.login.net.MyJsonCallback;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.adapter.BeautyHomeAdapter;
import com.wokun.dset.store.bean.DStoreHome;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import cn.iwgang.countdownview.CountdownView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DsyueshopActivity extends BaseBindingActivity {

    private SmartRefreshLayout mEasyRefreshLayout;
    private RecyclerView mRecyclerView;
    private BeautyHomeAdapter mAdapter;

    @Override
    public int createView() {
        return R.layout.activity_dsytshop;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("商城");
    }

    @Override
    public void init() {

        mEasyRefreshLayout = (SmartRefreshLayout) findViewById(R.id.easylayout);
        mEasyRefreshLayout.setEnableRefresh(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new BeautyHomeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);


        getStoreInfo();
        addHeaderView();
    }

    private void addHeaderView() {
        View header = LayoutInflater.from(this).inflate(R.layout.store_home_head_item, null);
        initBear(header);
        mAdapter.addHeaderView(header);
    }

    private void initBear(View header) {

    }

    private void getStoreInfo() {

        String token = (String) SpCommonUtils.get(DsyueshopActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DsyueshopActivity.this, Constants.USERID, "");

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
                        Log.e("usergetMsg: ", dStoreHome.getMsg());


                    }

                    @Override
                    public void onError(Response response) {
                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });


    }

}
