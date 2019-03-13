package com.wokun.dset.ucenter.quanyi.dashishop;

import android.support.v4.app.Fragment;

import com.wokun.dset.R;
import com.wokun.dset.base.BaseTabActivity;
import com.wokun.dset.model.Constants;
import com.wokun.dset.ucenter.quanyi.dashishop.fragment.GoodsOrderStateAll;
import com.wokun.dset.ucenter.quanyi.dashishop.fragment.GoodsOrderchuli;
import com.wokun.dset.ucenter.quanyi.dashishop.fragment.GoodsOrderfinish;
import com.wokun.dset.ucenter.quanyi.dashishop.fragment.GoodsOrdering;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;

/**
 * Created by Administrator on 2019\1\24 0024.
 */

public class GoodsOrderActivity extends BaseTabActivity {
    @BindString(R.string.tysl_my_order)
    String title;
    @BindString(R.string.tysl_state_all)
    String all;
    @BindString(R.string.wait_chuli)
    String wait_chuli;
    @BindString(R.string.wait_ing)
    String wait_ing;
    @BindString(R.string.wait_finish)
    String wait_finish;

    @Override
    protected int initState() {
        return getIntent().getIntExtra(Constants.STATE, 0);
    }

    @Override
    public void init() {

        super.init();
//        EventBus.getDefault().register(this);
    }

    @Override
    protected String initTitle() {
        return title;
    }

    @Override
    protected List<String> initTabTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(all);
        mTitles.add(wait_chuli);
        mTitles.add(wait_ing);
        mTitles.add(wait_finish);
        return mTitles;
    }

    @Override
    protected List<Fragment> initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new GoodsOrderStateAll());//全部
        mFragments.add(new GoodsOrderchuli());//GoodsOrderchuli待处理
        mFragments.add(new GoodsOrdering());//GoodsOrdering进行中
        mFragments.add(new GoodsOrderfinish());//GoodsOrderfinish已完成
        return mFragments;
    }
}
