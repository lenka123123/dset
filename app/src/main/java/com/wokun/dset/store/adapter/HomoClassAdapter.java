package com.wokun.dset.store.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wokun.dset.R;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.bean.DStoreHome;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;
import com.wokun.dset.store.dstore.dstorelist.DStoreSearchListActivity;
import com.wokun.dset.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;


public class HomoClassAdapter extends BaseAdapter {

    private Context context;
    //    private List<Picture> pictures;
//    private static int ROW_NUMBER = 3;
    private List<DStoreHome.DataBean.ClassBean> cateListBeans = new ArrayList<>();


    public HomoClassAdapter(Context context) {
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
        DStoreHome.DataBean.ClassBean bean = cateListBeans.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_class_info, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.title.setText(bean.getCategory_name());
            ImageLoaderUtils.getInstance().load(context, holder.img, bean.getCategory_pic(), 0);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        return convertView;
    }


    public void setData(List<DStoreHome.DataBean.ClassBean> dataes) {
        System.out.println("=TypeBean设置=== " + cateListBeans.size());
        cateListBeans.addAll(dataes);
        notifyDataSetChanged();
    }

    public void setOnClick(int position) {
        Intent intent = new Intent();
        intent.putExtra("category_id", cateListBeans.get(position).getCategory_id());
        intent.setClass(context, DStoreSearchListActivity.class);
        context.startActivity(intent);

//        if (typeBeanList.size() > 0) {
//            for (int i = 0; i < typeBeanList.size(); i++) {
//                if (onClick == i) {
//                    typeBeanList.get(i).name = "rrr";
//                } else {
//                    typeBeanList.get(i).name = "gggg";
//                }
//            }
//        }
//        notifyDataSetChanged();
    }

    class Holder {
        TextView title;
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
