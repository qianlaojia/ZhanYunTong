package com.dsgj.youyuntong.JavaBean.ThroughTrip.SearchTrip;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/16.
 * 邮箱：943332771@qq.com
 */

public class SearchResultImageBean {

    /**
     * thumb : https://dimg09.c-ctrip.com/images/tg/505/476/909/268394b2e9e54c66850aa352d2e63a87_R_1024_10000_Q90.jpg
     * photo : [{"url":"https://dimg05.c-ctrip.com/images/tg/688/824/821/6a844c37c9d54ef0921d7739872369aa_R_1024_10000_Q90.jpg"},{"url":"http://img2.baa.bitautotech.com/img/V2img2.baa.bitautotech.com/sdt/2012/11/16/3dcfd6bd3fb74412943c6b6b21154e7e_990_0_max_jpg.jpg"},{"url":"http://img1.baa.bitautotech.com/img/V2img2.baa.bitautotech.com/sdt/2012/11/16/822dfc58d940410a84f0bf2b2944eb81_990_0_max_jpg.jpg"},{"url":"http://s1.sinaimg.cn/mw690/001Q3cj1zy6LR54EpOwc0&690"},{"url":"http://s3.sinaimg.cn/mw690/001HS7lizy6Wsl0z8EW52&690"}]
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
         * url : https://dimg05.c-ctrip.com/images/tg/688/824/821/6a844c37c9d54ef0921d7739872369aa_R_1024_10000_Q90.jpg
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
