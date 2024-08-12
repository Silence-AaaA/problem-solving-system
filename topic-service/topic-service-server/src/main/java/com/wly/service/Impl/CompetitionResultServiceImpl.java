package com.wly.service.Impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.Competition;
import com.wly.domain.CompetitionParticipant;
import com.wly.domain.CompetitionResult;
import com.wly.domain.R;
import com.wly.dto.CompetitionResultDto;
import com.wly.mapper.CompetitionMapper;
import com.wly.mapper.CompetitionParticipantMapper;
import com.wly.mapper.CompetitionResultMapper;
import com.wly.service.CompetitionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Slience
* @description 针对表【competition_result(比赛结果表)】的数据库操作Service实现
* @createDate 2024-08-10 14:40:51
*/
@Service
public class CompetitionResultServiceImpl extends ServiceImpl<CompetitionResultMapper, CompetitionResult>
implements CompetitionResultService {

    @Autowired
    private CompetitionResultMapper competitionResultMapper;


    @Autowired
    private CompetitionMapper competitionMapper;

    @Autowired
    private CompetitionParticipantMapper competitionParticipantMapper;
    /**
     * 获取比赛结果
     * @param competitionId
     * @return
     */
    @Override
    public R getCompetitionResult(Long competitionId) {
//        查看是否有记录
        LambdaQueryWrapper<CompetitionResult> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(CompetitionResult::getCompetitionId, competitionId);
        Long count = competitionResultMapper.selectCount(lam);
        if (count == 0) {
//        查询是否比赛已经结束了
            Competition competition = competitionMapper.selectById(competitionId);
            Date endDate = competition.getEndDate();
            endDate.setTime(Long.valueOf(String.valueOf(endDate)) + 2);
            if (DateTime.now().isAfter(competition.getEndDate())) {
                if (DateTime.now().isAfter(endDate)) {
//                查询参赛者分数, 降序排序
                    LambdaQueryWrapper<CompetitionParticipant> lam0 = new LambdaQueryWrapper<>();
                    lam0 = lam0.eq(CompetitionParticipant::getCompetitionId, competition).orderByDesc(CompetitionParticipant::getScore);
                    List<CompetitionParticipant> competitionParticipants = competitionParticipantMapper.selectList(lam0);

//                    转化
                    CompetitionResultDto competitionResultDto = BeanUtil.copyProperties(competitionParticipants, CompetitionResultDto.class);
                    return R.ok(competitionResultDto);
                }
//            TODO 比赛结果清点
                return R.error("请耐心等待, 比赛结果正在统计当中");
            }
            return R.error("比赛尚未结束!");
        }
        LambdaQueryWrapper<CompetitionResult> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper = lambdaQueryWrapper.eq(CompetitionResult::getCompetitionId,competitionId);
        List<CompetitionResult> competitionResults = competitionResultMapper.selectList(lambdaQueryWrapper);
        return R.ok(competitionResults);
    }
}