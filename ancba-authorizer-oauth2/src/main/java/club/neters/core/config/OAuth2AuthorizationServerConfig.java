package club.neters.core.config;

import club.neters.app.service.UserDetailService;
import club.neters.core.constant.CommonConstant;
import club.neters.core.util.UserPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

/**
 * 客户端信息配置和access_token生成配置
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                // 验证获取Token的验证信息
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置授权终端（authorization）
     * 涉及：令牌（token）的增强器/转换器、token存储、令牌服务(token services)、密码编码器
     * TokenConverter: 配置签名、accessToken声明
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter()));
        endpoints.tokenStore(jwtTokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authentication -> daoAuhthenticationOauthProvider().authenticate(authentication));
    }

    /**
     * 客户端信息配置
     * 暂用内存模式
     */
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
        defaultTokenServices.setTokenStore(jwtTokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    /**
     * 使用jwtTokenStore存储token
     */
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public AuthenticationProvider daoAuhthenticationOauthProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(new UserPasswordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * 用于生成jwt
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // RSA非对称加密串，生成及加解密签名验签
        converter.setVerifierKey(CommonConstant.JWT_HMAC256_SECRET);
        converter.setSigningKey(CommonConstant.JWT_HMAC256_SECRET);
        //使用自定义的 TokenConverter
        converter.setAccessTokenConverter(new JwtCustomerAccessTokenConverter());
        return converter;
    }

//    /**
//     * 使用非对称加密算法对token签名
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setKeyPair(keyPair());
//        return converter;
//    }
//
//    /**
//     * 从classpath下的密钥库中获取密钥对(公钥+私钥)
//     */
//    @Bean
//    public KeyPair keyPair() {
//        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
//        KeyPair keyPair = factory.getKeyPair("jwt", "123456".toCharArray());
//        return keyPair;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}