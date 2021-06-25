package club.neters.blog.app.service.impl;

import club.neters.blog.app.service.ISysUserInfoService;
import club.neters.blog.infra.mapper.primary.SysUserInfoMapper;
import club.neters.blog.domain.entity.SysUserInfo;
import club.neters.blog.domain.request.user.UserInfoRequest;
import club.neters.blog.domain.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-23
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {

    @Override
    public List<UserInfoVo> findList(UserInfoRequest query) {
        LambdaQueryWrapper<SysUserInfo> wrapper = createWrapper(query);

        // 数据处理
        List<SysUserInfo> list = list(wrapper);

        return list.stream().map(v -> {
            UserInfoVo item = new UserInfoVo();
            BeanUtils.copyProperties(v, item);
            return item;
        }).collect(Collectors.toList());
    }

    @Override
    public SysUserInfo findOne(String name, String pass) {
        LambdaQueryWrapper<SysUserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUserInfo::getTdIsDelete, 0)
                .eq(SysUserInfo::getULoginName, name)
                .eq(SysUserInfo::getULoginPWD, pass.toUpperCase());
        return getOne(wrapper);
    }

    /**
     * 整理查询条件
     *
     * @param query 入参
     */
    private LambdaQueryWrapper<SysUserInfo> createWrapper(UserInfoRequest query) {

        LambdaQueryWrapper<SysUserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUserInfo::getTdIsDelete, 0);

        // 自定义条件
        if (query != null) {

            if (!ObjectUtils.isEmpty(query.getUID())) {
                wrapper.eq(SysUserInfo::getUID, query.getUID());
            }

            if (!ObjectUtils.isEmpty(query.getName())) {
                wrapper.like(SysUserInfo::getName, query.getName());
            }

            if (StringUtils.isNotBlank(query.getULoginName())) {
                wrapper.eq(SysUserInfo::getULoginName, query.getULoginName());
            }

            if (StringUtils.isNotBlank(query.getURealName())) {
                wrapper.eq(SysUserInfo::getURealName, query.getURealName());
            }

        }

        return wrapper;
    }
}
