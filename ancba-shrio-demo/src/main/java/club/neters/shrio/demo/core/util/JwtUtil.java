package club.neters.shrio.demo.core.util;

import club.neters.shrio.demo.core.constant.CommonConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class JwtUtil {

    /**
     * 生成token header.payload.sing
     */
    public static String createToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 1);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach(builder::withClaim);
        //生成token，头部默认jwt和Base64编码
        return builder
                .withExpiresAt(instance.getTime())   //设置过期时间
                .withIssuer(CommonConstant.JWT_ISSUER)   //设置issuer
                .sign(Algorithm.HMAC256(CommonConstant.JWT_HMAC256_SECRET));
    }

    /**
     * 获取请求的token
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter(HttpHeaders.AUTHORIZATION);
        }
        return token;
    }

    /**
     * 验证 token 合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(CommonConstant.JWT_HMAC256_SECRET)).build().verify(token);
    }

    /**
     * 验证 token 是否过期
     */
    public static boolean isNotExpired(String token) {
        try {
            token = token.replace("Bearer ","");
            DecodedJWT jwt = verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Integer exp = Optional.ofNullable(claims.get("exp")).map(Claim::asInt).orElse(0);
            return System.currentTimeMillis() / 1000L <= exp;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    /**
     * 获取当前token下的角色列表
     */
    public static List<String> getRoleFromToken(String token) {
        token = token.replace("Bearer ","");
        Algorithm algorithm = Algorithm.HMAC256(CommonConstant.JWT_HMAC256_SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(CommonConstant.JWT_ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        String roles = Optional.ofNullable(claims.get("role")).map(Claim::asString).orElse("");
        return  StringUtils.isNotEmpty(roles) ? Arrays.asList(roles.split(",")) : null;
    }
}