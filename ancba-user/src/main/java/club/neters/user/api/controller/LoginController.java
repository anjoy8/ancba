package club.neters.user.api.controller;

import club.neters.user.domain.vo.ApiResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * login controller
 *
 * @author wuare
 * @date 2021/6/25
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public ApiResultVo<String> login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            // TODO
            return ApiResultVo.ok("token0");
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ApiResultVo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ApiResultVo.error("认证失败");
        }
    }

    @RequiresRoles("admin")
    @GetMapping("/test")
    public ApiResultVo<String> test() {
        return ApiResultVo.ok("test");
    }

    @RequestMapping("/unAuth")
    public ApiResultVo<String> unAuth() {
        return ApiResultVo.error("未认证");
    }
}
