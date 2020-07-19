package com.xinyue.blog.utils;

import com.xinyue.blog.constant.QueryConstant;
import com.xinyue.blog.vo.PageVO;
import com.xinyue.blog.vo.requestVO.RequestVO;


public class PageUtils {
    public static Integer getRealPageIndex(Integer page) {
        if (page == null || page <= 0) {
            return 0;
        }
        return page - 1;
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
