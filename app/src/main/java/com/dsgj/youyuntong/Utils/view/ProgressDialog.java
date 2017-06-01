package com.dsgj.youyuntong.Utils.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;


/**
 * 提醒小菊花("加载中。。。")
 * http://blog.csdn.net/u014600432/article/details/42714021#
 *
 * @author Cong
 */
public class ProgressDialog extends Dialog {

    private ImageView img;
    private TextView txt;
    private Animation anim;

    public ProgressDialog(Context context) {
        super(context, R.style.DialogProgress);
        // 加载布局文件
        View view = View.inflate(context, R.layout.dialog_progress, null);
        img = (ImageView) view.findViewById(R.id.iv);
        txt = (TextView) view.findViewById(R.id.tv);
        anim = AnimationUtils.loadAnimation(context, R.anim.dialog_progress);
        img.setAnimation(anim);
        txt.setText(R.string.dialog_progress);
        getWindow().setBackgroundDrawableResource(R.mipmap.bg_dialog_progress);
        // dialog添加视图
        setContentView(view);
        this.setCancelable(false);

    }

    /**
     * 对话框设置内容
     *
     * @param msg
     */
    public void setMsg(String msg) {
        txt.setText(msg);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        txt.setText(title);
    }

    /**
     * 设置对话框背景
     *
     * @param msgId
     */
    public void setMsg(int msgId) {
        txt.setText(msgId);
    }

    /**
     * 显示对话框
     */
    public void showProgersssDialog() {
        this.show();
    }

    @Override
    public void show() {
        img.setAnimation(anim);
        super.show();
    }

    /**
     * 关闭对话框
     */
    public void closeProgersssDialog() {
        this.dismiss();
    }

}