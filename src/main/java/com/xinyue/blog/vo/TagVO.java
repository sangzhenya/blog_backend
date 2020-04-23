package com.xinyue.blog.vo;

import java.util.List;

public class TagVO {
    private Integer id;
    private String name;
    private Integer count;
    private List<ArticleVO> articleList;

    public TagVO() {
    }

    public TagVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ArticleVO> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleVO> articleList) {
        this.articleList = articleList;
    }
}
