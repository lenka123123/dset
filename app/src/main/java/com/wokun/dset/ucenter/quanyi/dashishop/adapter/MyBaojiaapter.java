package com.wokun.dset.ucenter.quanyi.dashishop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.R;
import com.wokun.dset.ucenter.addcards.OnItemClickListener;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.BaojiaBean;
import com.wokun.dset.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2019\1\28 0028.
 */

public class MyBaojiaapter  extends RecyclerView.Adapter<MyBaojiaapter.MyViewHolder> {
    private List<BaojiaBean.Baojiapeople> mData;
    private OnItemClickListener mListener=null;
    //添加数据
    public void setData(List<BaojiaBean.Baojiapeople> data) {
        this.mData=data;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baojia_mai, parent, false);
        return new MyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemdename.setText("UID:"+mData.get(position).getOut_uid());
        holder.item_de_chujia.setText("出价：￥"+mData.get(position).getOut_offer());
        ImageLoader.loadImage( mData.get(position).getHeadimg(),holder.user_head_img);
        holder.item_de_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxToast.showShort("点击了");
            }
        });

    }

    @Override
    public int getItemCount() {

        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView itemdename,item_de_chujia,item_de_num;
        private ImageView user_head_img;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemdename = (TextView) itemView.findViewById(R.id.item_de_name);
            item_de_chujia = (TextView) itemView.findViewById(R.id.item_de_chujia);
            item_de_num = (TextView) itemView.findViewById(R.id.item_de_num);
            user_head_img = (ImageView) itemView.findViewById(R.id.user_head_img);


        }
    }
}
