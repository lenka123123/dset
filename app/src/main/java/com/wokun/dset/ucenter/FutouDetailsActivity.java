package com.wokun.dset.ucenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.lzy.okgo.request.base.Request;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity;

public class FutouDetailsActivity extends SimpleRefreshAndLoadMoreActivity<ZhihuanjilvBean> {

private  JilvDetailsAdapter  mAdapter;
@Override
public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("复投明细");
        }

@Override
public void init() {
        mMultipleStatusView.showLoading();
        }




@Override
public Request initRequest() {
        return null;
        }

@Override
public BaseQuickAdapter<ZhihuanjilvBean, BaseViewHolder> initAdapter() {
     /*   mAdapter =  new JilvDetailsAdapter(R.layout.item_my_details, null);
        return mAdapter;*/
     return null;
        }

@Override
public void loadData(boolean isRefresh) {

        }

        }

