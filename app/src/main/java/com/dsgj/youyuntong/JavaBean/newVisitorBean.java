package com.dsgj.youyuntong.JavaBean;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/21.
 * 邮箱：943332771@qq.com
 */

public class newVisitorBean {

    /**
     * retCode : 200
     * retDese : 游客列表查询成功!
     * result : [{"contact_id":"55","user_id":"2336","contact_name":"","idnumber":""},{"contact_id":"56","user_id":"2336","contact_name":"张云浩","idnumber":"412702198705052771"}]
     */

    private int retCode;
    private String retDese;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * contact_id : 55
         * user_id : 2336
         * contact_name :
         * idnumber :
         */

        private String contact_id;
        private String user_id;
        private String contact_name;
        private String idnumber;

        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
            this.contact_id = contact_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getIdnumber() {
            return idnumber;
        }

        public void setIdnumber(String idnumber) {
            this.idnumber = idnumber;
        }
    }
}
