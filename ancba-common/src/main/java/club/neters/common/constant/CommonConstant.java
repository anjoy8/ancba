package club.neters.common.constant;

public class CommonConstant {
    public static final String JWT_HMAC256_SECRET = "sdfsdfsrty45634kkhllghtdgdfss345t678fs";
    public static final String JWT_ISSUER = "Blog.Core";
    public static final String AUTHORIZATION_PREFIX = "Bearer";
    public static final String REDIS_SECURITY_ROLE_API_KEY = "security:roles_apis:";
    public static final String[] GW_SECURITY_WHITELIST = {"/", "/doc.html", "/oauth/**",
            "/*/v2/api-docs",
            "/actuator/**", "/error", "/open/api", "/actuator/health", "/actuator", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/v2/**", "/error"};
}
