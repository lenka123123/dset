package com.wokun.dset.ucenter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019\2\21 0021.
 */

public class AboutusBean implements Serializable {
    private List<AboutBean>   about;
    public List<AboutBean> getAbout() {
        return about;
    }

    @Override
    public String toString() {
        return "AboutusBean{" +
                "about=" + about +
                '}';
    }

    public void setAbout(List<AboutBean> about) {
        this.about = about;
    }
}
