package com.wokun.dset.ucenter.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class MyBean {

    /**
     * setting : {"url":"http://web.dasether.com/user/setting.html"}
     * opportunity : 3
     * quota : 10000
     * head : http://web.dasether.com/http://web.dasether.com/hash/ab/1546411248_979.png
     * userid : 1866
     * start : 2
     * app : [{"name":"我的昵称","url":"http://web.dasether.com/user/alternick.html","img":"http://web.dasether.com/img/nickname.png","title":"","type":1},{"name":"分享","url":"http://web.dasether.com/user/share.html","img":"http://web.dasether.com/img/share.png","title":"","type":2},{"name":"我的银行卡","url":"http://web.dasether.com/user/addcard.html","img":"http://web.dasether.com/img/bank.png","title":"","type":3},{"name":"收付款","url":"http://web.dasether.com/register/checkaccount.html","img":"http://web.dasether.com/img/turnin.png","title":"","type":4},{"name":"账户记录","url":"http://web.dasether.com/profile/walletrecord.html","img":"http://web.dasether.com/img/accountrecord.png","title":"","type":5},{"name":"消息","url":"http://web.dasether.com/user/mynotify.html","img":"http://web.dasether.com/img/news.png","title":"","type":6},{"name":"公告","url":"http://web.dasether.com/user/notice.html","img":"http://web.dasether.com/img/notice.png","title":"","type":8},{"name":"申请超级节点","url":"http://web.dasether.com/user/applysupernode.html","img":"http://web.dasether.com/img/supernode.png","title":"未申请","type":9},{"name":"我的地址","url":"","img":"http://web.dasether.com/img/notic.png","title":"","type":10}]
     */

    private SettingBean setting;
    private int opportunity;
    private int quota;
    private String head;
    private String userid;
    private String   balance;
    private  String  integral;
    private int start;
    private  Double quanyi;
    private List<AppBean> app;

    public Double getQuanyi() {
        return quanyi;
    }

    public void setQuanyi(Double quanyi) {
        this.quanyi = quanyi;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public SettingBean getSetting() {
        return setting;
    }

    public void setSetting(SettingBean setting) {
        this.setting = setting;
    }

    public int getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(int opportunity) {
        this.opportunity = opportunity;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<AppBean> getApp() {
        return app;
    }

    public void setApp(List<AppBean> app) {
        this.app = app;
    }

    public static class SettingBean {
        /**
         * url : http://web.dasether.com/user/setting.html
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class AppBean {
        /**
         * name : 我的昵称
         * url : http://web.dasether.com/user/alternick.html
         * img : http://web.dasether.com/img/nickname.png
         * title :
         * type : 1
         */

        private String name;
        private String url;
        private String img;
        private String title;
        private int type;
        private String unread;

        public String getUnread() {
            return unread;
        }

        public void setUnread(String unread) {
            this.unread = unread;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
