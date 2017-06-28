package com.dsgj.youyuntong.JavaBean;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/28.
 * 邮箱：943332771@qq.com
 */

public class OrderDetailBean {

    /**
     * retCode : 200
     * retDese : 成功生成订单
     * result : {"order_id":"64","order_sn":"20170628095706661076","goods_name":"嵩顶滑雪场全季卡","user_id":"2336","order_status":"待支付","pay_status":"未支付","city":"郑州市","contact_name":"张云浩","tel":"13623717683","traveler":"[{\"contact_id\":\"63\",\"contact_name\":\"99\",\"idnumber\":\"412702198705052771\",\"user_id\":\"2336\"},{\"contact_id\":\"72\",\"contact_name\":\"张张\",\"idnumber\":\"412702198705052771\",\"user_id\":\"2336\"},{\"contact_id\":\"73\",\"contact_name\":\"字体\",\"idnumber\":\"412702198705052771\",\"user_id\":\"2336\"}]","price":"2299.00","cost_price":"2279.00","total":"6897.00","date":"2017-06-28","add_time":"1498615026","pay_time":null,"trade_no":null,"refund_no":null,"product_code":"TK00012","product_id":"12","payment":null,"pay_source":"APP","product_type":"门票","verify_code":null,"verify_time":null,"is_show":"1"}
     */

    private int retCode;
    private String retDese;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * order_id : 64
         * order_sn : 20170628095706661076
         * goods_name : 嵩顶滑雪场全季卡
         * user_id : 2336
         * order_status : 待支付
         * pay_status : 未支付
         * city : 郑州市
         * contact_name : 张云浩
         * tel : 13623717683
         * traveler : [{"contact_id":"63","contact_name":"99","idnumber":"412702198705052771","user_id":"2336"},{"contact_id":"72","contact_name":"张张","idnumber":"412702198705052771","user_id":"2336"},{"contact_id":"73","contact_name":"字体","idnumber":"412702198705052771","user_id":"2336"}]
         * price : 2299.00
         * cost_price : 2279.00
         * total : 6897.00
         * date : 2017-06-28
         * add_time : 1498615026
         * pay_time : null
         * trade_no : null
         * refund_no : null
         * product_code : TK00012
         * product_id : 12
         * payment : null
         * pay_source : APP
         * product_type : 门票
         * verify_code : null
         * verify_time : null
         * is_show : 1
         */

        private String order_id;
        private String order_sn;
        private String goods_name;
        private String user_id;
        private String order_status;
        private String pay_status;
        private String city;
        private String contact_name;
        private String tel;
        private String traveler;
        private String price;
        private String cost_price;
        private String total;
        private String date;
        private String add_time;
        private Object pay_time;
        private Object trade_no;
        private Object refund_no;
        private String product_code;
        private String product_id;
        private Object payment;
        private String pay_source;
        private String product_type;
        private Object verify_code;
        private Object verify_time;
        private String is_show;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTraveler() {
            return traveler;
        }

        public void setTraveler(String traveler) {
            this.traveler = traveler;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCost_price() {
            return cost_price;
        }

        public void setCost_price(String cost_price) {
            this.cost_price = cost_price;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public Object getPay_time() {
            return pay_time;
        }

        public void setPay_time(Object pay_time) {
            this.pay_time = pay_time;
        }

        public Object getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(Object trade_no) {
            this.trade_no = trade_no;
        }

        public Object getRefund_no() {
            return refund_no;
        }

        public void setRefund_no(Object refund_no) {
            this.refund_no = refund_no;
        }

        public String getProduct_code() {
            return product_code;
        }

        public void setProduct_code(String product_code) {
            this.product_code = product_code;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public Object getPayment() {
            return payment;
        }

        public void setPayment(Object payment) {
            this.payment = payment;
        }

        public String getPay_source() {
            return pay_source;
        }

        public void setPay_source(String pay_source) {
            this.pay_source = pay_source;
        }

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
        }

        public Object getVerify_code() {
            return verify_code;
        }

        public void setVerify_code(Object verify_code) {
            this.verify_code = verify_code;
        }

        public Object getVerify_time() {
            return verify_time;
        }

        public void setVerify_time(Object verify_time) {
            this.verify_time = verify_time;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }
    }
}
