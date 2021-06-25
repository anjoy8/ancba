package club.neters.blog.api.controller;

import club.neters.blog.core.util.JwtUtils;
import club.neters.blog.domain.entity.SysUserInfo;
import club.neters.blog.domain.vo.ApiResultVo;
import club.neters.blog.app.service.ISysUserInfoService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author laozhang
 * @date 2021/06/19
 */
@Api(tags = "登录管理")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final ISysUserInfoService sysUserInfoService;

    @Autowired
    public LoginController(ISysUserInfoService sysUserInfoService) {
        this.sysUserInfoService = sysUserInfoService;
    }

    /**
     * 登录
     */
    @ApiOperation(value = "获取令牌")
    @GetMapping(value = "/JWTToken3.0")
    public ApiResultVo<String> getJwtToken(String name, String pass) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(pass)) {
            return ApiResultVo.error("用户名和密码不能为空");
        }
        pass = DigestUtils.md5DigestAsHex(pass.getBytes());
        SysUserInfo sysUserInfo = sysUserInfoService.findOne(name, pass);
        if (sysUserInfo != null) {
            // ToDo 将该用户所有的角色权限管理存放到内存中

            Map<String, String> map = new HashMap<>();
            map.put("userId", sysUserInfo.getUID().toString());
            map.put("jti", sysUserInfo.getUID().toString());
            map.put("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name", name);
            map.put("role", "user");
            String token = JwtUtils.getToken(map);
            return ApiResultVo.ok(token);
        } else {
            return ApiResultVo.error("认证失败");
        }
    }
}
