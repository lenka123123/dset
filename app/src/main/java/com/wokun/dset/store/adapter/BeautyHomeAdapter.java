package com.wokun.dset.store.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.store.bean.DStoreHome;


/**
 * Created by guanaj on 16/9/3.
 */
public class BeautyHomeAdapter extends BaseQuickAdapter<DStoreHome.DataBean.TuijianBean, BaseViewHolder> {

    private Context context;

    public BeautyHomeAdapter(Context context) {
        super(R.layout.item_store_adapter_info, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final DStoreHome.DataBean.TuijianBean item) {

        ImageView imageView = helper.getView(R.id.img);
        Glide.with(context).load(item.getPic_cover_big()).into(imageView);

        helper.setText(R.id.title, item.getGoods_name());
        helper.setText(R.id.price, item.getPrice());

        helper.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(context, StoreInfoActivity.class);
//                intent.putExtra("shop_id", item.getShop_id());
//                context.startActivity(intent);
            }
        });

    }
}
