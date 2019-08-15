package com.qzy.mab.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.qzy.mab.entity.user.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.crazycake.shiro.SerializeUtils;

import com.qzy.mab.config.AuthenticFactory;
import com.qzy.mab.support.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取当前系统登录用户信息
 */
@Component
public class AuthenticContextUtil {

    @Autowired
    private RedisUtil redisUtil;

    //重点二：建一个静态的本类
    private static AuthenticContextUtil authenticContextUtil;

    //重点三：初始化
    @PostConstruct
    public void init() {
        authenticContextUtil= this;
        authenticContextUtil.redisUtil= this.redisUtil;
    }

    public static AuthenticContextUtil getInstance() {
        if (authenticContextUtil == null) {
            return new AuthenticContextUtil();
        }
        return authenticContextUtil;
    }

    public static AuthenticFactory getFactoryInstance() {
        return AuthenticFactory.getInstance();
    }


    /**
     * 获取登录用户编号
     * @return
     */
    public Serializable getCurrentPripacilId(){
        return getCurrentPripacil().getId();
    }

    /**
     * 判断当前用户是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return false;
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public User getCurrentPripacil() {
        return getSessionByCookie();
    }

    /**
     * 保存session信息
     *
     * @param key
     * @param userPrincipal
     */
    public void putSession(String key, UserPrincipal userPrincipal) {
        //将登陆成功的用户信息放入codis中
        redisUtil.set(key,userPrincipal,2*3600);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public User getSessionByCookie() {
        HttpServletRequest httpServletRequest = WebContext.currentRequest();
        String cookieName = AuthenticFactory.getInstance().getAuthenticationConfig().getCookieName();
        String salt = AuthenticFactory.getInstance().getAuthenticationConfig().getSalt();
        String hashSessionKey = CookiesUtil.getCookieValue(httpServletRequest, cookieName);
        String sessionKey = hashSessionKey;
        Object value = redisUtil.get(sessionKey);
        return value == null ? null : (User) value;
    }

    /**
     * 将标识写入cookie
     *
     * @param sessionKey
     */
    public static void writeCookie4Session(String domain , String sessionKey) {
        String cookieName = AuthenticFactory.getInstance().getAuthenticationConfig().getCookieName();
        String salt = AuthenticFactory.getInstance().getAuthenticationConfig().getSalt();
        String hashSessoin = sessionKey;
        Cookie cookie = new Cookie(cookieName, hashSessoin);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*2);//单位为秒，设置2小时失效
        CookiesUtil.writeCookie(WebContext.currentResponse(), cookie);
    }



}
