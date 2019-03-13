package com.wokun.dset.ucenter.addcards;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.wokun.dset.R;
import com.wokun.dset.ucenter.bean.BankCardBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class CardListadapter extends RecyclerView.Adapter<CardListadapter.MyViewHolder> {
    private List<BankCardBean> mData;
    private OnItemClickListener mListener=null;
    //添加数据
    public void setData(List<BankCardBean> data) {
      this.mData=data;
      notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     //此处吧布局写上
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhihui_addbank, parent, false);
        return new MyViewHolder(convertView);
    }
    public void setOnItemclickListener(OnItemClickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.zhihui_it_call.setText(mData.get(position).getBank());
        holder.zhihui_it_title.setText(mData.get(position).getBank_number());
        String id = mData.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.onItemClick(view,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView zhihui_it_call,zhihui_it_title,realname;


        public MyViewHolder(View itemView) {
            super(itemView);
          //  home_place_img = (ImageView) itemView.findViewById(R.id.home_place_img);
            zhihui_it_call = (TextView) itemView.findViewById(R.id.zhihui_it_call);
            zhihui_it_title = (TextView) itemView.findViewById(R.id.zhihui_it_title);

        }
    }
}
