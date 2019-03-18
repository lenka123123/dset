package com.wokun.dset.ucenter.myyijian.fragment;



import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreFragment1;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.response.BaseResponse2;

import com.wokun.dset.ucenter.bean.AdviceRecordBean;
import com.wokun.dset.ucenter.myyijian.HistoryResponse;
import com.wokun.dset.ucenter.myyijian.adapter.MyListHistoryadapter;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.LbSellListBean;
import com.wokun.dset.utils.StringUtil;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2018/7/9/009.
 */
//历史建议
public class HistoryMymessagefragment extends SimpleRefreshAndLoadMoreFragment<AdviceRecordBean> {
    @Override
    public Request initRequest() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", "1");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);

        return OkGo.<BaseResponse2<AdviceRecordBean>>post(Constants.BASE_URL + Constants.ADVICE_RECORD)
                .tag(this)
                .params("sign", sign)
                .params("page", "1")
                .params("timestamp",  StringUtil.getCurrentTime());
    }

    @Override
    public BaseQuickAdapter<AdviceRecordBean, BaseViewHolder> initAdapter() {
        MyListHistoryadapter mAdapter = new MyListHistoryadapter(R.layout.home_history,null);
        return mAdapter;
    }

    @Override
    public void loadData(boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse2<AdviceRecordBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse2<AdviceRecordBean>> response) {
                BaseResponse2 body = response.body();
                if(body == null)return;
                Log.e("进来了22231","进来了2!!!!");
                if (body.getStatus().equals("0001")) {
//                    RxToast.showShort(body.getMessage());
                  List<AdviceRecordBean>  data1 =body.getData();
                    if(data1 == null){return;}
                    setData(true,data1);

                    Log.e("data",""+data1);
                    Log.e("进来了223","进来了");

                }
            }

            @Override
            public void onError(Response<BaseResponse2<AdviceRecordBean>> response) {
                super.onError(response);

                BaseResponse2 body = response.body();
                  Log.e("进来了223","进来了2"+body);
            //    RxToast.showShort(body.getMessage());


            }
        });

    }

    // private MyListHistoryadapter myHistoryapter;
    @Override
    public void onResume() {
        super.onResume();
        doOnRefreshData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }

}
