package com.wly.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.domain.JudgeTopicResult;
import com.wly.dto.CompetitionTopicDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
* @author Slience
* @description 针对表【judge_topic_result(判断题题目结果表)】的数据库操作Mapper
* @createDate 2024-08-09 23:46:12
* @Entity generator.domain.JudgeTopicResult
*/
@Mapper
public interface JudgeTopicResultMapper extends BaseMapper<JudgeTopicResult> {

    void insertResults(Set<CompetitionTopicDto> topics);

}
