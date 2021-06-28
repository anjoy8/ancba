package club.neters.core.config;

import club.neters.core.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("clientapp-res") // 配置资源id，这里的资源id和授权服务器中的资源id一致
                .tokenStore(tokenStore) //指定保存token的方式
                .tokenServices(tokenServices)
                .stateless(true); // 设置这些资源仅基于令牌认证
    }

    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //指定不同请求方式访问资源所需要的权限，一般查询是read，其余是write。
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .headers().addHeaderWriter((request, response) -> {
            response.addHeader("Access-Control-Allow-Origin", "*");//允许跨域
            if (request.getMethod().equals("OPTIONS")) {//如果是跨域的预检请求，则原封不动向下传达请求头信息
                response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
                response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
            }
        });
    }
}