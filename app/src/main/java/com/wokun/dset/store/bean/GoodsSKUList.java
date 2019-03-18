package com.wokun.dset.store.bean;


import java.util.List;

public class GoodsSKUList {


    /**
     * spec_id : 7
     * spec_name : u5185u5b58u5927u5c0f
     * value : [{"spec_id":7,"spec_name":"u5185u5b58u5927u5c0f","spec_show_type":1,"spec_value_data":"","spec_value_id":10,"spec_value_name":"16Gu5185u5b58"},{"spec_id":7,"spec_name":"u5185u5b58u5927u5c0f","spec_show_type":1,"spec_value_data":"","spec_value_id":11,"spec_value_name":"32Gu5185u5b58"},{"spec_id":7,"spec_name":"u5185u5b58u5927u5c0f","spec_show_type":1,"spec_value_data":"","spec_value_id":9,"spec_value_name":"8Gu5185u5b58"}]
     */

    private int spec_id;
    private String spec_name;
    private List<ValueBean> value;

    public int getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(int spec_id) {
        this.spec_id = spec_id;
    }

    public String getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(String spec_name) {
        this.spec_name = spec_name;
    }

    public List<ValueBean> getValue() {
        return value;
    }

    public void setValue(List<ValueBean> value) {
        this.value = value;
    }

    public static class ValueBean {
        /**
         * spec_id : 7
         * spec_name : u5185u5b58u5927u5c0f
         * spec_show_type : 1
         * spec_value_data :
         * spec_value_id : 10
         * spec_value_name : 16Gu5185u5b58
         */

        private int spec_id;
        private String spec_name;
        private int spec_show_type;
        private String spec_value_data;
        private int spec_value_id;
        private String spec_value_name;

        public int getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(int spec_id) {
            this.spec_id = spec_id;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public int getSpec_show_type() {
            return spec_show_type;
        }

        public void setSpec_show_type(int spec_show_type) {
            this.spec_show_type = spec_show_type;
        }

        public String getSpec_value_data() {
            return spec_value_data;
        }

        public void setSpec_value_data(String spec_value_data) {
            this.spec_value_data = spec_value_data;
        }

        public int getSpec_value_id() {
            return spec_value_id;
        }

        public void setSpec_value_id(int spec_value_id) {
            this.spec_value_id = spec_value_id;
        }

        public String getSpec_value_name() {
            return spec_value_name;
        }

        public void setSpec_value_name(String spec_value_name) {
            this.spec_value_name = spec_value_name;
        }
    }
}
