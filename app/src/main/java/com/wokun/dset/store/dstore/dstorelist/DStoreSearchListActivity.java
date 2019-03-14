package com.wokun.dset.store.dstore.dstorelist;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView tv_total_search;
    private TextView tv_sales_search;
    private TextView tv_price_search;
    private TextView tv_search_search;
    private int array[] = {0, 0, 0, 0};

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

        tv_total_search = findViewById(R.id.tv_total_search);
        tv_sales_search = findViewById(R.id.tv_sales_search);
        tv_price_search = findViewById(R.id.tv_price_search);
        tv_search_search = findViewById(R.id.tv_search_search);

        findViewById(R.id.linearlayout_total_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_sales_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_price_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_search_search).setOnClickListener(this);
    }



    private void changeStatus(boolean total, boolean sales, boolean price, boolean search) {
        tv_total_search.setTextColor(total ? getResources().getColor(R.color.color_05_61_96) : getResources().getColor(R.color.color_3_3_3));
        tv_sales_search.setTextColor(sales ? getResources().getColor(R.color.color_05_61_96) : getResources().getColor(R.color.color_3_3_3));
        tv_price_search.setTextColor(price ? getResources().getColor(R.color.color_05_61_96) : getResources().getColor(R.color.color_3_3_3));
        tv_search_search.setTextColor(search ? getResources().getColor(R.color.color_05_61_96) : getResources().getColor(R.color.color_3_3_3));

        mImg_total_search.setBackgroundResource(total ? R.drawable.single_down_blue : R.drawable.single_down_black);
        if (array[1] == 1) {
            if (sales) {
                mImg_sales_search.setBackgroundResource(change ? R.drawable.double_up_blue : R.drawable.double_down_blue);

            } else {
                mImg_sales_search.setBackgroundResource(R.drawable.double_black);
            }
        }

        if (array[3] == 1)
            mImg_search_search.setBackgroundResource(search ? R.drawable.search_blue : R.drawable.search_black);
    }

    private boolean sales = false;
    private boolean change = false;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearlayout_total_search:
                array[0] = 1;
                sales = false;
                change = false;
                changeStatus(true, false, false, false);
                break;
            case R.id.linearlayout_sales_search:
                array[1] = 1;
                change = !change;
                sales = true;
                changeStatus(false, true, false, false);
                break;
            case R.id.linearlayout_price_search:
                array[2] = 1;
                sales = false;
                change = false;
                changeStatus(false, false, true, false);
                break;
            case R.id.linearlayout_search_search:
                array[3] = 1;
                sales = false;
                change = false;
                changeStatus(false, false, false, true);
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
