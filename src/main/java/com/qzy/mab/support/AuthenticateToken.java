package com.qzy.mab.support;

import java.io.Serializable;

/**
 * 认证Token
 * Created by hidehai on 2015/9/15.
 */
public interface AuthenticateToken extends Serializable{

    //用户凭证
     String getPrincipal();
    //鉴权凭证
    String getCredentials();
    //来源平台
    Integer getPlatform();
    //获取域名
    String getDomain();
}
