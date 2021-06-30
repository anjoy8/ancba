package club.neters.infra.mapper.primary;


import club.neters.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author laozhang
 * @since 2021-06-23
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from Role where Id in (select RoleId from UserRole where UserId = #{uID})")
    List<Role> findAllByUId(Integer uID);
}
