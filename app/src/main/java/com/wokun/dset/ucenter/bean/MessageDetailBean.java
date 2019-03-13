package com.wokun.dset.ucenter.bean;

/**
 * Created by Administrator on 2018/9/20.
 */

public class MessageDetailBean {

    /**
     * status : 您好: 您的LKC订单(239)已经匹配成功，请您尽快登录APP操作。
     * created_at : 2018/08/28
     */

    private String status;
    private String created_at;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
