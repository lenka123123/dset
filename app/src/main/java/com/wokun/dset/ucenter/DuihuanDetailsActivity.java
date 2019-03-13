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
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity6;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity7;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.response.BaseResponse3;
import com.wokun.dset.ucenter.bean.BankCardBean;
import com.wokun.dset.ucenter.bean.ExchangeRecordBean;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
兑换记录 积分明细
* */
public class DuihuanDetailsActivity extends SimpleRefreshAndLoadMoreActivity7<ExchangeRecordBean> {

private  JilvDetailsAdapter  mAdapter;
@Override
public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("兑换记录");
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
    Map<String, String> removeMap = removeEmptyData(params);
    Map<String, String> resultMap = sortMapByKey(removeMap);
    String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
    Log.e("进来来dsa2211","进来来dsa22");

        return OkGo.<BaseResponse2<ExchangeRecordBean>>post(Constants.BASE_URL + Constants.ASSETS_ADD_RECORD)
                .tag(this)
                .params("sign", sign)
                .params("timestamp",  StringUtil.getCurrentTime())
                .params("page", "1");
        }

@Override
public BaseQuickAdapter<ExchangeRecordBean, BaseViewHolder> initAdapter() {
        mAdapter =  new JilvDetailsAdapter(R.layout.item_my_duihuandetails, null);
        return mAdapter;
        }

@Override
public void loadData(final boolean isRefresh) {
    mRequest .execute(new JsonCallback<BaseResponse2<ExchangeRecordBean>>(mMultipleStatusView) {
                @Override
                public void onSuccess(Response<BaseResponse2<ExchangeRecordBean>> response) {
                        BaseResponse2 body = response.body();
                        Log.e("11body",""+body);
                        if(body == null)return;
                             mMultipleStatusView.showContent();
                            if(body.getStatus().equals("0001")){
                                List<ExchangeRecordBean> data = (List<ExchangeRecordBean>) body.getData();
                                if(data == null){return;}
                                Log.e("11data",""+data.toString());
                                  setData(isRefresh,data);

                        }
                }
        });

        }

        }

