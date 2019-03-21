package com.wokun.dset.pinkongshop;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.common.utils.Logger;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.DsetApp.getContext;


/**
 * Created by Administrator on 2018\10\11 0011.
 */

public class ZhihuiSuccessfulActivity extends BaseBindingActivity {

    @BindView(R.id.pay_name)
    TextView payName;

    @BindView(R.id.pay_phone)
    TextView pay_phone;

    @BindView(R.id.pay_place)
    TextView pay_place;

    @BindView(R.id.pay_total1)
    TextView pay_total1;


    @Override
    public int createView() {
        return R.layout.activity_pay_success;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("付款成功");
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        String doubleprice = intent.getStringExtra("doubleprice");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");

        pay_total1.setText("￥" + doubleprice);
        payName.setText(name);
        pay_phone.setText(phone);
        pay_place.setText(address);
    }


    @OnClick({R.id.see_order, R.id.return_first})
    public void action(View v) {
        switch (v.getId()) {
            case R.id.see_order: //查看订单
             /*   Intent intent = new Intent(ZhihuiSuccessfulActivity.this, GoodsOrderDetailActivity2.class);
                intent.putExtra(Constants.ORDER_ID,order_id);
                startActivity(intent);*/
                break;
            case R.id.return_first: //返回首页
                Intent mina = new Intent(ZhihuiSuccessfulActivity.this, MainActivity.class);
                mina.putExtra("main", "main");
                ZhihuiSuccessfulActivity.this.startActivity(mina);
                ZhihuiSuccessfulActivity.this.finish();
                break;
        }
    }

}
