package com.wly.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.domain.CompetitionTopic;
import com.wly.dto.CompetitionTopicDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
* @author Slience
* @description 针对表【competition_topic】的数据库操作Mapper
* @createDate 2024-08-12 10:06:37
* @Entity generator.domain.CompetitionTopic
*/
@Mapper
public interface CompetitionTopicMapper extends BaseMapper<CompetitionTopic> {

    void insertTopics(Long id, Set<CompetitionTopicDto> topics);
}
