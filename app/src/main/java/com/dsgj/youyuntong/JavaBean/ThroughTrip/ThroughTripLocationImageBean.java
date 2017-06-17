package com.dsgj.youyuntong.JavaBean.ThroughTrip;

import com.dsgj.youyuntong.Utils.log.LogUtils;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/13.
 * 邮箱：943332771@qq.com
 */

public class ThroughTripLocationImageBean {

    /**
     * thumb : https://dimg06.c-ctrip.com/images/tg/728/824/132/16ee04fcf702419e90b8018fa78e2ade_R_1024_10000_Q90.jpg
     * photo : [{"url":"https://dimg02.c-ctrip.com/images/tg/195/491/826/cdc820a463db481a872d7226befd4e17_R_1024_10000_Q90.jpg"},{"url":"http://image16-c.poco.cn/mypoco/myphoto/20141018/20/17372161220141018204417057_640.jpg"},{"url":"http://image16-c.poco.cn/mypoco/myphoto/20141018/20/17372161220141018204221027_640.jpg"},{"url":"http://i3.bbs.fd.zol-img.com.cn/t_s1200x5000/g3/M06/01/07/Cg-4V1JnlZiIK3IwAAmVsbUzUb4AAMuzgLrw9QACZXJ439.jpg"},{"url":"http://i2.bbs.fd.zol-img.com.cn/t_s1200x5000/g4/M09/0B/07/Cg-4WlJmAViIax0VAAy9zbjPVn8AAMsuwDkKGMADL3l998.jpg"}]
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
         * url : https://dimg02.c-ctrip.com/images/tg/195/491/826/cdc820a463db481a872d7226befd4e17_R_1024_10000_Q90.jpg
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
