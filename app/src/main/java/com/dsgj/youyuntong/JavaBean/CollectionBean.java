package com.dsgj.youyuntong.JavaBean;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/23.
 * 邮箱：943332771@qq.com
 */

public class CollectionBean {


    /**
     * retCode : 200
     * retDese : 查找成功!
     * result : {"ticket":[{"product_id":"491","title":"郑州市青龙山温泉疗养院","product_code":"TK00491","summary":"【郑东新区】青龙山温泉度假村温泉洗浴+游泳套票","smeta":"{\"thumb\":\"http:\\/\\/www.xiaoniren.cn\\/upload\\/images\\/2016\\/12\\/201612301418064989.jpg\",\"photo\":[{\"url\":\"http:\\/\\/www.xiaoniren.cn\\/upload\\/images\\/2016\\/12\\/201612301418272166.jpg\"},{\"url\":\"http:\\/\\/www.xiaoniren.cn\\/upload\\/images\\/2016\\/12\\/201612301420206250.jpg\"},{\"url\":\"http:\\/\\/www.xiaoniren.cn\\/upload\\/images\\/2016\\/12\\/201612301420389584.jpg\"},{\"url\":\"http:\\/\\/www.xiaoniren.cn\\/upload\\/images\\/2016\\/12\\/201612301418476798.jpg\"},{\"url\":\"\"}]}","price":"68.00","city":"郑州市"}],"through":[{"product_id":"57","title":"翰园碑林+天波杨府+铁塔","product_code":"ZTC00000057","smeta":"{\"thumb\":\"http:\\/\\/image16-c.poco.cn\\/mypoco\\/myphoto\\/20141018\\/20\\/17372161220141018204417057_640.jpg\",\"photo\":[{\"url\":\"http:\\/\\/image16-c.poco.cn\\/mypoco\\/myphoto\\/20141018\\/20\\/17372161220141018204221027_640.jpg\"},{\"url\":\"http:\\/\\/www.mingzer.cn\\/tieshi_pic\\/henan_yangfu1.jpg\"},{\"url\":\"http:\\/\\/i5.bbs.fd.zol-img.com.cn\\/t_s1200x5000\\/g5\\/M00\\/00\\/0F\\/ChMkJledTQSIDwYOACb3FgvQoTIAAUBCQDYV1EAJvcu455.jpg\"},{\"url\":\"http:\\/\\/www.mingzer.cn\\/tieshi_pic\\/henan_yangfu2.jpg\"},{\"url\":\"http:\\/\\/i3.bbs.fd.zol-img.com.cn\\/t_s1200x5000\\/g5\\/M00\\/00\\/0F\\/ChMkJledTNWIBla5ACHRpXpixFQAAUBCAKR-JEAIdG9519.jpg\"}]}","price":"140.00","from_city":"许昌中心站","to_city":"开封"}],"tourism":[{"product_id":"5","title":"陕西西安法门寺、乾陵（永泰公主墓或懿德太子墓）、茂陵精品一日游B","product_code":"TP00005","summary":"法门寺、乾陵（永泰公主墓或懿德太子墓）、茂陵","smeta":"{\"thumb\":\"https:\\/\\/ss0.bdstatic.com\\/70cFvHSh_Q1YnxGkpoWK1HF6hhy\\/it\\/u=2005011658,2476803384&fm=26&gp=0.jpg\",\"photo\":[{\"url\":\"https:\\/\\/timgsa.baidu.com\\/timg?image&quality=80&size=b9999_10000&sec=1497004549591&di=d88eb9cb95166fda6034359c1fb3c047&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D1893241293%2C2307406675%26fm%3D214%26gp%3D0.jpg\"},{\"url\":\"https:\\/\\/timgsa.baidu.com\\/timg?image&quality=80&size=b9999_10000&sec=1497004345380&di=d90f88438e65dc8066f1d07d8b6844b9&imgtype=0&src=http%3A%2F%2Fdimg05.c-ctrip.com%2Fimages%2Ffd%2Ftg%2Fg3%2FM0B%2FA6%2F68%2FCggYGVaWUfqAWE25ABRIbo-QMSU763.jpg\"},{\"url\":\"https:\\/\\/timgsa.baidu.com\\/timg?image&quality=80&size=b9999_10000&sec=1497004605422&di=911dbdc32ee30d15590d14c6d9d3536d&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140515%2F9129085_085904580125_2.jpg\"},{\"url\":\"https:\\/\\/timgsa.baidu.com\\/timg?image&quality=80&size=b9999_10000&sec=1497004840505&di=e4adc5a8eb579e655738f43d69d5a876&imgtype=0&src=http%3A%2F%2Ffile21.mafengwo.net%2FM00%2FBD%2F79%2FwKgB3FEkisKAPks8ABEPJR56--A43.jpeg\"},{\"url\":\"https:\\/\\/timgsa.baidu.com\\/timg?image&quality=80&size=b9999_10000&sec=1497004840513&di=875e850849036c61cc3d560a7379ad88&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1401%2F24%2Fc2%2F30838578_30838578_1390545936733.jpg\"}]}","price":"330.00","city":"西安市"}]}
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
        private List<TicketBean> ticket;
        private List<ThroughBean> through;
        private List<TourismBean> tourism;

        public List<TicketBean> getTicket() {
            return ticket;
        }

        public void setTicket(List<TicketBean> ticket) {
            this.ticket = ticket;
        }

        public List<ThroughBean> getThrough() {
            return through;
        }

        public void setThrough(List<ThroughBean> through) {
            this.through = through;
        }

        public List<TourismBean> getTourism() {
            return tourism;
        }

        public void setTourism(List<TourismBean> tourism) {
            this.tourism = tourism;
        }

        public static class TicketBean {
            /**
             * product_id : 491
             * title : 郑州市青龙山温泉疗养院
             * product_code : TK00491
             * summary : 【郑东新区】青龙山温泉度假村温泉洗浴+游泳套票
             * smeta : {"thumb":"http:\/\/www.xiaoniren.cn\/upload\/images\/2016\/12\/201612301418064989.jpg","photo":[{"url":"http:\/\/www.xiaoniren.cn\/upload\/images\/2016\/12\/201612301418272166.jpg"},{"url":"http:\/\/www.xiaoniren.cn\/upload\/images\/2016\/12\/201612301420206250.jpg"},{"url":"http:\/\/www.xiaoniren.cn\/upload\/images\/2016\/12\/201612301420389584.jpg"},{"url":"http:\/\/www.xiaoniren.cn\/upload\/images\/2016\/12\/201612301418476798.jpg"},{"url":""}]}
             * price : 68.00
             * city : 郑州市
             */

