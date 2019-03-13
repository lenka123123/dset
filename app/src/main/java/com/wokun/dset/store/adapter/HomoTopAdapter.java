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


public class HomoTopAdapter extends BaseAdapter {

    private Context context;
    private List<Picture> pictures;
    private static int ROW_NUMBER = 3;
    private List<DStoreHome.DataBean.Top6Bean> cateListBeans = new ArrayList<>();


    public HomoTopAdapter(Context context) {
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
      DStoreHome.DataBean.Top6Bean bean = cateListBeans.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_miaosha_info, null);

            holder.img = (ImageView) convertView.findViewById(R.id.img);
           Glide.with(context).load(bean.getPic_url()).into(holder.img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        return convertView;
    }


    public void setData(List<DStoreHome.DataBean.Top6Bean> dataes) {

        cateListBeans.addAll(dataes);
        System.out.println("=TypeBean设置=== " + cateListBeans.size());
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
