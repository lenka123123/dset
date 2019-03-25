package com.wokun.dset.store.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.bean.DStoreGoodesList;
import com.wokun.dset.store.bean.OrderStateBean;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;
import com.wokun.dset.store.dstore.dstorestate.DStoreStateActivity;
import com.wokun.dset.store.dstore.immediatelypay.DStoreOrderDetailActivity;


/**
 * Created by guanaj on 16/9/3.
 */
public class DStoreOrderStateListAdapter extends BaseQuickAdapter<OrderStateBean.DataBean.OrderInfoBean, BaseViewHolder> {

    private DStoreStateActivity context;
    private DStoreOrderStateListItemAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView action_1;
    private TextView action_2;
    private TextView action_3;

    public DStoreOrderStateListAdapter(DStoreStateActivity context) {
        super(R.layout.activity_order_state, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrderStateBean.DataBean.OrderInfoBean item) {


        helper.setText(R.id.store_name, item.getShop_name());

        //  all全部0待付款1待发货2待收货3已收货4已完成5已关闭
        TextView textView = helper.getView(R.id.order_state);
        action_1 = helper.getView(R.id.action_1);
        action_2 = helper.getView(R.id.action_2);
        action_3 = helper.getView(R.id.action_3);

//        if (item.getOrderGoods().size() > 1)
//            item.setOrder_status("2");

        if (item.getOrder_status().equals("0")) {
            textView.setText("待付款");
            action_2.setText("取消订单");
            action_3.setText("立即付款");
            action_3.setVisibility(View.VISIBLE);
        } else if (item.getOrder_status().equals("1")) {
            textView.setText("待发货");
            action_2.setText("提醒发货");
            action_3.setVisibility(View.GONE);
        } else if (item.getOrder_status().equals("2")) {
            textView.setText("待收货");
            action_2.setText("物流信息");
            action_3.setVisibility(View.GONE);
        } else if (item.getOrder_status().equals("3")) {
            textView.setText("已收货");
            action_2.setText("删除订单");
            action_3.setVisibility(View.GONE);
        } else if (item.getOrder_status().equals("4")) {
            textView.setText("已完成");
            action_2.setText("删除订单");
            action_3.setVisibility(View.GONE);
        }


        helper.setText(R.id.zhihui_total_goods, "共" + item.getGoods_nums() + "件商品  合计:￥" + item.getOrder_money());

        mRecyclerView = helper.getView(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DStoreOrderStateListItemAdapter(context);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.getData().addAll(item.getOrderGoods());


        helper.getView(R.id.action_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, DStoreOrderDetailActivity.class);
                intent.putExtra(Constants.ORDER_ID, item.getOrder_id());
                context.startActivity(intent);
            }
        });
        helper.getView(R.id.action_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick(action_2.getText().toString(), item);
            }
        });
        helper.getView(R.id.action_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick(action_3.getText().toString(), item);
            }
        });
    }


    public void itemClick(String text, OrderStateBean.DataBean.OrderInfoBean item) {
        context.itemClick(text, item);
    }
}
