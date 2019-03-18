package com.wokun.dset.store.adapter;


import android.content.Context;
import android.graphics.Picture;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.wokun.dset.R;
import com.wokun.dset.store.bean.DStoreHome;

import java.util.ArrayList;
import java.util.List;


public class HomeSGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Picture> pictures;
    private static int ROW_NUMBER = 3;
    private List<DStoreHome.DataBean.JingxuanBean.GoodsBean> cateListBeans = new ArrayList<>();


    public HomeSGridViewAdapter(Context context) {
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
        DStoreHome.DataBean.JingxuanBean.GoodsBean bean = cateListBeans.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_jingxuan_info, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.light = (TextView) convertView.findViewById(R.id.light);
            holder.img = (ImageView) convertView.findViewById(R.id.img);

//            Log.i("=====", position + "==getView总数==" + bean.isClick);

            //设置标题
            holder.title.setText(bean.getGoods_name());

            if (bean.getIntroduction() != null && bean.getIntroduction().length() > 0) {
                holder.light.setText(bean.getIntroduction());
            } else {
                holder.light.setVisibility(View.INVISIBLE);
            }


            Glide.with(context).load(bean.getPic_cover_small()).into(holder.img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        //高度计算
//        AbsListView.LayoutParams param = new AbsListView.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                mGv.getHeight() / ROW_NUMBER);
//
//        convertView.setLayoutParams(param);
        return convertView;
    }

//    private void setBg(Holder holder, boolean show) {
////        holder.title.setTextColor(show ? context.getResources().getColor(cn.ideamark.layoutlib.R.color.color_F86442) : context.getResources().getColor(cn.ideamark.layoutlib.R.color.color_18_18_18));
////        holder.content.setTextColor(show ? context.getResources().getColor(cn.ideamark.layoutlib.R.color.white) : context.getResources().getColor(cn.ideamark.layoutlib.R.color.color_153_153_153));
////        holder.content.setBackgroundResource(show ? R.drawable.mm_home_select_bg : R.color.transparent);
//    }

    public void setData(List<DStoreHome.DataBean.JingxuanBean.GoodsBean> dataes) {
        cateListBeans.addAll(dataes);
        notifyDataSetChanged();
    }

    public void setOnClick(int onClick) {


    }

    class Holder {
        TextView title;
        TextView light;
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
