package com.wokun.dset.login.net;



import com.wokun.dset.model.UserBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    @POST
    Call<UserBean> login(@Url String url, @QueryMap Map<String,String> map);
}
