package com.dsgj.youyuntong.Utils.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * gridview要显示的资源
 * Created by zhangyunhao on 2017/4/24.
 */

public class LinearLayoutUtils {
    private static List<Map<String, Object>> lloutlayList;

    public static List<Map<String, Object>> getStringAndLogo() {
        lloutlayList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("title", "门票");
        map.put("pic", R.mipmap.service_mp);
        lloutlayList.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("title", "直通车");
        map1.put("pic", R.mipmap.service_ztc);
        lloutlayList.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("title", "跟团游");
        map2.put("pic", R.mipmap.service_ly);
        lloutlayList.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("title", "导游");
        map3.put("pic", R.mipmap.service_df);
        lloutlayList.add(map3);
        return lloutlayList;
    }


    public static void setFourSelect(final Context context, LinearLayout linearLayout) {
        for (int i =0  ; i < getStringAndLogo().size(); i++) {

            //初始化view
            View view = View.inflate(context, R.layout.item_four_select, null);
            ImageView imageview = (ImageView) view.findViewById(R.id.iv_logo);
            TextView textView = (TextView) view.findViewById(R.id.tv_explain);

            //加载图片
            Glide.with(context)
                    .load(getStringAndLogo().get(i).get("pic"))
                    .into(imageview);

            //加载图片下的说明
            textView.setText(getStringAndLogo().get(i).get("title").toString());

            //布局设置一下
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            params.setMargins(24, 0, 24, 0);
           view.setTag(i);
            final int finalI = i;
            //设置每一个栏目的监听
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.show(context, "第" + finalI + "被点击");
                }
            });

            linearLayout.addView(view);
        }
    }
}
