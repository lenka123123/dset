package com.wokun.dset.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wokun.dset.R;
import com.wokun.dset.store.bean.GoodsSKUList;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * 美宝更多列表，上方更多按钮点击后的POP
 */

public class GoodsSizeAdapter extends TagAdapter {

    private List<GoodsSKUList.ValueBean> mData;
    private LayoutInflater mLayoutInflater;
    private TagFlowLayout mFlowLayout;


    public GoodsSizeAdapter(Context context, TagFlowLayout mFlowLayout, List<GoodsSKUList.ValueBean> datas) {
        super(datas);
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mData = datas;
        this.mFlowLayout = mFlowLayout;
    }

    @Override
    public View getView(FlowLayout parent, int position, Object o) {

        TextView textView = (TextView) mLayoutInflater.inflate(R.layout.goodsdetail_item, mFlowLayout, false);
        textView.setText(mData.get(position).getSpec_value_name());
        return textView;
    }

}
