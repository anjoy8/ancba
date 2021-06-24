package club.neters.blog.app.service.impl;

import club.neters.blog.domain.entity.TestMuchTableResult;
import club.neters.blog.infra.mapper.primary.TestMuchTableResultMapper;
import club.neters.blog.app.service.ITestMuchTableResultService;
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
public class TestMuchTableResultServiceImpl extends ServiceImpl<TestMuchTableResultMapper, TestMuchTableResult> implements ITestMuchTableResultService {

}
