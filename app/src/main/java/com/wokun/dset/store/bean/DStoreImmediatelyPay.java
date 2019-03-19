package com.wokun.dset.store.bean;


public class DStoreImmediatelyPay {


    /**
     * data : {"cart_id_str":"78"}
     * msg : 成功
     * status : 0001
     */

    private DataBean data;
    private String msg;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * cart_id_str : 78
         */

        private String cart_id_str;

        public String getCart_id_str() {
            return cart_id_str;
        }

        public void setCart_id_str(String cart_id_str) {
            this.cart_id_str = cart_id_str;
        }
    }
}
