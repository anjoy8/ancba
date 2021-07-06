package club.neters.blog.api.controller;

import club.neters.blog.app.service.IBlogArticleService;
import club.neters.blog.domain.entity.BlogArticle;
import club.neters.blog.domain.request.user.UserInfoRequest;
import club.neters.blog.domain.vo.ApiResultVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 *
 * @author laozhang
 * @date 2021/06/12
 */
@Api(tags = "用户管理")
@RestController
public class BlogController {


    private IBlogArticleService blogArticleService;

    @Autowired
    public BlogController(IBlogArticleService blogArticleService) {
        this.blogArticleService = blogArticleService;
    }


    /**
     * 列表查询
     *
     * @param query 请求参数
     * @return 用户列表
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping(value = "list")
    public ApiResultVo<List<BlogArticle>> listPage(UserInfoRequest query) {
        LambdaQueryWrapper<BlogArticle> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(BlogArticle::getIsDeleted, false)
                .orderByDesc(BlogArticle::getBID)
                .last("limit 3");

        List<BlogArticle> list = blogArticleService.list(wrapper);
        return ApiResultVo.ok(list);
    }

    @PreAuthorize("hasAnyAuthority('AdminTest')")
    @ApiOperation(value = "测试接口授权")
    @GetMapping(value = "test")
    public String test() {
        return "当前角色需要admin权限，恭喜你访问成功了!";
    }

}
