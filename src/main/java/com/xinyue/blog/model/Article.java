package com.xinyue.blog.model;

import com.xinyue.blog.utils.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * @author sangz
 */
@Entity
@Table(name = "article")
public class Article implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String title;

    @Column(columnDefinition = "longtext")
    private String summary;
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longtext")
    private String content;
    private Timestamp createDate;
    private Timestamp lastUpdateDate;
    private String creator;
    private String lastUpdater;

    private boolean deleteFlag;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "article_tag",
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            joinColumns = @JoinColumn(name = "article_id"))
    private Set<Tag> tags;

    @Transient
    private String tagIds;
    @Transient
    private String tagNames;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLastUpdater() {
        return lastUpdater;
    }

    public void setLastUpdater(String lastUpdater) {
        this.lastUpdater = lastUpdater;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.summary);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Article) {
            Article newArticle = (Article) obj;
            return this.id == newArticle.getId()
                    && StringUtils.isEquals(this.title, newArticle.getTitle())
                    && StringUtils.isEquals(this.summary, newArticle.getSummary());
        }
        return false;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", creator='" + creator + '\'' +
                ", lastUpdater='" + lastUpdater + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", category=" + category +
                ", tags=" + tags +
                ", tagIds='" + tagIds + '\'' +
                ", tagNames='" + tagNames + '\'' +
                '}';
    }
}
