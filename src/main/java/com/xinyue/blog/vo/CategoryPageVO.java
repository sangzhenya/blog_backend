package com.xinyue.blog.vo;

import java.io.Serializable;
import java.util.List;

public class CategoryPageVO implements Serializable {
    private List<CategoryVO> categoryVOList;

    public CategoryPageVO(List<CategoryVO> categoryVOList) {
        this.categoryVOList = categoryVOList;
    }

    public List<CategoryVO> getCategoryVOList() {
        return categoryVOList;
    }

    public void setCategoryVOList(List<CategoryVO> categoryVOList) {
        this.categoryVOList = categoryVOList;
    }
}
