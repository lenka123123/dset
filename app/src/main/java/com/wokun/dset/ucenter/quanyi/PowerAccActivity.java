package com.wokun.dset.ucenter.quanyi;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.bean.DStoreGoodsDetail;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;
import com.wokun.dset.ucenter.quanyi.dashishop.GoodsOrderActivity;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.PopWindow;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class PowerAccActivity extends BaseBindingActivity {
    @BindView(R.id.webview)
    WebView mWebView;

    @BindView(R.id.apply_et)
    EditText apply_et;

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

    @Override
    public int createView() {
        return R.layout.activity_power_acc;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }


    @Override
    public void init() {
        mWebView.loadUrl("http://api.dasether.com/user/release-rule.html");
    }

    @OnClick(R.id.apply_selease)
    public void apply(View view) {
        getStoreInfo();
    }

    @OnClick(R.id.back)
    public void back(View view) {
        PowerAccActivity.this.finish();
    }


    private void getStoreInfo() {
        if (apply_et.getText().toString().trim().length() < 1) {
            ToastUtil.show(PowerAccActivity.this, "请输入内容", Toast.LENGTH_LONG);
            return;
        }

        String token = (String) SpCommonUtils.get(PowerAccActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(PowerAccActivity.this, Constants.USERID, "");

        Map params = new HashMap();
        params.put("content", apply_et.getText().toString().trim());
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", StringUtil.getCurrentTime());
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.APPLY_RELEASE)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("user_id", user_id)
                .params("token", token)
                .params("content", apply_et.getText().toString().trim())
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        DStoreGoodsDetail goodesDetail = (DStoreGoodsDetail) JosnFrom.getInstance().getObj(response.body().toString(), DStoreGoodsDetail.class);
                        if (goodesDetail != null && goodesDetail.getStatus().equals("0001")) {
                            ToastUtil.show(PowerAccActivity.this, "申请提交成功", Toast.LENGTH_LONG);
                            PowerAccActivity.this.finish();
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
