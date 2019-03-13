package com.wokun.dset.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.request.base.Request;
import com.wokun.dset.R;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * RecyclerView 的Activity基类
 * RecyclerView 上拉加载、下拉刷新、网络出错、数据为空等功能
 */
public abstract class BaseRefreshAndLoadMoreActivity8<T> extends BaseBindingActivity implements SwipeRefreshLayout.OnRefreshListener , BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener{
    private  int page =1;
    private int mNextRequestPage = 1;
    private  String    mNextRequestPage2  ;
    protected Request mRequest;
    protected List<T> mDataList;
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;


    @Override
    public void init() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create();
        onRefresh();
    }

    public abstract SwipeRefreshLayout initSwipeRefreshLayout();

    public abstract RecyclerView initRecyclerView();

    public abstract Request initRequest();

    public abstract BaseQuickAdapter<T, BaseViewHolder> initAdapter();

    private void create() {
        mSwipeRefreshLayout = initSwipeRefreshLayout();
        mRecyclerView = initRecyclerView();

        if(mSwipeRefreshLayout==null)return;
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = initAdapter();
        mRequest = initRequest();

        if(mAdapter==null)return;
        mAdapter.setEmptyView(R.layout.layout_no_data_view, (ViewGroup) mRecyclerView.getParent());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        if(mRecyclerView==null)return;
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setData(boolean isRefresh, List<T> data) {

        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            Log.e("进来了aaa1","进来了");
            mAdapter.setNewData(data);
            mAdapter.disableLoadMoreIfNotFullPage();
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            Log.e("进来了aaa12","进来了2");
            if (size > 0) {
                //loadData(true);
                mDataList = mAdapter.getData();
                mDataList.clear();
                mAdapter.addData(data);
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.loadMoreComplete();
            }
        }
      //  mAdapter.loadMoreComplete();
     /*if (mNextRequestPage > 1) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
           if(!isRefresh){
                Toast.makeText(this, "沒有更多数据了", Toast.LENGTH_SHORT).show();
            }
        } else {
            mAdapter.loadMoreComplete();
        }*/

    }

    @Override
    public void onRefresh() {
        mNextRequestPage = 1;
        mNextRequestPage2 =String.valueOf(mNextRequestPage);
     if(mRequest==null)return;
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", mNextRequestPage2);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来dsa221121","进来来dsa"+mNextRequestPage2);
        mRequest.params("page", mNextRequestPage2)
                 .params("sign", sign)
                 .params("timestamp", StringUtil.getCurrentTime())             ;
        if(mAdapter==null)return;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        doOnLoadMoreData();
    }

    @Override
    public void onLoadMoreRequested() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", mNextRequestPage2);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来ds2a221121","进来来dsa"+mNextRequestPage2);
        mRequest.params("page", mNextRequestPage2)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime());
        //  .params("page_size", PAGE_SIZE);
        doOnLoadMoreData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {}

    public void doOnRefreshData(){
        loadData(true);
    }

    public void doOnLoadMoreData(){
        loadData(false);
    }

    public abstract void loadData(boolean isRefresh);
}
