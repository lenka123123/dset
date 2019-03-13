package com.wokun.dset.utils;


public class PayUtil {
/*
    *//**
     * 微信支付
     * *//*
    public static void wxpay(BaseBindingActivity activity, String orderNumber, String payType) {
        if (!TyslApp.getInstance().isLogin()) {
            activity.startActivity(LoginActivity.class);
            return;
        }
        OkGo.<BaseResponse<WXPayResult>>post(Constants.BASE_URL + Constants.PAYMENT_BUY_SERVICE_URL)
                .tag(activity)
                .params(Constants.ORDER_NUMBER,orderNumber)
                .params(Constants.PAY_TYPE, payType)
                .execute(new JsonCallback<BaseResponse<WXPayResult>>(Constants.WITH_TOKEN,Constants.PAYMENT_BUY_SERVICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WXPayResult>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if (body.isState()) {
                            WXPayResult data = (WXPayResult) body.getData();
                            if(data==null){return;}
                            //在服务端签名
                            new WXPay.Builder()
                                    .setAppId(data.getAppid())
                                    .setPartnerId(data.getPartnerid())
                                    .setPrepayId(data.getPrepayid())
                                    .setPackageValue(data.getPackageX())
                                    .setNonceStr(data.getNoncestr())
                                    .setTimeStamp(data.getTimestamp() + "")
                                    .setSign(data.getSign())
                                    .build()
                                    .pay(TyslApp.getContext(), data.getAppid());
                        }
                    }
                });
    }

    *//**
     * 支付宝支付
     * *//*
    public static void alipay(final BaseBindingActivity activity, String orderNumber, String payType) {
        if (!TyslApp.getInstance().isLogin()) {
            activity.startActivity(LoginActivity.class);
            return;
        }
        OkGo.<BaseResponse<Alipay>>post(Constants.BASE_URL + Constants.PAYMENT_BUY_SERVICE_URL)
                .tag(activity)
                .params(Constants.ORDER_NUMBER,orderNumber)
                .params(Constants.PAY_TYPE, payType)
                .execute(new JsonCallback<BaseResponse<Alipay>>(Constants.WITH_TOKEN,Constants.PAYMENT_BUY_SERVICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Alipay>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if (body.isState()) {
                            Alipay data = (Alipay) body.getData();
                            if(data == null ){return;}
                            AliPay.getInstance()
                                .pay(activity, data.getOrderString(),
                                new PayResultCallback() {
                                    @Override
                                    public void onPaySuccess(String result, String message) {
                                        Toast.makeText(activity,"支付成功!",Toast.LENGTH_SHORT).show();
                                        activity.finish();
                                    }

                                    @Override
                                    public void onPayFail(String result, String message) {
                                        RxToast.showShort(message);
                                    }
                            });
                        }
                    }
                });
    }*/
}