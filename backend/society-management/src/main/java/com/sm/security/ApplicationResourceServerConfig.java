package com.sm.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
public class ApplicationResourceServerConfig extends ResourceServerConfigurerAdapter {

    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("resourceId");
    }
}
