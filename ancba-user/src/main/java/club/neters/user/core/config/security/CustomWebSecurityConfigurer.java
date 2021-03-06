package club.neters.user.core.config.security;

import club.neters.user.core.annotation.AnonAllowed;
import club.neters.user.core.constant.CommonConstant;
import club.neters.user.core.util.HandlerMethodUtil;
import club.neters.user.core.util.JsonUtil;
import club.neters.user.domain.vo.ApiResultVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * ?????????????????????
 *
 * @author wuare
 * @date 2021/7/1
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter implements ApplicationContextAware {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .authenticationProvider(authenticationProvider());

        // ???????????????????????????
        http.authorizeRequests().accessDecisionManager(accessDecisionManager());
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        // ??????401/403?????????
        http.exceptionHandling().accessDeniedHandler(new AdminAccessDeineHandler());
        // ??????????????? - ??????????????????token???????????????????????????????????????????????????
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.anonymous().authenticationFilter(anonymousAuthenticationFilter());
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtDecoder());
        jwtAuthenticationProvider.setJwtAuthenticationConverter(jwtAuthenticationConverter());
        return jwtAuthenticationProvider;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(
                CommonConstant.JWT_HMAC256_SECRET.getBytes(StandardCharsets.UTF_8), "HMAC256"))
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // ???Jwt?????????????????????SecurityContext#authentication#authorities???
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(JsonUtil.toJson(ApiResultVo.forbidden("???????????????????????????")));
        };
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> voterList = new ArrayList<>();
        voterList.add(accessDecisionVoter());
        return new AffirmativeBased(voterList);
    }

    @Bean
    public AccessDecisionVoter<FilterInvocation> accessDecisionVoter() {
        return new CustomAccessDecisionVoter();
    }

    @Bean
    public AnonymousAuthenticationFilter anonymousAuthenticationFilter() {
        return new AnonymousAuthenticationFilter("ANONYMOUS") {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

                // TODO
                String[] urls = CommonConstant.SECURITY_WHITELIST;
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                if (HttpMethod.OPTIONS.matches(request.getMethod())) {
                    super.doFilter(req, res, chain);
                    return;
                }
                for (String url : urls) {
                    RequestMatcher pathMatcher = new AntPathRequestMatcher(url);
                    if (pathMatcher.matches(request)) {
                        super.doFilter(req, res, chain);
                        return;
                    }
                }

                // ??????????????????????????????
                HandlerMethod handlerMethod = HandlerMethodUtil.getHandlerMethod(request);
                if (handlerMethod != null) {
                    AnonAllowed annotation = handlerMethod.getMethodAnnotation(AnonAllowed.class);
                    if (annotation != null) {
                        super.doFilter(req, res, chain);
                        return;
                    }
                }

                String header = request.getHeader(HttpHeaders.AUTHORIZATION);
                if (header == null || "".equals(header)) {
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("text/javascript;charset=utf-8");
                    response.getWriter().print(JsonUtil.toJson(ApiResultVo.unauthorized("???????????????????????????")));
                    response.setStatus(401);
                    return;
                }
                super.doFilter(req, res, chain);
            }
        };
    }


    @Resource
    private ApplicationContext applicationContext;

}
