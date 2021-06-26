package club.neters.user.infra.mapper.primary;

import club.neters.user.domain.entity.SysUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author laozhang
 * @since 2021-06-25
 */
public interface SysUserInfoMapper extends BaseMapper<SysUserInfo> {

    @Select("select * from sysUserInfo where uID = #{uID}")
    @Results(id="UserMap",value={
            @Result(property = "roles",column = "uID",
                    many = @Many(select = "club.neters.user.infra.mapper.primary.RoleMapper.findAllByUId",
                            fetchType = FetchType.LAZY))
    })
    List<SysUserInfo> allSysUserInfo(Integer uID);
}
