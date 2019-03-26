package com.wokun.dset.store.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.store.bean.CartDetailBean;
import com.wokun.dset.store.bean.CartList;
import com.wokun.dset.utils.ImageLoaderUtils;

import java.util.List;

public class ShopCartDetailAdapter extends BaseQuickAdapter<CartDetailBean.DataBean.OrderGoodsBean, BaseViewHolder> {


    public ShopCartDetailAdapter(@LayoutRes int layoutResId, @Nullable List<CartDetailBean.DataBean.OrderGoodsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CartDetailBean.DataBean.OrderGoodsBean item) {

        Context context = helper.itemView.getContext();

        ImageLoaderUtils.getInstance().load(context, (ImageView) helper.getView(R.id.goods_image), item.getPic_cover_small(), 2);
        helper.setText(R.id.goods_name, item.getGoods_name());
        helper.setText(R.id.size, item.getSku_name());
        helper.setText(R.id.goods_price, "￥" + item.getPrice());
        helper.setText(R.id.goods_num, "× " + item.getNum());

    }
}