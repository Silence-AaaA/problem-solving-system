package com.wly.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.CompetitionResult;
import com.wly.domain.R;

/**
* @author Slience
* @description 针对表【competition_result(比赛结果表)】的数据库操作Service
* @createDate 2024-08-10 14:40:51
*/
public interface CompetitionResultService extends IService<CompetitionResult> {

    R getCompetitionResult(Long competitionId);
}
