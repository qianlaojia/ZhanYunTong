package com.dsgj.youyuntong.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dsgj.youyuntong.R;

/**
 * TODO:
 * Created by 张云浩  on 2017/5/17.
 * 邮箱：943332771@qq.com
 */

public class MyPopuWindow extends PopupWindow {

    private View mMyView;
    private TextView mFromCamera;
    private TextView mFromAlbum;
    private TextView mCancle;


    public MyPopuWindow(Context context, View.OnClickListener onClickListener) {
        super(context);
        //初始化view

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMyView = layoutInflater.inflate(R.layout.popuwindowgetimage, null);
        //找到这个初始化view中的控件
        mFromCamera = (TextView) mMyView.findViewById(R.id.tv_from_camera);
        mFromAlbum = (TextView) mMyView.findViewById(R.id.tv_from_album);
        mCancle = (TextView) mMyView.findViewById(R.id.tv_cancel);
        //设置点击事件
        mFromCamera.setOnClickListener(onClickListener);
        mFromAlbum.setOnClickListener(onClickListener);
        mCancle.setOnClickListener(onClickListener);
        //设置布局
        this.setContentView(mMyView);
        //设置高宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);//宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);//高
        //可获得焦点
        this.setFocusable(true);
        //背景颜色
        this.setBackgroundDrawable(new ColorDrawable(0xb0eeeeee));
        //设置触摸别的区域的时候 消失
        mMyView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int high = mMyView.findViewById(R.id.ll_get_head_image).getTop();//上边界的位置
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < high) {

                        dismiss();
                    }
                }
                return true;
            }
        });
    }

}
