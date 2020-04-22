package com.xinyue.blog.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author sangz
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @Transient
    private int articleCount;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Article> articles;

    public Category() {
    }

    public Category(String name) {
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

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articleCount=" + articleCount +
                '}';
    }
}
