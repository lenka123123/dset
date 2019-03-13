package com.example.baselib.net.net;


import com.example.baselib.bean.CateBean;
import com.example.baselib.bean.EditBaseInfoBean;
import com.example.baselib.bean.HomeInfoBean;
import com.example.baselib.bean.IndentBean;
import com.example.baselib.bean.IndentDetailBean;
import com.example.baselib.bean.LoginBean;
import com.example.baselib.bean.MbGuessLikeBean;
import com.example.baselib.bean.MbHomeBean;
import com.example.baselib.bean.MessageListBean;
import com.example.baselib.bean.MqHomeBean;
import com.example.baselib.bean.MzHomeBean;
import com.example.baselib.bean.ProdutInfoBean;
import com.example.baselib.bean.RegisterBean;
import com.example.baselib.bean.ShoppingListBean;
import com.example.baselib.bean.UserBean;
import com.example.baselib.bean.WalletBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface NetApi {


//    @GET("/form") @Multipart
//    Call<ResponseBody> testFileUpload2(@PartMap Map<String, RequestBody> args, @Part MultipartBody.Part file);


    @GET("/?app=default&act=login")
    Observable<LoginBean> testLogin(@QueryMap() Map<String, String> args);

    // 图片上传

    @GET("/?app=ucenter&act=editBaseInfo")
    Observable<EditBaseInfoBean> editBaseInfo(
            @Query("token") String token,
            @Query("user_id") String user_id,
            @Query("nickname") String nickname,
            @Query("logo_path") String avatar
    );

    //  获取我的订单详情
    @GET("/?app=ucenter&act=editBaseInfo")
    Observable<EditBaseInfoBean> editBaseInfo(@QueryMap() Map<String, Object> args);


    @POST("/?app=default&act=uploadImage")
    Observable<ResponseBody> uploadImage(@Part("image") RequestBody image);

    @Multipart
    @POST("/?app=default&act=uploadImage")
    Call<ResponseBody> uploadAvatar();

    @GET("/")
    Observable<HomeInfoBean> lgoin(
            @Query("app") String app, @Query("act") String act
            , @Query("app_version") String app_version, @Query("device_id") String device_id
            , @Query("mobile") String mobile, @Query("password") String password
            , @Query("lng") String lng, @Query("lat") String lat
            , @Query("device") String device, @Query("vcode") String vcode
    );



    //登录
    @FormUrlEncoded
    @POST("api/loginapi.html")
    Observable<ResponseBody> mTestLogin(@FieldMap() Map<String, String> args);

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("api/loginapi.html")
    Observable<ResponseBody> login(@Body User user);

    @POST("/?app=default&act=uploadImage")
    Observable<ResponseBody> uploadImagqqqqqqqqqqe(@Part("image") RequestBody image);

}