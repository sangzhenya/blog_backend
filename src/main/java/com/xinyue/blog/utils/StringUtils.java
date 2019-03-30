package com.xinyue.blog.utils;

public class StringUtils {
    public static boolean isEquals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 != null) {
            return str1.equals(str2);
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String buildSearchKey(String str) {
        return "%" + str + "%";
    }
}
