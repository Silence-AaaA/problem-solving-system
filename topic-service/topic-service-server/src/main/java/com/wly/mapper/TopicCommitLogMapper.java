package com.wly.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.domain.TopicCommitLog;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Slience
* @description 针对表【topic_commit_log(题目提交记录表)】的数据库操作Mapper
* @createDate 2024-08-09 08:45:13
* @Entity generator.domain.TopicCommitLog
*/
@Mapper
public interface TopicCommitLogMapper extends BaseMapper<TopicCommitLog> {


}
