package com.wokun.dset.ucenter;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity3;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity4;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.ucenter.adapter.JilvjifenDetailsAdapter;
import com.wokun.dset.ucenter.bean.BillRecordBean;
import com.wokun.dset.ucenter.bean.ExchangeRecordBean;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class JifenDetailsActivity extends SimpleRefreshAndLoadMoreActivity4<BillRecordBean> {

private  JilvjifenDetailsAdapter  mAdapter;
@Override
public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("积分明细");
        }

@Override
public void init() {
        mMultipleStatusView.showLoading();
        }




@Override
public Request initRequest() {
    Map params = new HashMap();
    params.put("timestamp", StringUtil.getCurrentTime());
    params.put("page", "1");
    params.put("event_type", "0");
    params.put("wallet_type", "积分");
    Map<String, String> removeMap = removeEmptyData(params);
    Map<String, String> resultMap = sortMapByKey(removeMap);
    String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
    Log.e("进来来dsa2211","进来来dsa22");

    return OkGo.<BaseResponse2<ExchangeRecordBean>>post(Constants.BASE_URL + Constants.BILL_RECORD_LIST)
            .tag(this)
            .params("page", "1")
            .params("event_type", "0")
            .params("sign", sign)
            .params("wallet_type", "积分")
            .params("timestamp", StringUtil.getCurrentTime());

        }

@Override
public BaseQuickAdapter<BillRecordBean, BaseViewHolder> initAdapter() {
       mAdapter =  new JilvjifenDetailsAdapter(R.layout.item_my_details, null);
        return mAdapter;

        }

@Override
public void loadData(final boolean isRefresh) {
    mRequest .execute(new JsonCallback<BaseResponse2<BillRecordBean>>(mMultipleStatusView) {
        @Override
        public void onSuccess(Response<BaseResponse2<BillRecordBean>> response) {
            BaseResponse2 body = response.body();
            Log.e("11body",""+body);
            if(body == null)return;
            mMultipleStatusView.showContent();
            if(body.getStatus().equals("0001")){
                List<BillRecordBean> data = (List<BillRecordBean>) body.getData();
                if(data == null){return;}
                Log.e("11data",""+data.toString());
                setData(isRefresh,data);

            }
        }
    });
        }

        }

