package com.wokun.dset.model;

import com.google.gson.annotations.SerializedName;

/*
* 获取个人中心数据
*
*{
      "state": true,
      "stateCode": "00002",
      "msg": "成功",
      "data":
      {
             "favorite_good_total": "7",
             "favorite_store_total": "1",
             "favorite_dietitian_total": "2",
             "favorite_article_total": "2",
             "info_notice": 1,
             "invite_code": "0005GH",
             "integral": "897008.00",
             "avatar": "http://img.tyitop.com/m_tyitop_com/avatar/user_1464569631755.jpg",
             "username": "沈夏冰",
             "job_type_name": "公共营养师",
             "mobile": "15951866618",
             "user_type": 1,
             "dietitian_id": "62",
             "balance": "0.01",
             "in_service": 1,
             "end_service": 0,
             "goods_reward": 0,
             "service_reward": "0.04",
             "sex": 0,
             "money": "1453.00",
             "have_service": 0,
             "service_dietitian": 0
      }
}
*
*
*
*
*
*
* */
//用戶基本信息
public class UserInfo {

    private String avatar;//头像

    @SerializedName("username")
    private String userName;//名称

    private String mobile;//手机
    private String integral;//积分
    private String balance;//收益余额
    private String money;//余额
     private  int  sex;//性别
      private  int have_service;//有无营养师
    private  String  invite_code;
    private  String service_dietitian;

    public String getService_dietitian() {
        return service_dietitian;
    }

    public void setService_dietitian(String service_dietitian) {
        this.service_dietitian = service_dietitian;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getHave_service() {
        return have_service;
    }

    public void setHave_service(int have_service) {
        this.have_service = have_service;
    }

    @SerializedName("favorite_good_total")
    private String favoriteGoodTotal;
    @SerializedName("favorite_store_total")
    private String favoriteStoreTotal;
    @SerializedName("favorite_dietitian_total")
    private String favoriteDietitianTotal;
    @SerializedName("favorite_article_total")
    private String favoriteArticleTotal;

    @SerializedName("job_type_name")
    private String jobTypeName;//营养师职业类型

    @SerializedName("user_type")
    private int userType;//用户角色类型

    @SerializedName("dietitian_id")
    private int dietitianId;//营养师id

    public String getAvatar() {
        return avatar;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getIntegral() {
        return integral;
    }

    public String getBalance() {
        return balance;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFavoriteGoodTotal() {
        return favoriteGoodTotal;
    }

    public String getFavoriteStoreTotal() {
        return favoriteStoreTotal;
    }

    public String getFavoriteDietitianTotal() {
        return favoriteDietitianTotal;
    }

    public String getFavoriteArticleTotal() {
        return favoriteArticleTotal;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public int getUserType() {
        return userType;
    }

    public int getDietitianId() {
        return dietitianId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setFavoriteGoodTotal(String favoriteGoodTotal) {
        this.favoriteGoodTotal = favoriteGoodTotal;
    }

    public void setFavoriteStoreTotal(String favoriteStoreTotal) {
        this.favoriteStoreTotal = favoriteStoreTotal;
    }

    public void setFavoriteDietitianTotal(String favoriteDietitianTotal) {
        this.favoriteDietitianTotal = favoriteDietitianTotal;
    }

    public void setFavoriteArticleTotal(String favoriteArticleTotal) {
        this.favoriteArticleTotal = favoriteArticleTotal;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setDietitianId(int dietitianId) {
        this.dietitianId = dietitianId;
    }
}
