package com.wokun.dset.model;

import com.google.gson.annotations.SerializedName;

/*
*
*app用户登录
*
{
      "state": true,
      "stateCode": "00001",
      "msg": "登陆成功",
      "data":
      {
             "user_id": "1697",
             "user_type": "0",
             "access_token": "Md39-kTwMJDQAttgM39egeoxWV3WGNAl",
             "expire_time": 1512033839
      }
}
*
*
*
*
*
* */

public class User {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("user_type")
    private int userType;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expire_time")
    private int expireTime;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getUserId() {
        return userId;
    }

    public int getUserType() {
        return userType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpireTime() {
        return expireTime;
    }
}