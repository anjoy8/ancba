package club.neters.blog.api.controller;

import club.neters.blog.app.service.ISysUserInfoService;
import club.neters.blog.core.annotation.Auth;
import club.neters.blog.domain.request.user.UserInfoRequest;
import club.neters.blog.domain.vo.ApiResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import club.neters.blog.domain.vo.user.UserInfoVo;

import java.util.List;

/**
 * 用户控制器
 *
 * @author laozhang
 * @date 2021/06/12
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    private final ISysUserInfoService sysUserInfoService;

    @Autowired
    public UserInfoController(ISysUserInfoService sysUserInfoService) {
        this.sysUserInfoService = sysUserInfoService;
    }


    /**
     * 列表查询
     *
     * @param query 请求参数
     * @return 用户列表
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping(value = "/list")
    @Auth(role = "user")
    public ApiResultVo<List<UserInfoVo>> listPage(UserInfoRequest query) {
        List<UserInfoVo> list = sysUserInfoService.findList(query);
        return ApiResultVo.ok(list);
    }

}
