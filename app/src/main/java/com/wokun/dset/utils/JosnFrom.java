package com.wokun.dset.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wokun.dset.store.bean.GoodsSKUList;

import java.util.List;

public class JosnFrom {

    private static volatile JosnFrom instance = null;
    private   final Gson gson;

    private JosnFrom() {
        gson = new Gson();
    }

    public static JosnFrom getInstance() {
        if (instance == null) {
            synchronized (JosnFrom.class) {
                if (instance == null) {
                    instance = new JosnFrom();
                }
            }
        }
        return instance;
    }




    public Object getObj(String json, Class t) {
        //  User user = gson.fromJson(userJson, User.class);

        return gson.fromJson(json, t);

    }


    public List<GoodsSKUList> getObjList(String json) {
        //  User user = gson.fromJson(userJson, User.class);
//  List<Person> ps = gson.fromJson(str, new TypeToken<List<Person>>(){}.getType());
        return gson.fromJson(json, new TypeToken<List<GoodsSKUList>>(){}.getType());

    }

}
