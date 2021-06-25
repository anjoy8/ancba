package club.neters.user.infra.shiro;

import club.neters.user.core.util.JsonUtil;
import club.neters.user.domain.vo.ApiResultVo;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpHeaders;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author wuare
 * @date 2021/6/25
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION) != null) {
            try {
                // 验证token
                return true;
            } catch (Exception e) {
                // token error
                try {
                    response.getWriter().write(JsonUtil.toJson(ApiResultVo.error(e.getMessage())));
                } catch (IOException ex) {
                    // ignore
                }
                return false;
            }
        }
        return false;
    }

    // TODO custom subject ?
    // TODO ensure subject.isAuthenticated() && subject.getPrincipal() != null
    // TODO verify token from redis
    @Override
    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return super.getSubject(request, response);
    }
}
