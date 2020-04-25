package com.xinyue.blog.vo;

import com.xinyue.blog.constant.QueryConstant;
import com.xinyue.blog.utils.PageUtils;

import java.io.Serializable;

public class PageVO implements Serializable {
    private Integer page = 1;
    private Integer totalPage = 1;
    private Integer totalCount = 1;
    private Integer pageSize = QueryConstant.PAGE_SIZE;
    private Integer offset = 0;

    public PageVO() {
    }

    public PageVO(Integer page) {
        this.page = PageUtils.getRealPageIndex(page);
        this.offset = this.page * pageSize;
    }

    public PageVO(Integer page, Integer totalPage) {
        this.page = PageUtils.getRealPageIndex(page);
        this.offset = this.page * pageSize;
        this.totalPage = totalPage;
    }

    public PageVO(Integer page, Integer totalPage, Integer pageSize) {
        this.page = PageUtils.getRealPageIndex(page);
        this.totalPage = totalPage;
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = PageUtils.getRealPageIndex(page);
        this.offset = this.page * pageSize;
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
        this.offset = this.page * pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
