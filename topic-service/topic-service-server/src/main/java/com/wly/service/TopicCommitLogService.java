package com.wly.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.R;
import com.wly.domain.TopicCommitLog;

/**
* @author Slience
* @description 针对表【topic_commit_log(题目提交记录表)】的数据库操作Service
* @createDate 2024-08-09 08:45:13
*/
public interface TopicCommitLogService extends IService<TopicCommitLog> {

    R getLogById(Long logId);

}
