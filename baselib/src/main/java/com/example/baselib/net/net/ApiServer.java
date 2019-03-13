package com.example.baselib.net.net;

import okhttp3.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer {
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    //完整URL：http://112.124.22.238:8081/course_api/cniaoplay/featured?p={"page":0}
    @GET("featured")
    Call getApps(@Query("p") String jsonParms);
}
