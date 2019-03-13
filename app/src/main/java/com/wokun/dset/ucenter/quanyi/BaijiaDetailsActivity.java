package com.wokun.dset.ucenter.quanyi;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseRefreshAndLoadMoreActivity;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity1;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity2;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity6;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.ucenter.bean.RollOutRecordBean;
import com.wokun.dset.ucenter.quanyi.dashishop.adapter.BaojiaListAdapter;
import com.wokun.dset.ucenter.quanyi.dashishop.adapter.MyBaojiaapter;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.BaojiaBean;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.LbSellListBean;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnItemClick;

import static com.wokun.dset.DsetApp.getContext;
import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class BaijiaDetailsActivity extends SimpleRefreshAndLoadMoreActivity6<BaojiaBean> {

    private   BaojiaListAdapter  adapter;
    private      List<BaojiaBean> data;

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("报价记录");
    }
    @Override
    public void init() {

        mMultipleStatusView.showLoading();
    }

    @Override
    public Request initRequest() {
        Map params = new HashMap();
        params.put("page", "1");
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        return OkGo.<BaseResponse2<BaojiaBean>>post(Constants.BASE_URL + Constants.MY_BAOJIAJL)
                .tag(this)
                .params("page","1")
                .params("timestamp",StringUtil.getCurrentTime())
                .params("sign",sign);
    }

    @Override
    public BaseQuickAdapter<BaojiaBean, BaseViewHolder> initAdapter() {
        adapter = new BaojiaListAdapter(R.layout.item_baojia,null);
        return adapter;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest .execute(new JsonCallback<BaseResponse2<BaojiaBean>>(mMultipleStatusView) {
            @Override
            public void onSuccess(Response<BaseResponse2<BaojiaBean>> response) {
                BaseResponse2 body = response.body();
                Log.e("11body",""+body);
                if(body == null)return;
                mMultipleStatusView.showContent();
                if(body.getStatus().equals("0001")){
                    data = (List<BaojiaBean>) body.getData();
                    if(data == null){return;}
                    Log.e("data",""+data);
                    setData(isRefresh,data);

                }
            }
        });



    }



    //item_baojia
    // item_baojia_mai
    //MY_BAOJIAJL


}
