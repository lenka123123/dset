package com.wokun.dset.ucenter.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019\2\21 0021.
 */

public class AboutBean  implements Serializable{

  private  String  title;
    private  String  url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
