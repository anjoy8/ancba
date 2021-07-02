package club.neters.common.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

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
        // TODO 扩展
        String[] urls = {"/swagger-ui.html", "/swagger-resources/**",
                "/webjars/**", "/v2/**"};
        HttpServletRequest request = invocation.getRequest();
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return ACCESS_GRANTED;
        }
        for (String url : urls) {
            RequestMatcher pathMatcher = new AntPathRequestMatcher(url);
            if (pathMatcher.matches(request)) {
                return ACCESS_GRANTED;
            }
        }
        return ACCESS_DENIED;
    }
}
