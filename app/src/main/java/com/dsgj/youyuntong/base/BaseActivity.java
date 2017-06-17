package com.dsgj.youyuntong.base;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * TODO:
 * Created by 张云浩  on 2017/4/19.
 * 邮箱：943332771@qq.com
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private BaseActivity ctx;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.GRAY);
        }
        setContentView(getLayoutID());
       decorView = getWindow().getDecorView();


        //处理公共逻辑
        dealCommonPart();
        initView();
        initData();
        initListener();
    }

    /**
     * 获取布局的id
     *
     * @return 布局id
     */
    protected abstract int getLayoutID();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    private void dealCommonPart() {
        this.ctx = this;

    }


    /**
     * 不带参数跳转到clz的Activity
     *
     * @param clz Activity名称
     */
    public void jumpToActivity(Class<? extends BaseActivity> clz) {
        jumpToActivity(clz, null);
    }

    /**
     * （可调用方法）携带参数跳转界面
     *
     * @param clz        跳转到的Activity
     * @param intentData 跳转的携带数据
     */
    public void jumpToActivity(Class<? extends BaseActivity> clz, Map<String, String> intentData) {
        Intent intent = new Intent(ctx, clz);
        if (intentData != null && intentData.size() > 0) {
            //加强For循环
            for (Map.Entry<String, String> entry : intentData.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());

            }
        }
        startActivity(intent);
    }

    /**
     * （可调用方法）跳转界面，不携带数据，但是携带数据的返回码
     *
     * @param clz         跳转到的Activity
     * @param requestCode 请求码
     */
    public void jumpToActivityForResult(Class<? extends BaseActivity> clz, int requestCode) {
        jumpToActivityForResult(clz, null, requestCode);
    }

    /**
     * （可调用方法）携带数据跳转界面并要求返回数据
     *
     * @param clz         跳转到的Activity
     * @param intentData  跳转的携带数据
     * @param requestCode 请求码
     */
    public void jumpToActivityForResult(Class<? extends BaseActivity> clz, Map<String, String> intentData, int requestCode) {
        Intent intent = new Intent(ctx, clz);
        if (intentData != null && intentData.size() > 0) {
            for (Map.Entry<String, String> entry : intentData.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 接受返回的数据：
     * requestCode   请求码
     * resultCode    结果码
     * data   数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onStart() {
        //调用配置
       init();
        super.onStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init() {
        int flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

        //判断当前版本在4.0以上并且存在虚拟按键，否则不做操作
        if (Build.VERSION.SDK_INT < 19 || !checkDeviceHasNavigationBar()) {
            //一定要判断是否存在按键，否则在没有按键的手机调用会影响别的功能。如之前没有考虑到，导致图传全屏变成小屏显示。
            return;
        } else {
            // 获取属性
           decorView.setSystemUiVisibility(flag);
        }
    }

    /**
     * 判断是否存在虚拟按键
     *
     * @return
     */
    public boolean checkDeviceHasNavigationBar() {
        boolean hasNavigationBar = false;
        Resources rs = getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }
    /**
     * 摧毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
