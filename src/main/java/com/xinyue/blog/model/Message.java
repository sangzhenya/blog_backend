package com.xinyue.blog.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "message")
public class Message implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column(columnDefinition = "text")
    private String content;
    private LocalDateTime lastUpdateDate;
    private String creator;

    @OneToMany(mappedBy = "message", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<CustomerFile> customerFiles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Set<CustomerFile> getCustomerFiles() {
        return customerFiles;
    }

    public void setCustomerFiles(Set<CustomerFile> customerFiles) {
        this.customerFiles = customerFiles;
    }
}
