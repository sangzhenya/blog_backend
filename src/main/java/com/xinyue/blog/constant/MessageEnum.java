package com.xinyue.blog.constant;

/**
 * @author sangz
 */

public enum MessageEnum {
    SUCCESS("Success"),
    ERROR("Error"),
    LOGIN_FAILED("Login Failed"),
    LOGIN_SUCCESSED("Login Success");

    private String desc;

    private MessageEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
