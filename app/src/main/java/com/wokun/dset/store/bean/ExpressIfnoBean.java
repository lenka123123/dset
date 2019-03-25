package com.wokun.dset.store.bean;


public class ExpressIfnoBean {

    /**
     * status : 0001
     * msg : 成功
     * data : {"kd_url":"https://m.kuaidi100.com/index_all.html?type=%E5%9C%86%E9%80%9A%E5%BF%AB%E9%80%92&postid=946513213213"}
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
         * kd_url : https://m.kuaidi100.com/index_all.html?type=%E5%9C%86%E9%80%9A%E5%BF%AB%E9%80%92&postid=946513213213
         */

        private String kd_url;

        public String getKd_url() {
            return kd_url;
        }

        public void setKd_url(String kd_url) {
            this.kd_url = kd_url;
        }
    }
}
