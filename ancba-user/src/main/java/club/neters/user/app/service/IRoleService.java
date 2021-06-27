package club.neters.user.app.service;

import club.neters.user.domain.entity.Role;
import club.neters.user.domain.entity.SysUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-23
 */
public interface IRoleService extends IService<Role> {
    List<Role> findAllByUId(Integer uID);
}
