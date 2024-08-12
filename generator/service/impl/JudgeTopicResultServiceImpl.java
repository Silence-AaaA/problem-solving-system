package generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import generator.domain.JudgeTopicResult;
import generator.service.JudgeTopicResultService;
import generator.mapper.JudgeTopicResultMapper;
import org.springframework.stereotype.Service;

/**
* @author Slience
* @description 针对表【judge_topic_result(判断题题目结果表)】的数据库操作Service实现
* @createDate 2024-08-12 09:57:21
*/
@Service
public class JudgeTopicResultServiceImpl extends ServiceImpl<JudgeTopicResultMapper, JudgeTopicResult>
implements JudgeTopicResultService{

}
