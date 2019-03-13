package com.wokun.dset.ucenter.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019\1\30 0030.
 * "tradeOrderList": [
 {
 "id": "55",
 "in_userid": "1927",
 "in_username": "Dset18761654084",
 "out_userid": "1890",
 "out_username": "ybb123",
 "wallet_token": "oPITKxPfG0H157cFVNntU4PZhVhDEZAW",
 "bank": "君麻吕",
 "bank_num": "2996889655",
 "amount": "123.0000",
 "samount": "100.0000",
 "number": "100.0000",
 "price": "1.0000",
 "sysprice": "1.0000",
 "terrace_fee": "0.0000",
 "integral_fee": "0.0000",
 "discount_fee": null,
 "note": null,
 "type": "1",
 "status": "2",
 "traded_at": "2019-01-25 15:44:02",
 "created_at": "2019-01-23 13:16:05",
 "updated_at": "2019-01-25 15:44:02",
 "method": "1",
 "order_type": "1",
 "realname": "你困",
 "alipay": "",
 "wechat": "",
 "phone": "1850741289",
 "picture": ": "http://api.dasether.com/hash/bd/1548402240_4266.png",
 ",
 "deposit": "50.0000",
 "alipay_img": "",
 "wechat_img": "",
 "jy_method_name": "",
 "jyType": 2,
 "jy_type_name": "卖出",
 "jy_username": "Dset18761654084",
 "show": "100.0000",
 "control": "是否收款",
 "buyer_phone": "18761654084",
 "buyer_headimg": ": "http://api.dasether.com/hash/ae/1548063163_8547.png",
 ",
 "seller_headimg": ": "http://api.dasether.com/hash/ae/1547804197_3811.png",
 ",
 "description": "买家已付款"
 */

public class TradeOrderListBean implements Serializable {
    private String id;
    private String in_userid;
    private String in_username;
    private String out_userid;
    private String out_username;
    private String wallet_token;
    private String bank;
    private String bank_num;
    private String amount;
    private String samount;
    private String number;
    private String price;
    private String sysprice;
    private String terrace_fee;
    private String integral_fee;
    private String discount_fee;
    private String note;
    private String type;
    private String status;
    private String traded_at;
    private String created_at;
    private String updated_at;
    private String method;
    private String order_type;
    private String realname;
    private String alipay;
    private String wechat;
    private String phone;
    private String picture;
    private String deposit;
    private String alipay_img;
    private String wechat_img;
    private String jy_method_name;
    private int jyType;
    private String jy_type_name;
    private String jy_username;
    private String show;
    private String control;
    private String buyer_phone;
    private String buyer_headimg;
    private String seller_headimg;
    private String description;


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

    public String getOut_userid() {
        return out_userid;
    }

    public void setOut_userid(String out_userid) {
        this.out_userid = out_userid;
    }

    public String getOut_username() {
        return out_username;
    }

    public void setOut_username(String out_username) {
        this.out_username = out_username;
    }

    public String getWallet_token() {
        return wallet_token;
    }

    public void setWallet_token(String wallet_token) {
        this.wallet_token = wallet_token;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank_num() {
        return bank_num;
    }

    public void setBank_num(String bank_num) {
        this.bank_num = bank_num;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSamount() {
        return samount;
    }

    public void setSamount(String samount) {
        this.samount = samount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSysprice() {
        return sysprice;
    }

    public void setSysprice(String sysprice) {
        this.sysprice = sysprice;
    }

    public String getTerrace_fee() {
        return terrace_fee;
    }

    public void setTerrace_fee(String terrace_fee) {
        this.terrace_fee = terrace_fee;
    }

    public String getIntegral_fee() {
        return integral_fee;
    }

    public void setIntegral_fee(String integral_fee) {
        this.integral_fee = integral_fee;
    }

    public String getDiscount_fee() {
        return discount_fee;
    }

    public void setDiscount_fee(String discount_fee) {
        this.discount_fee = discount_fee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTraded_at() {
        return traded_at;
    }

    public void setTraded_at(String traded_at) {
        this.traded_at = traded_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getAlipay_img() {
        return alipay_img;
    }

    public void setAlipay_img(String alipay_img) {
        this.alipay_img = alipay_img;
    }

    public String getWechat_img() {
        return wechat_img;
    }

    public void setWechat_img(String wechat_img) {
        this.wechat_img = wechat_img;
    }

    public String getJy_method_name() {
        return jy_method_name;
    }

    public void setJy_method_name(String jy_method_name) {
        this.jy_method_name = jy_method_name;
    }

    public int getJyType() {
        return jyType;
    }

    public void setJyType(int jyType) {
        this.jyType = jyType;
    }

    public String getJy_type_name() {
        return jy_type_name;
    }

    public void setJy_type_name(String jy_type_name) {
        this.jy_type_name = jy_type_name;
    }

    public String getJy_username() {
        return jy_username;
    }

    public void setJy_username(String jy_username) {
        this.jy_username = jy_username;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getBuyer_phone() {
        return buyer_phone;
    }

    public void setBuyer_phone(String buyer_phone) {
        this.buyer_phone = buyer_phone;
    }

    public String getBuyer_headimg() {
        return buyer_headimg;
    }

    public void setBuyer_headimg(String buyer_headimg) {
        this.buyer_headimg = buyer_headimg;
    }

    public String getSeller_headimg() {
        return seller_headimg;
    }

    public void setSeller_headimg(String seller_headimg) {
        this.seller_headimg = seller_headimg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
