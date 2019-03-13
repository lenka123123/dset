package com.wokun.dset.ucenter.quanyi;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreFragment1;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.RollOutRecordBean;
import com.wokun.dset.ucenter.quanyi.dashishop.adapter.LbsellAdapter;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.LbSellListBean;
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\24 0024.
 * SimpleRefreshAndLoadMoreFragment
 *       R.layout.item_maichu;
 *       R.layout.layout_swipe_refresh;
 */

public class Maichufragment  extends SimpleRefreshAndLoadMoreFragment1<LbSellListBean.ListBean> {

    private  EditText    myedit;
    @Override
    public Request initRequest() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", "1");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);

        return OkGo.<BaseResponse<LbSellListBean>>post(Constants.BASE_URL + Constants.LB_SELL)
                .tag(this)
                .params("sign", sign)
                .params("page", "1")
                .params("timestamp",  StringUtil.getCurrentTime());
    }

    @Override
    public BaseQuickAdapter<LbSellListBean.ListBean, BaseViewHolder> initAdapter() {
        Log.e("点击进来了a3","进来了11");
        LbsellAdapter mAdapter = new LbsellAdapter(R.layout.item_maichu,null);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LbSellListBean.ListBean data = (LbSellListBean.ListBean) adapter.getData().get(position);
                if(R.id.itenm_de_id == view.getId()){
                    String mid = data.getId();
                    String amount = data.getAmount();
                    String number = data.getNumber();
                    showDialog(mid,amount,number);
                }
            }
        });

        return mAdapter;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showDialog(final String mid, String amount, final String number) {
        LayoutInflater inflaterDl = LayoutInflater.from(getContext());
final RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(R.layout.pop_chujia, null);

 final Dialog dialog = new Dialog(getContext());
                 dialog.create();
                 dialog.show();
                 dialog.getWindow().setContentView(layout);
        ImageView delete_pop = (ImageView) dialog.findViewById(R.id.delete_pop);
        TextView item_baojia_price = (TextView) dialog.findViewById(R.id.item_baojia_price);
        TextView item_baojia_num = (TextView) dialog.findViewById(R.id.item_baojia_num);
        EditText my_edit = (EditText) dialog.findViewById(R.id.my_edit);

        final TextView dsyt_msg = (TextView) dialog.findViewById(R.id.dsyt_msg);
        item_baojia_price.setText("参考价：￥"+amount);
        item_baojia_num.setText("出售数量:"+number+"金票");
        dsyt_msg.setText("您将以"+0+"元的价格出售"+0+"金票");
        my_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dsyt_msg.setText("您将以"+charSequence.toString().trim()+"元的价格出售"+number+"金票");

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        TextView btnCancel = (TextView) dialog.findViewById(R.id.btnCommit);
           myedit = (EditText) dialog.findViewById(R.id.my_edit);//输入单价

          btnCancel.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
                comitData(mid);
               dialog.dismiss();
 }
 });

        delete_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }

    private void comitData(String mid ) {
        String price = myedit.getText().toString();
        Log.e("我的出价","!!price!"+price+"mid:"+mid);
        if (TextUtils.isEmpty(price)) {
            RxToast.showShort("请输入我的出价");
            return;
        }

        Map params = new HashMap();
        params.put("price", price);
        params.put("order_id", mid);
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        Log.e("我的出价",sign+"!!price!"+price+"mid:"+mid);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.MY_BAOJIA)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("price", price)
                .params("order_id", mid)
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
                        Log.e("user",response+"!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });





    }


    @Override
    public void loadData( boolean isRefresh) {
/*
        Log.e("进来了2232d3","进来了11");
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", "1");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来了2232d3","进来了11"+sign);

        OkGo.<BaseResponse<LbSellListBean>>post(Constants.BASE_URL + Constants.LB_SELL)
                .tag(this)
                .params("sign", sign)
                .params("page", "1")
                .params("timestamp",  StringUtil.getCurrentTime())*/
        mRequest.execute(new JsonCallback<BaseResponse<LbSellListBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<LbSellListBean>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                Log.e("user","进来了2!!!!");
                if (body.getStatus().equals("0001")) {
                  //  RxToast.showShort(body.getMessage());
                    LbSellListBean data1 = (LbSellListBean) body.getData();
                    if(data1 == null){return;}
                    setData(true,data1.getList());

                    Log.e("data",""+data1);
                    Log.e("进来了223","进来了");

                }

            }

            @Override
            public void onError(Response<BaseResponse<LbSellListBean>> response) {
                super.onError(response);
                 BaseResponse body = response.body();
                RxToast.showShort(body.getMessage());
                Log.e("进来了223","进来了2"+body);

            }
        });
    }

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
