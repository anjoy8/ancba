package club.neters.shrio.demo.api.controller;

import club.neters.shrio.demo.domain.vo.ApiResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * login controller
 *
 * @author wuare
 * @date 2021/6/25
 */
@Api(tags = "登录管理")
@RestController
public class LoginController {

    @ApiOperation(value = "获取令牌")
    @GetMapping("/login")
    public ApiResultVo<String> login(String username, String password) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return ApiResultVo.ok((String) subject.getPrincipal());
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ApiResultVo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ApiResultVo.error("认证失败");
        }
    }

    /**
     * 退出
     */
    @ApiOperation(value = "退出")
    @GetMapping("/logout1")
    public ApiResultVo<String> logout1() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ApiResultVo.ok("退出成功");
    }
}
