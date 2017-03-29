package com.youjuke.optimalmaterialtreasure.entity;

/**
 * 描述: 创建订单需要ID和留言即可
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-03-27 14:35
 */
public class createOrderInfo {
    String mat_id;
    String mat_txt;

    public String getMat_id() {
        return mat_id;
    }

    public void setMat_id(String mat_id) {
        this.mat_id = mat_id;
    }

    public String getMat_txt() {
        return mat_txt;
    }

    public void setMat_txt(String mat_txt) {
        this.mat_txt = mat_txt;
    }
}
