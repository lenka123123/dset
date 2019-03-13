package com.wokun.dset.pinkongshop;

import android.app.ActionBar;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.utils.ImageLoader;


import java.util.List;

public class GoodsListAdapter extends BaseQuickAdapter<GoodsListBean, BaseViewHolder> {

    public GoodsListAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListBean item) {
        RelativeLayout rlRoot  = helper.getView(R.id.rl_root);

        if(helper.getAdapterPosition()%2 == 0){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,10,10,10);
            rlRoot.setLayoutParams(layoutParams);
        }else if(helper.getAdapterPosition()%2 == 1){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10,10,0,10);
            rlRoot.setLayoutParams(layoutParams);
        }

        ImageLoader.loadImage(item.getGoodsImage(), (ImageView) helper.getView(R.id.iv_goods_image));
        helper.setText(R.id.tv_goods_name, item.getGoodsName())
        .setText(R.id.tv_goods_price, item.getGoodsPrice())
        .setText(R.id.tv_month_sales_num, "已售 "+item.getMonthSalesNum());
    }
}