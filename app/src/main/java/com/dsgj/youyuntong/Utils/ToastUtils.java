package com.dsgj.youyuntong.Utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;


import com.dsgj.youyuntong.Utils.log.LogUtils;

import java.util.ArrayList;

/**
 * 弹toast工具类。多toast累积显示时最多只显示最后2个；调用clear则立即关闭所有toast
 */
public class ToastUtils {

    private static ArrayList<Toast> ts;

    /**
     * 弹出吐司（可在子线程实现）
     *
     * @param ctx  上下文，仅限Activity和Application的上下文
     * @param text 吐司内容
     */
    public static void show(Context ctx, String text) {
        clearToCount(2);
        Toast toast = Toast.makeText(ctx, text, Toast.LENGTH_LONG);
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            LogUtils.writeLog("主线程toast:" + text);
            // 在主线程
            toast.show();
        } else {
            // 在子线程
            LogUtils.writeLog("子线程toast:" + text);
            Looper.prepare();
            toast.show();
            Looper.loop();
        }
        if (ts == null)
            ts = new ArrayList<>();
        ts.add(toast);
    }

    /**
     * 弹出吐司（可在子线程实现）
     *
     * @param ctx  上下文，仅限Activity和Application的上下文
     * @param text 吐司内容
     */
    public static void showShort(Context ctx, String text) {
        clearToCount(2);
        Toast toast = Toast.makeText(ctx, text, Toast.LENGTH_SHORT);
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            LogUtils.writeLog("主线程toast:" + text);
            // 在主线程
            toast.show();
        } else {
            // 在子线程
            LogUtils.writeLog("子线程toast:" + text);
            Looper.prepare();
            toast.show();
            Looper.loop();
        }
        if (ts == null)
            ts = new ArrayList<>();
        ts.add(toast);
    }

    /**
     * 已在退出APP时调用
     */
    public synchronized static void clear() {
        clearToCount(0);
    }

    /**
     * 已在退出APP时调用
     */
    private synchronized static void clearToCount(int count) {
        if (ts == null || ts.size() <= count)
            return;
        for (int i = 0; i < ts.size() - count; i++) {
            Toast toast = ts.get(i);
            if (toast != null)
                toast.cancel();
        }
        ts.clear();
    }

}
