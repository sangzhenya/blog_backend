package com.xinyue.blog.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Xinyue on 2017/3/1.
 */
public class IPUtils {
    private static final String[] PROXY_REMOTE_IP_ADDRESS = {"X-Forwarded-For", "X-Real-IP"};

    public static String getRemoteIp(HttpServletRequest request) {
        for (String proxyRemoteIpAddress : PROXY_REMOTE_IP_ADDRESS) {
            String ip = request.getHeader(proxyRemoteIpAddress);
            if (ip != null && ip.trim().length() > 0) {
                return getRemoteIpFromForward(ip.trim());
            }
        }
        return request.getRemoteHost();
    }

    private static String getRemoteIpFromForward(String xforwardIp) {
        int commaOffset = xforwardIp.indexOf(',');
        if (commaOffset < 0) {
            return xforwardIp;
        }
        return xforwardIp.substring(0, commaOffset);
    }
}
