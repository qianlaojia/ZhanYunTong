package com.dsgj.youyuntong.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码验证工具
 * Created by 张云浩  on 2017/5/13.
 * 邮箱：943332771@qq.com
 */

public class MatchPhoneNum {
    public static boolean isMobileNO(String mobiles) {
/**
 * 匹配以下开头的号码 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
 * 增加183
 ***/
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return !m.matches();
    }
}
