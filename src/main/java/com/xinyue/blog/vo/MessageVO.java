package com.xinyue.blog.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class MessageVO  implements Serializable {
    private int id;
    private String content;
    private LocalDateTime lastUpdateDate;
    private String creator;
    private List<CustomerFileVO> files;

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

    public List<CustomerFileVO> getFiles() {
        return files;
    }

    public void setFiles(List<CustomerFileVO> files) {
        this.files = files;
    }
}
