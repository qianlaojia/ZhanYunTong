package com.dsgj.youyuntong.JavaBean.ProductDetail;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/17.
 * 邮箱：943332771@qq.com
 */

public class ProductDetailImageBean {

    /**
     * thumb : http://img.piaopianyi.com/scenery/20170602/104840_8785.jpg
     * photo : [{"url":"http://img.piaopianyi.com/scenery/20170602/095510_2222.jpg"},{"url":"http://img.piaopianyi.com/scenery/20170602/104847_7691.jpg"},{"url":"http://img.piaopianyi.com/scenery/20170602/095527_5191.jpg"},{"url":"http://img.piaopianyi.com/scenery/20170602/102543_0972.png"},{"url":"http://img.piaopianyi.com/scenery/20170602/102113_6285.jpg"}]
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
         * url : http://img.piaopianyi.com/scenery/20170602/095510_2222.jpg
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
