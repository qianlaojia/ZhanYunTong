package com.dsgj.youyuntong.Utils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    /**
     * @param obj   对象
     * @return 字符串
     */
    public static String toJson(Object obj) {
        return obj == null ? null : new Gson().toJson(obj);
    }

    /**
     * 对象型Json解析为bean对象
     *
     * @param json Json字符串
     * @param clz  bean的类对象
     * @return bean对象
     */
    public static <E> E toBean(String json, Class<E> clz) {
        E e = new Gson().fromJson(json, clz);
        return e;
    }

    /**
     * 数组型Json解析为bean的List对象
     *
     * @param json  Json字符串
     * @param clazz bean对象
     * @return bean的List对象
     */
    public static <T> List<T> toArr(String json, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(json, clazz);
        return Arrays.asList(arr);
    }

    /**
     * 从Json中获取key对应的字符串
     *
     * @param json Json字符串
     * @param key  Json中的key值
     * @return key对应的value字符串
     */
    public static String getString(String json, String key) {
        String value = "";
        try {
            JSONObject jo = new JSONObject(json);
            value = jo.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 从Json中获取key对应的int数字(解析错误返回值为最小int值)
     *
     * @param json Json字符串
     * @param key  Json中的key值
     * @return key对应的value
     */
    public static int getInt(String json, String key) {
        int value = Integer.MIN_VALUE;
        try {
            JSONObject jo = new JSONObject(json);
            value = jo.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

}
