package club.neters.shrio.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RestController
public class UserController {

    @ApiOperation(value = "测试授权AdminTest")
    @RequiresRoles({"AdminTest"})
    @GetMapping("/test")
    public String test(){
        return "xx";
    }

    @ApiOperation(value = "测试授权AdminTest2")
    @RequiresRoles({"AdminTest2"})
    @GetMapping("/test2")
    public String test2(){
        return "yy";
    }
}
