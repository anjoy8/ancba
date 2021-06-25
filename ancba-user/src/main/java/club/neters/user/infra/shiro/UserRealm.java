package club.neters.user.infra.shiro;

import club.neters.user.app.service.ISysUserInfoService;
import club.neters.user.domain.entity.SysUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * realm
 *
 * @author wuare
 * @date 2021/6/25
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private ISysUserInfoService sysUserInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Serializable id = SecurityUtils.getSubject().getSession().getId();
        System.out.println("token: " + id);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("user");
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("doc:create");
        simpleAuthorizationInfo.setRoles(roleSet);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
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
        return new SimpleAuthenticationInfo(userInfo, password, getName());
    }
}
