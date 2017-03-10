package com.youjuke.optimalmaterialtreasure.wxapi;

import com.google.gson.annotations.SerializedName;

/**
 * 描述:
 * <p>
 * 工程:
 * #0000    Tian Xiao    2016-11-15 17:22
 */
public class WxPayEntity {


    /**
     * sign : 0F07107657422361374812A2FDA3F5C5
     * timestamp : 1487758848
     * package : Sign=WXPay
     * noncestr : KJ3DBqNNRBCQIMHNMol1EE22caBbgJZQ
     * partnerid : 1440837802
     * appid : wxfe75f73496c8bb4a
     * prepayid : wx201702221820488380f624480961830002
     */

    private String sign;
    private String timestamp;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private String partnerid;
    private String appid;
    private String prepayid;

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

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }
}
