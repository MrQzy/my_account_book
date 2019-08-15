package com.qzy.mab.config;

/**
 */
public class AuthenticationConfig {
    //平台属性
    private Integer platformId;
    //平台盐
    private String salt;
    //登录URL
    private String loginUrl;
    //编码
    private String encoding;
    //排除URL
    private String excludeUrls;
    //本地session缓存类型
    private String sessionCacheType;
    //cookie
    private String cookieName = "WX_SSO_KEY";
    //cookie作用域名
    private String domain;
    // 跨域访问时允许的域源地址
    private String allowOriginDomain;
    // 跨域访问允许的域源操作.
    private String allowOriginMethods = "GET,POST,PUT,DELETE,HEAD";

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(String excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public String getSessionCacheType() {
        return sessionCacheType;
    }

    public void setSessionCacheType(String sessionCacheType) {
        this.sessionCacheType = sessionCacheType;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAllowOriginDomain() { return allowOriginDomain; }

    public void setAllowOriginDomain(String allowOriginDomain) { this.allowOriginDomain = allowOriginDomain; }

    public String getAllowOriginMethods() { return allowOriginMethods; }

    public void setAllowOriginMethods(String allowOriginMethods) { this.allowOriginMethods = allowOriginMethods; }
}
