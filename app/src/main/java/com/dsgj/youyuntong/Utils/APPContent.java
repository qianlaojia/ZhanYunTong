package com.dsgj.youyuntong.Utils;

import android.os.Environment;
import android.support.compat.BuildConfig;


public class APPContent {

    public static final boolean isDebug = BuildConfig.DEBUG;

    /**
     * SD卡下的目录地址
     */
    private static final String baseDirOnSD = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Sodosofa/";
    /**
     * 缓存目录地址
     */
    public static String cacheDir = baseDirOnSD + "/cache";
    /**
     * 照片拍摄目录
     */
    public static String photoDir = baseDirOnSD + "/photo";
    /**
     * 图片压缩后的目录（该目录下的内容，用完则立即删除）
     */
    public static String simpleDir = baseDirOnSD + "/simplePic";
    /**
     * dabug日志存储存储目录
     */
    public static String logDir = baseDirOnSD + "/log";
}
