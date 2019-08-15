package com.qzy.mab.support;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 客户端请求Token
 */
public class RequestToken implements AuthenticationToken {

	private static final long serialVersionUID = -2921350902921890152L;
	private String usernmae;
    private String password;

    public RequestToken(String usernmae, String password) {
        this.usernmae = usernmae;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernmae() {
        return usernmae;
    }

    public void setUsernmae(String usernmae) {
        this.usernmae = usernmae;
    }

    @Override
    public Object getPrincipal() {
        return getUsernmae();
    }

    @Override
    public Object getCredentials() {
        return getPassword();
    }
}
