package club.neters.user.infra.shiro;

import club.neters.user.app.service.IRoleService;
import club.neters.user.app.service.ISysUserInfoService;
import club.neters.user.core.util.JwtUtil;
import club.neters.user.domain.entity.Role;
import club.neters.user.domain.entity.SysUserInfo;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private IRoleService roleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //如果用户未登录返回没有权限
        String token = principalCollection.toString();
        if (!(StringUtils.isNotEmpty(token) && JwtUtil.isNotExpired(token))) {
            return simpleAuthorizationInfo;
        }
        //添加角色和权限
        List<String> roleList = JwtUtil.getRoleFromToken(token);
        if (CollectionUtils.isEmpty(roleList)) {
            return simpleAuthorizationInfo;
        }
        for (String role : roleList) {
            simpleAuthorizationInfo.addRole(role);
            // 也可以进一步追加permission权限
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken instanceof UsernamePasswordToken) {
            String userName = (String) authenticationToken.getPrincipal();
            if (StringUtils.isBlank(userName) || (authenticationToken.getCredentials() == null)) {
                throw new UnknownAccountException("用户名或密码错误");
            }
            String password = new String((char[]) authenticationToken.getCredentials());

            SysUserInfo userInfo = sysUserInfoService.getOne(new LambdaQueryWrapper<SysUserInfo>()
                    .eq(SysUserInfo::getTdIsDelete, false).eq(SysUserInfo::getULoginName, userName), false);
            if (userInfo == null) {
                throw new UnknownAccountException("用户名或密码错误");
            }
            String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            if (!md5Pwd.equalsIgnoreCase(userInfo.getULoginPWD())) {
                throw new IncorrectCredentialsException("用户名或密码错误");
            }

            List<Role> roles = roleService.findAllByUId(userInfo.getUID());
            Map<String, String> map = new HashMap<>();
            map.put("userId", userInfo.getUID().toString());
            map.put("jti", userInfo.getUID().toString());
            map.put("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name", userInfo.getULoginName());
            map.put("role", roles.stream().map(Role::getName).collect(Collectors.joining(",")));
            String tokenStr = JwtUtil.createToken(map);
            return new SimpleAuthenticationInfo(tokenStr, password, getName());

        } else if (authenticationToken instanceof BearerToken) {
            String token = ((BearerToken) authenticationToken).getToken();
            try {
                DecodedJWT decodedJWT = JwtUtil.verify(token);
                return new SimpleAuthenticationInfo(decodedJWT, authenticationToken.getCredentials(), getName());
            } catch (JWTVerificationException e) {
                throw new AuthenticationException(e);
            }
        }

        throw new AuthenticationException("认证失败");
    }
}
