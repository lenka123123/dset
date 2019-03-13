package com.wokun.dset.store.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;


/**
 * Created by guanaj on 16/9/3.
 */
public class BeautyHomeAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    private Context context;

    public BeautyHomeAdapter(Context context) {
        super(R.layout.item_goods_list, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Object item) {


//        helper.setText(R.id.store_name, item.getName());
//        helper.setText(R.id.store_distance, "距离" + item.getDistance());
//        ImageView imageView = helper.getView(R.id.store_img);
//        Glide.with(context).load(item.getScore()).into(imageView);
//
//        helper.setText(R.id.store_score, "评分" + item.getScore());
//        helper.setText(R.id.stroe_consume_num, "消费人数" + item.getConsume_num());
//        helper.setText(R.id.store_slogan, item.getSlogan());
//        helper.setText(R.id.store_price, "人均消费人数" + item.getConsume_num() + "元");
//
//        helper.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, StoreInfoActivity.class);
//                intent.putExtra("shop_id", item.getShop_id());
//                context.startActivity(intent);
//            }
//        });

    }
}
