package com.wly.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.domain.TopicKey;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Slience
* @description 针对表【topic_key(题解表)】的数据库操作Mapper
* @createDate 2024-08-09 08:45:13
* @Entity generator.domain.TopicKey
*/
@Mapper
public interface TopicKeyMapper extends BaseMapper<TopicKey> {


}