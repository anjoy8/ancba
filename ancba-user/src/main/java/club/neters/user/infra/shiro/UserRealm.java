package club.neters.user.infra.shiro;

import club.neters.user.app.service.ISysUserInfoService;
import club.neters.user.core.constant.CommonConstant;
import club.neters.user.core.util.JwtUtil;
import club.neters.user.domain.entity.Role;
import club.neters.user.domain.entity.SysUserInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * realm
 *
 * @author wuare
 * @date 2021/6/25
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    private ISysUserInfoService sysUserInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        SysUserInfo user = (SysUserInfo) principalCollection.getPrimaryPrincipal();
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<>();
        List<SysUserInfo> sysUserInfos = sysUserInfoService.allSysUserInfo(user.getUID());
        for (Role role : sysUserInfos.get(0).getRoles()) {
            //2.1添加角色
            simpleAuthorizationInfo.addRole(role.getName());
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tokenPrincipal = (String) authenticationToken.getPrincipal();
        String token = tokenPrincipal.replace("Bearer ", "");

        if (!JwtUtil.isNotExpired(token)) {
            throw new UnknownAccountException("token 已过期！");
        }
        SysUserInfo userInfo = new SysUserInfo();
        try {
            Algorithm algorithm = Algorithm.HMAC256(CommonConstant.JWT_HMAC256_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(CommonConstant.JWT_ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            String userId = Optional.ofNullable(claims.get("userId")).map(Claim::asString).orElse("");
            userInfo = sysUserInfoService.getOne(new LambdaQueryWrapper<SysUserInfo>()
                    .eq(SysUserInfo::getTdIsDelete, false).eq(SysUserInfo::getUID, userId), false);
            if (userInfo == null) {
                throw new UnknownAccountException("用户不存在!");
            }
        } catch (JWTVerificationException e) {
            throw new UnknownAccountException("用户不存在!");
        }

        return new SimpleAuthenticationInfo(userInfo, tokenPrincipal, getName());
    }
}
