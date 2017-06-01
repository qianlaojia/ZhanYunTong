package com.dsgj.youyuntong.Utils.log;

import android.os.Environment;
import android.util.Log;



import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LogUtils {

    private static boolean isDebug = true;

    /**
     * 输出Log名字常量
     */
    private static final String LOG_TAG_DEFAULT = "youyuntong_lock";// 默认TAG

    /**
     * 线程队列
     */
    private static ConcurrentLinkedQueue<LogInfo> logQueue = new ConcurrentLinkedQueue<LogInfo>();

    /**
     * 获取消息队列
     */
    public synchronized static ConcurrentLinkedQueue<LogInfo> getLogQueue() {
        return logQueue;
    }

    /**
     * 记录日志信息
     */
    private static synchronized void addLog(String content, String fileName) {
        logQueue.add(new LogInfo(content, fileName));
        if (!LogWriteThread.isWriteThreadLive) {
            new LogWriteThread().start();
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     */

    public static void i(String msg) {

        if (isDebug) {

            Log.i(LOG_TAG_DEFAULT, msg);
        }

    }

    /**
     * 以级别为 i 的形式输出LOG
     */

    public static void i(String tag, String msg) {

        if (isDebug) {
            Log.i(tag, msg);
        }

    }

    /**
     * 以级别为 e 的形式输出LOG
     */

    public static void e(String msg) {

        if (isDebug) {
            Log.e(LOG_TAG_DEFAULT, msg);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */

    public static void e(String tag, String msg) {

        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */

    public static void e(String tag, String msg, Throwable throwable) {

        if (isDebug) {
            Log.e(tag, msg, throwable);
        }
    }

    public static void println(String msg) {
        if (isDebug) {
            System.out.println(msg);
        }
    }

    /**
     * 流程记录(文本日志+LogInBean.i输出)
     */
    public static void writeLog(String content) {
        if (!isDebug) {
            return;
        }
        writeLog(LOG_TAG_DEFAULT, content);
    }

    /**
     * 流程记录
     *
     * @param msg
     * @param tag
     */
    public static void writeLog(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        i(tag, msg);
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS", Locale.CHINESE);
        String time = formatter.format(new Date());
        String result = time + "->" + msg + "\r\n";// 日志文本
        addLog(result, tag);
    }

    /**
     * 生成log文件的方法(不对外使用)
     *
     * @param content
     * @param name
     */
    synchronized static void writeFile(String name, String content) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS", Locale.CHINESE);
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd", Locale.CHINESE);
        try {
            String time = formatter.format(new Date());
            String result = time + "  " + content + "\r\n";// 日志文本
            String fileName = name + "_" + dateFormatter.format(new Date()) + ".txt";
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/youyutong/"+
             "/cache";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + "/" + fileName, true);
                fos.write(result.getBytes());
                fos.close();
            }
        } catch (Exception e) {
        }
    }

}
