package com.wokun.dset.ucenter.quanyi.dashishop.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.LbSellListBean;
import com.wokun.dset.utils.ImageLoader;


import java.util.List;

/**
 * Created by Administrator on 2018/9/25.
 */

public class LbsellAdapter extends BaseQuickAdapter<LbSellListBean.ListBean,BaseViewHolder> {

    public LbsellAdapter(@LayoutRes int layoutResId, @Nullable List<LbSellListBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, LbSellListBean.ListBean item) {
        helper.setText(R.id.item_de_name,"UID:"+item.getIn_userid());
        helper.setText(R.id.item_de_time,"DSET:"+item.getNumber()+"");
        helper.setText(R.id.item_de_num,"参考价"+item.getAmount());
        ImageLoader.loadImage(item.getIcon(),(ImageView)helper.getView(R.id.user_head_img));
        helper.addOnClickListener(R.id.itenm_de_id).addOnLongClickListener(R.id.itenm_de_id);
      /*  if (lbBuy!=null){
            helper.getView(R.id.tv_item_lb_sell_buy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lbBuy.buy(helper.getLayoutPosition());
                }
            });
        }*/
    }

    private OnCutListener mOnCutListener;

    public interface OnCutListener{
        void onCut(LbSellListBean.ListBean mlistbean, int position);
    }
    public void setOnCutListener(OnCutListener listener){
        this.mOnCutListener = listener;
    }

}
