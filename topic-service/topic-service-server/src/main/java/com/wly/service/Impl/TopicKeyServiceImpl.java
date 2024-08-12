package com.wly.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.TopicKey;
import com.wly.mapper.TopicKeyMapper;
import com.wly.service.TopicKeyService;
import org.springframework.stereotype.Service;

/**
* @author Slience
* @description 针对表【topic_key(题解表)】的数据库操作Service实现
* @createDate 2024-08-09 08:45:13
*/
@Service
public class TopicKeyServiceImpl extends ServiceImpl<TopicKeyMapper, TopicKey> implements TopicKeyService {

}
