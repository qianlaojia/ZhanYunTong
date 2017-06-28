package com.dsgj.youyuntong.JavaBean.InternetDataBean;

import android.content.Context;

import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.activity.OrderInputInfoActivity;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/27.
 * 邮箱：943332771@qq.com
 */

public class OrderCreateBean {
    /*type	字符串	登陆类型（weixin/phone）
access_token    (第三方登陆)	字符串	第三方登陆令牌
userName(手机登陆)	字符串	用户手机号
token	字符串	接口安全验证令牌
product_code	字符串	产品编码
traveler	字符串	出行游客
contact_name	字符串	联系人姓名
telephone	字符串	联系人电话
date	字符串	出行时间
number	整型	出行人数*/
    private String type;
    private String access_token;
    private String userName;
    private String token;
    private String product_code;
    private String traveler;
    private String contact_name;
    private String telephone;
    private String date;
    private int number;
    private Context mContext;

    public OrderCreateBean(Context context) {
        this.mContext = context;
    }

    public String getType() {
        return "phone";
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccess_token() {
        return "";
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }



    public String getUserName() {
        return SPUtils.with(mContext).get("userName","");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return SPUtils.with(mContext).get("token","");
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getTraveler() {
        return SPUtils.with(mContext).get("ChooseVisitor","");
    }

    public void setTraveler(String traveler) {
        this.traveler = traveler;
    }

    public String getContact_name() {
        return SPUtils.with(mContext).get("realName", "");
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
