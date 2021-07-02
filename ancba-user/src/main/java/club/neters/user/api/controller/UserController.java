package club.neters.user.api.controller;

import club.neters.user.core.annotation.AnonAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuare
 * @date 2021/7/1
 */
@RestController
public class UserController {

    @GetMapping("/test")
    @AnonAllowed
    public String test() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication.getAuthorities());
        return "test";
    }

    @GetMapping("/test1")
    public String test1() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        System.out.println(authentication.getAuthorities());
        return "test1";
    }
}
