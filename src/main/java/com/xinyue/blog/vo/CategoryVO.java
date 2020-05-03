package com.xinyue.blog.vo;

import java.io.Serializable;
import java.util.List;

public class CategoryVO  implements Serializable {
    private int id;
    private String name;
    private Integer count;
    private List<ArticleVO> articleList;

    public CategoryVO() {
    }

    public CategoryVO(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public CategoryVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "CategoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", articleList=" + articleList +
                '}';
    }
}
