package com.xinyue.blog.utils;

import com.xinyue.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberUtils {
    private static final Logger logger = LoggerFactory.getLogger(NumberUtils.class);

    public static Integer convert2Int(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            logger.error("Meet error when parse integer" + obj);
        }
        return null;
    }

    public static boolean isEmptyInt(Integer integer) {
        return integer == null || integer == 0;
    }
}
