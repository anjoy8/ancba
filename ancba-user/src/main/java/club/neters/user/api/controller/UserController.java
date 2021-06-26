package club.neters.user.api.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequiresRoles({"AdminTest"})
    @GetMapping("/test")
    public String test(){
        return "xx";
    }
}
