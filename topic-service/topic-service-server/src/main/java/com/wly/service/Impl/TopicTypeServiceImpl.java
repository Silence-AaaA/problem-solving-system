package com.wly.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.TopicType;
import com.wly.mapper.TopicTypeMapper;
import com.wly.service.TopicTypeService;
import org.springframework.stereotype.Service;

/**
* @author Slience
* @description 针对表【topic_type(题目类型表)】的数据库操作Service实现
* @createDate 2024-08-09 08:45:13
*/
@Service
public class TopicTypeServiceImpl extends ServiceImpl<TopicTypeMapper, TopicType> implements TopicTypeService {

}
