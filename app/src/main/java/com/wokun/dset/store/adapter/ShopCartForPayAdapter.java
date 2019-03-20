package com.wokun.dset.store.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.widget.imageview.SelectorImageView;
import com.wokun.dset.R;
import com.wokun.dset.mainfragment.ShopCartFragment;
import com.wokun.dset.store.bean.CartList;
import com.wokun.dset.utils.ImageLoadUtils;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopCartForPayAdapter extends BaseQuickAdapter<CartList.DataBean.CartListInfoBean.GoodsItemBean, BaseViewHolder> {


    public ShopCartForPayAdapter(@LayoutRes int layoutResId, @Nullable List<CartList.DataBean.CartListInfoBean.GoodsItemBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CartList.DataBean.CartListInfoBean.GoodsItemBean item) {

        Context context = helper.itemView.getContext();

        ImageLoaderUtils.load(context, (ImageView) helper.getView(R.id.goods_image), item.getGoods_picture(), 0);
        helper.setText(R.id.goods_name, item.getGoods_name());
        helper.setText(R.id.size, item.getSku_name());
        helper.setText(R.id.goods_price, "￥" + item.getPrice());
        helper.setText(R.id.goods_num, "× " + item.getNum());

    }
}