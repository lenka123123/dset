package com.wokun.dset.response;

import java.io.Serializable;

public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;


    private String stateCode;
    private String msg;

    public BaseResponse toBaseResponse() {
        BaseResponse response = new BaseResponse();
        response.setStatus(stateCode);
        response.setMessage(msg);
        return response;
    }
}
