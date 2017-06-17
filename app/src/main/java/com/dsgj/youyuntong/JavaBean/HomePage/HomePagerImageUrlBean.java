package com.dsgj.youyuntong.JavaBean.HomePage;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/9.
 * 邮箱：943332771@qq.com
 */

public class HomePagerImageUrlBean {

    /**
     * thumb : https://dimg04.c-ctrip.com/images/30040a0000004r0kfEC08_C_500_280.jpg
     * photo : [{"url":"https://dimg04.c-ctrip.com/images/300i0a0000004r176380B_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/300p0a0000004rtzf27B3_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/300e0a0000004rtt2A635_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/30090a0000004rtg84EC8_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/30040a0000004r0kgFBE7_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/30040a0000004r0kfEC08_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/300p0a0000004r0xaEE59_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/300m0a0000004r0w9FA92_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/300o0a0000004r0wk294F_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/300m0a0000004r19xAEEE_C_500_280.jpg","alt":""},{"url":"https://dimg04.c-ctrip.com/images/300d0a0000004r0wq13F1_C_500_280.jpg","alt":""}]
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
         * url : https://dimg04.c-ctrip.com/images/300i0a0000004r176380B_C_500_280.jpg
         * alt :
         */

        private String url;
        private String alt;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }
    }
}
