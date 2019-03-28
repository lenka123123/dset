package com.wokun.dset.store.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.bean.DStoreHome;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;
import com.wokun.dset.utils.ImageLoaderUtils;
import com.wokun.dset.utils.ImageUtils;


/**
 * Created by guanaj on 16/9/3.
 */
public class BeautyHomeAdapter extends BaseQuickAdapter<DStoreHome.DataBean.TuijianBean, BaseViewHolder> {

    private Context context;

    public BeautyHomeAdapter(Context context) {
        super(R.layout.item_store_adapter_info, null);
        this.context = context;
    }

    public void setData() {
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DStoreHome.DataBean.TuijianBean item) {
        ImageView imageView = helper.getView(R.id.img);
//        Glide.with(context).load(item.getPic_cover_big()).into(imageView);
        ImageLoaderUtils.getInstance().load(context, imageView, item.getPic_cover_big(), 0);

//        helper.setText(R.id.title, item.getGoods_name());
        ImageUtils.setImgTv(context, item.getShop_id(), (TextView) helper.getView(R.id.title), item.getGoods_name());

        helper.setText(R.id.price, "￥" + item.getPrice());
        if (getData().indexOf(item) % 2 == 0) {
            helper.getView(R.id.left_linearlayout).setVisibility(View.VISIBLE);
            helper.getView(R.id.right_linearlayout).setVisibility(View.GONE);
            helper.getView(R.id.left_linearlayout_item).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.left_linearlayout).setVisibility(View.GONE);
            helper.getView(R.id.right_linearlayout).setVisibility(View.VISIBLE);
            helper.getView(R.id.left_linearlayout_item).setVisibility(View.VISIBLE);
        }
        helper.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constants.GOODS_ID, item.getGoods_id());
                intent.setClass(context, DStoreDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
