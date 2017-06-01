package com.dsgj.youyuntong.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 约束密码输入
 * Created by 张云浩  on 2017/5/13.
 * 邮箱：943332771@qq.com
 */

public class PassWordMarchUtils {
    public static boolean isPwd(String psd) {
        Pattern p = Pattern
                .compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
        Matcher m = p.matcher(psd);
        return m.matches();
    }
}
