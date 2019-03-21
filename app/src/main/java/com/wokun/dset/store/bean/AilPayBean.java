package com.wokun.dset.store.bean;


public class AilPayBean {

    /**
     * status : 0001
     * msg : 成功
     * data : {"orderString":"alipay_sdk=alipay-sdk-php-20161101&app_id=2019010362711898&biz_content=%7B%22body%22%3A%22%5Cu54c1%5Cu63a7%5Cu5546%5Cu54c1%22%2C%22subject%22%3A%22%5Cu8d2d%5Cu4e70%5Cu5546%5Cu54c1%22%2C%22out_trade_no%22%3A%22201903192126015862%22%2C%22timeout_express%22%3A%2210h%22%2C%22total_amount%22%3A118%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fapi.dasether.com%2Fpayment%2Falipay-callback.html&sign_type=RSA2&timestamp=2019-03-19+21%3A26%3A01&version=1.0&sign=BTXkmrq2PrjiChFGSHmKPHePIlL57Asr94iXYdtj1E2jj0IqpVTnaKV67w7J9TIW3mmCOLsMYQyvoO9o1Vsp4eZwHG%2FX9qI5Ikr51fe9jc6%2BHoW1qDvz9VnMJH%2FCr80cueJjfVPk839geXX5uo%2FnQx7IVJjwrlVraprz3DIOrj4EqXeruGjiut025hDkVhDbk%2FRbOaLT2j9G4VH9avDrxTzD4rfqJaSOn%2BXYNNnpZi5hN2dTTeEaPxdGoLTFwHfbcmagptMWo0XccAW8SLncHwjn%2FoJcfPRxias5JTWSv34o9RFQAX92G2rLncKzFtDiyjEOq6MLLF1qCnSS0U3VTQ%3D%3D"}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderString : alipay_sdk=alipay-sdk-php-20161101&app_id=2019010362711898&biz_content=%7B%22body%22%3A%22%5Cu54c1%5Cu63a7%5Cu5546%5Cu54c1%22%2C%22subject%22%3A%22%5Cu8d2d%5Cu4e70%5Cu5546%5Cu54c1%22%2C%22out_trade_no%22%3A%22201903192126015862%22%2C%22timeout_express%22%3A%2210h%22%2C%22total_amount%22%3A118%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fapi.dasether.com%2Fpayment%2Falipay-callback.html&sign_type=RSA2&timestamp=2019-03-19+21%3A26%3A01&version=1.0&sign=BTXkmrq2PrjiChFGSHmKPHePIlL57Asr94iXYdtj1E2jj0IqpVTnaKV67w7J9TIW3mmCOLsMYQyvoO9o1Vsp4eZwHG%2FX9qI5Ikr51fe9jc6%2BHoW1qDvz9VnMJH%2FCr80cueJjfVPk839geXX5uo%2FnQx7IVJjwrlVraprz3DIOrj4EqXeruGjiut025hDkVhDbk%2FRbOaLT2j9G4VH9avDrxTzD4rfqJaSOn%2BXYNNnpZi5hN2dTTeEaPxdGoLTFwHfbcmagptMWo0XccAW8SLncHwjn%2FoJcfPRxias5JTWSv34o9RFQAX92G2rLncKzFtDiyjEOq6MLLF1qCnSS0U3VTQ%3D%3D
         */

        private String orderString;

        public String getOrderString() {
            return orderString;
        }

        public void setOrderString(String orderString) {
            this.orderString = orderString;
        }
    }
}
