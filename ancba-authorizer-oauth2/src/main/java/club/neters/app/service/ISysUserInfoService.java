package club.neters.app.service;

import club.neters.domain.entity.SysUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-30
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {
    SysUserInfo findOne(String name, String pass);
    List<SysUserInfo> allSysUserInfo(Integer uID);
}
