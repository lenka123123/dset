package com.wokun.dset.store.dstore.immediatelypay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.classic.common.MultipleStatusView;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.r0adkll.slidr.Slidr;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.shantoo.common.utils.AppManager;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.address.ui.AddressListActivity;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.pinkongshop.ZhihuiSuccessfulActivity;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.adapter.DStoreGoodsListAdapter;
import com.wokun.dset.store.bean.DStoreGoodesList;
import com.wokun.dset.store.bean.DStoreImmediatelyPay;
import com.wokun.dset.store.bean.DefaultAddress;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;
import com.wokun.dset.store.dstore.dstorelist.DStoreSearchListActivity;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreImmediatelyPayActivity extends BaseBindingActivity {

    private String goods_id;
    private String sku_id;
    private String cart_id_str;

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


    private DefaultAddress.DataBean.DefaultBean showSefaultAddress;
    private String link_man;
    private String phone;
    private String address;
    private String promote_price;

    @Override
    public int createView() {
        return R.layout.activity_immediately_pay;
    }


    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("确认订单");
    }


    @Override
    public void init() {

        Intent intent = getIntent();
        goods_id = intent.getStringExtra("goods_id");
        sku_id = intent.getStringExtra("sku_id");
        cart_id_str = intent.getStringExtra("cart_id_str");
        promote_price = intent.getStringExtra("promote_price");
        defaultAddress();

    }

    // R.id.action_wxpay, R.id.action_pay, R.id.select_address, R.id.show_address, R.id.zitidian, R.id.iv_go1})
    @OnClick(R.id.goto_select)
    public void action(View v) {
        switch (v.getId()) {
//            case R.id.action_alipay: //支付宝支付
//                alipaySelectorImageView.toggle(true);
//                weixingSelectorImageView.toggle(false);
//                pay_type = ALIPAY;
//                break;
//            case R.id.action_wxpay: //微信支付
//                alipaySelectorImageView.toggle(false);
//                weixingSelectorImageView.toggle(true);
//                pay_type = WXPAY;
//                break;
//            case R.id.action_pay: //确认支付
//                if (ALIPAY.equals(pay_type)) {
//                    pay(pay_type);
//                } else if (WXPAY.equals(pay_type)) {
//                    pay(pay_type);
//                }
//                break;
            case R.id.goto_select:
                Intent intent = new Intent();
                intent.setClass(this, AddressListActivity.class);
                startActivityForResult(intent, 99);
                Log.e("点击了1", "点击了1");
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 20) {
            show_address.setVisibility(View.GONE);
            select_address.setVisibility(View.VISIBLE);

            //Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
            String name = data.getStringExtra(Constants.CONTACTS);
            String mobile = data.getStringExtra(Constants.TEL);
            String sAddress = data.getStringExtra(Constants.ADDRESS);
            String province_id = data.getStringExtra(Constants.PROVINCE_ID);
            //   actionCartGetFreightPrice(cart_id_str, province_id);
            showAddress(name, mobile, sAddress);
        }
    }


    //支付
    private void pay(String pay_type) {
//        Log.e("支付商品列表", "pay_type:" + pay_type + "/gid:" + totalgid + " /self_lifting:" +
//                self_lifting + "/addressId:" + address_id + "/store_name:" +
//                selfLifting.getStore_name() + "/mobile:" + selfLifting.getMobile() + "/addressDetail:" + sAddress
//                + "/store_code" + store_code + "/totalPrice:" + shoppingTotalPrice + "/goodsData:" + goodsjson);
//
//        if (!TyslApp.getInstance().isLogin()) {
//            Toast.makeText(this, "亲，您还未登录", Toast.LENGTH_SHORT).show();
//            return;
//        }

//        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(sAddress)) {
//            Toast.makeText(this, "请选择收货地址", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (ALIPAY.equals(pay_type)) {
//            doAlipay();
//        } else if (WXPAY.equals(pay_type)) {
//            doWxpay();
//        }
    }

    //支付宝支付
    private void doAlipay() {

//        Log.e("支付商品列表", "pay_type:" + pay_type + "/gid:" + totalgid + "/self_lifting:" +
//                self_lifting + "/addressId:" + address_id + "/store_name:" +
//                selfLifting.getStore_name() + "/mobile:" + selfLifting.getMobile() + "/addressDetail:" + sAddress
//                + "/store_code" + store_code + "/totalPrice:" + shoppingTotalPrice + "/goodsData:" + goodsjson);
//
//        OkGo.<BaseResponse<Alipay2>>post(Constants.BASE_URL + Constants.RETAILPAYORDER)
//                .params("pay_type", pay_type)
//                .params("gid", totalgid)
//                .params("self_lifting", self_lifting)
//                .params("addressId", address_id)
//                .params("store_name", selfLifting.getStore_name())
//                .params("mobile", selfLifting.getMobile())
//                .params("addressDetail", sAddress)
//                .params("store_code", store_code)
//                .params("totalPrice", shoppingTotalPrice)
//                .params("goodsData", goodsjson)
//                .execute(new JsonCallback<BaseResponse<Alipay2>>(Constants.WITH_TOKEN, Constants.RETAILPAYORDER) {
//                    @Override
//                    public void onSuccess(Response<BaseResponse<Alipay2>> response) {
//                        BaseResponse body = response.body();
//                        if (body == null) return;
//                        if (body.isState()) {
//                            Alipay2 data = (Alipay2) body.getData();
//                            if (data == null || data.getOrderString() == null) {
//                                return;
//                            }
//                            finish();
//                            alipay(data.getOrderString());
//                            buy_gid = data.getBuy_gid();
//                            order_id = data.getOrder_id();
//                            Log.e("进来了4", "进来了4" + buy_gid);
//
//                        } else {
//                            RxToast.showShort(body.getMsg());
//                        }
//                    }
//                });
    }

    //微信支付
    private void doWxpay() {
//        OkGo.<BaseResponse<WXPayResult>>post(Constants.BASE_URL + Constants.RETAILPAYORDER)
//                .params("pay_type", pay_type)
//                .params("gid", totalgid)
//                .params("self_lifting", self_lifting)
//                .params("addressId", address_id)
//                .params("store_name", selfLifting.getStore_name())
//                .params("mobile", selfLifting.getMobile())
//                .params("addressDetail", sAddress)
//                .params("store_code", store_code)
//                .params("totalPrice", shoppingTotalPrice)
//                .params("goodsData", goodsjson)
//                .execute(new JsonCallback<BaseResponse<WXPayResult>>(Constants.WITH_TOKEN, Constants.RETAILPAYORDER) {
//                    @Override
//                    public void onSuccess(Response<BaseResponse<WXPayResult>> response) {
//                        BaseResponse body = response.body();
//                        if (body == null) return;
//                        if (body.isState()) {
//                            finish();
//                            WXPayResult data = (WXPayResult) body.getData();
//                            //在服务端签名
//
//                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
//                            //  new WXPay.Builder()
//                            builder.setAppId(data.getAppid())
//                                    .setPartnerId(data.getPartnerid())
//                                    .setPrepayId(data.getPrepayid())
//                                    .setPackageValue(data.getPackageX())
//                                    .setNonceStr(data.getNoncestr())
//                                    .setTimeStamp(data.getTimestamp() + "")
//                                    .setSign(data.getSign())
//                                    .build()
//                                    .toWXPayNotSign(SmartRetailOrderListActivity.this);
//                            //.pay(TyslApp.getContext(), data.getAppid());
//
//
//                        } else {
//                            Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

    protected void alipay(final String body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(DStoreImmediatelyPayActivity.this);
                Map<String, String> result = alipay.payV2(body, true);
                Log.e("进来了5", "进来了5");
                // Log.i("msp", result.toString());
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
            }
        }).start();
    }

    protected Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SDK_PAY_FLAG: {
            Log.e("进来了6", "进来了6" + msg.obj);
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                    String resultStatus = payResult.getResultStatus();
//                    // 判断resultStatus 为9000则代表支付成功
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        Log.e("进来了7", "进来了7");
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(TyslApp.getContext(), "支付成功!", Toast.LENGTH_SHORT).show();
//                        setResult(1);
//                        finish();
//                        Intent intent = new Intent(SmartRetailOrderListActivity.this, ZhihuiSuccessfulActivity.class);
//                        intent.putExtra("order_id", order_id);
//                        intent.putExtra("buy_gid", buy_gid);
//                        startActivity(intent);
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        Toast.makeText(TyslApp.getContext(), "支付失败!", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                    break;
//                }
        }
    };

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
    private void pay() {
        Log.i(TAG, "promote_price: " + promote_price);

        String token = (String) SpCommonUtils.get(DStoreImmediatelyPayActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreImmediatelyPayActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        params.put("cart_id_str", cart_id_str);
        params.put("link_man", link_man);
        params.put("phone", phone);
        params.put("address", address);
        params.put("pay_type", "2");
        params.put("order_amount", promote_price);
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
                .params("pay_type", "2")
                .params("order_amount", promote_price)


                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
//                        DefaultAddress defaultAddress = (DefaultAddress) JosnFrom.getInstance().getObj(response.body().toString(), DefaultAddress.class);
//                        if (defaultAddress != null && defaultAddress.getStatus().equals("0001")) {
//
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

    private void showAddress(String name, String num, String add) {
        selectAddressName.setText(name);
        selectAddressPhone.setText(num);
        selectAddressDetail.setText(add);

        link_man = name;
        phone = num;
        address = add;

        pay();

    }


}
