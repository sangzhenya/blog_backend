package com.xinyue.blog.utils;

import com.xinyue.blog.vo.DeviceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceUtil {
    private static final Logger logger = LoggerFactory.getLogger(DeviceUtil.class);

    public static DeviceInfo getDeviceInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String language = request.getHeader("accept-language");
        DeviceInfo browserInfo = new DeviceInfo();
        browserInfo.setBrowser(getBrowserSimpleInfo(userAgent));
        browserInfo.setEdition(getBrowserEdition(browserInfo.getBrowser(), userAgent));
        browserInfo.setKernel(getBrowserKernelInfo(browserInfo.getBrowser(), browserInfo.getEdition(), userAgent));
        browserInfo.setLanguage(getBrowserLanguage(language));
        browserInfo.setDevice(getBrowserDevice(userAgent));
        browserInfo.setSystem(getBrowserSystem(userAgent));
        return browserInfo;
    }

    private static String getBrowserSystem(String userAgent) {
        String system = "";
        if (userAgent.contains("Windows")) {
            system = "Windows";
        }
        if (userAgent.contains("Linux") || userAgent.contains("X11")) {
            system = "Linux";
        }
        if (userAgent.contains("Macintosh")) {
            system = "Mac OS";
        }
        if (userAgent.contains("Android") || userAgent.contains("Adr")) {
            system = "Android";
        }
        if (userAgent.contains("Ubuntu")) {
            system = "Ubuntu";
        }
        if (userAgent.contains("FreeBSD")) {
            system = "FreeBSD";
        }
        if (userAgent.contains("Debian")) {
            system = "Debian";
        }
        if (userAgent.contains("IEMobile") || userAgent.contains("Windows Phone")) {
            system = "Windows Phone";
        }
        if (userAgent.contains("BlackBerry") || userAgent.contains("RIM")) {
            system = "BlackBerry";
        }
        if (userAgent.contains("MeeGo")) {
            system = "MeeGo";
        }
        if (userAgent.contains("Symbian")) {
            system = "Symbian";
        }
        if (userAgent.contains("like Mac OS X")) {
            system = "iOS";
        }
        if (userAgent.contains("CrOS")) {
            system = "Chrome OS";
        }
        if (userAgent.contains("hpwOS")) {
            system = "WebOS";
        }

        switch (system) {
            case "Windows":
                return getWindowsSystemInfo(userAgent);
            case "Android":
                return getSystemInfoDefault("Android ", userAgent);
            case "iOS":
                return getMacOSSystemInfo("iOS ", userAgent);
            case "Debian":
                return getSystemInfoDefault("Debian/", userAgent);
            case "Mac OS":
                return getMacOSSystemInfo("Mac OS X ", userAgent);
            case "WebOS":
                return getSystemInfoDefault("hpwOS/", userAgent);
        }
        return null;
    }

    private static String getMacOSSystemInfo(String system, String userAgent) {
        Pattern p = Pattern.compile(system + "([\\d_]+)");
        Matcher m = p.matcher(userAgent);
        if (m.find() && m.groupCount() > 0) {
            return system + " " + m.group(1).replace("_", ".");
        }
        return system;
    }

    private static String getSystemInfoDefault(String system, String userAgent) {
        Pattern p = Pattern.compile(system + "([\\d.]+)");
        Matcher m = p.matcher(userAgent);
        if (m.find() && m.groupCount() > 0) {
            return system + " " + m.group(1);
        }
        return system;
    }

    private static String getWindowsSystemInfo(String userAgent) {
        Pattern p = Pattern.compile("Windows NT ([\\d.]+)");
        Matcher m = p.matcher(userAgent);
        String osEdition = "";
        if (m.find() && m.groupCount() > 0) {
            osEdition = m.group(1);
        }
        switch (osEdition) {
            case "6.4":
                return "Windows 10";
            case "6.3":
                return "Windows 8.1";
            case "6.2":
                return "Windows 8";
            case "6.1":
                return "Windows 7";
            case "6.0":
                return "Windows Vista";
            case "5.2":
                return "Windows XP";
            case "5.1":
                return "Windows XP";
            case "5.0":
                return "Windows 2000";
            default:
                return "Windows " + osEdition;
        }
    }

    private static String getBrowserDevice(String userAgent) {
        String device = "PC";
        if (userAgent.contains("Mobi") || userAgent.contains("iPh") || userAgent.contains("480")) {
            device = "Mobile";
        }
        if (userAgent.contains("Tablet") || userAgent.contains("Pad") || userAgent.contains("Nexus 7")) {
            device = "Tablet";
        }
        return device;
    }

    private static String getBrowserLanguage(String language) {
        try {
            return language.split(",")[0];
        } catch (Exception e) {
            logger.error("Handle Browser Language Error");
        }
        return "";
    }

    private static String getBrowserEdition(String browser, String userAgent) {
        switch (browser) {
            case "Safari":
                return getBrowserEditionInner(new String[]{"Version/"}, userAgent);
            case "Chrome":
                return getBrowserEditionInner(new String[]{"Chrome/", "CriOS/"}, userAgent);
            case "IE":
                return getBrowserEditionInner(new String[]{"MSIE ", "rv:"}, userAgent);
            case "Edge":
                return getBrowserEditionInner(new String[]{"Edge/"}, userAgent);
            case "Firefox":
                return getBrowserEditionInner(new String[]{"Firefox/", "FxiOS/"}, userAgent);
            case "Firefox Focus":
                return getBrowserEditionInner(new String[]{"Focus/"}, userAgent);
            case "Chromium":
                return getBrowserEditionInner(new String[]{"Chromium/"}, userAgent);
            case "Opera":
                return getBrowserEditionInner(new String[]{"Opera/", "OPR/"}, userAgent);
            case "Vivaldi":
                return getBrowserEditionInner(new String[]{"Vivaldi/"}, userAgent);
            case "Yandex":
                return getBrowserEditionInner(new String[]{"YaBrowser/"}, userAgent);
            case "Kindle":
                return getBrowserEditionInner(new String[]{"Version/"}, userAgent);
            case "Maxthon":
                return getBrowserEditionInner(new String[]{"Maxthon/"}, userAgent);
            case "QQBrowser":
                return getBrowserEditionInner(new String[]{"QQBrowser/"}, userAgent);
            case "QQ":
                return getBrowserEditionInner(new String[]{"QQ/"}, userAgent);
            case "Baidu":
                return getBrowserEditionInner(new String[]{"BIDUBrowser/"}, userAgent);
            case "UC":
                return getBrowserEditionInner(new String[]{"UC?Browser/"}, userAgent);
            case "Sogou":
                return getBrowserEditionInner(new String[]{"SE ", "SogouMobileBrowser/"}, userAgent);
            case "2345Explorer":
                return getBrowserEditionInner(new String[]{"2345Explorer/"}, userAgent);
            case "TheWorld":
                return getBrowserEditionInner(new String[]{"TheWorld "}, userAgent);
            case "XiaoMi":
                return getBrowserEditionInner(new String[]{"MiuiBrowser/"}, userAgent);
            case "Quark":
                return getBrowserEditionInner(new String[]{"Quark/"}, userAgent);
            case "Qiyu":
                return getBrowserEditionInner(new String[]{"Qiyu/"}, userAgent);
            case "Wechat":
                return getBrowserEditionInner(new String[]{"MicroMessenger/"}, userAgent);
            case "Taobao":
                return getBrowserEditionInner(new String[]{"AliApp(TB/"}, userAgent);
            case "Alipay":
                return getBrowserEditionInner(new String[]{"AliApp(AP/"}, userAgent);
            case "Weibo":
                return getBrowserEditionInner(new String[]{"weibo__"}, userAgent);
            case "Douban":
                return getBrowserEditionInner(new String[]{"com.douban.frodo"}, userAgent);
            case "Suning":
                return getBrowserEditionInner(new String[]{"SNEBUY-APP/"}, userAgent);
            case "iQiYi":
                return getBrowserEditionInner(new String[]{"IqiyiVersion/"}, userAgent);
        }
        return "";
    }

    private static String getBrowserEditionInner(String[] browserInfo, String userAgent) {
        for (String infoItem : browserInfo) {
            Pattern p = Pattern.compile(infoItem.replace("(", "\\\\(") + "([\\d.]+)");
            Matcher m = p.matcher(userAgent);
            if (m.find() && m.groupCount() > 0) {
                return m.group(1);
            }
        }
        return "";
    }

    private static String getBrowserSimpleInfo(String userAgent) {
        String browser = "";
        if (userAgent.contains("Safari")) {
            browser = "Safari";
        }
        if (userAgent.contains("Chrome") || userAgent.contains("CriOS")) {
            browser = "Chrome";
        }
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            browser = "IE";
        }
        if (userAgent.contains("Edge")) {
            browser = "Edge";
        }
        if (userAgent.contains("Firefox") || userAgent.contains("FxiOS")) {
            browser = "Firefox";
        }
        if (userAgent.contains("Focus")) {
            browser = "Firefox Focus";
        }
        if (userAgent.contains("Chromium")) {
            browser = "Chromium";
        }
        if (userAgent.contains("Opera") || userAgent.contains("OPR")) {
            browser = "Opera";
        }
        if (userAgent.contains("Vivaldi")) {
            browser = "Vivaldi";
        }
        if (userAgent.contains("YaBrowser")) {
            browser = "Yandex";
        }
        if (userAgent.contains("Kindle") || userAgent.contains("Silk/")) {
            browser = "Kindle";
        }
        if (userAgent.contains("360EE") || userAgent.contains("360SE")) {
            browser = "360";
        }
        if (userAgent.contains("UC") || userAgent.contains("UBrowser")) {
            browser = "UC";
        }
        if (userAgent.contains("QQBrowser")) {
            browser = "QQBrowser";
        }
        if (userAgent.contains("QQ/")) {
            browser = "QQ";
        }
        if (userAgent.contains("Baidu") || userAgent.contains("BIDUBrowser")) {
            browser = "Baidu";
        }
        if (userAgent.contains("Maxthon")) {
            browser = "Maxthon";
        }
        if (userAgent.contains("MetaSr") || userAgent.contains("Sogou")) {
            browser = "Sogou";
        }
        if (userAgent.contains("LBBROWSER")) {
            browser = "LBBROWSER";
        }
        if (userAgent.contains("2345Explorer")) {
            browser = "2345Explorer";
        }
        if (userAgent.contains("TheWorld")) {
            browser = "TheWorld";
        }
        if (userAgent.contains("MiuiBrowser")) {
            browser = "XiaoMi";
        }
        if (userAgent.contains("Quark")) {
            browser = "Quark";
        }
        if (userAgent.contains("Qiyu")) {
            browser = "Qiyu";
        }
        if (userAgent.contains("MicroMessenger")) {
            browser = "Wechat";
        }
        if (userAgent.contains("AliApp(TB")) {
            browser = "Taobao";
        }
        if (userAgent.contains("AliApp(AP")) {
            browser = "Alipay";
        }
        if (userAgent.contains("Weibo")) {
            browser = "Weibo";
        }
        if (userAgent.contains("com.douban.frodo")) {
            browser = "Douban";
        }
        if (userAgent.contains("SNEBUY-APP")) {
            browser = "Suning";
        }
        if (userAgent.contains("IqiyiApp")) {
            browser = "iQiYi";
        }
        return browser;
    }

    private static String getBrowserKernelInfo(String browser, String edition, String userAgent) {
        if (browser.equals("Edge")) {
            return "EdgeHTML";
        }
        if (browser.equals("Chrome") && edition.compareTo("27") > 0) {
            return "Blink";
        }
        if (browser.equals("Opera") && edition.compareTo("12") > 0) {
            return "Blink";
        }
        if (browser.equals("Yandex")) {
            return "Blink";
        }

        String kernelInfo = "";
        if (userAgent.contains("Trident") || userAgent.contains("NET CLR")) {
            kernelInfo = "Trident";
        }
        if (userAgent.contains("Presto")) {
            kernelInfo = "Presto";
        }
        if (userAgent.contains("Gecko")) {
            kernelInfo = "Gecko";
        }
        if (userAgent.contains("AppleWebKit")) {
            kernelInfo = "WebKit";
        }
        return kernelInfo;
    }
}
