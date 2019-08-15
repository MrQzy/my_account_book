package com.qzy.mab.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContext {
    private static final ThreadLocal WEBCONTEXT_LOCAL = new ThreadLocal();

    public WebContext() {
    }

    public static HttpServletRequest currentRequest() {
        Object[] locals = (Object[])((Object[])WEBCONTEXT_LOCAL.get());
        return locals == null?null:(HttpServletRequest)locals[0];
    }

    public static HttpServletResponse currentResponse() {
        Object[] locals = (Object[])((Object[])WEBCONTEXT_LOCAL.get());
        return locals == null?null:(HttpServletResponse)locals[1];
    }

    public static void registry(HttpServletRequest request, HttpServletResponse response) {
        Object[] locals = new Object[]{request, response};
        WEBCONTEXT_LOCAL.set(locals);
    }

    public static void release() {
        WEBCONTEXT_LOCAL.set((Object)null);
    }
}
