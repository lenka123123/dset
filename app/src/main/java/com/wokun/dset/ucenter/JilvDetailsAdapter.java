package com.wokun.dset.ucenter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.ucenter.addcards.CardListadapter;
import com.wokun.dset.ucenter.bean.ExchangeRecordBean;

import java.util.List;

/**
 * Created by Administrator on 2019\1\16 0016.
 */

public class JilvDetailsAdapter extends BaseQuickAdapter<ExchangeRecordBean, BaseViewHolder> {
                                         //   extends RecyclerView.Adapter<CardListadapter.MyViewHolder>

    public JilvDetailsAdapter(int layoutResId, @Nullable List<ExchangeRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExchangeRecordBean item) {
        helper.setText(R.id.jl_dashiyue, item.getCash_amount())
                .setText(R.id.jl_dsjifen, item.getHcg_amount())
                .setText(R.id.jl_time, item.getCreated_at());
    }

   /* public void setData(List<ExchangeRecordBean> data) {
        this.mData=data;
        notifyDataSetChanged();
    }*/
}
