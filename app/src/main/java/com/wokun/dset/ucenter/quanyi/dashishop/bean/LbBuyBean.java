package com.wokun.dset.ucenter.quanyi.dashishop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/21.
 */

public class LbBuyBean {

    /**
     * title : 订单发布->卢宝购买
     * user_wallet : {"hcg_wa":"4031.0000","cash_wa":"266.0000","care_wa":"0.0000"}
     * trade_num : [{"id":"1","num":"100"},{"id":"2","num":"500"},{"id":"3","num":"1000"},{"id":"4","num":"5000"},{"id":"5","num":"10000"},{"id":"6","num":"50000"}]
     * sysprice : 1.0000
     * default_bank : {"id":"13","userid":"2","username":"汪思","bank_number":"423358513329333","bank":"/hash/ac/1536815313_3092.png","branch":"/hash/ad/1536815326_2908.jpg","state":"1","isdefault":"2"}
     * unbind : 0
     */

    private String title;
    private UserWallet user_wallet;
    private String sysprice;
    private String discount_ratio;
    private DefaultBank default_bank;
    private int unbind;
    private List<TradeNum> trade_num;
    private String list_url;

    public String getDiscount_ratio() {
        return discount_ratio;
    }

    public void setDiscount_ratio(String discount_ratio) {
        this.discount_ratio = discount_ratio;
    }

    public String getList_url() {
        return list_url;
    }

    public void setList_url(String list_url) {
        this.list_url = list_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserWallet getUser_wallet() {
        return user_wallet;
    }

    public void setUser_wallet(UserWallet user_wallet) {
        this.user_wallet = user_wallet;
    }

    public String getSysprice() {
        return sysprice;
    }

    public void setSysprice(String sysprice) {
        this.sysprice = sysprice;
    }

    public DefaultBank getDefault_bank() {
        return default_bank;
    }

    public void setDefault_bank(DefaultBank default_bank) {
        this.default_bank = default_bank;
    }

    public int getUnbind() {
        return unbind;
    }

    public void setUnbind(int unbind) {
        this.unbind = unbind;
    }

    public List<TradeNum> getTrade_num() {
        return trade_num;
    }

    public void setTrade_num(List<TradeNum> trade_num) {
        this.trade_num = trade_num;
    }

    public static class UserWallet {
        /**
         * hcg_wa : 4031.0000
         * cash_wa : 266.0000
         * care_wa : 0.0000
         */

        private String hcg_wa;
        private String cash_wa;
        private String care_wa;

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

        public String getCare_wa() {
            return care_wa;
        }

        public void setCare_wa(String care_wa) {
            this.care_wa = care_wa;
        }
    }

    public static class DefaultBank {
        /**
         * id : 13
         * userid : 2
         * username : 汪思
         * bank_number : 423358513329333
         * bank : /hash/ac/1536815313_3092.png
         * branch : /hash/ad/1536815326_2908.jpg
         * state : 1
         * isdefault : 2
         */

        private String id;
        private String userid;
        private String username;
        private String bank_number;
        private String bank;
        private String branch;
        private String state;
        private String isdefault;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getBank_number() {
            return bank_number;
        }

        public void setBank_number(String bank_number) {
            this.bank_number = bank_number;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(String isdefault) {
            this.isdefault = isdefault;
        }
    }

    public static class TradeNum {
        /**
         * id : 1
         * num : 100
         */

        private String id;
        private String num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
