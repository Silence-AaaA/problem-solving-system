package com.wly.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wly.domain.R;
import com.wly.domain.TopicCommitLog;
import com.wly.domain.UserWarehouse;
import com.wly.service.JudgeTopicResultService;
import com.wly.service.TopicCommitLogService;
import com.wly.service.UserWarehouseService;
import com.wly.utils.UserContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicResult")
public class TopicResultController {

    @Autowired
    private JudgeTopicResultService judgeTopicResultService;

    @Autowired
    private TopicCommitLogService topicCommitLogService;

    @Autowired
    private UserWarehouseService userWarehouseService;

    /**
     * 提交题目答案
     *
     * @param id     题目id
     * @param result 题目结果
     * @return
     */
    @PostMapping("/commit/{id}")
    @PreAuthorize("hasAuthority('oj:topic:user')")
    public R topicCommit(@Param("id") @PathVariable Long id, @RequestParam("result") String result) {
        return judgeTopicResultService.commit(id, result);
    }

    /**
     * @return 获取用户的所有题目提交日志信息
     */
    @GetMapping("/commit/logs")
    @PreAuthorize("hasAuthority('oj:topic:user')")
    public R getCommitLog() {
        LambdaQueryWrapper<TopicCommitLog> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(TopicCommitLog::getCreator, UserContext.getUser());
        List<TopicCommitLog> list = topicCommitLogService.list(lam);
        return R.ok(list);
    }

    /**
     * @return 获取本用户仓库当中的所有信息
     */
    @GetMapping("/commit/warehouse")
    @PreAuthorize("hasAuthority('oj:topic:user')")
    public R getWarehouse() {
        return userWarehouseService.getWarehouse();
    }

    /**
     * @param topicId 题目id
     * @return 获取对应题目的所有提交记录
     */
    @GetMapping("/commit/logs/{id}")
    @PreAuthorize("hasAuthority('oj:topic:user')")
    public R getWarehouseById(@Param("topicId") @PathVariable Long topicId) {
        LambdaQueryWrapper<TopicCommitLog> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(TopicCommitLog::getCreator, UserContext.getUser()).eq(TopicCommitLog::getTopicId, topicId);
        List<TopicCommitLog> logs = topicCommitLogService.list(lam);
        return R.ok(logs);
    }

    /**
     * @param logId 历史记录id
     * @return 返还对应ID的历史记录信心
     */
    @GetMapping("/commit/log/{id}")
    @PreAuthorize("hasAuthority('oj:topic:user')")
    public R getLogById(@Param("logId") @PathVariable Long logId) {
        return topicCommitLogService.getLogById(logId);
    }


}
