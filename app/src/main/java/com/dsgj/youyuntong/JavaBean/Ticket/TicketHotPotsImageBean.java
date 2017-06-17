package com.dsgj.youyuntong.JavaBean.Ticket;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/12.
 * 邮箱：943332771@qq.com
 */

public class TicketHotPotsImageBean {
    /**
     * thumb : http://img.piaopianyi.com/scenery/20150427/160700_8638.jpg
     * photo : [{"url":"http://img.piaopianyi.com/scenery/20150427/160705_3326.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150427/160709_4107.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150427/160715_6920.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150427/160958_4888.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150427/161028_3013.jpg"}]
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
         * url : http://img.piaopianyi.com/scenery/20150427/160705_3326.jpg
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
