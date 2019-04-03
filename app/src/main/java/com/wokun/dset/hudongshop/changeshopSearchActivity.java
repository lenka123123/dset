package com.wokun.dset.hudongshop;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.R;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.ucenter.addcards.OnItemClickListener;
import com.wokun.dset.utils.LocationUtils;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class changeshopSearchActivity extends BaseActivity {

    private EditText search;
    private RecyclerView mRecycler;
    private SearchRecyclerAdapter recyclerAdapter;

    @Override
    public int setActivityLayout() {
        return R.layout.activity_near_search;
    }

    @Override
    public void initView() {

        search = findViewById(R.id.search);
        mRecycler = findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(context));
        recyclerAdapter = new SearchRecyclerAdapter();
        mRecycler.setAdapter(recyclerAdapter);
    }

    @Override
    public void initData() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loadData(editable.toString());
            }
        });

    }

    private void loadData(String msg) {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("lat", String.valueOf(LocationUtils.getLatLng().latitude));
        params.put("long", String.valueOf(LocationUtils.getLatLng().longitude));
        params.put("type", "0");
        params.put("keywords ", msg);
        params.put("page ", "1");
        params.put("page_size", "10");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse2<ShopBean>>post(Constants.BASE_URL + Constants.BUSINESS_LIST)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("lat", String.valueOf(LocationUtils.getLatLng().latitude))
                .params("long", String.valueOf(LocationUtils.getLatLng().longitude))
                .params("type", "0")
                .params("keywords", msg)
                .params("page ", "1")
                .params("page_size", "10")
                .execute(new DialogCallback<BaseResponse2<ShopBean>>(context) {
                    @Override
                    public void onSuccess(Response<BaseResponse2<ShopBean>> response) {
                        BaseResponse2 body = response.body();
                        if (body == null) return;
                        if (body.getStatus().equals("0001")) {
                            if (body.getData() == null) return;
                            recyclerAdapter.setData((List<ShopBean>) body.getData());
                        } else {
                            RxToast.showShort(body.getMessage());
                        }
                    }
                });

    }
}
