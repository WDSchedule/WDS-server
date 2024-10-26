package com.WDS.utils;

public class Md5Util {
    public static String getMd5String(String str) {
        return org.springframework.util.DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
