package club.neters.blog.app.service.impl;

import club.neters.blog.domain.entity.PasswordLib;
import club.neters.blog.infra.mapper.primary.PasswordLibMapper;
import club.neters.blog.app.service.IPasswordLibService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * WMBLOG_MSSQL_2 服务实现类
 * </p>
 *
 * @author laozhang
 * @since 2021-06-23
 */
@Service
public class PasswordLibServiceImpl extends ServiceImpl<PasswordLibMapper, PasswordLib> implements IPasswordLibService {

}
