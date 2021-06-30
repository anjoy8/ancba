package club.neters.app.service;

import club.neters.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-30
 */
public interface IRoleService extends IService<Role> {
    List<Role> findAllByUId(Integer uID);
}
