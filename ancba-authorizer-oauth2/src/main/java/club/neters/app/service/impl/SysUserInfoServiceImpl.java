package club.neters.app.service.impl;

import club.neters.app.service.ISysUserInfoService;
import club.neters.domain.entity.SysUserInfo;
import club.neters.infra.mapper.primary.SysUserInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-25
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {

    private final SysUserInfoMapper sysUserInfoMapper;

    @Autowired
    public SysUserInfoServiceImpl(SysUserInfoMapper sysUserInfoMapper){
        this.sysUserInfoMapper = sysUserInfoMapper;
    }

    @Override
    public SysUserInfo findOne(String name, String pass) {
        LambdaQueryWrapper<SysUserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUserInfo::getTdIsDelete, 0)
                .eq(SysUserInfo::getULoginName, name)
                .eq(SysUserInfo::getULoginPWD, pass.toUpperCase());
        return getOne(wrapper);
    }

    @Override
    public List<SysUserInfo> allSysUserInfo(Integer uID) {
        return sysUserInfoMapper.allSysUserInfo(uID);
    }
}
