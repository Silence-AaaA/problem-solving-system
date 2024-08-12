package com.wly.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.CompetitionParticipant;
import com.wly.domain.R;

/**
* @author Slience
* @description 针对表【competition_participant(比赛参与者表)】的数据库操作Service
* @createDate 2024-08-10 14:40:51
*/
public interface CompetitionParticipantService extends IService<CompetitionParticipant> {

    R joinCompetition(Long id);
}
