package com.wokun.dset.ucenter.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/17.
 */

public class BankCardBean implements Serializable {

    /**
     * id : 14
     * username : simon
     * bank_number : 12345111321321
     * bank : 银行名称
     * phone : 15100000000
     * zmpath : /hash/cd/1535680490_3948.jpg
     * fmpath : /hash/ac/1535680514_7966.jpg
     * isdefault : 2
     */

    private String id;
    private String username;
    private String bank_number;
    private String bank;
    private String phone;
    private String zmpath;
    private String fmpath;
    private int isdefault;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZmpath() {
        return zmpath;
    }

    public void setZmpath(String zmpath) {
        this.zmpath = zmpath;
    }

    public String getFmpath() {
        return fmpath;
    }

    public void setFmpath(String fmpath) {
        this.fmpath = fmpath;
    }

    public int getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(int isdefault) {
        this.isdefault = isdefault;
    }
}
