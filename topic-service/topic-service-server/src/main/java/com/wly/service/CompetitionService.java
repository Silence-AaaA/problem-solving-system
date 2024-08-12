package com.wly.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.Competition;
import com.wly.domain.R;
import com.wly.dto.CompetitionTopicDto;

import java.util.Set;

/**
* @author Slience
* @description 针对表【competition(比赛表)】的数据库操作Service
* @createDate 2024-08-10 14:40:51
*/
public interface CompetitionService extends IService<Competition> {

    R getCompetitionById(Long id);

    R deleteById(Long id);

    R updateCompetition(Competition competition);

    R saveCompetition(Competition competition);

    R commitTopic(Long competitionId, Long topicId, String result);

    R saveCompetitionTopic(Set<CompetitionTopicDto> topics, Long id);

    R getCompetitionTopics(Long competitionId);
}
