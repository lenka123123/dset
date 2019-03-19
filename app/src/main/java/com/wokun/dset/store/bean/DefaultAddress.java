package com.wokun.dset.store.bean;


import com.google.gson.annotations.SerializedName;

public class DefaultAddress {

    /**
     * status : 0001
     * msg : 成功
     * data : {"default":{"id":"11","name":"五","phone":"13902576741","provice":"内蒙古","city":"呼和浩特市","area":"和林格尔县","address":"bnnn","is_default":"1"}}
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
         * default : {"id":"11","name":"五","phone":"13902576741","provice":"内蒙古","city":"呼和浩特市","area":"和林格尔县","address":"bnnn","is_default":"1"}
         */

        @SerializedName("default")
        private DefaultBean defaultX;

        public DefaultBean getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(DefaultBean defaultX) {
            this.defaultX = defaultX;
        }

        public static class DefaultBean {
            /**
             * id : 11
             * name : 五
             * phone : 13902576741
             * provice : 内蒙古
             * city : 呼和浩特市
             * area : 和林格尔县
             * address : bnnn
             * is_default : 1
             */

            private String id;
            private String name;
            private String phone;
            private String provice;
            private String city;
            private String area;
            private String address;
            private String is_default;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getProvice() {
                return provice;
            }

            public void setProvice(String provice) {
                this.provice = provice;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }
        }
    }
}
