package com.xinyue.blog.utils;

import java.util.Collection;

/**
 * @author sangz
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }
}
