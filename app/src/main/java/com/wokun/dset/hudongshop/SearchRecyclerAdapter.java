package com.wokun.dset.hudongshop;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wokun.dset.R;
import com.wokun.dset.ucenter.addcards.OnItemClickListener;
import com.wokun.dset.utils.ImageLoader;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolders> {

    private OnItemClickListener mListener = null;
    private List<ShopBean> data;
    private Context context;

    public void clearData() {
        if (data == null) return;
        data.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchRecyclerAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new SearchRecyclerAdapter.ViewHolders(LinearLayout.inflate(viewGroup.getContext(), R.layout.item_fragment_near, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.ViewHolders viewHolders, final int i) {
        viewHolders.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data == null) return;
                Intent intent = new Intent(context, ShopDetailsActivity.class);
                intent.putExtra("shop_id", String.valueOf(data.get(i).getId()));
                context.startActivity(intent);
            }
        });

        int id = data.get(i).getId();

        ImageLoader.loadImage(data.get(i).getPicture(), viewHolders.item_img);
        viewHolders.item_title.setText(data.get(i).getName());
        viewHolders.item_place.setText(data.get(i).getAddress());
        viewHolders.itenm_distance.setText(data.get(i).getDistance() + "km");
        Log.e("星星", "" + data.get(i).getScore());
        //   viewHolders.star.removeAllViews();

        for (int j = 0; j < (data.get(i).getScore()); j++) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(32, 32);
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.ic_shop_xinxin2);
            viewHolders.star.addView(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<ShopBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    class ViewHolders extends RecyclerView.ViewHolder {
        private TextView item_title, item_place, itenm_distance;
        private ImageView item_img;
        private LinearLayout star;

        //  private  RatingBar rating;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            item_title = itemView.findViewById(R.id.item_title);
            item_place = itemView.findViewById(R.id.item_place);
            item_img = itemView.findViewById(R.id.item_img);
            itenm_distance = itemView.findViewById(R.id.itenm_distance);
            star = (LinearLayout) itemView.findViewById(R.id.rating);
            //  rating = itemView.findViewById(R.id.rating);
        }
    }


}
