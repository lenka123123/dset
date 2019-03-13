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
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity2;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity3;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreActivity5;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse2;


import com.wokun.dset.ucenter.bean.MessageBean;
import com.wokun.dset.ucenter.myyijian.adapter.NoticeAdapter;
import com.wokun.dset.ucenter.zhifudiaolog.JilvyueDetailsAdapter;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class XiaoxiDetailsActvitity extends SimpleRefreshAndLoadMoreActivity5<MessageBean> {

    private  NoticeAdapter  mAdapter;
    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("消息");
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

        return OkGo.<BaseResponse2<MessageBean>>post(Constants.BASE_URL + Constants.MESSAGE_LIST)
                .tag(this)
                .params("page", "1")
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime());
    }


    @Override
    public BaseQuickAdapter<MessageBean, BaseViewHolder> initAdapter() {
       mAdapter =  new NoticeAdapter(R.layout.item_system_message, null);
        return mAdapter;

    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest .execute(new JsonCallback<BaseResponse2<MessageBean>>(mMultipleStatusView) {
            @Override
            public void onSuccess(Response<BaseResponse2<MessageBean>> response) {
                BaseResponse2 body = response.body();
                Log.e("11body",""+body);
                if(body == null)return;
                mMultipleStatusView.showContent();
                if(body.getStatus().equals("0001")){
                    List<MessageBean> data = (List<MessageBean>) body.getData();
                    if(data == null){return;}
                    Log.e("11data",""+data.toString());
                    setData(isRefresh,data);

                }
            }
        });
    }

}
