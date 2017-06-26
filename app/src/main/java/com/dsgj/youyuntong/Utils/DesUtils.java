package com.dsgj.youyuntong.Utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 功能:使用des进行加密
 * Created by 张云浩  on 17/06/16.
 * 邮箱：943332771@qq.com
 */
public class DesUtils {
    public static String DecryptDoNet(String message, String key) throws Exception {
        byte[] bytesrc = Base64.decode(message.getBytes(), Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }

    public static String EncryptAsDoNet(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory
                .generateSecret(desKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptbyte = cipher.doFinal(message.getBytes());
        return new String(Base64.encode(encryptbyte, Base64.DEFAULT));
    }
}
