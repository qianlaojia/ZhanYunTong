package com.dsgj.youyuntong.Utils.view;

import android.content.Context;
import android.opengl.Visibility;
import android.view.View;
import android.widget.ImageView;

import com.jauker.widget.BadgeView;


/**
 * 图片的右上角的红点提示的实现
 * Created by zhangyunhao on 2017/4/22.
 * 更新时间：20170422
 */

public class RedPotUtils {
    private Context mContext;
    private BadgeView sBadgeView;

    public RedPotUtils(Context context,BadgeView sBadgeView) {
        this.mContext = context;
        this.sBadgeView=sBadgeView;
    }



    /**
     * 显示ImageView类的右上角红色的提示
     *
     * @param imageView Want to show image;
     * @param number    The number of message;
     */
    public void onlyShowPot(ImageView imageView, int number) {
        sBadgeView = new BadgeView(mContext);
        sBadgeView.setVisibility(View.GONE);
        sBadgeView.setTargetView(imageView);
        sBadgeView.setBadgeCount(number);

    }


}
