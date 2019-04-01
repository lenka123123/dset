package com.wokun.dset.hudongshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.R;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.ucenter.addcards.OnItemClickListener;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.LocationUtils;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;


/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2019/1/16/10:38
 * @Version:1.0.0
 * @Class:Fragment_near
 * @Description:
 */
public class Fragment_near extends Fragment {

    private RecyclerAdapter recyclerAdapter;
    private List<ShopBean> data2;
    private static String keywords = "";
    private static int type = 0;
    private RecyclerView mRecycler;

    private void loadConetext() {
        LocationUtils.getInstance().start(100, true, null);
        Log.e("位置", LocationUtils.getLatLng().latitude + "位置");
        Log.e("位置", LocationUtils.getLatLng().longitude + "位置");
        Log.e("fragmet进来了1", "fragmet进来了1" + type);
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("lat", String.valueOf(LocationUtils.getLatLng().latitude));
        params.put("long", String.valueOf(LocationUtils.getLatLng().longitude));
        params.put("type", String.valueOf(type));
        params.put("keywords ", keywords);
        params.put("page ", "1");
        params.put("page_size", "10");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse2<ShopBean>>post(Constants.BASE_URL + Constants.BUSINESS_LIST)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("lat", String.valueOf(LocationUtils.getLatLng().latitude))
                .params("long", String.valueOf(LocationUtils.getLatLng().longitude))
                .params("type", String.valueOf(type))
                .params("keywords", keywords)
                .params("page ", "1")
                .params("page_size", "10")
                .execute(new DialogCallback<BaseResponse2<ShopBean>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<BaseResponse2<ShopBean>> response) {
                        BaseResponse2 body = response.body();
                        if (body == null) return;
                        if (body.getStatus().equals("0001")) {
                            if (body.getData() == null) return;

                            data2 = (List<ShopBean>) body.getData();

                            Log.i("", "recyclerAdapter2: " + data2.size());
                            Log.i("", "recyclerAdapter1: " + (recyclerAdapter == null));
                            recyclerAdapter.setData(data2);
                        } else {
                            RxToast.showShort(body.getMessage());
                        }
                    }
                });

    }

    public void setKeywords(String msg) {
//        recyclerAdapter.clearData();
        if (recyclerAdapter == null) {
            recyclerAdapter = new RecyclerAdapter();
            mRecycler.setAdapter(recyclerAdapter);
        }
        loadConetext();
    }

    public static int getType() {
        return type;
    }

    public static Fragment_near getInstance(int type, String msg) {
        keywords = msg;
        Fragment_near fragment_near = new Fragment_near();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment_near.setArguments(bundle);
        return fragment_near;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        type = getArguments().getInt("type", 0);

        mRecycler = view.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter();
        mRecycler.setAdapter(recyclerAdapter);
        loadConetext();

        recyclerAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.e("监听", "监听");
                Intent intent = new Intent(getContext(), ShopDetailsActivity.class);
                int id = data2.get(position).getId();
                intent.putExtra("shop_id", String.valueOf(id));
                startActivity(intent);
            }
        });
    }


    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolders> {
        private OnItemClickListener mListener = null;
        private List<ShopBean> data;

        public void clearData() {
            if (data == null) return;
            data.clear();
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            return new ViewHolders(LinearLayout.inflate(viewGroup.getContext(), R.layout.item_fragment_near, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolders viewHolders, final int i) {
            viewHolders.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClick(view, i);
                    }
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
                ImageView imageView = new ImageView(getActivity());
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
}
