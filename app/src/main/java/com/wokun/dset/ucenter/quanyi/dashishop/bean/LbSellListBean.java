package com.wokun.dset.ucenter.quanyi.dashishop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/25.
 */

public class LbSellListBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * amount : 80.0000
         * created_at : 2018-09-27 14:41:59
         * icon : http://lktokencenter.com/hash/ac/1538029349_2366.png
         * id : 33
         * in_userid : 1
         * in_username : cdl001
         * isbind : 0
         * number : 100.0000
         */

        private String amount;
        private String created_at;
        private String icon;
        private String id;
        private String in_userid;

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getDiscount_ratio() {
            return discount_ratio;
        }

        public void setDiscount_ratio(String discount_ratio) {
            this.discount_ratio = discount_ratio;
        }

        private String in_username;
        private int isbind;
        private  String deposit;
        private  String discount_ratio;
        private String number;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIn_userid() {
            return in_userid;
        }

        public void setIn_userid(String in_userid) {
            this.in_userid = in_userid;
        }

        public String getIn_username() {
            return in_username;
        }

        public void setIn_username(String in_username) {
            this.in_username = in_username;
        }

        public int getIsbind() {
            return isbind;
        }

        public void setIsbind(int isbind) {
            this.isbind = isbind;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
