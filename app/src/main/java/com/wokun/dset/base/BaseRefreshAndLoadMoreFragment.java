package com.wokun.dset.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.R;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * 下拉刷新，上拉加载几类Fragment
 */
public abstract class BaseRefreshAndLoadMoreFragment<T> extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener {

    private int mNextRequestPage = 1;
    //  private int PAGE_SIZE = Constants.PG_SIZE;
    private String mNextRequestPage2;
    protected Request mRequest;
    protected List<T> mDataList;
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;
    protected String TAG = this.getClass().getSimpleName();

    public abstract RecyclerView initRecyclerView();

    public abstract SwipeRefreshLayout initSwipeRefreshLayout();

    public abstract Request initRequest();

    public abstract BaseQuickAdapter<T, BaseViewHolder> initAdapter();

    public abstract void loadData(boolean isRefresh);

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout = initSwipeRefreshLayout();
        mRecyclerView = initRecyclerView();

        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = initAdapter();
        mRequest = initRequest();

        mAdapter.setEmptyView(R.layout.layout_no_data_view, (ViewGroup) mRecyclerView.getParent());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);

        onRefresh();
    }

    @Override
    public void onRefresh() {
        Log.i(TAG, mNextRequestPage + "==aaaaaaaaaaaa: " + mNextRequestPage2);
        mNextRequestPage = 1;
        mNextRequestPage2 = String.valueOf(mNextRequestPage);
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", mNextRequestPage2);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        mRequest.params(Constants.PAGE, mNextRequestPage2)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("sign", sign);

        //   .params(Constants.PAGE_SIZE, PAGE_SIZE);
        mAdapter.setEnableLoadMore(true);//这里的作用是防止下拉刷新的时候还可以上拉加载
        doOnRefreshData();
    }

    @Override
    public void onLoadMoreRequested() {

        Log.i(TAG, mNextRequestPage + "==onLoadMoreRequested: " + mNextRequestPage2);

        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", mNextRequestPage2);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        mRequest.params(Constants.PAGE, mNextRequestPage2)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("sign", sign);
        //  .params(Constants.PAGE_SIZE, PAGE_SIZE);
        doOnLoadMoreData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    public void setData(boolean isRefresh, List<T> data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {

            mAdapter.setNewData(data);
            mAdapter.disableLoadMoreIfNotFullPage();
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            if (size > 0) {
                mDataList = mAdapter.getData();
                mDataList.clear();
                mAdapter.addData(data);
                mAdapter.disableLoadMoreIfNotFullPage();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }/*if (mNextRequestPage <= 1) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
           if(!isRefresh){
                RxToast.showShort("沒有更多数据了");
            }
        } else {
            mAdapter.loadMoreComplete();
        }*/
        //   mAdapter.loadMoreComplete();
        mDataList = mAdapter.getData();
    }

    public void doOnRefreshData() {
        loadData(true);
    }

    public void doOnLoadMoreData() {
        loadData(true);
    }
}