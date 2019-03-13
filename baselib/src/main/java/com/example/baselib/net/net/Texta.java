package com.example.baselib.net.net;

public class Texta extends BaseResponse {
    public String seller;
    public String token;
    public String mobile;
    public String nickname;
    public String password;
    public String pay_password;
    public String avatar;
    public String province_id;
    public String city_id;
    public String area_id;
    public String gender;
    public String status;
    public String coin_num;
    public String diamond_num;
    public String ticket_num;
    public String code;
    public String introducer_id;
    public String parent_introducer_id;
    public String lat;
    public String lng;
    public String device_type;
    public String device_id;
    public String push_tag;
    public String login_num;
    public String login_time;
    public String login_ip;
    public String old_time;
    public String old_ip;
    public String add_time;
    public String id;
    public String add_date;
    public String user_id;

    @Override
    public String toString() {
        return getCode() + "返回成功";
    }
}
