package com.dsgj.youyuntong.JavaBean;

/**
 * TODO:
 * Created by 张云浩  on 2017/5/18.
 * 邮箱：943332771@qq.com
 */

public class PhoneVerificationBean {

    /**
     * retCode : 0
     * retDese : 验证码已发送!
     * result :
     */

    private int retCode;
    private String retDese;
    private String result;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetDese() {
        return retDese;
    }

    public void setRetDese(String retDese) {
        this.retDese = retDese;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
