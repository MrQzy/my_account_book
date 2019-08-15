package com.qzy.mab.interceptor;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qzy.mab.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qzy.mab.config.AuthenticFactory;
import com.qzy.mab.config.AuthenticationConfig;
import com.qzy.mab.exception.AuthenticateTokenInvalidException;
import com.qzy.mab.support.WebContext;
import com.qzy.mab.util.AuthenticContextUtil;
import com.qzy.mab.util.UserPrincipal;

/**
 * 基于SpringMVC的SSO拦截器
 */
public class WebAuthenticationHandler implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(WebAuthenticationHandler.class);

    @SuppressWarnings("unused")
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        WebContext.registry(httpServletRequest,httpServletResponse);
        String currentUri = httpServletRequest.getRequestURI();
        try {
            if (!isExclude(httpServletRequest)) {
                User userPrincipal = AuthenticContextUtil.getInstance().getCurrentPripacil();
                if (null != userPrincipal) {
                    return true;
                } else {
                    throw new AuthenticateTokenInvalidException("当前用户未找到登录信息,请重新登录!");
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            if (e instanceof AuthenticateTokenInvalidException) {
                httpServletResponse.sendError(401, "Token Invalid");
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean isExclude(HttpServletRequest request) {
        AuthenticationConfig authenticationConfig = AuthenticFactory.getInstance().getAuthenticationConfig();
        Pattern[] exclude = null;
        String excludeString = authenticationConfig.getExcludeUrls();
        if (!StringUtils.isEmpty(excludeString)) {
            String[] excludeArray = excludeString.split(",");
            exclude = new Pattern[excludeArray.length];
            for (int i = 0; i < excludeArray.length; i++) {
                exclude[i] = Pattern.compile(excludeArray[i]);
            }
        }
        if (exclude == null || exclude.length == 0) {
            return false;
        }
        String currentUri = request.getRequestURI();
        for (Pattern excludeUri : exclude) {
            if (excludeUri.matcher(currentUri).find()) {
                return true;
            }
        }
        return false;
    }
}
