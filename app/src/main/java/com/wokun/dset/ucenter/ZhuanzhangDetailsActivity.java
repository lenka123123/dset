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
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity1;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.ucenter.adapter.ZhuanzhangDetailsAdapter;

import com.wokun.dset.ucenter.bean.RollOutRecordBean;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class ZhuanzhangDetailsActivity extends SimpleRefreshAndLoadMoreActivity1<RollOutRecordBean> {

private ZhuanzhangDetailsAdapter mAdapter;
@Override
public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("转账记录");
        }

@Override
public void init() {
   mMultipleStatusView.showLoading();

        }

@Override
public Request initRequest() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("pay_type", "2");
        params.put("page", "1");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来dsa2211","进来来dsa22");

        return OkGo.<BaseResponse2<RollOutRecordBean>>post(Constants.BASE_URL + Constants.ROLL_OUT_record)
                .tag(this)
                .params("sign", sign)
                .params("timestamp",  StringUtil.getCurrentTime())
                .params("page", "1")
                .params("pay_type", "2");
        }

@Override
public BaseQuickAdapter<RollOutRecordBean, BaseViewHolder> initAdapter() {
       mAdapter =  new ZhuanzhangDetailsAdapter(R.layout.item_myzhuanzhang_details, null);
        return mAdapter;

        }

@Override
public void loadData(final boolean isRefresh) {
        mRequest .execute(new JsonCallback<BaseResponse2<RollOutRecordBean>>(mMultipleStatusView) {
                @Override
                public void onSuccess(Response<BaseResponse2<RollOutRecordBean>> response) {
                        BaseResponse2 body = response.body();
                        Log.e("11body",""+body);
                        if(body == null)return;
                         mMultipleStatusView.showContent();
                        if(body.getStatus().equals("0001")){
                                List<RollOutRecordBean> data = (List<RollOutRecordBean>) body.getData();
                                if(data == null){return;}
                                Log.e("data",""+body);
                                setData(isRefresh,data);
                        }
                }
        });
        }

        }

