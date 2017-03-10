package com.youjuke.optimalmaterialtreasure.entity;

import java.io.Serializable;

/**
 * 描述：用户信息
 * author：zyb
 * Created by Administrator on 2017/2/10.
 */
public class User implements Serializable {
    private String username;
    private int id;
    private String mobile;
    private String token;
    private String has_paypwd;

    public User(String username, int id, String mobile, String token, String has_paypwd) {
        this.username = username;
        this.id = id;
        this.mobile = mobile;
        this.token = token;
        this.has_paypwd = has_paypwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHas_paypwd() {
        return has_paypwd;
    }

    public void setHas_paypwd(String has_paypwd) {
        this.has_paypwd = has_paypwd;
    }
}
