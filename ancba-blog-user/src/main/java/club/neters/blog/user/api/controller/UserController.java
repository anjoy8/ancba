package club.neters.blog.user.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuare
 * @date 2021/6/25
 */
@RestController
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
