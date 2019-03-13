package com.example.baselib.net.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpManager {

    /**
     * OkHttpClient的封装
     */

    public OkHttpClient okHttpClient() {
        // log用拦截器
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等

        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
        // SSLSocketFactory sslSocketFactory = null;
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
                //.addInterceptor(new HeadInterceptor())
                .addInterceptor(logger)//添加拦截器
                .connectTimeout(10, TimeUnit.SECONDS) // 连接超时时间设置
                .readTimeout(10, TimeUnit.SECONDS) // 读取超时时间设置
                .build();

    }

    /**
     * Retrofit的封装
     */

    public Retrofit getRetrofit(OkHttpClient okHttpClient) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiServer.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);//不设置则使用的是默认的OKHttpClient
        return builder.build();

    }
}
