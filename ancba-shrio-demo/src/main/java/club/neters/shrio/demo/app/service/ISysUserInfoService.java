package club.neters.shrio.demo.app.service;

import club.neters.shrio.demo.domain.entity.SysUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-25
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {
    SysUserInfo findOne(String name, String pass);
    List<SysUserInfo> allSysUserInfo(Integer uID);
}
