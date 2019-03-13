package com.wokun.dset.ucenter.bean;

/**
 * Created by Administrator on 2018/9/25.
 */

public class BillRecordBean {

    /**
     * event_type : 系统拨发
     * wallet_type : 积分
     * amount : 1000.0000
     * created_at : 2018/08/28 16:54:40
     */

    private String event_type;
    private String wallet_type;
    private String amount;
    private String created_at;

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getWallet_type() {
        return wallet_type;
    }

    public void setWallet_type(String wallet_type) {
        this.wallet_type = wallet_type;
    }

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
}
