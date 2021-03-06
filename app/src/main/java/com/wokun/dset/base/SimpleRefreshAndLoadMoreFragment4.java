package com.wokun.dset.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shantoo.widget.recyclerview.MItemDecoration;
import com.wokun.dset.R;

import butterknife.BindView;

/**
 * 下拉刷新，上拉加载几类Fragment
 * */
public abstract class SimpleRefreshAndLoadMoreFragment4<T> extends BaseRefreshAndLoadMoreFragment4<T> {

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;

    @Override
    public int createView() {
        return R.layout.layout_swipe_refresh1;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    @Override
    public RecyclerView initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(hasItemDecoration()){
            mRecyclerView.addItemDecoration(new MItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }
        return mRecyclerView;
    }

    public boolean hasItemDecoration(){
        return true;
    }
}
