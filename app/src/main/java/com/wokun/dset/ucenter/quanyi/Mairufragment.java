package com.wokun.dset.ucenter.quanyi;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.addcards.TixianMoneyActivity2;
import com.wokun.dset.ucenter.quanyi.dashishop.adapter.LbBuyGVAdapter;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.LbBuyBean;
import com.wokun.dset.utils.AlertDialogUtil;
import com.wokun.dset.utils.PwdDialog;
import com.wokun.dset.utils.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\24 0024.
 */

public class Mairufragment extends BaseFragment {

    @BindView(R.id.gv_lb_buy)
    CustomGridView gvLbBuy;

    @BindView(R.id.txt_jifen)
    TextView tvLbBuyLb2Value;
    @BindView(R.id.text_yue)
    TextView tvLbBuyLbValue;
    @BindView(R.id.mairu_txt1)
    TextView mairu_txt1;
    @BindView(R.id.mairu_txt2)
    TextView mairu_txt2;
    private String number = "";//求购数量
    private LbBuyGVAdapter gvAdapter;
    private List<LbBuyBean.TradeNum> mListBean;
    private int unBind ;
    private  LbBuyBean data;
    @Override
    public int createView() {
        return R.layout.fragment_mairu;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse<LbBuyBean>>post(Constants.BASE_URL + Constants.LB_BUY)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<LbBuyBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<LbBuyBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("修改忘记密码信息", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                             data = (LbBuyBean) body.getData();
                            mListBean = data.getTrade_num();
                            unBind = data.getUnbind();
                        //    record_list = data.getList_url();
                       /*     int xitong = Integer.parseInt(data.getSysprice());
                            int cunzai = Integer.parseInt(data.getDiscount_ratio());
                            int zongshu = xitong * cunzai;*/

                            tvLbBuyLbValue.setText(data.getUser_wallet().getCash_wa() + "");
                            tvLbBuyLb2Value.setText(data.getUser_wallet().getHcg_wa() + "");
                            setGrid(mListBean);
                        } else if (body.getStatus().equals("0002")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);
                        } else if (body.getStatus().equals("0003")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);

                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("首页3",response+"!!!!");
                        // DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });

    }


    /**
     * 列表
     *
     * @param
     */
    private void setGrid(List<LbBuyBean.TradeNum> integerList) {
        // 初始化gridview
        gvLbBuy.setNumColumns(3);
        gvAdapter = new LbBuyGVAdapter(getContext(), integerList);
        gvLbBuy.setAdapter(gvAdapter);
        initMenuListener(integerList);
    }

    @OnClick(R.id.btn_lb_buy)
    public void actionBtnlbbuy(){
        if (unBind==1){//未绑定
            AlertDialogUtil.getInstance().showDialog(getContext(), getString(R.string.alert_title), getString(R.string.binding_card_tip), new AlertDialogUtil.OnClickYesListener() {
                @Override
                public void onClickYes() {
                    Intent intent = new Intent(getContext(), TixianMoneyActivity2.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }, new AlertDialogUtil.OnClickNoListener() {
                @Override
                public void onClickNo() {
                    getActivity().finish();
                }
            },false);
        }else {
            final PwdDialog dialog = new PwdDialog(getContext());
            dialog.showDialog();
            dialog.setPwdDialogInterface(new PwdDialog.PwdDialogInterface() {
                @Override
                public void onInputFinish(String result) {
                 commit(result);
                }
            });
        }
    }

    private void commit(String result) {
          Log.e("result",result+"");
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("number", number);
        params.put("jymm", result);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        Log.e("交易信息", "交易信息!!!!"+number+result);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.LB_BUY_CHECK)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("number", number)
                .params("jymm", result)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("修改忘记密码信息", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());

                        } else if (body.getStatus().equals("0002")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);
                        } else if (body.getStatus().equals("0003")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);

                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("首页3",response+"!!!!");
                        // DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });


    }


    private View oldView;
    private void initMenuListener(List<LbBuyBean.TradeNum> integerList) {
        gvLbBuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               number = mListBean.get(position).getNum();
              //  String multiply = Solution.getInstance().multiply(data.getSysprice(), data.getDiscount_ratio());

                BigDecimal num1 = new BigDecimal(data.getSysprice());
                BigDecimal num3 = new BigDecimal(number);
                BigDecimal num2 = new BigDecimal(data.getDiscount_ratio());
                BigDecimal result = num1.multiply(num2).multiply(num3);
                DecimalFormat df1 = new DecimalFormat("0.00");
                String result1 = df1.format(result);
                Log.e("数据",data.getSysprice()+""+data.getDiscount_ratio());
                mairu_txt1.setText("预计您将以"+result1);
                mairu_txt2.setText("价格购买"+  number+"金票");

                Drawable drawable = getResources().getDrawable(R.drawable.shape_main_color_bord_white);
                ((LinearLayout) view).getChildAt(0).setBackground(drawable);
                ((TextView) ((LinearLayout) view).getChildAt(0)).setTextColor(Color.parseColor("#056196"));

                if (oldView!=null){
                    Drawable drawable1 = getResources().getDrawable(R.drawable.shape_main_color_bord);
                    ((LinearLayout) oldView).getChildAt(0).setBackground(drawable1);
                    ((TextView) ((LinearLayout) oldView).getChildAt(0)).setTextColor(Color.parseColor("#dddddd"));
                }
                oldView = view;

            }
        });
    }



}
