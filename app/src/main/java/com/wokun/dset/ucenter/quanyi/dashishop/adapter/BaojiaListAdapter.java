package com.wokun.dset.ucenter.quanyi.dashishop.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.R;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.BaojiaBean;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.DsetApp.getContext;
import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\28 0028.
 */

public class BaojiaListAdapter extends BaseQuickAdapter<BaojiaBean, BaseViewHolder> {
     private  MyBaojiaapter myBaojiaapter;
     private String mid;
    public BaojiaListAdapter(int layoutResId, @Nullable List<BaojiaBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaojiaBean item) {
        mid  = item.getId();
        helper.setText(R.id.baojia_danhao,"订单号："+ item.getId())
                .setText(R.id.baojia_time, item.getCreated_at())
                .setText(R.id.baojia_count, item.getNumber())
                .setText(R.id.baojia_myprice, item.getAmount());

       // RecyclerView myrecylview = (RecyclerView)helper.getView(R.id.item_recyclerView);
       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myrecylview.setLayoutManager(linearLayoutManager);
        myBaojiaapter = new MyBaojiaapter();*/
        final List<BaojiaBean.Baojiapeople> offer = item.getOffer();
        if(offer.size()==0) return;
        Log.e("报价列表参数",""+ offer.toString());
        LinearLayout view1 = (LinearLayout)helper.getView(R.id.linearlayout);
        view1.removeAllViews();
        for (int i = 0; i < offer.size(); i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_baojia_mai, null);
            TextView mUid = view.findViewById(R.id.item_de_name);
            mUid.setText("UID:"+offer.get(i).getOut_uid());
            TextView item_de_chujia = view.findViewById(R.id.item_de_chujia);
            item_de_chujia .setText("出价：￥"+offer.get(i).getOut_offer());
            TextView item_de_num = view.findViewById(R.id.item_de_num);
            final String offer_id = offer.get(i).getOffer_id();
            final String sell_uid = offer.get(i).getOut_uid();
            item_de_num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // RxToast.showShort("dianjil");

                    comitMessage(mid,sell_uid,offer_id);
                }
            });

            ImageView user_head_img = view.findViewById(R.id.user_head_img);
            ImageLoader.loadImage( offer.get(i).getHeadimg(),user_head_img);
            view1.addView(view);

        }
       // myBaojiaapter.setData(offer);
    }

    private void comitMessage(String id,String sell_uid,String offer_id) {

        Map params = new HashMap();
        params.put("id", id);
        params.put("sell_uid", sell_uid);
        params.put("offer_id", offer_id);
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        Log.e("user",sign+"!!!!"+sell_uid+offer_id+id);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.BUY_ta)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("sell_uid", sell_uid)
                .params("id", id)
                .params("offer_id", offer_id)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("user","进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());

                        }
                    }
                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                    }
                });













    }
}
