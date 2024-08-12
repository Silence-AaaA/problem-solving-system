package com.wly.mapper;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.domain.Topic;
import com.wly.dto.CompetitionTopicDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
* @author Slience
* @description 针对表【topic(题目表)】的数据库操作Mapper
* @createDate 2024-08-09 08:45:12
* @Entity generator.domain.Topic
*/
@Mapper
public interface TopicMapper extends BaseMapper<Topic> {
    @Select("select distinct * from topic where topic.id IN(select topic_id from type tp left JOIN topic_type tt on tp.id = tt.type_id and tp.id in(#{typeIds}))")
    List<Topic> selectTopicByType(@Param("typeIds")String typeIds);

//    select count(*) from topic where id in (select topic_id from bank bk left JOIN bank_topic bt on bk.id = bt.bank_id and bk.id in(1))

//    查询题目数量
    @Select("select COUNT(id) from topic where id in (${topicIds}) ")
    Long selectTopicsNum(@Param("topicIds")String topicIds);

    void insertTopics(Set<CompetitionTopicDto> topics, String userName, Long userId, DateTime now);

    List<Long> selectTopicIds(Set<CompetitionTopicDto> topics);
}
