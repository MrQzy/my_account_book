package com.qzy.mab.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookiesUtil {

    private static final Log LOG = LogFactory.getLog(CookiesUtil.class);

    public static String getCookieValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = getCookie(name, httpServletRequest);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    public static Cookie getCookie(String name, HttpServletRequest httpServletRequest) {
        Cookie[] cookies = getCookies(httpServletRequest);
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String cookName = cookie.getName();
                if (cookName != null && cookName.equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static Cookie[] getCookies(HttpServletRequest request) {
        return request == null ? null : request.getCookies();
    }


    /**
     * 将cookie写入客户端
     *
     * @param response
     * @param cookie   要写入的cookie
     */
    public static void writeCookie(HttpServletResponse response, Cookie cookie) {
        if (response != null) {
            response.addCookie(cookie);
        } else {
            LOG.warn("Cookies is NULL");
        }
    }
}
