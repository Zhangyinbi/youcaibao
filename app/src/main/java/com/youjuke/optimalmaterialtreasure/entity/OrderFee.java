package com.youjuke.optimalmaterialtreasure.entity;

/**
 * 描述: 选择地址后 返回费用
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-03-24 14:31
 */
public class OrderFee {

    /**
     * total_shipping_fee : 600
     * total_stair_fee : 200
     * all_total_price : 800.00
     */

    private String total_shipping_fee;
    private String total_stair_fee;
    private String all_total_price;

    public String getTotal_shipping_fee() {
        return total_shipping_fee;
    }

    public void setTotal_shipping_fee(String total_shipping_fee) {
        this.total_shipping_fee = total_shipping_fee;
    }

    public String getTotal_stair_fee() {
        return total_stair_fee;
    }

    public void setTotal_stair_fee(String total_stair_fee) {
        this.total_stair_fee = total_stair_fee;
    }

    public String getAll_total_price() {
        return all_total_price;
    }

    public void setAll_total_price(String all_total_price) {
        this.all_total_price = all_total_price;
    }
}
