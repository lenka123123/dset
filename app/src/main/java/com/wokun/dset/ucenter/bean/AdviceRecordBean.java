package com.wokun.dset.ucenter.bean;

import com.nostra13.universalimageloader.utils.L;

import java.util.List;

/**
 * Created by Administrator on 2018/9/14.
 */

public class AdviceRecordBean {

    /**
     * id : 3
     * title : 我的建议
     * create_time : 2018-08-28 14:38:43
     */

    private String id;
    private String title;
    private String create_time;
    private String status;
    private String content;
    private List<String> picture;
    private String order_id;
    private String replay;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
