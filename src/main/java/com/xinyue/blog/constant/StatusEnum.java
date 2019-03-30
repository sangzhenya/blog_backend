package com.xinyue.blog.constant;

public enum StatusEnum {
    LOGIN_FAILED(101),
    SUCCESS(200),
    LOGIN_SUCCESSED(201),
    ERROR(500),
    NOT_FOUND(404);

    private Integer status;
    private StatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
