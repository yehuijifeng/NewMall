/**
 * LAB139 com.alsfox.lab139.utils 2015
 */
package com.alsfox.mall.utils;

import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * 1.0 MD5加密工具类
 */
public class MD5Util {

    public static String MD5(String sourceStr) {
        String s = null;
        try {
            sourceStr = sourceStr.replace("+", "%2B").replace(" ", "%20");
            sourceStr = URLEncoder.encode(sourceStr, "utf-8").replace("*", "%2A");
            sourceStr = new String(sourceStr.getBytes("ISO-8859-1"), "UTF-8");
            byte[] source = sourceStr.getBytes();
            char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char[] str = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}