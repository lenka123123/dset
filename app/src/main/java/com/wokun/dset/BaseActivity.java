package com.wokun.dset;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wokun.dset.store.LowMemory;
import com.wokun.dset.store.bean.OrderStateBean;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {


    protected BaseActivity context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setActivityLayout());
//        StatusBarCompat.setStatusBarColor(this, Color.WHITE, true);
        context = this;
        registerComponentCallbacks(new LowMemory());
        initView();
        initData();
    }

    public abstract int setActivityLayout();

    public void initView() {
    }

    public void initData() {
    }


}
