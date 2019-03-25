package com.wokun.dset.store.dstore.immediatelypay;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.itheima.loopviewpager.LoopViewPager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.address.ui.AddressListActivity;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.pinkongshop.ZhihuiSuccessfulActivity;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.adapter.SelectGoogsParamAdapter;
import com.wokun.dset.store.adapter.ShopCartDetailAdapter;
import com.wokun.dset.store.adapter.ShopCartForPayAdapter;
import com.wokun.dset.store.bean.AilPayBean;
import com.wokun.dset.store.bean.BaseResPonse;
import com.wokun.dset.store.bean.CartDetailBean;
import com.wokun.dset.store.bean.CartList;
import com.wokun.dset.store.bean.DStoreGoodsDetail;
import com.wokun.dset.store.bean.DStoreImmediatelyPay;
import com.wokun.dset.store.bean.DefaultAddress;
import com.wokun.dset.store.bean.GoodsSKUList;
import com.wokun.dset.store.bean.MoneyBean;
import com.wokun.dset.store.dstore.dstorestate.DStoreStateActivity;
import com.wokun.dset.store.dstore.expressinfo.DStoreExpressInfoActivity;
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;
import com.wokun.dset.utils.ImageLoadUtils;
import com.wokun.dset.utils.ImageLoaderUtils;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.TextViewUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreOrderDetailActivity extends BaseBindingActivity {

    @BindView(R.id.show_address)
    RelativeLayout show_address;

    @BindView(R.id.select_address)
    RelativeLayout select_address;

    @BindView(R.id.tv_contacts)
    TextView selectAddressName;

    @BindView(R.id.tv_order_placer_tel)
    TextView selectAddressPhone;

    @BindView(R.id.tv_order_placer_address)
    TextView selectAddressDetail;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.store_name)
    TextView store_name;
    @BindView(R.id.iv_go1)
    ImageView iv_go1;

    @BindView(R.id.order_id_tv)
    TextView order_id_tv;
    @BindView(R.id.order_id_cope_tv)
    TextView order_id_cope_tv;
    @BindView(R.id.order_tiime)
    TextView order_tiime;
    @BindView(R.id.order_pay_type)
    TextView order_pay_type;
    @BindView(R.id.order_send)
    TextView order_send;
    @BindView(R.id.zhihui_goods_price)
    TextView zhihui_goods_price;
    @BindView(R.id.send_money)
    TextView send_money;
    @BindView(R.id.look_sned)
    TextView look_sned;


    private String link_man = "";
    private String phone = "";
    private String address = "";
    private ShopCartDetailAdapter adapter;
    private String cart_id_str = "";
    private String inputContent = "";
    private String orderid;
    private String order_id;


    @Override
    public int createView() {
        return R.layout.activity_order_detail;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar.setWidgetBarTitle("确认付款");
    }

    @Override
    public void init() {
        iv_go1.setVisibility(View.GONE);
        mWidgetBar.setBackgroundColor(getResources().getColor(R.color.white));
        Intent intent = getIntent();
        orderid = intent.getStringExtra(Constants.ORDER_ID);
        getCartDetail(orderid);

        recyclerView.setLayoutManager(new LinearLayoutManager(DStoreOrderDetailActivity.this));
        recyclerView.addItemDecoration(new MItemDecoration(DStoreOrderDetailActivity.this, DividerItemDecoration.VERTICAL));
        adapter = new ShopCartDetailAdapter(R.layout.item_shop_cart_pay, null);
        recyclerView.setAdapter(adapter);
    }

    // 支付类型 1微信 2支付宝 5余额
    private String pay_type = "";

    // R.id.action_wxpay, R.id.action_pay, R.id.select_address, R.id.show_address, R.id.zitidian, R.id.iv_go1})
    //   @OnClick({R.id.goto_select, R.id.action_money_layout, R.id.action_alipey_layout, R.id.action_pay, R.id.back, R.id.show_address})
    @OnClick({R.id.order_id_cope_tv, R.id.look_sned, R.id.back})
    public void action(View v) {
        switch (v.getId()) {
            case R.id.look_sned:
                Intent intent = new Intent(DStoreOrderDetailActivity.this, DStoreExpressInfoActivity.class);
                intent.putExtra("orderid", order_id);
                DStoreOrderDetailActivity.this.startActivity(intent);
                break;
            case R.id.order_id_cope_tv:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", order_id_tv.getText().toString().trim());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                if (order_id_tv.getText().toString().trim().length() > 0) {
                    RxToast.showShort("复制成功");
                }
                break;
//            case R.id.action_money_layout: //金票支付
//                money_selector_select.toggle(true);
//                alipay_selector_select.toggle(false);
//                pay_type = "5";
//                break;
//            case R.id.action_alipey_layout: //2支付宝
//                money_selector_select.toggle(false);
//                alipay_selector_select.toggle(true);
//                pay_type = "2";
//                break;
//            case R.id.action_pay: //确认支付
//                if (pay_type.equals("")) {
//                    RxToast.showShort("请选择支付类型");
//                    return;
//                } else if (pay_type.equals("2")) { //支付宝
//                    payForOrder_id(pay_type);
//
//                } else if (pay_type.equals("5")) {
//                    payForOrder_id(pay_type);
//                }
//
//                break;
            case R.id.back:
                DStoreOrderDetailActivity.this.finish();
                break;
        }


    }


    //调用支付
    private void payForOrder_id(final String payType) {
        Log.i(TAG, "promote_price:1 " + cart_id_str);
        Log.i(TAG, "promote_price:2 " + link_man);
        Log.i(TAG, "promote_price:3 " + phone);
        Log.i(TAG, "promote_price:4 " + address);

        String token = (String) SpCommonUtils.get(DStoreOrderDetailActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreOrderDetailActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        final Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("cart_id_str", cart_id_str);
        params.put("link_man", link_man);
        params.put("phone", phone);
        params.put("address", address);
        params.put("pay_type", payType);
//        params.put("order_amount", String.valueOf(promote_price));
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.PAY_FOR_IMMEDIATELY)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("cart_id_str", cart_id_str)
                .params("link_man", link_man)
                .params("phone", phone)
                .params("address", address)
                .params("pay_type", payType)
//                .params("order_amount", String.valueOf(promote_price))
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
//  余额支付返回的数据如下（返回的参数是余额支付输入密码点击确认调用的接口的参数）
// "data": { "out_trade_no": "201903151424502268", "order_id": 53 }
//  支付宝支付返回的data里面数据如下 "data":{"orderString":"**"}
                        if (payType.equals("2")) {
                            AilPayBean payBean = (AilPayBean) JosnFrom.getInstance().getObj(response.body().toString(), AilPayBean.class);
                            if (payBean != null && payBean.getStatus().equals("0001")) {
                                realPay(payBean.getData().getOrderString());
                            } else {
                                RxToast.showShort(payBean.getMsg());
                            }
                        } else if (payType.equals("5")) { //金额支付
                            MoneyBean moneyBean = (MoneyBean) JosnFrom.getInstance().getObj(response.body().toString(), MoneyBean.class);
                            if (moneyBean != null && moneyBean.getStatus().equals("0001")) {
                                getCenterCancelDialog(moneyBean.getData());
                            } else {
                                RxToast.showShort(moneyBean.getMsg());
                            }
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

    private void showAddress(String name, String num, String add) {
        selectAddressName.setText(name);
        selectAddressPhone.setText(num);
        selectAddressDetail.setText(add);

        link_man = name;
        phone = num;
        address = add;

        // TODO: 2019/3/21 0021    payForOrder_id();

    }


    protected void realPay(final String body) {

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(DStoreOrderDetailActivity.this);
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
                Intent intent = new Intent(DStoreOrderDetailActivity.this, ZhihuiSuccessfulActivity.class);
//                intent.putExtra("doubleprice", promote_price);
                intent.putExtra("name", link_man);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                DStoreOrderDetailActivity.this.startActivity(intent);

            }

            if (result.get("resultStatus").equals("4000")) {
                RxToast.showShort("支付失败，请重试！");
                // centerDialog.showDialog("支付失败，请重试！", R.drawable.payes_fail);
            }

            if (result.get("resultStatus").equals("6001")) {
                RxToast.showShort("取消支付");
                //  centerDialog.showDialog("取消支付", R.drawable.payes_fail);
            }
        }
        DStoreOrderDetailActivity.this.finish();
    }

    private Dialog dialog;

    public void getCenterCancelDialog(final MoneyBean.DataBean data) {
        dialog = new Dialog(DStoreOrderDetailActivity.this, R.style.diydialog);
        dialog.setContentView(R.layout.activity_pay_namepassword);
        Window window = dialog.getWindow();
        //设置窗口的位置
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        DisplayMetrics displayMetrics = DStoreOrderDetailActivity.this.getResources().getDisplayMetrics();//获取屏幕的宽和高用
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
    private void moneyPay(MoneyBean.DataBean data, String password) {
        String token = (String) SpCommonUtils.get(DStoreOrderDetailActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreOrderDetailActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("order_id", data.getOrder_id());
        params.put("out_trade_no", data.getOut_trade_no());
        params.put("password", password);

        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.MONEY_PAY)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("order_id", data.getOrder_id())
                .params("out_trade_no", data.getOut_trade_no())
                .params("password", password)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        BaseResPonse baseResponse = (BaseResPonse) JosnFrom.getInstance().getObj(response.body().toString(), BaseResPonse.class);
                        RxToast.showShort(baseResponse.getMsg());
                        if (dialog != null)
                            dialog.dismiss();
                        if (baseResponse != null && baseResponse.getStatus().equals("0001")) {
                            Intent intent = new Intent(DStoreOrderDetailActivity.this, ZhihuiSuccessfulActivity.class);
//                            intent.putExtra("doubleprice", promote_price);
                            intent.putExtra("name", link_man);
                            intent.putExtra("phone", phone);
                            intent.putExtra("address", address);
                            DStoreOrderDetailActivity.this.startActivity(intent);
                            DStoreOrderDetailActivity.this.finish();
                        } else {
                            RxToast.showShort(baseResponse.getMsg());
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


    //获取购物车订单详情页面
    private void getCartDetail(String order_id) {

        String token = (String) SpCommonUtils.get(DStoreOrderDetailActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreOrderDetailActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        final Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("order_id", order_id);
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.CART_DETAIL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("order_id", order_id)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        CartDetailBean cartDetailBean = (CartDetailBean) JosnFrom.getInstance().getObj(response.body().toString(), CartDetailBean.class);
                        if (cartDetailBean != null && cartDetailBean.getStatus().equals("0001")) {
                            CartDetailBean.DataBean dataBean = cartDetailBean.getData();
                            if (dataBean == null) return;
                            showAddress(dataBean.getReceiver_name(), dataBean.getReceiver_mobile(),
                                    dataBean.getReceiver_province() + dataBean.getReceiver_district() + dataBean.getReceiver_address());
                            dellData(dataBean);

                        } else {
                            RxToast.showShort(cartDetailBean.getMsg());
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

    private void dellData(CartDetailBean.DataBean dataBean) {
        if (dataBean == null) return;
        order_id = dataBean.getOrder_id();
        store_name.setText(dataBean.getShop_name());
        order_id_tv.setText(dataBean.getShippingInfo().getExpress_no());
        order_tiime.setText(StringUtil.getDateToString(Long.valueOf(dataBean.getCreate_time())));
        order_pay_type.setText("");
        order_send.setText(dataBean.getShippingInfo().getExpress_company());
        zhihui_goods_price.setText(dataBean.getOrder_money());
        send_money.setText("￥" + dataBean.getShipping_money());
        adapter.getData().addAll(dataBean.getOrderGoods());
        adapter.notifyDataSetChanged();
    }

}
