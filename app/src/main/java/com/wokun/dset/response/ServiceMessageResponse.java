package com.wokun.dset.response;


import java.util.List;

public class ServiceMessageResponse {

    private List<ServiceNoticeBean> serviceList;

    public List<ServiceNoticeBean> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceNoticeBean> serviceList) {
        this.serviceList = serviceList;
    }
}
