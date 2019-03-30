package com.xinyue.blog.vo;

import java.util.List;

public class ArticlePageVO {
    private List<ArticleVO> articles;
    private PageVO page;

    public List<ArticleVO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleVO> articles) {
        this.articles = articles;
    }

    public PageVO getPage() {
        return page;
    }

    public void setPage(PageVO page) {
        this.page = page;
    }
}
