package com.example.baselib.bean;

public class EditBaseInfoBean {


    /**
     * code : 10000
     * data : {"avatar":"","coin_num":"0.00","diamond_num":"0.00","mobile":"13902276741","nickname":"mjmk","ticket_num":"0.00","user_id":"4"}
     * msg : 操作成功
     * request_id : 7022e4002feb102e
     * success : 1
     */

    private String code;
    private DataBean data;
    private String msg;
    private String request_id;
    private String success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * avatar :
         * coin_num : 0.00
         * diamond_num : 0.00
         * mobile : 13902276741
         * nickname : mjmk
         * ticket_num : 0.00
         * user_id : 4
         */

        private String avatar;
        private String coin_num;
        private String diamond_num;
        private String mobile;
        private String nickname;
        private String ticket_num;
        private String user_id;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public String getTicket_num() {
            return ticket_num;
        }

        public void setTicket_num(String ticket_num) {
            this.ticket_num = ticket_num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
