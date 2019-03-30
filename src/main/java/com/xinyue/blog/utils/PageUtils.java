package com.xinyue.blog.utils;

import com.xinyue.blog.constant.QueryConstant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * @author sangz
 */
public class PageUtils {
    public static Integer getRealPageIndex(Integer page) {
        if (page == null || page <= 0) {
            return 0;
        }
        return page - 1;
    }

    public static Pageable getPageable4Before() {
        return PageRequest.of(0, QueryConstant.LIMIT_1, new Sort(Sort.Direction.DESC, "id"));
    }

    public static Pageable getPageable4After() {
        return PageRequest.of(0, QueryConstant.LIMIT_1, new Sort(Sort.Direction.ASC, "id"));
    }
}