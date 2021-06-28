package club.neters.user.infra.shiro;


import club.neters.user.core.constant.CommonConstant;
import club.neters.user.core.util.HttpContextUtil;
import club.neters.user.core.util.JsonUtil;
import club.neters.user.core.util.JwtUtil;
import club.neters.user.domain.vo.ApiResultVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        ApiResultVo<Object> resultVo = ApiResultVo.error("授权认识失败");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        // 自定义返回内容
        response.getWriter().write(JsonUtil.toJson(resultVo));
    }

    /**
     * 生成自定义token
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        //获取请求token
        String token = JwtUtil.getRequestToken((HttpServletRequest) request);
        if (token != null) {
            token = token.replace("Bearer ", "");
        }
        return new BearerToken(token);
    }


    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(CommonConstant.JWT_TOKEN_HEADER);
        return authorization != null;
    }

    /**
     * 步骤1.所有请求全部拒绝访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    /**
     * 步骤2，拒绝访问的请求，会调用onAccessDenied方法，
     * onAccessDenied方法先获取 token，
     * 再调用executeLogin方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回
        String token = JwtUtil.getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            Map<String, Object> result = new HashMap<>();
            result.put("status", 400);
            result.put("msg", "请先登录");
            httpResponse.getWriter().print(JsonUtil.toJson(result));
            return false;
        }
        return executeLogin(request, response);
    }

    /**
     * token失效时候调用
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Map<String, Object> result = new HashMap<>();
            result.put("status", 400);
            result.put("msg", "登录凭证已失效，请重新登录");
            httpResponse.getWriter().print(JsonUtil.toJson(result));
        } catch (IOException ignored) {
        }
        return false;
    }

}
