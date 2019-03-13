package com.wokun.dset.ucenter.zhifudiaolog;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.ucenter.bean.BillRecordBean;
import com.wokun.dset.ucenter.bean.ExchangeRecordBean;

import java.util.List;

/**
 * Created by Administrator on 2019\1\16 0016.
 */

public class JilvyueDetailsAdapter extends BaseQuickAdapter<BillRecordBean, BaseViewHolder> {
                                         //   extends RecyclerView.Adapter<CardListadapter.MyViewHolder>

    public JilvyueDetailsAdapter(int layoutResId, @Nullable List<BillRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillRecordBean item) {
        helper.setText(R.id.item_de_name, item.getEvent_type())
                .setText(R.id.item_de_num, item.getAmount())
                .setText(R.id.item_de_time, item.getCreated_at());
    }

}
