package com.wokun.dset.ucenter.bean;

/**
 * Created by Administrator on 2018/9/13.
 */

public class AssetsAddBean {

    /**
     * hcg_wa : 3260.5000
     * cash_wa : 97.5000
     * ratio : 6
     * record : {"name":"记录","url":"http://lkc.cn/profile/converrecord.html"}
     */

    private String hcg_wa;
    private String ft_limit;

    private String cash_wa;
    private String ratio;
    private Record record;
    private String unbind;

    public String getFt_limit() {
        return ft_limit;
    }

    public void setFt_limit(String ft_limit) {
        this.ft_limit = ft_limit;
    }

    public String getUnbind() {
        return unbind;
    }

    public void setUnbind(String unbind) {
        this.unbind = unbind;
    }

    public String getHcg_wa() {
        return hcg_wa;
    }

    public void setHcg_wa(String hcg_wa) {
        this.hcg_wa = hcg_wa;
    }

    public String getCash_wa() {
        return cash_wa;
    }

    public void setCash_wa(String cash_wa) {
        this.cash_wa = cash_wa;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public static class Record {
        /**
         * name : 记录
         * url : http://lkc.cn/profile/converrecord.html
         */

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
