package club.neters.blog.core.Interceptor;

import club.neters.blog.core.annotation.Auth;
import club.neters.blog.core.constant.CommonConstant;
import club.neters.blog.core.util.JsonUtil;
import club.neters.blog.domain.vo.ApiResultVo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class UserLoginAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 请求不是控制器的方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth authAnnotation = handlerMethod.getMethodAnnotation(Auth.class);

        // 不包含 @Auth 注解
        if (authAnnotation == null) {
            return true;
        }

        // 验证token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(token)) {
            printErrorMsg(response, "未登录，请先登录！");
            return false;
        }

        try {
            token = token.replace("Bearer ","");
            Algorithm algorithm = Algorithm.HMAC256(CommonConstant.JWT_HMAC256_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(CommonConstant.JWT_ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            String role = Optional.ofNullable(claims.get("role")).map(Claim::asString).orElse("");
            Integer exp = Optional.ofNullable(claims.get("exp")).map(Claim::asInt).orElse(0);
            log.info(role);
            if (!authAnnotation.role().equals(role)) {
                printErrorMsg(response, "请确保有查看此接口的权限！");
                return false;
            }
            if (System.currentTimeMillis() / 1000L > exp) {
                printErrorMsg(response, "令牌已过期");
                return false;
            }
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            log.error("异常信息", exception);
            printErrorMsg(response, "认证失败，请确保已经登录！");
            return false;
        }

        return true;
    }


    private void printErrorMsg(HttpServletResponse response, String msg) {
        try {
            ApiResultVo<Object> resultVo = ApiResultVo.error(msg);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JsonUtil.toJson(resultVo));
        } catch (IOException e) {
            log.error("异常信息", e);
        }
    }
}