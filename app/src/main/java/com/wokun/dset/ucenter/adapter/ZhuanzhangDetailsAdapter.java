package com.wokun.dset.ucenter.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;

import com.wokun.dset.ucenter.bean.RollOutRecordBean;
import com.wokun.dset.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2019\1\16 0016.
 */

public class ZhuanzhangDetailsAdapter extends BaseQuickAdapter<RollOutRecordBean, BaseViewHolder> {
                                         //   extends RecyclerView.Adapter<CardListadapter.MyViewHolder>

    public ZhuanzhangDetailsAdapter(int layoutResId, @Nullable List<RollOutRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RollOutRecordBean item) {
        ImageLoader.loadImage(item.getIcon(), (ImageView) helper.getView(R.id.user_head_img));

        helper.setText(R.id.item_de_name, item.getUsername())
                .setText(R.id.item_de_time, item.getCreated_at())
                .setText(R.id.item_de_num, item.getAmount())
                .setText(R.id.itenm_de_id, item.getUserid());
    }

}
