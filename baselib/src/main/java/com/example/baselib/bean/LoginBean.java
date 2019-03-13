package com.example.baselib.bean;

public class LoginBean {


    /**
     * success : 1
     * msg : 操作成功
     * data : {"id":"4","seller":"0","token":"DG94SCI_mupS","mobile":"13902276741","nickname":"mjmk6741","password":"0ab44bd43d6b18fcd5cd928d6faab1b8","pay_password":"","avatar":"","province_id":"0","city_id":"0","area_id":"0","gender":"3","status":"1","coin_num":"0.00","diamond_num":"0.00","ticket_num":"0.00","code":"9bh5ac2f","introducer_id":"0","parent_introducer_id":"0","lat":"","lng":"","device_type":"1","device_id":"1111","push_tag":"ordinary","login_num":"27","login_time":"2018-12-18 14:02:02","login_ip":"58.212.100.25","old_time":"2018-12-18 13:54:52","old_ip":"58.212.100.25","add_time":"1544714942","add_date":"2018-12-13 23:29:02","user_id":"4"}
     * code : 10000
     * request_id : dab1f7ecbeeff94a
     */

    private String success;
    private String msg;
    private DataBean data;
    private String code;
    private String request_id;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public static class DataBean {
        /**
         * id : 4
         * seller : 0
         * token : DG94SCI_mupS
         * mobile : 13902276741
         * nickname : mjmk6741
         * password : 0ab44bd43d6b18fcd5cd928d6faab1b8
         * pay_password :
         * avatar :
         * province_id : 0
         * city_id : 0
         * area_id : 0
         * gender : 3
         * status : 1
         * coin_num : 0.00
         * diamond_num : 0.00
         * ticket_num : 0.00
         * code : 9bh5ac2f
         * introducer_id : 0
         * parent_introducer_id : 0
         * lat :
         * lng :
         * device_type : 1
         * device_id : 1111
         * push_tag : ordinary
         * login_num : 27
         * login_time : 2018-12-18 14:02:02
         * login_ip : 58.212.100.25
         * old_time : 2018-12-18 13:54:52
         * old_ip : 58.212.100.25
         * add_time : 1544714942
         * add_date : 2018-12-13 23:29:02
         * user_id : 4
         */

        private String id;
        private String seller;
        private String token;
        private String mobile;
        private String nickname;
        private String password;
        private String pay_password;
        private String avatar;
        private String province_id;
        private String city_id;
        private String area_id;
        private String gender;
        private String status;
        private String coin_num;
        private String diamond_num;
        private String ticket_num;
        private String code;
        private String introducer_id;
        private String parent_introducer_id;
        private String lat;
        private String lng;
        private String device_type;
        private String device_id;
        private String push_tag;
        private String login_num;
        private String login_time;
        private String login_ip;
        private String old_time;
        private String old_ip;
        private String add_time;
        private String add_date;
        private String user_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPay_password() {
            return pay_password;
        }

        public void setPay_password(String pay_password) {
            this.pay_password = pay_password;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCoin_num() {
            return coin_num;
        }

        public void setCoin_num(String coin_num) {
            this.coin_num = coin_num;
        }

        public String getDiamond_num() {
            return diamond_num;
        }

        public void setDiamond_num(String diamond_num) {
            this.diamond_num = diamond_num;
        }

        public String getTicket_num() {
            return ticket_num;
        }

        public void setTicket_num(String ticket_num) {
            this.ticket_num = ticket_num;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIntroducer_id() {
            return introducer_id;
        }

        public void setIntroducer_id(String introducer_id) {
            this.introducer_id = introducer_id;
        }

        public String getParent_introducer_id() {
            return parent_introducer_id;
        }

        public void setParent_introducer_id(String parent_introducer_id) {
            this.parent_introducer_id = parent_introducer_id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getPush_tag() {
            return push_tag;
        }

        public void setPush_tag(String push_tag) {
            this.push_tag = push_tag;
        }

        public String getLogin_num() {
            return login_num;
        }

        public void setLogin_num(String login_num) {
            this.login_num = login_num;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public String getLogin_ip() {
            return login_ip;
        }

        public void setLogin_ip(String login_ip) {
            this.login_ip = login_ip;
        }

        public String getOld_time() {
            return old_time;
        }

        public void setOld_time(String old_time) {
            this.old_time = old_time;
        }

        public String getOld_ip() {
            return old_ip;
        }

        public void setOld_ip(String old_ip) {
            this.old_ip = old_ip;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
