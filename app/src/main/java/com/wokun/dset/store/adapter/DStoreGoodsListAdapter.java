package com.wokun.dset.store.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.model.Constants;
import com.wokun.dset.pinkongshop.GoodsDetailActivity;
import com.wokun.dset.store.bean.DStoreGoodesList;
import com.wokun.dset.store.bean.DStoreHome;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;


/**
 * Created by guanaj on 16/9/3.
 */
public class DStoreGoodsListAdapter extends BaseQuickAdapter<DStoreGoodesList.DataBean.GoodsListBean, BaseViewHolder> {

    private Context context;

    public DStoreGoodsListAdapter(Context context) {
        super(R.layout.item_goods_search_list, null);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, final DStoreGoodesList.DataBean.GoodsListBean item) {

        ImageView imageView = helper.getView(R.id.img);
        Glide.with(context).load(item.getPic_cover_small()).into(imageView);

        helper.setText(R.id.title, item.getGoods_name());
        helper.setText(R.id.price, "￥" + item.getPrice());

        helper.setText(R.id.old_price, "￥" + item.getMarket_price());
        ((TextView) (helper.getView(R.id.old_price))).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

        helper.setText(R.id.shop_name, item.getShop_name());

        helper.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constants.GOODS_ID, item.getGoods_id());
                intent.setClass(context, DStoreDetailActivity.class);
                context.startActivity(intent);

//                Toast.makeText(context, helper.getItemId() + "当前是第几个" + getData().indexOf(item), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context, StoreInfoActivity.class);
//                intent.putExtra("shop_id", item.getShop_id());
//                context.startActivity(intent);
            }
        });
    }
}
