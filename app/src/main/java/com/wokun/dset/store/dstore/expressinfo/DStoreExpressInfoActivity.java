package com.wokun.dset.store.dstore.expressinfo;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.adapter.DStoreOrderStateListAdapter;
import com.wokun.dset.store.bean.AilPayBean;
import com.wokun.dset.store.bean.BaseResPonse;
import com.wokun.dset.store.bean.ExpressIfnoBean;
import com.wokun.dset.store.bean.OrderStateBean;
import com.wokun.dset.store.dstore.dstorestate.DStoreStateActivity;
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.alertview.AlertViewContorller;
import com.wokun.dset.utils.alertview.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreExpressInfoActivity extends BaseActivity {


    private WebView mWebView;
    private ImageView back;

    @Override
    public int setActivityLayout() {
        return R.layout.activity_express_info;
    }

    @Override
    public void initView() {
        mWebView = findViewById(R.id.webview);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DStoreExpressInfoActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String orderid = intent.getStringExtra("orderid");
        expressInfo(orderid);
    }

    private void expressInfo(String order_id) {
        String token = (String) SpCommonUtils.get(DStoreExpressInfoActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreExpressInfoActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("order_id", order_id);
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.EXPRESS_INFO)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("order_id", order_id)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        ExpressIfnoBean expressIfnoBean = (ExpressIfnoBean) JosnFrom.getInstance().getObj(response.body().toString(), ExpressIfnoBean.class);
                        if (expressIfnoBean != null && expressIfnoBean.getStatus().equals("0001")) {
                            mWebView.loadUrl(expressIfnoBean.getData().getKd_url());
                        } else {
                            RxToast.showShort(expressIfnoBean.getMsg());
                            DStoreExpressInfoActivity.this.finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyWebView();
    }

    public void destroyWebView() {
//        mWebContainer.removeAllViews();
        if (mWebView != null) {
            mWebView.clearHistory();
            mWebView.clearCache(true);
            mWebView.loadUrl("about:blank");
            mWebView.freeMemory();
            mWebView.pauseTimers();
            mWebView = null;
        }
    }

}
