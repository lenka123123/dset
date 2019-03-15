package com.wokun.dset.store.dstore.dstoredetail;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.bean.DStoreGoodsDetail;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreDetailActivity extends BaseActivity {

    private String goods_id;
    private TextView goods_name;
    private TextView price;
    private TextView free_send;
    private TextView search_goods_size;
    private ImageView store_logo;
    private TextView cash_deposit;
    private TextView store_detail_point;
    private TextView store_attitude_point;
    private TextView store_send_point;
    private WebView webView;

    @Override
    public int setActivityLayout() {
        return R.layout.activity_goods_infoes;
    }

    @Override
    public void initView() {
        super.initView();
        goods_name = findViewById(R.id.goods_name);
        price = findViewById(R.id.price);
        free_send = findViewById(R.id.free_send);
        search_goods_size = findViewById(R.id.search_goods_size);
        store_logo = findViewById(R.id.store_logo);
        cash_deposit = findViewById(R.id.cash_deposit);
        store_detail_point = findViewById(R.id.store_detail_point);
        //态度
        store_attitude_point = findViewById(R.id.store_attitude_point);
        store_send_point = findViewById(R.id.store_send_point);
        webView = findViewById(R.id.webview);
        Intent intent = getIntent();
        goods_id = intent.getStringExtra("goods_id");
    }

    private void detailData(DStoreGoodsDetail goodsDetail) {
        if (goodsDetail == null || goodsDetail.getData() == null) return;
        goods_name.setText(goodsDetail.getData().getGoods_name());
        price.setText(goodsDetail.getData().getPrice());





    }


    @Override
    public void initData() {
        super.initData();
        getStoreInfo();
    }

    private void getStoreInfo() {
        String token = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.USERID, "");

        Map params = new HashMap();
        params.put("goods_id", goods_id);
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", StringUtil.getCurrentTime());
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.STORE_GOODS_DETAIL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("user_id", user_id)
                .params("token", token)
                .params("goods_id", goods_id)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        DStoreGoodsDetail goodesDetail = (DStoreGoodsDetail) JosnFrom.getInstance().getObj(response.body().toString(), DStoreGoodsDetail.class);
                        if (goodesDetail != null && goodesDetail.getStatus().equals("0001")) {
                            detailData(goodesDetail);
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
