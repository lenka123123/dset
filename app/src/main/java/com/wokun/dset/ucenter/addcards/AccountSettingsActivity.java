package com.wokun.dset.ucenter.addcards;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseTransparencyBindingActivity;


import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//账户设置
public class AccountSettingsActivity extends BaseTransparencyBindingActivity {

    @BindString(R.string.tysl_select_account)
    String title;
    @BindView(R.id.toolbar)
    WidgetBar widgetBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public int createView() {
        return R.layout.activity_account_settings;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        super.init();
    /*    AccountListAdapter adapter =  new AccountListAdapter(R.layout.item_account_list, null);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        EarningsMgr.getInstance().getAccountList(adapter);*/
    }

/*    @OnClick({R.id.action_deposit_card, R.id.action_alipay})
    public void action(View view) {
        if(R.id.action_deposit_card == view.getId()){//添加储蓄卡账号页面
            startActivity(AddDepositCardActivity.class);
        }else if(R.id.action_alipay == view.getId()){//添加支付宝账号页面
            startActivity(AddAlipayAccountActivity.class);
        }
    }*/
}

