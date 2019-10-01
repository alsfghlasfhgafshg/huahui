package com.aaa.huahui.utils;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

    public static String getBaseUrl(HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme();
        String remoteHost = httpServletRequest.getRemoteHost();
        int localPort = httpServletRequest.getLocalPort();

        String baseurl = scheme + "://" + remoteHost;

        if (localPort != 80) {
            baseurl = baseurl + ":";
            baseurl = baseurl + localPort;
        }
        return baseurl;
    }

    //如果是以http开头，直接返回
    //以/开头，加上baseurl
    public static String getFullUrl(HttpServletRequest httpServletRequest, String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url;
        }
        String fullurl = getBaseUrl(httpServletRequest) + url;
        return fullurl;
    }

}
