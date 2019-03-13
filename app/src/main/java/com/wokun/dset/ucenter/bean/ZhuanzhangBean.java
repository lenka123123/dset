package com.wokun.dset.ucenter.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019\2\26 0026.
 */

public class ZhuanzhangBean implements Serializable{

    private  String  url;
    private  int  shouxu;
    private  int  out_num;
    private  int  pay_num;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getShouxu() {
        return shouxu;
    }

    public void setShouxu(int shouxu) {
        this.shouxu = shouxu;
    }

    public int getOut_num() {
        return out_num;
    }

    public void setOut_num(int out_num) {
        this.out_num = out_num;
    }

    public int getPay_num() {
        return pay_num;
    }

    public void setPay_num(int pay_num) {
        this.pay_num = pay_num;
    }

    @Override
    public String toString() {
        return "ZhuanzhangBean{" +
                "url='" + url + '\'' +
                ", shouxu='" + shouxu + '\'' +
                ", out_num='" + out_num + '\'' +
                ", pay_num='" + pay_num + '\'' +
                '}';
    }
}
