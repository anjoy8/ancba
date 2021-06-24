package club.neters.blog.app.service.impl;

import club.neters.blog.domain.entity.OperateLog;
import club.neters.blog.infra.mapper.primary.OperateLogMapper;
import club.neters.blog.app.service.IOperateLogService;
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
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements IOperateLogService {

}
