package com.wokun.dset.ucenter.quanyi.dashishop.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wokun.dset.R;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreFragment6;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.DsytDindanBean;
import com.wokun.dset.ucenter.bean.TradeOrderListBean;
import com.wokun.dset.ucenter.quanyi.dashishop.PaydetailsActivity;
import com.wokun.dset.ucenter.quanyi.dashishop.adapter.Dsytchangealladapter;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.GoodsOrderBean;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class GoodsOrderfinish extends SimpleRefreshAndLoadMoreFragment6<TradeOrderListBean> {
    //item_dashi_all
    private Dsytchangealladapter mAdapter;


    public Request initRequest() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", "1");
        params.put("status", "ywc");
        params.put("page_size", "10");
        params.put("order_type", "1");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        return OkGo.<BaseResponse<DsytDindanBean>>post(Constants.BASE_URL + Constants.TRADEORDER)
                .tag(this)
                .params("sign", sign)
                .params("status", "ywc")
                .params("order_type", "1")
                .params("page", "1")
                .params("timestamp",  StringUtil.getCurrentTime())
                .params("page_size", "10");
    }

    @Override
    public BaseQuickAdapter<TradeOrderListBean, BaseViewHolder> initAdapter() {
        mAdapter = new Dsytchangealladapter(R.layout.item_dashi_all,  null);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TradeOrderListBean bean = (TradeOrderListBean) adapter.getData().get(position);
                if(R.id.action_order_detail == view.getId()){//订单详情
                    Log.e("订单详情11","订单详情11");
                    Log.e("订单详情11","订单详情11");
                    Intent intent = new Intent(getContext(), PaydetailsActivity.class);
                    intent.putExtra("id",bean.getId());
                    startActivity(intent);
                }else if(R.id.action_cancel_order == view.getId()){//取消订单
                    LoginMgr.getInstance().cancelDindan(bean.getId());
                }else if(R.id.action_sure_order == view.getId()){//确认收款
                    //   GoodsOrderMgr.getInstance().onEvaluate(GoodsOrderStateAll.this,bean);
                }else if(R.id.action_sure_myorder == view.getId()){//确认付款
                    //  GoodsOrderMgr.getInstance().onConfirmReceipt(GoodsOrderStateAll.this,bean);
                }

            }
        });

        return mAdapter;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<DsytDindanBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<DsytDindanBean>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.getStatus().equals("0001")){
                    DsytDindanBean data = (DsytDindanBean) body.getData();
                    if(data==null){return;}
                    List<TradeOrderListBean> list = data.getTradeOrderList();
                    setData(isRefresh, list);
                }

            }});

    }
}