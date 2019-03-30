package com.xinyue.blog.model;

import com.xinyue.blog.utils.StringUtils;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author sangz
 */
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Article> articles;

    public Tag() {
    }

    public Tag(String name) {
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

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tag) {
            Tag newTag = (Tag) obj;
            return this.id == newTag.getId()
                    && StringUtils.isEquals(this.name, newTag.getName());
        }
        return false;
    }
}
