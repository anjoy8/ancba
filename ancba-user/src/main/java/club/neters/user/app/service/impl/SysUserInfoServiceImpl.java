package club.neters.user.app.service.impl;

import club.neters.user.domain.entity.SysUserInfo;
import club.neters.user.infra.mapper.primary.SysUserInfoMapper;
import club.neters.user.app.service.ISysUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-25
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {

}
