package com.wokun.dset.ucenter.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/11.
 */

public class RollOutBean implements Serializable {


    /**
     * cash_wa : 16800.0000
     * sendrecord : {"pay_type":2,"url":"http://web.dasether.com/register/sendoutrecord.html"}
     */

    private String cash_wa;
    private SendrecordBean sendrecord;

    public String getCash_wa() {
        return cash_wa;
    }

    public void setCash_wa(String cash_wa) {
        this.cash_wa = cash_wa;
    }

    public SendrecordBean getSendrecord() {
        return sendrecord;
    }

    public void setSendrecord(SendrecordBean sendrecord) {
        this.sendrecord = sendrecord;
    }

    public static class SendrecordBean {
        /**
         * pay_type : 2
         * url : http://web.dasether.com/register/sendoutrecord.html
         */

        private int pay_type;
        private String url;

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
