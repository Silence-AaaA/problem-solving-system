package com.wly.service.Impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.R;
import com.wly.domain.RedisDate;
import com.wly.domain.TopicCommitLog;
import com.wly.mapper.TopicCommitLogMapper;
import com.wly.service.TopicCommitLogService;
import com.wly.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.wly.redis.RedisConstants.*;

/**
 * @author Slience
 * @description 针对表【topic_commit_log(题目提交记录表)】的数据库操作Service实现
 * @createDate 2024-08-09 08:45:13
 */
@Service
public class TopicCommitLogServiceImpl extends ServiceImpl<TopicCommitLogMapper, TopicCommitLog> implements TopicCommitLogService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TopicCommitLogMapper topicCommitLogMapper;


    /**
     * 根据ID查看提交记录信息
     * @param logId
     * @return
     */
    @Override
    public R getLogById(Long logId) {
//        1.查询日志信息
        RedisDate commitLog = (RedisDate) stringRedisTemplate.opsForHash().get(COMMIT_LOG_KEY + UserContext.getUser(), logId);
        if (!Objects.isNull(commitLog.getData())) {
            return R.ok(null);
        }

        if (Objects.isNull(commitLog.getData())){
            return R.error("没有此日志信息!");
        }

        if (commitLog.getExpire().isAfter(LocalDateTime.now())){
//            说明已经过期, 删除即可
            stringRedisTemplate.opsForHash().delete(COMMIT_LOG_KEY + UserContext.getUser(), logId);
        }
//        查询数据库
        TopicCommitLog topicCommitLog = topicCommitLogMapper.selectById(logId);
        RedisDate redisDate = new RedisDate();
        if (Objects.isNull(topicCommitLog)) {
//            说明数据不存在, 设置空值, 防止缓存穿透
            redisDate.setData(null);
            redisDate.setExpire(LocalDateTime.now().plusSeconds(CACHE_NULL_TTL));
            stringRedisTemplate.opsForHash().put(COMMIT_LOG_KEY + UserContext.getUser(), logId, log);
            return R.error("没有此日志信息!");
        }
//        数据库存在对应信息, 写入缓存
        redisDate.setData(topicCommitLog);
//        产生随机时间, 防止缓存传击穿
        int expireTime = RandomUtil.randomInt(TOPIC_MIN_TTL, TOPIC_MAX_TTL);
        redisDate.setExpire(LocalDateTime.now().plusSeconds(expireTime));
        stringRedisTemplate.opsForHash().put(COMMIT_LOG_KEY + UserContext.getUser(), logId, redisDate);
        return R.ok(topicCommitLog);
    }
}
