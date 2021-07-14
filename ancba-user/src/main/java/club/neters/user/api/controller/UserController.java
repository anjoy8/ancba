package club.neters.user.api.controller;

import club.neters.user.app.service.UserApiService;
import club.neters.user.core.annotation.AnonAllowed;
import club.neters.user.domain.entity.BlogArticle;
import club.neters.user.domain.request.UserInfoRequestFromBlog;
import club.neters.user.domain.vo.ApiResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wuare
 * @date 2021/7/1
 */
@RestController
public class UserController {
    @Autowired
    private UserApiService userApiService;

    @GetMapping("/test")
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
        System.out.println(authentication.getAuthorities());
        return "test1";
    }

    @GetMapping("/user/testNo")
    @AnonAllowed
    public ApiResultVo<List<BlogArticle>> testNo() {
        return userApiService.loadUserListPage(new UserInfoRequestFromBlog());
    }
}
