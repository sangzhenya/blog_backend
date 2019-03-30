package com.xinyue.blog.utils;

import java.util.List;

public class ListUtils {
    public static <T> T getFirstElement(List<T> collection) {
        if (!CollectionUtils.isEmpty(collection)) {
            return collection.get(0);
        }
        return null;
    }
}
