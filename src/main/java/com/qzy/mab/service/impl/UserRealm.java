package com.qzy.mab.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qzy.mab.service.UserService;
import com.qzy.mab.support.RequestToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof RequestToken;
    }

    /**
     * 授权
     * 
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 在数据库中查询用户拥有的角色/权限
        // authorizationInfo.setRoles(userService.findRoles(username));
        // authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    /**
     * 验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        RequestToken reqeustToken = (RequestToken)token;
        String username = (String)reqeustToken.getPrincipal();
        String password = (String)reqeustToken.getCredentials();
        log.info(String.format("%s 调用登陆方法", username));
        // User user = userService.findByUsername(username);
        // if(user == null){
        // throw new UnknownAccountException(); //没找到账号
        // }

        // if(Boolean.TRUE.equals(user.getLocked())){
        // throw new LockedAccountException(); //账号被锁定
        // }
        try {
            userService.login(username, password);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password,
            // ByteSource.Util.bytes(user.getCredentialsSalt()), //salt = username+salt
            getName());

        return authenticationInfo;
    }

}
