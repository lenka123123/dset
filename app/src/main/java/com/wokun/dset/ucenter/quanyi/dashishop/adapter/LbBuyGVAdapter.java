package com.wokun.dset.ucenter.quanyi.dashishop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.wokun.dset.R;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.LbBuyBean;

import java.util.List;

/**
 * 分享页 适配器 二
 *
 * @author 陈白衣
 * @time 2018/4/26 16:56
 */
public class LbBuyGVAdapter extends BaseAdapter {

    private Context context;
    private List<LbBuyBean.TradeNum> list;

    public LbBuyGVAdapter(Context context, List<LbBuyBean.TradeNum> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Wrapper wrapper = null;
        if (convertView == null) {
            wrapper = new Wrapper();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lb_buy, null);
            convertView.setTag(wrapper);
        } else {
            wrapper = (Wrapper) convertView.getTag();
        }
        wrapper.textView = (TextView) convertView.findViewById(R.id.tv_item_lb_buy);

        wrapper.textView.setText(list.get(position).getNum()+"");
        return convertView;
    }

    static class Wrapper {
        TextView textView;
    }
}
