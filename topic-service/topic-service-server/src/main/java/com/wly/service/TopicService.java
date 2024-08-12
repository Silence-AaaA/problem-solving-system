package com.wly.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.R;
import com.wly.domain.Topic;
import com.wly.dto.TopicDto;

import java.util.Set;

/**
* @author Slience
* @description 针对表【topic(题目表)】的数据库操作Service
* @createDate 2024-08-09 08:45:12
*/
public interface TopicService extends IService<Topic> {

    R selectTopicById(Long id);

    R removeTopicById(Long id);

    R updateTopic(TopicDto topicDto);


    R save(TopicDto topicDto);

    R selectTopicsByTypes(Set<Long> ids);

    R selectByPage(Long page, Long size);

}
