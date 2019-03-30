package com.xinyue.blog.vo;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleVO {
    private Integer id;
    private List<TagVO> tags;
    private CategoryVO category;
    private LocalDateTime createDate;
    private String title;
    private String summary;
    private String content;
    private LocalDateTime updateDate;

    private ArticleVO preArticle;
    private ArticleVO nextArticle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }

    public CategoryVO getCategory() {
        return category;
    }

    public void setCategory(CategoryVO category) {
        this.category = category;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleVO getPreArticle() {
        return preArticle;
    }

    public void setPreArticle(ArticleVO preArticle) {
        this.preArticle = preArticle;
    }

    public ArticleVO getNextArticle() {
        return nextArticle;
    }

    public void setNextArticle(ArticleVO nextArticle) {
        this.nextArticle = nextArticle;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
