package com.dsgj.youyuntong.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;

public class SPUtils {

    private static SPUtils spUtils;
    private SharedPreferences sp;
    private static final String FILE_NAME = "ZhanYunTong";

    /**
     * 单例模式获取SPUtils对象
     *
     * @param ctx 上下文
     * @return 单例模式
     */
    synchronized public static SPUtils with(Context ctx) {
        if (spUtils == null) {
            spUtils = new SPUtils(ctx);
        }
        return spUtils;
    }

    /**
     * 单例模式中的不可引用的构造方法
     *
     * @param ctx
     */
    private SPUtils(Context ctx) {
        sp = ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 删除某key值
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {
        return sp.edit().remove(key).commit();
    }

    /**
     * 保存SP值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean save(String key, String value) {
        if (TextUtils.isEmpty(value)) {
            return sp.edit().remove(key).commit();
        }
        return sp.edit().putString(key, value).commit();
    }

    /**
     * 判断某个Key值是不是存在
     */
    public boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);

    }
    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }




    /**
     * 保存SP值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean save(String key, Uri value) {
        if (value != null) {
            return sp.edit().remove(key).commit();
        }


        return sp.edit().putString(key, value.toString()).commit();

    }

    /**
     * 读取SP值
     *
     * @param key
     * @return
     */
    public String get(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    /**
     * 保存SP值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean save(String key, long value) {
        return sp.edit().putLong(key, value).commit();
    }

    /**
     * 读取SP值
     *
     * @param key
     * @return
     */
    public long get(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    /**
     * 保存SP值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean save(String key, int value) {
        return sp.edit().putInt(key, value).commit();
    }

    /**
     * 读取SP值
     *
     * @param key
     * @return
     */
    public int get(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    /**
     * 保存SP值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean save(String key, boolean value) {
        return sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 读取SP值
     *
     * @param key
     * @return
     */
    public boolean get(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

}