            private String product_id;
            private String title;
            private String product_code;
            private String summary;
            private String smeta;
            private String price;
            private String city;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getProduct_code() {
                return product_code;
            }

            public void setProduct_code(String product_code) {
                this.product_code = product_code;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getSmeta() {
                return smeta;
            }

            public void setSmeta(String smeta) {
                this.smeta = smeta;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }

        public static class ThroughBean {
            /**
             * product_id : 57
             * title : 翰园碑林+天波杨府+铁塔
             * product_code : ZTC00000057
             * smeta : {"thumb":"http:\/\/image16-c.poco.cn\/mypoco\/myphoto\/20141018\/20\/17372161220141018204417057_640.jpg","photo":[{"url":"http:\/\/image16-c.poco.cn\/mypoco\/myphoto\/20141018\/20\/17372161220141018204221027_640.jpg"},{"url":"http:\/\/www.mingzer.cn\/tieshi_pic\/henan_yangfu1.jpg"},{"url":"http:\/\/i5.bbs.fd.zol-img.com.cn\/t_s1200x5000\/g5\/M00\/00\/0F\/ChMkJledTQSIDwYOACb3FgvQoTIAAUBCQDYV1EAJvcu455.jpg"},{"url":"http:\/\/www.mingzer.cn\/tieshi_pic\/henan_yangfu2.jpg"},{"url":"http:\/\/i3.bbs.fd.zol-img.com.cn\/t_s1200x5000\/g5\/M00\/00\/0F\/ChMkJledTNWIBla5ACHRpXpixFQAAUBCAKR-JEAIdG9519.jpg"}]}
             * price : 140.00
             * from_city : 许昌中心站
             * to_city : 开封
             */

            private String product_id;
            private String title;
            private String product_code;
            private String smeta;
            private String price;
            private String from_city;
            private String to_city;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getProduct_code() {
                return product_code;
            }

            public void setProduct_code(String product_code) {
                this.product_code = product_code;
            }

            public String getSmeta() {
                return smeta;
            }

            public void setSmeta(String smeta) {
                this.smeta = smeta;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getFrom_city() {
                return from_city;
            }

            public void setFrom_city(String from_city) {
                this.from_city = from_city;
            }

            public String getTo_city() {
                return to_city;
            }

            public void setTo_city(String to_city) {
                this.to_city = to_city;
            }
        }

        public static class TourismBean {
            /**
             * product_id : 5
             * title : 陕西西安法门寺、乾陵（永泰公主墓或懿德太子墓）、茂陵精品一日游B
             * product_code : TP00005
             * summary : 法门寺、乾陵（永泰公主墓或懿德太子墓）、茂陵
             * smeta : {"thumb":"https:\/\/ss0.bdstatic.com\/70cFvHSh_Q1YnxGkpoWK1HF6hhy\/it\/u=2005011658,2476803384&fm=26&gp=0.jpg","photo":[{"url":"https:\/\/timgsa.baidu.com\/timg?image&quality=80&size=b9999_10000&sec=1497004549591&di=d88eb9cb95166fda6034359c1fb3c047&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D1893241293%2C2307406675%26fm%3D214%26gp%3D0.jpg"},{"url":"https:\/\/timgsa.baidu.com\/timg?image&quality=80&size=b9999_10000&sec=1497004345380&di=d90f88438e65dc8066f1d07d8b6844b9&imgtype=0&src=http%3A%2F%2Fdimg05.c-ctrip.com%2Fimages%2Ffd%2Ftg%2Fg3%2FM0B%2FA6%2F68%2FCggYGVaWUfqAWE25ABRIbo-QMSU763.jpg"},{"url":"https:\/\/timgsa.baidu.com\/timg?image&quality=80&size=b9999_10000&sec=1497004605422&di=911dbdc32ee30d15590d14c6d9d3536d&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140515%2F9129085_085904580125_2.jpg"},{"url":"https:\/\/timgsa.baidu.com\/timg?image&quality=80&size=b9999_10000&sec=1497004840505&di=e4adc5a8eb579e655738f43d69d5a876&imgtype=0&src=http%3A%2F%2Ffile21.mafengwo.net%2FM00%2FBD%2F79%2FwKgB3FEkisKAPks8ABEPJR56--A43.jpeg"},{"url":"https:\/\/timgsa.baidu.com\/timg?image&quality=80&size=b9999_10000&sec=1497004840513&di=875e850849036c61cc3d560a7379ad88&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1401%2F24%2Fc2%2F30838578_30838578_1390545936733.jpg"}]}
             * price : 330.00
             * city : 西安市
             */

            private String product_id;
            private String title;
            private String product_code;
            private String summary;
            private String smeta;
            private String price;
            private String city;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getProduct_code() {
                return product_code;
            }

            public void setProduct_code(String product_code) {
                this.product_code = product_code;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getSmeta() {
                return smeta;
            }

            public void setSmeta(String smeta) {
                this.smeta = smeta;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
