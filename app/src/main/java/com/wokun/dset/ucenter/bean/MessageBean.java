package com.wokun.dset.ucenter.bean;

/**
 * Created by Administrator on 2018/9/19.
 */

public class MessageBean {

    /**
     * id : 1
     * isread : 1
     * status : 您好: 您的LKC订单(238) 已经匹配成功，请您尽快登录APP操作。
     * created_at : 2018/08/28
     */

    private String id;
    private String isread;
    private String status;
    private String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

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
