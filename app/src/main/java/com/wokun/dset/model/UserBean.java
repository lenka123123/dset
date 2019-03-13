package com.wokun.dset.model;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.List;

/**
 * des:
 * Created by xsf
 * on 2016.09.9:54
 */
public class UserBean implements Serializable {


    /**
     * token : 5a5037f5398141563fb035dcfe21328f
     * notic : [{"title":"公告测试","url":"http://dsyt.cn/user/noticecontent.html?id=11"},{"title":"区块链分类","url":"http://dsyt.cn/user/noticecontent.html?id=7"}]
     * user : {"user_img":"http://dsyt.cn/img/header.png","userid":1866,"start":1}
     * checksign : {"signin_status":"0"}
     * upgrade : {"upstatus":0,"upmsg":""}
     * user_cash_wa : 0.0000
     * user_hcg_wa : 0.0000
     * botton : [{"name":"首页","url":"http://dsyt.cn/","img_normal":"http://dsyt.cn/pic/home_no.png","img_selected":"http://dsyt.cn/pic/home_yes.png","type":"1"},{"name":"转账","url":"http://dsyt.cn","img_normal":"http://dsyt.cn/pic/transfer_no.png","img_selected":"http://dsyt.cn/pic/share_yes.png","type":"0"},{"name":"达事生态","url":"","img_normal":"http://dsyt.cn/pic/ecology_no.png","img_selected":"http://dsyt.cn/pic/ecology_yes.png","type":"0"},{"name":"购物车","url":"http://dsyt.cn/user/comsubmit.html","img_normal":"http://dsyt.cn/pic/cart_no.png","img_selected":"http://dsyt.cn/pic/cart_yes.png","type":"0"},{"name":"我的","url":"http://dsyt.cn/user/mycontent.html","img_normal":"http://dsyt.cn/pic/my_no.png","img_selected":"http://dsyt.cn/pic/my_yes.png","type":"0"}]
     */

    private String token;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private UserMsgBean user;
    private ChecksignBean checksign;
    private UpgradeBean upgrade;
    private String user_cash_wa;
    private String user_hcg_wa;
    private List<NoticBean> notic;
    private List<BottonBean> botton;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserMsgBean getUser() {
        return user;
    }

    public void setUser(UserMsgBean user) {
        this.user = user;
    }

    public ChecksignBean getChecksign() {
        return checksign;
    }

    public void setChecksign(ChecksignBean checksign) {
        this.checksign = checksign;
    }

    public UpgradeBean getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(UpgradeBean upgrade) {
        this.upgrade = upgrade;
    }

    public String getUser_cash_wa() {
        return user_cash_wa;
    }

    public void setUser_cash_wa(String user_cash_wa) {
        this.user_cash_wa = user_cash_wa;
    }

    public String getUser_hcg_wa() {
        return user_hcg_wa;
    }

    public void setUser_hcg_wa(String user_hcg_wa) {
        this.user_hcg_wa = user_hcg_wa;
    }

    public List<NoticBean> getNotic() {
        return notic;
    }

    public void setNotic(List<NoticBean> notic) {
        this.notic = notic;
    }

    public List<BottonBean> getBotton() {
        return botton;
    }

    public void setBotton(List<BottonBean> botton) {
        this.botton = botton;
    }

    public static class UserMsgBean implements Serializable {
        /**
         * user_img : http://dsyt.cn/img/header.png
         * userid : 1866
         * start : 1
         */

        private String user_img;
        private int userid;
        private int start;

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }
    }

    public static class ChecksignBean implements Serializable {
        /**
         * signin_status : 0
         */

        private String signin_status;

        public String getSignin_status() {
            return signin_status;
        }

        public void setSignin_status(String signin_status) {
            this.signin_status = signin_status;
        }
    }

    public static class UpgradeBean implements Serializable {
        /**
         * upstatus : 0
         * upmsg :
         */

        private int upstatus;
        private String upmsg;

        public int getUpstatus() {
            return upstatus;
        }

        public void setUpstatus(int upstatus) {
            this.upstatus = upstatus;
        }

        public String getUpmsg() {
            return upmsg;
        }

        public void setUpmsg(String upmsg) {
            this.upmsg = upmsg;
        }
    }

    public static class NoticBean implements Serializable {
        /**
         * title : 公告测试
         * url : http://dsyt.cn/user/noticecontent.html?id=11
         */

        private String title;
        private String url;
        private String id;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class BottonBean implements Serializable {
        /**
         * name : 首页
         * url : http://dsyt.cn/
         * img_normal : http://dsyt.cn/pic/home_no.png
         * img_selected : http://dsyt.cn/pic/home_yes.png
         * type : 1
         */

        private String name;
        private String url;
        private String img_normal;
        private String img_selected;
        private String type;

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

        public String getImg_normal() {
            return img_normal;
        }

        public void setImg_normal(String img_normal) {
            this.img_normal = img_normal;
        }

        public String getImg_selected() {
            return img_selected;
        }

        public void setImg_selected(String img_selected) {
            this.img_selected = img_selected;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
