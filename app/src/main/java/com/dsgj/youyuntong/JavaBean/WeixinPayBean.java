package com.dsgj.youyuntong.JavaBean;

import com.google.gson.annotations.SerializedName;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/28.
 * 邮箱：943332771@qq.com
 */

public class WeixinPayBean {

    /**
     * appid : wxcf03d9aa82b2caf8
     * partnerid : 1480845162
     * prepayid : wx201706281414257739c618c90604894813
     * package : Sign=WXPay
     * noncestr : BvNamj6BELRpTSjO
     * timestamp : 1498630465
     * sign : 6C2C0F4421D2DA8C5A0964665593714E
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private int timestamp;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
