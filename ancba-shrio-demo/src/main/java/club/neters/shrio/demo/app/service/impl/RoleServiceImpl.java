package club.neters.shrio.demo.app.service.impl;

import club.neters.shrio.demo.infra.mapper.primary.RoleMapper;
import club.neters.shrio.demo.app.service.IRoleService;
import club.neters.shrio.demo.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper){
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> findAllByUId(Integer uID) {
        return roleMapper.findAllByUId(uID);
    }
}
