package com.youjuke.optimalmaterialtreasure.entity;

import java.io.Serializable;

/**
 * 描述：个人信息的实体类
 * author：zyb
 * Created by Administrator on 2017/2/15.
 */

public class MeInfo implements Serializable{
    private String account_money;
    private String logo;
    private String username;
    private String company_name;

    public String getAccount_money() {
        return account_money;
    }

    public void setAccount_money(String account_money) {
        this.account_money = account_money;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
