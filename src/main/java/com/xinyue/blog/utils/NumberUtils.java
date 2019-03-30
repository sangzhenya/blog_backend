package com.xinyue.blog.utils;

public class NumberUtils {
    public static Integer convert2Int(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmptyInt(Integer integer) {
        return integer == null || integer == 0;
    }
}
