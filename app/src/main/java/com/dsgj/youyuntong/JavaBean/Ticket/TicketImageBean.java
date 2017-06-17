package com.dsgj.youyuntong.JavaBean.Ticket;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/12.
 * 邮箱：943332771@qq.com
 */

public class TicketImageBean {
    /**
     * thumb : http://img.piaopianyi.com/scenery/20150420/154342_9062.jpg
     * photo : [{"url":"http://img.piaopianyi.com/scenery/20150420/154336_6406.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150420/154325_0000.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150420/154318_0937.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150420/154134_0781.jpg"},{"url":"http://img.piaopianyi.com/scenery/20150420/154131_4218.jpg"}]
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
         * url : http://img.piaopianyi.com/scenery/20150420/154336_6406.jpg
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
