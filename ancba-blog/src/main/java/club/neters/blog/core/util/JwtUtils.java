package club.neters.blog.core.util;

import club.neters.blog.core.constant.CommonConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {

    /**
     * 生成token header.payload.sing
     */
    public static String getToken(Map<String, String> map) {
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
     * 验证 token 合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(CommonConstant.JWT_HMAC256_SECRET)).build().verify(token);
    }

}