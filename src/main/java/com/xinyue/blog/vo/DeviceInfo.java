package com.xinyue.blog.vo;

import java.io.Serializable;

public class DeviceInfo implements Serializable {
    private String browser;
    private String edition;
    private String kernel;
    private String system;
    private String device;
    private String language;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getKernel() {
        return kernel;
    }

    public void setKernel(String kernel) {
        this.kernel = kernel;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "browser='" + browser + '\'' +
                ", edition='" + edition + '\'' +
                ", kernel='" + kernel + '\'' +
                ", system='" + system + '\'' +
                ", device='" + device + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
