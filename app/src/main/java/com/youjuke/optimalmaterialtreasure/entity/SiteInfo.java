package com.youjuke.optimalmaterialtreasure.entity;

/**
 * 描述：工地地址的实体
 * author：zyb
 * Created by Administrator on 2017/2/16.
 */

public class SiteInfo {

    private String address;
    private int gd_id;
    private String start_time;
    private String yezhu_mobile;
    private String yezhu_name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGd_id() {
        return gd_id;
    }

    public void setGd_id(int gd_id) {
        this.gd_id = gd_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getYezhu_mobile() {
        return yezhu_mobile;
    }

    public void setYezhu_mobile(String yezhu_mobile) {
        this.yezhu_mobile = yezhu_mobile;
    }

    public String getYezhu_name() {
        return yezhu_name;
    }

    public void setYezhu_name(String yezhu_name) {
        this.yezhu_name = yezhu_name;
    }
}
