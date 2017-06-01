package com.dsgj.youyuntong.Utils;

import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;

/**
 * TODO:
 * Created by 张云浩  on 2017/5/18.
 * 邮箱：943332771@qq.com
 */

public class ImageUtils {
    /**
     * 提供整整的保存路径，返回Uri数据
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Uri getImageUri() {
        String imageDateName = new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + ".jpg";
        String path = MyConstant.PhotoDir + imageDateName;
        return UriUtils.getUriFromFilePath(path);
    }
}
