package com.dsgj.youyuntong.Utils.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;


import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图的相关显示：
 */

public class XBannerUtils {
    public static List<String> imageUrls;

    public XBannerUtils(List<String> imageUrls) {
        XBannerUtils.imageUrls = imageUrls;
    }

    //这个设置相关的显示的网络的地址：
    public static List<String> getImageUrl() {
        List<String> imageUrls = new ArrayList<>();
        String picPath = "http://fdfs.xmcdn.com/group27/M04/D4/24/wKgJW1j11VzTmYOeAAG9Mld0icA505_android_large.jpg";
        String picPath1 = "http://fdfs.xmcdn.com/group27/M0A/D4/81/wKgJR1j13gKTWVXaAALwrIB1AVY346_android_large.jpg";
        String picPath2 = "http://fdfs.xmcdn.com/group26/M05/D8/97/wKgJRlj13vqRHDmVAASRJaudX3I424_android_large.jpg";
        imageUrls.add(picPath);
        imageUrls.add(picPath1);
        imageUrls.add(picPath2);
        return imageUrls;
    }

    public static void setBannerHolder(final Context context, XBanner xbanner) {
        xbanner.setData(getImageUrl(), null);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(context).load(getImageUrl().get(position)).into((ImageView) view);
            }
        });
        /**
         * 这个可以对每个点击事件进行设置：9
         */
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(context, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static void setBannerHolderFromInternet(final Context context
            , XBanner xbanner,List<String>  imageUrl) {
        xbanner.setData(imageUrl, null);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(context).load(getImageUrl().get(position)).into((ImageView) view);
            }
        });
        /**
         * 这个可以对每个点击事件进行设置：9
         */
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(context, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
