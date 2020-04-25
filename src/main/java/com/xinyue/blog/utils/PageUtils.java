package com.xinyue.blog.utils;

import com.xinyue.blog.constant.QueryConstant;
import com.xinyue.blog.vo.PageVO;
import com.xinyue.blog.vo.requestVO.RequestVO;
import org.springframework.data.domain.Page;
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

    public static Integer calculateTotalPage(Integer totalCount) {
        return totalCount/ QueryConstant.PAGE_SIZE + 1;
    }


    public static PageVO buildPageVOByRequestVO(RequestVO requestVO) {
        PageVO pageVO = new PageVO();
        pageVO.setPage(requestVO.getPage());
        pageVO.setPageSize(requestVO.getPageSize());
        return pageVO;
    }
}
