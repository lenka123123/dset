package com.wokun.dset.store.dstore.dstorestate;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.pinkongshop.ZhihuiSuccessfulActivity;
import com.wokun.dset.store.adapter.DStoreGoodsListAdapter;
import com.wokun.dset.store.adapter.DStoreOrderStateListAdapter;
import com.wokun.dset.store.bean.AilPayBean;
import com.wokun.dset.store.bean.BaseResPonse;
import com.wokun.dset.store.bean.DefaultAddress;
import com.wokun.dset.store.bean.MoneyBean;
import com.wokun.dset.store.bean.OrderStateBean;
import com.wokun.dset.store.dstore.expressinfo.DStoreExpressInfoActivity;
import com.wokun.dset.store.dstore.immediatelypay.DStoreImmediatelyPayActivity;
import com.wokun.dset.store.dstore.immediatelypay.DStoreOrderDetailActivity;
import com.wokun.dset.ucenter.quanyi.BaijiaDetailsActivity;
import com.wokun.dset.ucenter.quanyi.Maichufragment;
import com.wokun.dset.ucenter.quanyi.Mairufragment;
import com.wokun.dset.ucenter.quanyi.MyMessageFragmentAdapter;
import com.wokun.dset.ucenter.quanyi.dashishop.GoodsOrderActivity;
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.PopWindow;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.ToastUtil;
import com.wokun.dset.utils.alertview.AlertViewContorller;
import com.wokun.dset.utils.alertview.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreStateActivity extends BaseActivity implements OnItemClickListener {

    private static final String TAG = "";
    private List<String> tab = new ArrayList<>();
    private TabLayout mTabLayout;
    private String status = "all";
    private int page = 1;

    private SmartRefreshLayout mEasyRefreshLayout;
    private RecyclerView mRecyclerView;
    private ImageView activity_no_data;
    private DStoreOrderStateListAdapter mAdapter;
    private AlertViewContorller mAlertViewContorller;
    private TextView empty;
    private int select = 0;

    @Override
    public int setActivityLayout() {
        return R.layout.activity_store_state;
    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        String me = intent.getStringExtra("me");

        mTabLayout = findViewById(R.id.tab_layout);
        mEasyRefreshLayout = (SmartRefreshLayout) findViewById(R.id.easylayout);
        activity_no_data = (ImageView) findViewById(R.id.activity_no_data);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DStoreStateActivity.this.finish();
            }
        });
        empty = (TextView) findViewById(R.id.empty);
        mEasyRefreshLayout.setEnableRefresh(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new DStoreOrderStateListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);


        tab.add("全部");
        tab.add("待付款");
        tab.add("待发货");
        tab.add("待收货");
        tab.add("已完成");

        if (me.equals("all")) {
            status = "all";
            select = 0;
        } else if (me.equals("pay")) {
            select = 1;
            status = "0";
        } else if (me.equals("send")) {
            select = 2;
            status = "1";
        } else if (me.equals("accept")) {
            select = 3;
            status = "2";
        } else if (me.equals("refund")) {
            select = 4;
            status = "4";
        }
        //添加tab选项
        for (int i = 0; i < tab.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tab.get(i)));
        }
        getList();

        mTabLayout.getTabAt(select).select();
    }

    @Override
    public void initData() {

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //all全部  0待付款  1待发货  2待收货  3已收货  4已完成     5已关闭
                String text = tab.getText().toString();
                if (text.equals("全部")) {
                    status = "all";
                } else if (text.equals("待付款")) {
                    status = "0";
                } else if (text.equals("待发货")) {
                    status = "1";
                } else if (text.equals("待收货")) {
                    status = "2";
                } else if (text.equals("已完成")) {
                    status = "4";
                }
                if (mAdapter != null) {
                    mAdapter.getData().clear();
                    mAdapter.notifyDataSetChanged();
                }
                page = 1;
                getList();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override  //重新选择
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mEasyRefreshLayout.setDisableContentWhenLoading(true);
        // 禁止刷新
//        mEasyRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                currentPage = 1;
//                getShopList();
//                refreshlayout.finishRefresh();
//            }
//        });
        //加载更多
        mEasyRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getList();
                refreshlayout.finishLoadmore();
            }
        });


    }


    //all全部0待付款1待发货2待收货3已收货4已完成5已关闭
    private void getList() {
        String token = (String) SpCommonUtils.get(DStoreStateActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreStateActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("status", status);
        params.put("page", page + "");
        params.put("page_size", "10");

        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.ME_ORDER_LIST)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("status", status)
                .params("page", page + "")
                .params("page_size", "10")
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        OrderStateBean stateBean = (OrderStateBean) JosnFrom.getInstance().getObj(response.body().toString(), OrderStateBean.class);
                        if (stateBean != null && stateBean.getStatus().equals("0001")) {

                            if (page == 1 && stateBean.getData().getOrderInfo().size() == 0) {
                                mEasyRefreshLayout.setVisibility(View.GONE);
                                activity_no_data.setVisibility(View.VISIBLE);
                            } else {
                                mEasyRefreshLayout.setVisibility(View.VISIBLE);
                                activity_no_data.setVisibility(View.GONE);
                                detailDta(stateBean.getData().getOrderInfo());
                            }

                        } else {
                            RxToast.showShort(stateBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }

    private void detailDta(List<OrderStateBean.DataBean.OrderInfoBean> goodesList) {
        if (mAdapter == null) return;
        if (goodesList.size() >= 1) {
            page++;
            mAdapter.getData().addAll(goodesList);
            mAdapter.notifyDataSetChanged();
        } else {
            mEasyRefreshLayout.setLoadmoreFinished(true);
        }
    }

    private OrderStateBean.DataBean.OrderInfoBean orderInfoBean = null;


    String[] type = {"余额", "金票支付"};

    @Override
    public void onItemClick(View view, List<String> mOthers, Object o, int position) {
        if (position == -1) {
            mAlertViewContorller.dismiss();
        } else {
            if (mOthers.get(position).equals(type[0])) {
                getCenterCancelDialog(orderInfoBean);
            } else {
                pay(orderInfoBean.getOrder_id());
            }
            mAlertViewContorller.dismiss();
            TextView view1 = (TextView) view;
            view1.setText(mOthers.get(position));
        }
    }


    private void closeOrder(String order_id) {
        String token = (String) SpCommonUtils.get(DStoreStateActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreStateActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("order_id", order_id);
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.CLOSE_ORDER)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("order_id", order_id)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        BaseResPonse stateBean = (BaseResPonse) JosnFrom.getInstance().getObj(response.body().toString(), BaseResPonse.class);
                        if (stateBean != null && stateBean.getStatus().equals("0001")) {
                            page = 1;
                            getList();//重新刷新
                        } else {
                            RxToast.showShort(stateBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }


    // 	支付类型 1微信 2支付宝 5余额
    private void pay(String order_id) {
        String token = (String) SpCommonUtils.get(DStoreStateActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreStateActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("order_id", order_id);
        params.put("pay_type", "2");
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.ORDER_LIST_PAY)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("order_id", order_id)
                .params("pay_type", "2")
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {

                        AilPayBean payBean = (AilPayBean) JosnFrom.getInstance().getObj(response.body().toString(), AilPayBean.class);
                        if (payBean != null && payBean.getStatus().equals("0001")) {
                            realPay(payBean.getData().getOrderString());
                        } else {
                            RxToast.showShort(payBean.getMsg());
                        }
//                        BaseResPonse stateBean = (BaseResPonse) JosnFrom.getInstance().getObj(response.body().toString(), BaseResPonse.class);
//                        if (stateBean != null && stateBean.getStatus().equals("0001")) {
//                            page = 1;
//                            getList();//重新刷新
//                        } else {
//                            RxToast.showShort(stateBean.getMsg());
//                        }
                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }

    protected void realPay(final String body) {

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(DStoreStateActivity.this);
                Map<String, String> result = alipay.payV2(body, true);

                alipayManage(result);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    private void alipayManage(Map<String, String> result) {
        for (String aa : result.values()) {
            Log.i(TAG, "alipayManage" + aa);
        }
        Log.i(TAG, "alipayManage: " + result.get("resultStatus"));
        if (result.containsKey("resultStatus")) {
            Log.i(TAG, "alipayManage: " + result.get("resultStatus"));
            if (result.get("resultStatus").equals("9000")) {
                RxToast.showShort("支付成功！");
//                Intent intent = new Intent(DStoreStateActivity.this, ZhihuiSuccessfulActivity.class);
//                intent.putExtra("doubleprice", promote_price);
//                intent.putExtra("name", link_man);
//                intent.putExtra("phone", phone);
//                intent.putExtra("address", address);
//                DStoreOrderDetailActivity.this.startActivity(intent);
            }

            if (result.get("resultStatus").equals("4000")) {
                ToastUtil.showToastThread(DStoreStateActivity.this, "支付失败，请重试！");
                // centerDialog.showDialog("支付失败，请重试！", R.drawable.payes_fail);
            }

            if (result.get("resultStatus").equals("6001")) {
                ToastUtil.showToastThread(DStoreStateActivity.this, "取消支付！");
                //  centerDialog.showDialog("取消支付", R.drawable.payes_fail);
            }
        }
    }

    private Dialog dialog;
    private String inputContent;

    public void getCenterCancelDialog(final OrderStateBean.DataBean.OrderInfoBean data) {
        dialog = new Dialog(DStoreStateActivity.this, R.style.diydialog);
        dialog.setContentView(R.layout.activity_pay_namepassword);
        Window window = dialog.getWindow();
        //设置窗口的位置
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        DisplayMetrics displayMetrics = DStoreStateActivity.this.getResources().getDisplayMetrics();//获取屏幕的宽和高用
//        layoutParams.width = (int) (displayMetrics.widthPixels * 0.6);
//        layoutParams.y = (int) (displayMetrics.heightPixels * 0.5);
        final VerificationCodeView verificationCodeView = window.findViewById(R.id.icv);
        final TextView txt_next = window.findViewById(R.id.txt_next);
        final TextView pop_zhihui_shanchu = window.findViewById(R.id.pop_zhihui_shanchu);
        final TextView pop_delete = window.findViewById(R.id.pop_delete);
        txt_next.setText("提交");
        pop_zhihui_shanchu.setText("请输入支付密码");
        pop_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });
        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputContent.length() == 6) {

                    moneyPay(data, inputContent);
                } else {
                    RxToast.showShort("请输入完整支付密码");
                }
            }
        });
        verificationCodeView.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                inputContent = verificationCodeView.getInputContent();
                Log.i("icv_input", verificationCodeView.getInputContent());
            }

            @Override
            public void deleteContent() {
                Log.i("icv_delete", verificationCodeView.getInputContent());
            }
        });
        //x 位置设置
