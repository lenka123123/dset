package com.example.baselib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.baselib.net.MD5.MapKeyComparator;
import com.example.baselib.net.MD5.Md5Encrypt;
import com.example.baselib.net.RetrofitUnitl;
import com.example.baselib.net.net.ExceptionHandle;
import com.example.baselib.net.net.MySubscriber;
import com.example.baselib.net.net.NetApi;
import com.example.baselib.net.net.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread() {
            @Override
            public void run() {
                super.run();

            }
        }.start();
    }






}
