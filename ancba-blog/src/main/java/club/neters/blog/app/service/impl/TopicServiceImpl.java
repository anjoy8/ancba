package club.neters.blog.app.service.impl;

import club.neters.blog.app.service.ITopicService;
import club.neters.blog.domain.entity.Topic;
import club.neters.blog.infra.mapper.primary.TopicMapper;
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
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

}
