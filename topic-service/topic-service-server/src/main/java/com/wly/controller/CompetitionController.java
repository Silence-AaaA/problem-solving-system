package com.wly.controller;

import com.wly.domain.Competition;
import com.wly.domain.CompetitionResult;
import com.wly.domain.R;
import com.wly.dto.CompetitionTopicDto;
import com.wly.service.CompetitionParticipantService;
import com.wly.service.CompetitionResultService;
import com.wly.service.CompetitionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private CompetitionParticipantService competitionParticipantService;

    @Autowired
    private CompetitionResultService competitionResultService;

    /**
     * 根据ID查询比赛信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R selectById(@Param("id") @PathVariable Long id) {
        return competitionService.getCompetitionById(id);
    }

    /**
     * 查询比赛所有信息
     * @return
     */
    @GetMapping
    public R selectAll(){
        return R.ok(competitionService.list());
    }


    /**
     * 删除比赛
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
//    TODO 加权 M
    public R deleteById(@Param("id") @PathVariable Long id){
        return competitionService.deleteById(id);
    }


    /**
     * 修改比赛
     * @param competition
     * @return
     */

    @PutMapping
//    TODO 加权 M
    public R update(@Valid @RequestBody Competition competition){
        return competitionService.updateCompetition(competition);
    }

    /**
     * 添加比赛
     * @param competition
     * @return
     */
    @PostMapping
//    TODO 加权  Manager
    public R save(@Valid @RequestBody Competition competition){
        return competitionService.saveCompetition(competition);
    }

    /**
     * 参加比赛
     * @param id
     * @return
     */
    @PostMapping("/participant/{id}")
    public R joinCompetition(@Param("id") @PathVariable Long id){
        return competitionParticipantService.joinCompetition(id);
    }

    /**
     * 单个添加添加比赛题目
     * @param topics
     * @return
     */
    @PostMapping("/topic/{id}")
    public R saveCompetitionTopic(@Param("题目") @RequestParam Set<CompetitionTopicDto> topics, @Param("id")@PathVariable Long id){
        return competitionService.saveCompetitionTopic(topics,id);
    }

    /**
     * 提交比赛答案
     * @param competitionId
     * @param topicId
     * @return
     */
    @PostMapping("/commit/{competitionId}/{topicId}/{result}")
    public R commitTopic(@Param("competitionId") @PathVariable Long competitionId,@Param("topicId") @PathVariable Long topicId
    ,@Param("result") @PathVariable String result){
        return competitionService.commitTopic(competitionId,topicId,result);
    }

    /**
     * 根据ID获取比赛的所有题目
     * @param competitionId
     * @return
     */
    @GetMapping("/topic/{competitionId}")
    public R getCompetitionTopics(@Param("competitionId") @PathVariable Long competitionId){
        return competitionService.getCompetitionTopics(competitionId);
    }

    /**
     * 获取比赛结果
     * @param competitionId
     * @return
     */
    @GetMapping("/result/{competitionId}")
    public R getCompetitionResult(@Param("competitionId") @PathVariable Long competitionId){
        return competitionResultService.getCompetitionResult(competitionId);
    }
}
