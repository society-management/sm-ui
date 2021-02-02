package com.sm.security;

import com.sm.service.MyClientDetailService;
import com.sm.service.MyPasswordEncoder;
import com.sm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired private DataSource dataSource;

    @Autowired private UserService userService;

    @Autowired private MyClientDetailService myClientDetailService;

    @Autowired private MyPasswordEncoder myPasswordEncoder;

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.pathMapping("/oauth/token", "/authorize")// To change Token URL
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .exceptionTranslator(
                        exception -> {
                            if (exception instanceof InvalidGrantException) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(OAuth2Exception.create("BAD_CREDENTIAL", exception.getMessage()));
                            }
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(OAuth2Exception.create("INVALID_REQUEST", exception.getMessage()));
                        });
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients.withClientDetails(myClientDetailService);
        clients.inMemory()
                .withClient("tirth")
                .secret(myPasswordEncoder.encode("secret"))
                .accessTokenValiditySeconds(2000)        // expire time for access token
                .refreshTokenValiditySeconds(-1)         // expire time for refresh token
                .scopes("read", "write")                         // scope related to resource server
                .authorizedGrantTypes("password", "refresh_token")
                .resourceIds("resourceId");
    }
}
