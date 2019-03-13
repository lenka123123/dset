package com.example.baselib.net.net;

public class User {

    public String sign;
    public String timestamp;
    public String username;
    public String password;

    public User(String sign, String timestamp, String username, String password) {
        this.sign = sign;
        this.timestamp = timestamp;
        this.username = username;
        this.password = password;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
