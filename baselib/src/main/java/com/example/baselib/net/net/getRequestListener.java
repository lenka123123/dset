package com.example.baselib.net.net;


public interface getRequestListener<T> {
    void getRequestSuccess(T t);

    void getRequestFail(String error);
}