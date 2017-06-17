package com.dsgj.youyuntong.JavaBean.GroupTour;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/14.
 * 邮箱：943332771@qq.com
 */

public class HotPotImageBean {

    /**
     * thumb : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2005011658,2476803384&fm=26&gp=0.jpg
     * photo : [{"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497004549591&di=d88eb9cb95166fda6034359c1fb3c047&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D1893241293%2C2307406675%26fm%3D214%26gp%3D0.jpg"},{"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497004345380&di=d90f88438e65dc8066f1d07d8b6844b9&imgtype=0&src=http%3A%2F%2Fdimg05.c-ctrip.com%2Fimages%2Ffd%2Ftg%2Fg3%2FM0B%2FA6%2F68%2FCggYGVaWUfqAWE25ABRIbo-QMSU763.jpg"},{"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497004605422&di=911dbdc32ee30d15590d14c6d9d3536d&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140515%2F9129085_085904580125_2.jpg"},{"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497004840505&di=e4adc5a8eb579e655738f43d69d5a876&imgtype=0&src=http%3A%2F%2Ffile21.mafengwo.net%2FM00%2FBD%2F79%2FwKgB3FEkisKAPks8ABEPJR56--A43.jpeg"},{"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497004840513&di=875e850849036c61cc3d560a7379ad88&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1401%2F24%2Fc2%2F30838578_30838578_1390545936733.jpg"}]
     */

    private String thumb;
    private List<PhotoBean> photo;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public List<PhotoBean> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoBean> photo) {
        this.photo = photo;
    }

    public static class PhotoBean {
        /**
         * url : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497004549591&di=d88eb9cb95166fda6034359c1fb3c047&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D1893241293%2C2307406675%26fm%3D214%26gp%3D0.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
