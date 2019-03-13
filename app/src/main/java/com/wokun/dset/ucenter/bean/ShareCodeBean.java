package com.wokun.dset.ucenter.bean;

/**
 * Created by Administrator on 2018/9/14.
 */

public class ShareCodeBean {

    /**
     * share_qrcode : /img/notshare.png
     * msg : 卢呗低于1000,暂不能分享
     * mycode : GfuHeb
     * count_tj : 8
     */

    private String share_qrcode;
    private String msg;
    private String mycode;
    private String count_tj;

    public String getShare_qrcode() {
        return share_qrcode;
    }

    public void setShare_qrcode(String share_qrcode) {
        this.share_qrcode = share_qrcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMycode() {
        return mycode;
    }

    public void setMycode(String mycode) {
        this.mycode = mycode;
    }

    public String getCount_tj() {
        return count_tj;
    }

    public void setCount_tj(String count_tj) {
        this.count_tj = count_tj;
    }
}
