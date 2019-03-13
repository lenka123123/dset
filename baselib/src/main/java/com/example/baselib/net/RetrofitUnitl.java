package com.example.baselib.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUnitl {  // api.ideamark.cn
//    public static String SERVICE_PATH = "http://api.ideamark.cn";      //线上服
    //    public static String SERVICE_PATH = "https://newapi.891jyb.com";      //线上服
//  public static String SERVICE_PATH = "http://api.jyb.qimixi.net";
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;

    public static String base_url ="http://api.dasether.com/";    //线上服

    private Retrofit mRetrofit;
    private String baseUrl;
    OkHttpClient client;
    private static RetrofitUnitl mRetrofitManager;
    public static OkHttpClient ok;

    private RetrofitUnitl(String baseUrl, OkHttpClient client) {
        this.baseUrl = baseUrl;
        this.client = client;
        initRetrofit();
    }

    public static synchronized RetrofitUnitl getInstance() {
        if (mRetrofitManager == null) {
            getOkHttpClient();
            mRetrofitManager = new RetrofitUnitl(base_url, ok);
        }
        return mRetrofitManager;
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    public <T> T setCreate(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }


    public static void getOkHttpClient() {
        ok = new OkHttpClient.Builder()
//                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//连接 超时时间
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//写操作 超时时间
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//读操作 超时时间
                .retryOnConnectionFailure(true)//错误重连
                .build();


//        ImageLoaderConfiguration con = new ImageLoaderConfiguration.Builder(this)
//                .threadPriority(100)
//                .threadPoolSize(3)
//                .memoryCacheExtraOptions(200, 200)
//                .memoryCacheSize(2 * 1024 * 1024)
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                .build();
//        ImageLoader.getInstance().init(con);
    }
}