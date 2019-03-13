package com.wokun.dset.ucenter.quanyi.dashishop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/21.
 */

public class BaojiaBean {
    private String amount;
    private String created_at;
    private String id;
    private String number;
    private List<Baojiapeople> offer;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Baojiapeople> getOffer() {
        return offer;
    }

    public void setOffer(List<Baojiapeople> offer) {
        this.offer = offer;
    }

    public static class Baojiapeople {
        /**
         * id : 1
         * num : 100
         */

        private String headimg;
        private String offer_id;
        private String out_offer;
        private String out_uid;

        @Override
        public String toString() {
            return "Baojiapeople{" +
                    "headimg='" + headimg + '\'' +
                    ", offer_id='" + offer_id + '\'' +
                    ", out_offer='" + out_offer + '\'' +
                    ", out_uid='" + out_uid + '\'' +
                    '}';
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(String offer_id) {
            this.offer_id = offer_id;
        }

        public String getOut_offer() {
            return out_offer;
        }

        public void setOut_offer(String out_offer) {
            this.out_offer = out_offer;
        }

        public String getOut_uid() {
            return out_uid;
        }

        public void setOut_uid(String out_uid) {
            this.out_uid = out_uid;
        }
    }
}
