package com.xinyue.blog.vo;

import com.xinyue.blog.constant.QueryConstant;

public class PageVO {
    private Integer page;
    private Integer totalPage;
    private Integer pageSize = QueryConstant.PAGE_SIZE;

    public PageVO(Integer page, Integer totalPage) {
        this.page = page;
        this.totalPage = totalPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
