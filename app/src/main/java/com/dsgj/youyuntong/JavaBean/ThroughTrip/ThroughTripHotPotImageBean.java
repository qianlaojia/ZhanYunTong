package com.dsgj.youyuntong.JavaBean.ThroughTrip;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/13.
 * 邮箱：943332771@qq.com
 */

public class ThroughTripHotPotImageBean {

    /**
     * thumb : http://img.piaopianyi.com/scenery/20150422/150900_9218.jpg
     * photo : [{"url":"http://img.piaopianyi.com/scenery/20150422/150903_3593.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150422/150906_9375.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150422/150909_7656.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150422/151010_6250.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150422/151005_5625.jpg"}]
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
         * url : http://img.piaopianyi.com/scenery/20150422/150903_3593.jpg
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
