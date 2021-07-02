package club.neters.user.core.config.security;

import club.neters.user.core.annotation.AnonAllowed;
import club.neters.user.core.constant.CommonConstant;
import club.neters.user.core.util.HandlerMethodUtil;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author wuare
 * @date 2021/7/1
 */
public class CustomAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation invocation, Collection<ConfigAttribute> attributes) {
        HttpServletRequest request = invocation.getRequest();
        // OPTIONS全放开
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return ACCESS_GRANTED;
        }
        // 匿名注解允许无权限访问
        HandlerMethod handlerMethod = HandlerMethodUtil.getHandlerMethod(request);
        if (handlerMethod != null) {
            AnonAllowed annotation = handlerMethod.getMethodAnnotation(AnonAllowed.class);
            if (annotation != null) {
                return ACCESS_GRANTED;
            }
        }

        // TODO 扩展
        String[] whiteList = CommonConstant.SECURITY_WHITELIST;
        String[] permissionRequests = {"/test"};

        // 白名单全放开
        for (String url : whiteList) {
            RequestMatcher pathMatcher = new AntPathRequestMatcher(url);
            if (pathMatcher.matches(request)) {
                return ACCESS_GRANTED;
            }
        }

        // 开始处理授权验证逻辑
        // TODO 此处可从数据库扩展处理，也可以使用任意其他的声明
        String userNameVal = "";
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            userNameVal = String.valueOf(jwt.getClaims().get("username"));
        } catch (Exception ignored) {
        }
        for (String url : permissionRequests) {
            RequestMatcher pathMatcher = new AntPathRequestMatcher(url);
            if (pathMatcher.matches(request) && userNameVal.equals("test")) {
                return ACCESS_GRANTED;
            }
        }
        return ACCESS_DENIED;
    }
}
