package com.wokun.dset.ucenter.bean;

/**
 * Created by Administrator on 2018/9/12.
 */

public class RollInBean {
    /**
     * sendinrecord : {"name":"转入记录","pay_type":1,"url":"http://lkc.cn/register/sendoutrecord.html"}
     * sendin_qrcode : /qrcode/sendin/45c48cce2e2d7fbdea1afc51c7c6ad26.png
     */

    private Sendinrecord sendinrecord;
    private String sendin_qrcode;
    private  String  qrcode_str;

    public String getQrcode_str() {
        return qrcode_str;
    }

    public void setQrcode_str(String qrcode_str) {
        this.qrcode_str = qrcode_str;
    }

    public Sendinrecord getSendinrecord() {
        return sendinrecord;
    }

    public void setSendinrecord(Sendinrecord sendinrecord) {
        this.sendinrecord = sendinrecord;
    }

    public String getSendin_qrcode() {
        return sendin_qrcode;
    }

    public void setSendin_qrcode(String sendin_qrcode) {
        this.sendin_qrcode = sendin_qrcode;
    }

    public static class Sendinrecord {
        /**
         * name : 转入记录
         * pay_type : 1
         * url : http://lkc.cn/register/sendoutrecord.html
         */

        private String name;
        private int pay_type;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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
