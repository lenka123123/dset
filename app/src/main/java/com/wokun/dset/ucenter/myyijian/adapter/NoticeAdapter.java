package com.wokun.dset.ucenter.myyijian.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.ucenter.bean.MessageBean;
import com.wokun.dset.ucenter.bean.MessageDetailBean;


import java.util.List;

/**
 * Created by Administrator on 2019\1\16 0016.
 */

public class NoticeAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
                                         //   extends RecyclerView.Adapter<CardListadapter.MyViewHolder>

    public NoticeAdapter(int layoutResId, @Nullable List<MessageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setText(R.id.tv_title, item.getStatus())
                .setText(R.id.tv_create_time, item.getCreated_at());
    }

}
