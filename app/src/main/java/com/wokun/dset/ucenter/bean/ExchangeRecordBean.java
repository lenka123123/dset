package com.wokun.dset.ucenter.bean;

/**
 * Created by Administrator on 2018/9/14.
 */

public class ExchangeRecordBean {

    /**
     * id : 11
     * userid : 9
     * username : ymj007
     * hcg_amount : 600.0000
     * cash_amount : 100.0000
     * created_at : 2018-09-04 14:43:27
     */

    private String id;
    private String userid;
    private String username;
    private String hcg_amount;
    private String cash_amount;
    private String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHcg_amount() {
        return hcg_amount;
    }

    public void setHcg_amount(String hcg_amount) {
        this.hcg_amount = hcg_amount;
    }

    public String getCash_amount() {
        return cash_amount;
    }

    public void setCash_amount(String cash_amount) {
        this.cash_amount = cash_amount;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "ExchangeRecordBean{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", hcg_amount='" + hcg_amount + '\'' +
                ", cash_amount='" + cash_amount + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
