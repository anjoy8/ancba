package club.neters.blog.app.service;

import club.neters.blog.domain.entity.SysUserInfo;
import club.neters.blog.domain.request.user.UserInfoRequest;
import club.neters.blog.domain.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-23
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {

    List<UserInfoVo> findList(UserInfoRequest query);

    SysUserInfo findOne(String name, String pass);
}
