package com.wokun.dset.store.dstore.dstorelist;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.adapter.DStoreGoodsListAdapter;
import com.wokun.dset.store.bean.DStoreGoodesList;
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
    private ImageView mImg_price_search;
    private TextView tv_total_search;
    private TextView tv_sales_search;
    private TextView tv_price_search;
    private TextView tv_search_search;
    private int array[] = {0, 0, 0, 0};
    private SmartRefreshLayout mEasyRefreshLayout;
    private RecyclerView mRecyclerView;
    private DStoreGoodsListAdapter mAdapter;
    private EditText search_ed;

    @Override
    protected void onDestroy() {
        array = null;
        mAdapter.getData().clear();
        mAdapter = null;
        super.onDestroy();
    }

    @Override
    public int setActivityLayout() {
        return R.layout.activity_store_list;
    }

    @Override
    public void initView() {
        super.initView();
        Intent intent = getIntent();
        category_id = intent.getStringExtra("category_id");
        search_ed = findViewById(R.id.search_ed);

        mImg_total_search = findViewById(R.id.img_total_search);
        mImg_sales_search = findViewById(R.id.img_sales_search);
        mImg_search_search = findViewById(R.id.img_search_search);
        mImg_price_search = findViewById(R.id.ing_price_search);

        tv_total_search = findViewById(R.id.tv_total_search);
        tv_sales_search = findViewById(R.id.tv_sales_search);
        tv_price_search = findViewById(R.id.tv_price_search);
        tv_search_search = findViewById(R.id.tv_search_search);

        findViewById(R.id.linearlayout_total_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_sales_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_price_search).setOnClickListener(this);
        findViewById(R.id.linearlayout_search_search).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);

        mEasyRefreshLayout = (SmartRefreshLayout) findViewById(R.id.easylayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mEasyRefreshLayout.setEnableRefresh(false);


        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new DStoreGoodsListAdapter(context);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void changeStatus(boolean total, boolean sales, boolean price, boolean search) {
        changeValue(total, sales, price);
        tv_total_search.setTextColor(total ? getResources().getColor(R.color.color_05_61_96) : getResources().getColor(R.color.color_3_3_3));
        tv_sales_search.setTextColor(sales ? getResources().getColor(R.color.color_05_61_96) : getResources().getColor(R.color.color_3_3_3));
        tv_price_search.setTextColor(price ? getResources().getColor(R.color.color_05_61_96) : getResources().getColor(R.color.color_3_3_3));
        tv_search_search.setTextColor(search ? getResources().getColor(R.color.color_05_61_96) : getResources().getColor(R.color.color_3_3_3));

        mImg_total_search.setBackgroundResource(total ? R.drawable.single_down_blue : R.drawable.single_down_black);
        if (array[1] == 1) {
            if (sales) {
                mImg_sales_search.setBackgroundResource(changeSales ? R.drawable.double_up_blue : R.drawable.double_down_blue);
            } else {
                mImg_sales_search.setBackgroundResource(R.drawable.double_black);
            }
        }
        if (array[2] == 1) {
            if (price) {
                mImg_price_search.setBackgroundResource(changePrice ? R.drawable.double_up_blue : R.drawable.double_down_blue);
            } else {
                mImg_price_search.setBackgroundResource(R.drawable.double_black);
            }
        }

        if (array[3] == 1)
            mImg_search_search.setBackgroundResource(search ? R.drawable.search_blue : R.drawable.search_black);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearlayout_total_search:
                array[0] = 1;
                changeSales = false;
                changePrice = false;
                changeStatus(true, false, false, false);
                break;
            case R.id.linearlayout_sales_search:
                array[1] = 1;
                changeSales = !changeSales;
                changePrice = false;
                changeStatus(false, true, false, false);
                break;
            case R.id.linearlayout_price_search:
                array[2] = 1;
                changeSales = false;
                changePrice = !changePrice;
                changeStatus(false, false, true, false);
                break;

            case R.id.linearlayout_search_search:
                array[3] = 1;
                changeSales = false;
                changePrice = false;
                changeStatus(false, false, false, true);
                break;
            case R.id.back:
                DStoreSearchListActivity.this.finish();
                break;
        }
    }


    @Override
    public void initData() {
        super.initData();
        mEasyRefreshLayout.setDisableContentWhenLoading(true);
        // 禁止刷新
//        mEasyRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                currentPage = 1;
//                getShopList();
//                refreshlayout.finishRefresh();
//            }
//        });
        //加载更多
        mEasyRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                shopList();
                refreshlayout.finishLoadmore();
            }
        });

        search_ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入的内容变化的监听
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // 输入前的监听

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入后的监听
                mAdapter.getData().clear();
                mAdapter.notifyDataSetChanged();
                keywords = s.toString();
                page = "1";
                shopList();
            }
        });

        shopList();
    }

    private String category_id = "";  // 商品分类id 见备注
    private String keywords = "";   // 关键字

    private String order = "";     // 排序方式 取值sales（销量）、price价格、create_time添加时间
    private String sort = "";    // 升序降序 desc从大到小 asc
    private String page = "1"; // 第几页
    private boolean changeSales = false;
    private boolean changePrice = false;

    public void changeValue(boolean total, boolean sales, boolean price) {
        if (total) {
            order = "create_time";
            sort = "";
        }
        if (sales) {
            order = "sales";
            if (changeSales) {
                sort = "desc";
            } else {
                sort = "asc";
            }
        }

        if (price) {
            order = "price";
            if (changePrice) {
                sort = "desc";
            } else {
                sort = "asc";
            }
        }
        if (mAdapter.getData().size() >= 1) {
            mAdapter.getData().clear();
            mAdapter.notifyDataSetChanged();
        }
        page = "1";
        shopList();
    }

    private void shopList() {
        String token = (String) SpCommonUtils.get(DStoreSearchListActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreSearchListActivity.this, Constants.USERID, "");
        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", StringUtil.getCurrentTime());

        params.put("category_id", category_id);
        params.put("keywords", keywords);
        params.put("order", order);
        params.put("sort", sort);

        params.put("page", page);
        params.put("page_size", "10");

        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.STORE_GOODS_LIST)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("user_id", user_id)
                .params("token", token)
                //
                .params("category_id", category_id) // 商品分类id 见备注
                .params("keywords", keywords) // 商品分类id 见备注
                .params("order", order) // 排序方式 取值sales（销量）、price价格、create_time添加时间
                .params("sort", sort) // 升序降序 desc从大到小 asc
                .params("page", page) // 第几页
                .params("page_size", "10") // 每页显示数量

                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        DStoreGoodesList goodesList = (DStoreGoodesList) JosnFrom.getInstance().getObj(response.body().toString(), DStoreGoodesList.class);
                        if (goodesList != null && goodesList.getStatus().equals("0001")) {
                            detailDta(goodesList);

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

    private void detailDta(DStoreGoodesList goodesList) {
        int pageNumber = Integer.valueOf(page);

        if (goodesList.getData().getGoodsList().size() >= 1) {
            pageNumber++;
            mAdapter.getData().addAll(goodesList.getData().getGoodsList());
            mAdapter.notifyDataSetChanged();
            page = String.valueOf(pageNumber);
        } else {
            mEasyRefreshLayout.setLoadmoreFinished(true);
        }


    }

}
