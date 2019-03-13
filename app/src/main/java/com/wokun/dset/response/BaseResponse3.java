package com.wokun.dset.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseResponse3<T> implements Serializable {

    private String status;
    private String message;
    private ArrayList<T> data;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
