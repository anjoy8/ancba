package club.neters.user.core.config.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

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
        RequestMatcher pathMatcher = new AntPathRequestMatcher("/test");
        if (pathMatcher.matches(invocation.getRequest())) {
            return ACCESS_GRANTED;
        }
        return ACCESS_DENIED;
    }
}
