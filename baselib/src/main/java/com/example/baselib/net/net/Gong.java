package com.example.baselib.net.net;

public interface Gong<T> {
    void onAttach(T t);

    void onDeattch();
}
