package com.wokun.dset.store.adapter;


import android.content.Context;
import android.graphics.Paint;
import android.graphics.Picture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wokun.dset.R;
import com.wokun.dset.store.bean.DStoreHome;

import java.util.ArrayList;
import java.util.List;


public class HomoSecondAdapter extends BaseAdapter {

    private Context context;
    private List<Picture> pictures;
    private static int ROW_NUMBER = 3;
    private List<DStoreHome.DataBean.PromotionInfoBean.PromotionGoodsBean> cateListBeans = new ArrayList<>();


    public HomoSecondAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (null != cateListBeans) {
            return cateListBeans.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return cateListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DStoreHome.DataBean.PromotionInfoBean.PromotionGoodsBean bean = cateListBeans.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_miaosha_info, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.oldprice = (TextView) convertView.findViewById(R.id.oldprice);
            holder.img = (ImageView) convertView.findViewById(R.id.img);

//            Log.i("=====", position + "==getView总数==" + bean.isClick);

            //设置标题
            holder.title.setText(bean.getGoods_name());
            holder.price.setText(bean.getPrice());
            holder.title.setText(bean.getPromotion_price());
            holder.title.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            Glide.with(context).load(bean.getPic_cover_small()).into(holder.img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        return convertView;
    }


    public void setData(List<DStoreHome.DataBean.PromotionInfoBean.PromotionGoodsBean> dataes) {
        System.out.println("=TypeBean设置=== " + cateListBeans.size());
        cateListBeans.addAll(dataes);

        notifyDataSetChanged();
    }

    public void setOnClick(int onClick) {

//        if (typeBeanList.size() > 0) {
//            for (int i = 0; i < typeBeanList.size(); i++) {
//                if (onClick == i) {
//                    typeBeanList.get(i).name = "rrr";
//                } else {
//                    typeBeanList.get(i).name = "gggg";
//                }
//            }
//        }
        notifyDataSetChanged();
    }

    class Holder {
        TextView title;
        TextView price;
        TextView oldprice;
        ImageView img;
    }

//    public void updata(int isClick) {
//        typeBeanList.clear();
//        notifyDataSetChanged();
//
//        System.out.println("=TypeBeanTypeBean==== " + cateListBeans.size());
//
//        for (int i = 0; i < cateListBeans.size(); i++) {
//            TypeBean typeBean = new TypeBean();
//            typeBean.name = cateListBeans.get(i).name;
//            System.out.println("=TypeBean====" + cateListBeans.get(i).name);
//            typeBean.intro = cateListBeans.get(i).intro;
//            typeBean.isClick = isClick == i ? 1 : 0;
//            typeBeanList.add(i, typeBean);
//        }
//    }
}
