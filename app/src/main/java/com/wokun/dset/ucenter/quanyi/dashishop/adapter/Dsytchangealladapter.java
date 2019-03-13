package com.wokun.dset.ucenter.quanyi.dashishop.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.ucenter.bean.DsytDindanBean;
import com.wokun.dset.ucenter.bean.TradeOrderListBean;
import com.wokun.dset.utils.ImageLoader;

import java.util.List;

/**交易状态
 * 订单已挂出 0
 * 买家已下单 1
 * 买家已付款2
 * 订单已成交3
 * 订单已取消4
 * 付款已超时5
 * 卖家已下单7
 * 卖家申诉中8
 * 卖家申诉成功9
 * 付款超时10
 *
 *
 *
 */

public class Dsytchangealladapter extends BaseQuickAdapter<TradeOrderListBean,BaseViewHolder> {
    public Dsytchangealladapter(int layoutResId, @Nullable List<TradeOrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TradeOrderListBean item) {
        TextView action_order_detail = (TextView)helper.getView(R.id.action_order_detail);
        TextView action_cancel_order = (TextView)helper.getView(R.id.action_cancel_order);
        TextView action_sure_order = (TextView)helper.getView(R.id.action_sure_order);//确认收款
        TextView action_sure_myorder = (TextView)helper.getView(R.id.action_sure_myorder);//确认付款

        helper.setText(R.id.item_ds_dindan, "订单号:"+item.getId());
        helper.setText(R.id.item_ds_statu,item.getJy_type_name()+"");
        helper.setText(R.id.item_ds_num,"交易数量:x"+item.getNumber());
        helper.setText(R.id.item_ds_price,"￥:"+item.getAmount());

        if(item.getJyType()==1){//买入
            ImageLoader.loadImage(item.getBuyer_headimg(),(ImageView)helper.getView(R.id.item_ds_img));
            helper.setText(R.id.item_ds_uid,"UID:"+item.getOut_userid());
        }else  if(item.getJyType()==2){//"卖出
            ImageLoader.loadImage(item.getBuyer_headimg(),(ImageView)helper.getView(R.id.item_ds_img));
            helper.setText(R.id.item_ds_uid,"UID:"+item.getIn_userid());

        }
        if(item.getStatus().equals("0")){//订单已挂出
             if(item.getJyType()==1){//买入
              //   action_sure_order.setVisibility(View.GONE);
                          action_cancel_order.setVisibility(View.VISIBLE);
                          action_order_detail.setVisibility(View.VISIBLE);
                          action_sure_order.setVisibility(View.GONE);//确认收款
                          action_sure_myorder.setVisibility(View.GONE);//确认付款
             }else if(item.getJyType()==2){ //卖出
               //  action_sure_order.setVisibility(View.VISIBLE);
                 action_order_detail.setVisibility(View.VISIBLE);
                 action_cancel_order.setVisibility(View.GONE);
                 action_sure_order.setVisibility(View.GONE);//确认收款
                 action_sure_myorder.setVisibility(View.GONE); //确认付款
             }
        }
        else if(item.getStatus().equals("2")){//买家已付款
            if(item.getJyType()==1){
                action_order_detail.setVisibility(View.VISIBLE);
                action_cancel_order.setVisibility(View.GONE);
                action_sure_order.setVisibility(View.GONE);//确认收款
                action_sure_myorder.setVisibility(View.GONE); //确认付款

            }else if(item.getJyType()==2){//卖家

                action_order_detail.setVisibility(View.VISIBLE);
                action_cancel_order.setVisibility(View.GONE);
                action_sure_order.setVisibility(View.VISIBLE);//确认收款
                action_sure_myorder.setVisibility(View.GONE); //确认付款

            }
        }
        else if(item.getStatus().equals("3")){//订单已成交3
            if(item.getJyType()==1){

                action_order_detail.setVisibility(View.VISIBLE);
                action_cancel_order.setVisibility(View.GONE);
                action_sure_order.setVisibility(View.GONE);//确认收款
                action_sure_myorder.setVisibility(View.GONE); //确认付款
            }else if(item.getJyType()==2){
                action_order_detail.setVisibility(View.VISIBLE);
                action_cancel_order.setVisibility(View.GONE);
                action_sure_order.setVisibility(View.GONE);//确认收款
                action_sure_myorder.setVisibility(View.GONE); //确认付款

            }


        }
        else if(item.getStatus().equals("7")){//卖家已下单7

            if(item.getJyType()==1){//买
                action_order_detail.setVisibility(View.VISIBLE);
                action_cancel_order.setVisibility(View.GONE);

                action_sure_order.setVisibility(View.GONE);//确认收款
                action_sure_myorder.setVisibility(View.VISIBLE);//确认付款
            }else if(item.getJyType()==2){//卖
             //   action_cancel_order.setVisibility(View.GONE);
            //    action_sure_order.setVisibility(View.GONE);
                action_order_detail.setVisibility(View.VISIBLE);
                action_cancel_order.setVisibility(View.GONE);
                action_sure_order.setVisibility(View.GONE);//确认收款
                action_sure_myorder.setVisibility(View.GONE); //确认付款

            }


        }
        else if(item.getStatus().equals("8")){


        }
        else if(item.getStatus().equals("9")){}

         helper.addOnClickListener(R.id.action_order_detail).addOnLongClickListener(R.id.action_order_detail);
        helper.addOnClickListener(R.id.action_cancel_order).addOnLongClickListener(R.id.action_cancel_order);
        helper.addOnClickListener(R.id.action_sure_order).addOnLongClickListener(R.id.action_sure_order);
        helper.addOnClickListener(R.id.action_sure_myorder).addOnLongClickListener(R.id.action_sure_myorder);


    }















/*    private List<HistoryBean> mData;
    private OnItemClickListener mListener=null;
    //添加数据
    public void setData(List<HistoryBean> data) {
      this.mData=data;
      notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     //此处吧布局写上
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_history, parent, false);
        return new MyViewHolder(convertView);
    }
    public void setOnItemclickListener(OnItemClickListener listener){
        this.mListener=listener;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        HistoryBean historyBean = mData.get(position);
        holder.his_content.setText(historyBean.getContent());
        holder.his_msg.setText(historyBean.getStatus());
        holder.his_time.setText(historyBean.getCreate_time());

       //没有图片
        if(historyBean.getImages().size()==0){
            holder.his_imgs.setVisibility(View.GONE);
        }else {
                holder.his_imgs.setVisibility(View.VISIBLE);
          if(historyBean.getImages().size()==1){
            holder.his_img1.setVisibility(View.VISIBLE);
            ImageLoader.loadImage((String) historyBean.getImages().get(0), holder.his_img1);

            holder.his_img2.setVisibility(View.INVISIBLE);
            holder.his_img3.setVisibility(View.INVISIBLE);
        }
        else    if(historyBean.getImages().size()==2){
            holder.his_img1.setVisibility(View.VISIBLE);
            holder.his_img2.setVisibility(View.VISIBLE);
            ImageLoader.loadImage((String) historyBean.getImages().get(0), holder.his_img1);
            ImageLoader.loadImage((String) historyBean.getImages().get(1), holder.his_img2);
            holder.his_img3.setVisibility(View.INVISIBLE);
        }
        else  if (historyBean.getImages().size() >= 3){
            holder.his_img1.setVisibility(View.VISIBLE);
            holder.his_img2.setVisibility(View.VISIBLE);
            holder.his_img3.setVisibility(View.VISIBLE);
            ImageLoader.loadImage((String) historyBean.getImages().get(0), holder.his_img1);
            ImageLoader.loadImage((String) historyBean.getImages().get(1), holder.his_img2);
            ImageLoader.loadImage((String) historyBean.getImages().get(2), holder.his_img3);
        }}

           if(historyBean.getStatus().equals("已处理")){
               holder.his_kefu.setVisibility(View.VISIBLE);
               holder.his_kefu.setText( historyBean.getDo_result());
           }else {
               holder.his_kefu.setVisibility(View.GONE);

           }

    *//*    holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.onItemClick(view,position);
                }
            }
        });*//*


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView his_content,his_msg,his_time,his_kefu;
        private ImageView his_img1,his_img2,his_img3;
        private LinearLayout his_imgs;

        public MyViewHolder(View itemView) {
            super(itemView);
       //     home_place_img = (ImageView) itemView.findViewById(R.id.home_health_img);
            his_content = (TextView) itemView.findViewById(R.id.his_content);
            his_msg = (TextView) itemView.findViewById(R.id.his_msg);
            his_time = (TextView) itemView.findViewById(R.id.his_time);
            his_kefu = (TextView) itemView.findViewById(R.id.his_kefu);
            his_img1 = (ImageView) itemView.findViewById(R.id.his_img1);
            his_img2 = (ImageView) itemView.findViewById(R.id.his_img2);
            his_img3 = (ImageView) itemView.findViewById(R.id.his_img3);
            his_imgs = (LinearLayout) itemView.findViewById(R.id.his_imgs);


        }
    }*/
}
