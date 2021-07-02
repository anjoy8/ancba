package club.neters.user.app.service;

import club.neters.user.core.config.feign.FeignConfig;
import club.neters.user.domain.request.UserInfoRequestFromBlog;
import club.neters.user.domain.vo.ApiResultVo;
import club.neters.user.domain.vo.UserInfoVoFromBlog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "ancba-blog", configuration = {FeignConfig.class})
public interface UserApiService {
    /**
     * 查询用户数据
     */
    @RequestMapping(value = "/api/user/list", method = RequestMethod.GET)
    ApiResultVo<List<UserInfoVoFromBlog>> loadUserListPage(UserInfoRequestFromBlog query);
}
