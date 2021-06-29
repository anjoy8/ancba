package club.neters.core.config;

import club.neters.core.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Collections;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // 该对象用来支持 password 模式
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                // 验证获取Token的验证信息
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.inMemory()
                //Client1
                .withClient("clientapp1")
                .secret(passwordEncoder().encode("654321"))
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("READ_ONLY_CLIENT")
                .scopes("all")
                .resourceIds("clientapp-res")
                .redirectUris("http://localhost:8181/login")
                .accessTokenValiditySeconds(5000)
                .refreshTokenValiditySeconds(50000)
                .and()
                //Client2
                .withClient("clientapp2")
                .secret(passwordEncoder().encode("secret"))
                .authorizedGrantTypes("implicit", "authorization_code", "client_credentials")
                .scopes("read", "write", "foo", "bar", "webclient")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .redirectUris("http://localhost:8181/login");
    }


    @Bean
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter()));
        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(CommonConstant.JWT_HMAC256_SECRET);
        //使用自定义的 TokenConverter
        converter.setAccessTokenConverter(new JwtCustomerAccessTokenConverter());
        return converter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}