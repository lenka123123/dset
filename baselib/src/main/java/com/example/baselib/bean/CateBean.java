package com.example.baselib.bean;

import java.util.List;

public class CateBean {

    /**
     * success : 1
     * msg : 操作成功
     * data : {"page":"1","perpage":"10","count":"13","list":[{"id":"76","name":"手机","intro":"","parent_id":"68","type":"1","sort_order":"1","is_show":"1","image":"","category_id":"76"},{"id":"75","name":"IPHONE","intro":"","parent_id":"73","type":"1","sort_order":"1","is_show":"1","image":"","category_id":"75"},{"id":"74","name":"苹果","intro":"","parent_id":"68","type":"1","sort_order":"1","is_show":"1","image":"","category_id":"74"},{"id":"73","name":"苹果","intro":"","parent_id":"68","type":"1","sort_order":"1","is_show":"1","image":"","category_id":"73"},{"id":"72","name":"耳环","intro":"","parent_id":"72","type":"1","sort_order":"5","is_show":"1","image":"","category_id":"72"},{"id":"41","name":"东北菜","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"41"},{"id":"35","name":"粤菜","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"35"},{"id":"35","name":"粤菜","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"35"},{"id":"35","name":"粤菜","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"35"},{"id":"34","name":"香锅烤鱼","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"34"}]}
     * code : 10000
     * request_id : 0e59d96c45e41e6e
     */

    private String success;
    private String msg;
    private DataBean data;
    private String code;
    private String request_id;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public static class DataBean {
        /**
         * page : 1
         * perpage : 10
         * count : 13
         * list : [{"id":"76","name":"手机","intro":"","parent_id":"68","type":"1","sort_order":"1","is_show":"1","image":"","category_id":"76"},{"id":"75","name":"IPHONE","intro":"","parent_id":"73","type":"1","sort_order":"1","is_show":"1","image":"","category_id":"75"},{"id":"74","name":"苹果","intro":"","parent_id":"68","type":"1","sort_order":"1","is_show":"1","image":"","category_id":"74"},{"id":"73","name":"苹果","intro":"","parent_id":"68","type":"1","sort_order":"1","is_show":"1","image":"","category_id":"73"},{"id":"72","name":"耳环","intro":"","parent_id":"72","type":"1","sort_order":"5","is_show":"1","image":"","category_id":"72"},{"id":"41","name":"东北菜","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"41"},{"id":"35","name":"粤菜","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"35"},{"id":"35","name":"粤菜","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"35"},{"id":"35","name":"粤菜","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"35"},{"id":"34","name":"香锅烤鱼","intro":"","parent_id":"1","type":"2","sort_order":"50","is_show":"1","image":"","category_id":"34"}]
         */

        private String page;
        private String perpage;
        private String count;
        private List<MbHomeBean.DataBean.CateListBean> list;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPerpage() {
            return perpage;
        }

        public void setPerpage(String perpage) {
            this.perpage = perpage;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<MbHomeBean.DataBean.CateListBean> getList() {
            return list;
        }

        public void setList(List<MbHomeBean.DataBean.CateListBean> list) {
            this.list = list;
        }

    }
}
