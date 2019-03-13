package com.wokun.dset.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shantoo.widget.recyclerview.MItemDecoration;
import com.wokun.dset.R;

import butterknife.BindView;

public abstract class SimpleRefreshAndLoadMoreActivity6<T> extends BaseRefreshAndLoadMoreActivity8<T> {

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public int createView() {
        return R.layout.activity_normal_refresh_layout2;
    }

    @Override
    public RecyclerView initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL));
        return mRecyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }
}