package club.neters.user.api.controller;

import club.neters.user.app.service.IRoleService;
import club.neters.user.app.service.ISysUserInfoService;
import club.neters.user.core.util.JwtUtil;
import club.neters.user.domain.entity.Role;
import club.neters.user.domain.entity.SysUserInfo;
import club.neters.user.domain.vo.ApiResultVo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * login controller
 *
 * @author wuare
 * @date 2021/6/25
 */
@Api(tags = "登录管理")
@RestController
public class LoginController {

    private final ISysUserInfoService sysUserInfoService;
    private final IRoleService roleService;

    @Autowired
    public LoginController(ISysUserInfoService sysUserInfoService, IRoleService roleService) {
        this.sysUserInfoService = sysUserInfoService;
        this.roleService = roleService;
    }

    @ApiOperation(value = "获取令牌")
    @GetMapping("/login")
    public ApiResultVo<String> login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ApiResultVo.error("用户名和密码不能为空");
        }

        try {
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            SysUserInfo sysUserInfo = sysUserInfoService.findOne(username, password);
            if (sysUserInfo != null) {

                List<Role> roleList = roleService.findAllByUId(sysUserInfo.getUID());
                Map<String, String> map = new HashMap<>();
                map.put("userId", sysUserInfo.getUID().toString());
                map.put("jti", sysUserInfo.getUID().toString());
                map.put("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name", username);
                String roles = roleList.stream().map(Role::getName).collect(Collectors.joining(","));
                map.put("role", roles);
                String tokenStr = JwtUtil.createToken(map);

                return ApiResultVo.ok(tokenStr);
            } else {
                return ApiResultVo.error("认证失败");
            }

        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ApiResultVo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ApiResultVo.error("认证失败");
        }
    }

    @RequestMapping("/unAuth")
    public ApiResultVo<String> unAuth() {
        return ApiResultVo.error("未认证");
    }
}
