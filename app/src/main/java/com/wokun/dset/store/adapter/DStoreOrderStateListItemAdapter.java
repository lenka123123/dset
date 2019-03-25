package com.wokun.dset.store.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.store.bean.OrderStateBean;
import com.wokun.dset.utils.ImageLoaderUtils;


/**
 * Created by guanaj on 16/9/3.
 */
public class DStoreOrderStateListItemAdapter extends BaseQuickAdapter<OrderStateBean.DataBean.OrderInfoBean.OrderGoodsBean, BaseViewHolder> {

    private Context context;

    public DStoreOrderStateListItemAdapter(Context context) {
        super(R.layout.item_goods_order_body, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrderStateBean.DataBean.OrderInfoBean.OrderGoodsBean item) {

        ImageView imageView = helper.getView(R.id.goods_picture);
        ImageLoaderUtils.getInstance().load(context, imageView, item.getPic_cover_small(), 0);
//        Glide.with(context).load(item.getGoods_picture()).into(imageView);


        helper.setText(R.id.goods_pay_price, "￥" + item.getPrice());
        helper.setText(R.id.goods_name, item.getGoods_name());
        helper.setText(R.id.goods_num, "数量:×" + item.getNum());
//
//        helper.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra(Constants.GOODS_ID, item.getGoods_id());
//                intent.setClass(context, DStoreDetailActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }
}
