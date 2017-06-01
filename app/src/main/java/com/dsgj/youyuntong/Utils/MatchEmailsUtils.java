package com.dsgj.youyuntong.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断邮箱是否合法
 * Created by 张云浩  on 2017/5/24.
 * 邮箱：943332771@qq.com
 */

public class MatchEmailsUtils {
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
