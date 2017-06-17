package com.dsgj.youyuntong.JavaBean.GroupTour;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/15.
 * 邮箱：943332771@qq.com
 */

public class AroundTripImageBean {

    /**
     * thumb : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497601025&di=a51b5482d967d87cc5ff665ac850095b&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1405%2F03%2Fc4%2F33811002_33811002_1399099520578.jpg
     * photo : [{"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497006299590&di=5d88a4913ec77305910a178e12b6e847&imgtype=0&src=http%3A%2F%2Fs1.lvjs.com.cn%2Fuploads%2Fpc%2Fplace2%2F2015-09-15%2F6beea0df-506f-4e5a-b2fa-8341aabef378.jpg"},{"url":"http://userimage2.360doc.com/12/0725/07/2556131_201207250754160540.jpg"},{"url":"http://k.zol-img.com.cn/dcbbs/15550/a15549325_01000.jpg"},{"url":"http://file31.mafengwo.net/M00/F2/0A/wKgBs1bqUqGAHpLDAAgKZl72AWc38.groupinfo.w680.jpeg"},{"url":"http://youimg1.c-ctrip.com/target/100l090000003o6ztF5FF.jpg"}]
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
         * url : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497006299590&di=5d88a4913ec77305910a178e12b6e847&imgtype=0&src=http%3A%2F%2Fs1.lvjs.com.cn%2Fuploads%2Fpc%2Fplace2%2F2015-09-15%2F6beea0df-506f-4e5a-b2fa-8341aabef378.jpg
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
