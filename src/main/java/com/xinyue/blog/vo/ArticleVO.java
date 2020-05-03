package com.xinyue.blog.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ArticleVO  implements Serializable {
    private Integer id;
    private List<TagVO> tags;
    private CategoryVO category;
    private Date createDate;
    private String title;
    private String summary;
    private String content;
    private Date updateDate;
    private Boolean deleteFlag;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "id=" + id +
                ", tags=" + tags +
                ", category=" + category +
                ", createDate=" + createDate +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", updateDate=" + updateDate +
                ", preArticle=" + preArticle +
                ", nextArticle=" + nextArticle +
                '}';
    }
}
