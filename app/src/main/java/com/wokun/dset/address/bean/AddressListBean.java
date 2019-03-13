package com.wokun.dset.address.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/1/3.
 */

public class AddressListBean {

    /**
     * myaddress : []
     * add_address : {"name":"添加","url":"http://web.dasether.com/server/addmyaddress.html"}
     */

    private AddAddressBean add_address;
    private List<AddressBean> myaddress;

    public AddAddressBean getAdd_address() {
        return add_address;
    }

    public void setAdd_address(AddAddressBean add_address) {
        this.add_address = add_address;
    }

    public List<AddressBean> getMyaddress() {
        return myaddress;
    }

    public void setMyaddress(List<AddressBean> myaddress) {
        this.myaddress = myaddress;
    }

    public static class AddAddressBean {
        /**
         * name : 添加
         * url : http://web.dasether.com/server/addmyaddress.html
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
    public static class AddressBean{
        private String id;
        private String name;
        private String phone;
        private String provice;
        private String city;
        private String area;
        private String address;
        private String is_default;//默认地址（0：否，1：是）

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
