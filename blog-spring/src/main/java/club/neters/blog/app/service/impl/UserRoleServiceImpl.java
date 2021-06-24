package club.neters.blog.app.service.impl;

import club.neters.blog.app.service.IUserRoleService;
import club.neters.blog.domain.entity.UserRole;
import club.neters.blog.infra.mapper.primary.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
