package club.neters.app.service;

import club.neters.domain.dto.User;
import club.neters.domain.entity.Role;
import club.neters.domain.entity.SysUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {
    @Resource
    private ISysUserInfoService sysUserInfoService;

    @Resource
    private IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = new User();
        try {
            if (StringUtils.isBlank(userName)) {
                throw new UsernameNotFoundException("用户名为空！");
            }

            SysUserInfo userInfo = sysUserInfoService.getOne(new LambdaQueryWrapper<SysUserInfo>()
                    .eq(SysUserInfo::getTdIsDelete, false).eq(SysUserInfo::getULoginName, userName), false);
            if (userInfo == null) {
                throw new UsernameNotFoundException("用户数据不存在");
            }

            // 将用户的用户名+密码赋值服务
            String password = userInfo.getULoginPWD();
            user.setUserName(userInfo.getULoginName());
            user.setUserPassword(password);

            // 获取角色
            List<Role> roles = roleService.findAllByUId(userInfo.getUID());

            Set authoritiesSet = new HashSet();
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authoritiesSet.add(authority);
            }

            user.setAuthorities(authoritiesSet);

            return user;
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("用户数据有误！");
        } finally {
        }
    }
}