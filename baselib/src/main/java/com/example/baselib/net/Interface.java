package com.example.baselib.net;


import com.example.baselib.net.net.RegisterTestBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Interface {

    @GET("/")
    Observable<RegisterTestBean> getAddress(@Query("app") String app, @Query("act") String act
            , @Query("mobile") String mobile, @Query("password") String password
            , @Query("vcode") String vcode, @Query("device") String device, @Query("device_id") String device_id);    //

}