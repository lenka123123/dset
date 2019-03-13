package com.wokun.dset.address.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.widget.imageview.SelectorImageView;
import com.wokun.dset.R;
import com.wokun.dset.address.bean.AddressListBean;


import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<AddressListBean.AddressBean, BaseViewHolder> {

    public AddressAdapter(@LayoutRes int layoutResId, @Nullable List<AddressListBean.AddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AddressListBean.AddressBean item) {
        SelectorImageView selectorImageView =  helper.getView(R.id.selector_image_view);

        if(item.getIs_default().equals("1")){
            selectorImageView.toggle(true);
        }else if(item.getIs_default().equals("0")){
            selectorImageView.toggle(false);
        }
        helper.setText(R.id.user_contacts,item.getName())
        .setText(R.id.user_mobile,item.getPhone())
        .setText(R.id.user_address,item.getProvice()+item.getCity()+item.getArea()+item.getAddress())
        .addOnClickListener(R.id.action_set_normal_address)
        .addOnClickListener(R.id.action_edit_address)
        .addOnClickListener(R.id.action_delete_address);
    }
}