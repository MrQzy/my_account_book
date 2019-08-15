package com.qzy.mab.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

public class AuthenticFactory implements InitializingBean {

    private static final Log LOG = LogFactory.getLog(AuthenticFactory.class);

    private static AuthenticFactory authenticInstance;

    // private AuthorizerService authorizerService;

    private AuthenticationConfig authenticationConfig;

    public static AuthenticFactory getInstance() {
        if (authenticInstance == null) {
            authenticInstance = new AuthenticFactory();
        }
        return authenticInstance;
    }

    public AuthenticationConfig getAuthenticationConfig() {
        return authenticationConfig;
    }

    public void setAuthenticationConfig(AuthenticationConfig authenticationConfig) {
        this.authenticationConfig = authenticationConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("Init AuthenticFactory Instance Begin.");
        if (authenticInstance == null) {
            authenticInstance = new AuthenticFactory();
        }
        authenticInstance.setAuthenticationConfig(authenticationConfig);
        // authenticInstance.setAuthorizerService(authorizerService);
        // authenticInstance.setAuthenticationCache(authenticationCache);

        LOG.info("Init AuthenticFactory Instance End. PlatformId: " + AuthenticFactory.getInstance().getAuthenticationConfig().getPlatformId());
    }
}