//        layoutParams.y = show_dialog_add.getHeight() * 2;//y 位置设置
        layoutParams.alpha = 1f; // 透明度
        window.setAttributes(layoutParams);
        dialog.show();
    }


    //密码支付
    private void moneyPay(OrderStateBean.DataBean.OrderInfoBean data, String password) {
        String token = (String) SpCommonUtils.get(DStoreStateActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreStateActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("order_id", data.getOrder_id());
        params.put("pay_type", "5");
        params.put("password", password);
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.ORDER_LIST_PAY)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("order_id", data.getOrder_id())
                .params("pay_type", "5")
                .params("password", password)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        if (dialog != null) dialog.dismiss();
                        BaseResPonse stateBean = (BaseResPonse) JosnFrom.getInstance().getObj(response.body().toString(), BaseResPonse.class);
                        if (stateBean != null && stateBean.getStatus().equals("0001")) {
                            page = 1;
                            getList();//重新刷新
                        } else {
                            RxToast.showShort(stateBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        if (dialog != null) dialog.dismiss();
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void itemClick(String text, OrderStateBean.DataBean.OrderInfoBean item) {
        if (text.equals("取消订单")) {
            closeOrder(item.getOrder_id());
        } else if (text.equals("立即付款")) {
            orderInfoBean = item;
            mAlertViewContorller = new AlertViewContorller(empty, "选择支付类别", null, "取消", null, type,
                    context, AlertViewContorller.Style.ActionSheet, this);
            mAlertViewContorller.setCancelable(true).show();
        } else if (text.equals("提醒发货")) {
            RxToast.showShort("已向商家发出提醒");
        } else if (text.equals("物流信息")) {
            Intent intent = new Intent(DStoreStateActivity.this, DStoreExpressInfoActivity.class);
            intent.putExtra("orderid", item.getOrder_id());
            DStoreStateActivity.this.startActivity(intent);

        } else if (text.equals("取消w订单")) {
        } else if (text.equals("取消w订单")) {
        }
    }

}
