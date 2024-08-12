package com.wly.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.JudgeTopicResult;
import com.wly.domain.R;
import org.springframework.stereotype.Service;

/**
 * @author Slience
 * @description 针对表【judge_topic_result(判断题题目结果表)】的数据库操作Service
 * @createDate 2024-08-09 23:46:12
 */
@Service
public interface JudgeTopicResultService extends IService<JudgeTopicResult> {

    R commit(Long topicId, String result);
}
