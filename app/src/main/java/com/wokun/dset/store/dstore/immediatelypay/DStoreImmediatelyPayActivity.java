package com.wokun.dset.store.dstore.immediatelypay;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.address.ui.AddressListActivity;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.pinkongshop.ZhihuiSuccessfulActivity;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.adapter.ShopCartForPayAdapter;
import com.wokun.dset.store.bean.BaseResPonse;
import com.wokun.dset.store.bean.CartList.DataBean.CartListInfoBean.GoodsItemBean;
import com.wokun.dset.store.bean.DefaultAddress;
import com.wokun.dset.store.bean.AilPayBean;
import com.wokun.dset.store.bean.MoneyBean;
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.ScreenUtils;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreImmediatelyPayActivity extends BaseBindingActivity {

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

    @BindView(R.id.goto_select)
    TextView goto_select;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.store_name)
    TextView store_name;

    @BindView(R.id.zhihui_goods_price)
    TextView zhihui_goods_price;


    @BindView(R.id.action_money_layout)
    RelativeLayout action_money_layout;

    @BindView(R.id.money_selector_select)
    SelectorImageView money_selector_select;

    @BindView(R.id.action_alipey_layout)
    RelativeLayout action_alipey_layout;

    @BindView(R.id.alipay_selector_select)
    SelectorImageView alipay_selector_select;

    @BindView(R.id.true_price)
    TextView true_price;

    private DefaultAddress.DataBean.DefaultBean showSefaultAddress;
    private String link_man = "";
    private String phone = "";
    private String address = "";
    private String promote_price;
    private static final int SDK_PAY_FLAG = 1;
    private List<GoodsItemBean> mAllOrderList;
    private ShopCartForPayAdapter adapter;
    private String cart_id_str;
    private String inputContent;


    @Override
    public int createView() {
        return R.layout.activity_immediately_pay;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar.setWidgetBarTitle("确认付款");
    }

    @Override
    public void init() {
        mWidgetBar.setBackgroundColor(getResources().getColor(R.color.white));
        Intent intent = getIntent();

        mAllOrderList = (List<GoodsItemBean>) intent.getSerializableExtra("list");
        promote_price = intent.getStringExtra("doubleprice");
        cart_id_str = intent.getStringExtra("cart_id_str");

        Log.i(TAG, "init: 传过来的数据promote_price" + promote_price);
        Log.i(TAG, "init: 传过来的数据cart_id_str" + cart_id_str);
        store_name.setText(mAllOrderList.get(0).getStore_name());
        zhihui_goods_price.setText("￥" + promote_price);
        true_price.setText("￥" + promote_price);
        recyclerView.setLayoutManager(new LinearLayoutManager(DStoreImmediatelyPayActivity.this));
        recyclerView.addItemDecoration(new MItemDecoration(DStoreImmediatelyPayActivity.this, DividerItemDecoration.VERTICAL));
        adapter = new ShopCartForPayAdapter(R.layout.item_shop_cart_pay, mAllOrderList);
        recyclerView.setAdapter(adapter);
        defaultAddress();
    }

    // 支付类型 1微信 2支付宝 5余额
    private String pay_type = "";

    // R.id.action_wxpay, R.id.action_pay, R.id.select_address, R.id.show_address, R.id.zitidian, R.id.iv_go1})
    @OnClick({R.id.goto_select, R.id.action_money_layout, R.id.action_alipey_layout, R.id.action_pay, R.id.back,R.id.show_address})
    public void action(View v) {
        switch (v.getId()) {
            case R.id.action_money_layout: //金票支付
                money_selector_select.toggle(true);
                alipay_selector_select.toggle(false);
                pay_type = "5";
                break;
            case R.id.action_alipey_layout: //2支付宝
                money_selector_select.toggle(false);
                alipay_selector_select.toggle(true);
                pay_type = "2";
                break;
            case R.id.action_pay: //确认支付
                if (pay_type.equals("")) {
                    RxToast.showShort("请选择支付类型");
                    return;
                } else if (pay_type.equals("2")) { //支付宝
                    payForOrder_id(pay_type);

                } else if (pay_type.equals("5")) {
                    payForOrder_id(pay_type);
                }

                break;
            case R.id.goto_select:
            case R.id.show_address:
                Intent intent = new Intent();
                intent.setClass(this, AddressListActivity.class);
                startActivityForResult(intent, 99);
                Log.e("点击了1", "点击了1");
                break;
            case R.id.back:
                DStoreImmediatelyPayActivity.this.finish();
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == RESULT_OK) {
            show_address.setVisibility(View.VISIBLE);
            select_address.setVisibility(View.GONE);
            //Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
            String name = data.getStringExtra(Constants.CONTACTS);
            String mobile = data.getStringExtra(Constants.TEL);
            String sAddress = data.getStringExtra(Constants.ADDRESS);
            String province_id = data.getStringExtra(Constants.PROVINCE_ID);
            //   actionCartGetFreightPrice(cart_id_str, province_id);
            showAddress(name, mobile, sAddress);
        }
    }

    //获取默认地址
    private void defaultAddress() {
        String token = (String) SpCommonUtils.get(DStoreImmediatelyPayActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreImmediatelyPayActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.DEFAULT_ADRESS)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        DefaultAddress defaultAddress = (DefaultAddress) JosnFrom.getInstance().getObj(response.body().toString(), DefaultAddress.class);
                        if (defaultAddress != null && defaultAddress.getStatus().equals("0001")) {
                            if (defaultAddress.getData().getDefaultX() == null || defaultAddress.getData().getDefaultX().getPhone() == null) {
                                show_address.setVisibility(View.GONE);
                                select_address.setVisibility(View.VISIBLE);
                            } else {
                                show_address.setVisibility(View.VISIBLE);
                                select_address.setVisibility(View.GONE);

                                showSefaultAddress = defaultAddress.getData().getDefaultX();


                                showAddress(showSefaultAddress.getName(), showSefaultAddress.getPhone(),
                                        showSefaultAddress.getProvice() + showSefaultAddress.getCity() + showSefaultAddress.getArea() + showSefaultAddress.getAddress());
                            }

                        } else {

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

    //调用支付
    private void payForOrder_id(final String payType) {
        Log.i(TAG, "promote_price:1 " + cart_id_str);
        Log.i(TAG, "promote_price:2 " + link_man);
        Log.i(TAG, "promote_price:3 " + phone);
        Log.i(TAG, "promote_price:4 " + address);
        Log.i(TAG, "promote_price:5 " + String.valueOf(promote_price));

        String token = (String) SpCommonUtils.get(DStoreImmediatelyPayActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreImmediatelyPayActivity.this, Constants.USERID, "");
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
        params.put("order_amount", String.valueOf(promote_price));
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
                .params("order_amount", String.valueOf(promote_price))
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
                PayTask alipay = new PayTask(DStoreImmediatelyPayActivity.this);
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
                Intent intent = new Intent(DStoreImmediatelyPayActivity.this, ZhihuiSuccessfulActivity.class);
                intent.putExtra("doubleprice", promote_price);
                intent.putExtra("name", link_man);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                DStoreImmediatelyPayActivity.this.startActivity(intent);

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
        DStoreImmediatelyPayActivity.this.finish();
    }

    private Dialog dialog;

    public void getCenterCancelDialog(final MoneyBean.DataBean data) {
        dialog = new Dialog(DStoreImmediatelyPayActivity.this, R.style.diydialog);
        dialog.setContentView(R.layout.activity_pay_namepassword);
        Window window = dialog.getWindow();
        //设置窗口的位置
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        DisplayMetrics displayMetrics = DStoreImmediatelyPayActivity.this.getResources().getDisplayMetrics();//获取屏幕的宽和高用
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
        String token = (String) SpCommonUtils.get(DStoreImmediatelyPayActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreImmediatelyPayActivity.this, Constants.USERID, "");
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
                            Intent intent = new Intent(DStoreImmediatelyPayActivity.this, ZhihuiSuccessfulActivity.class);
                            intent.putExtra("doubleprice", promote_price);
                            intent.putExtra("name", link_man);
                            intent.putExtra("phone", phone);
                            intent.putExtra("address", address);
                            DStoreImmediatelyPayActivity.this.startActivity(intent);
                            DStoreImmediatelyPayActivity.this.finish();
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


}
