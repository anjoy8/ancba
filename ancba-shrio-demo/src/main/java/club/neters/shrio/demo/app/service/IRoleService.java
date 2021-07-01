package club.neters.shrio.demo.app.service;

import club.neters.shrio.demo.domain.entity.Role;
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
