package com.dsgj.youyuntong.base;

/**
 * TODO:
 * Created by 张云浩  on 2017/5/26.
 * 邮箱：943332771@qq.com
 */

public class BaseJavaBean {
    /**
     * retCode : 403
     * retDese : 当前状态拒绝访问!
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
