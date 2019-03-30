package com.xinyue.blog.vo;

import com.xinyue.blog.constant.MessageEnum;
import com.xinyue.blog.constant.StatusEnum;

public class ResultVO {
    private Integer status;
    private String message;
    private Object data;

    public ResultVO(Object data) {
        this.status = StatusEnum.SUCCESS.getStatus();
        this.message = MessageEnum.SUCCESS.getDesc();
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
