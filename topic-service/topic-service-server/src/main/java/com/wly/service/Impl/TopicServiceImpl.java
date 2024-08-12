package com.wly.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.JudgeTopicResult;
import com.wly.domain.R;
import com.wly.domain.Topic;
import com.wly.dto.TopicDto;
import com.wly.mapper.JudgeTopicResultMapper;
import com.wly.mapper.TopicMapper;
import com.wly.mapper.TopicTypeMapper;
import com.wly.service.TopicService;
import com.wly.utils.ConvertUtils;
import com.wly.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.time.Duration;
import java.util.*;

import static com.wly.redis.RedisConstants.*;

/**
 * @author Slience
 * @description 针对表【topic(题目表)】的数据库操作Service实现
 * @createDate 2024-08-09 08:45:12
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TopicMapper topicMapper;

    /**
     * 根据ID删除题目
     * @param topicId
     * @return
     */
    @Override
    public R selectTopicById(Long topicId) {

        String jsonTopic = stringRedisTemplate.opsForValue().get(TOPIC_KEY + topicId);

        if (StrUtil.isNotBlank(jsonTopic)) {
            Topic topic = JSONUtil.toBean(jsonTopic, Topic.class);
            return R.ok(topic);
        }

        if (jsonTopic != null) {
            return R.error("题目不存在!");
        }

        Topic topic = topicMapper.selectById(topicId);

        if (Objects.isNull(topic)) {
            stringRedisTemplate.opsForValue().set(TOPIC_KEY + topicId, "", Duration.ofMillis(CACHE_NULL_TTL));
            return R.error("题目不存在!");
        }

        String jsonStr = JSONUtil.toJsonStr(topic);
        stringRedisTemplate.opsForValue().set(TOPIC_KEY + topicId, jsonStr);
        int expireTime = RandomUtil.randomInt(TOPIC_MIN_TTL, TOPIC_MAX_TTL);
        stringRedisTemplate.expire(TOPIC_KEY + topicId, Duration.ofMillis(expireTime));

        return R.ok();
    }

    /**
     * 通过ID删除题目
     * @param topicId
     * @return
     */
    @Override
    public R removeTopicById(Long topicId) {
        Topic topic = topicMapper.selectById(topicId);
        if (Objects.isNull(topic)) {
            return R.error("题目不存在!");
        }
        topicMapper.deleteById(topicId);
        stringRedisTemplate.delete(TOPIC_KEY + topicId);

        return R.ok();
    }

    /**
     * 修改题目
     * @param topicDto
     * @return
     */
    @Override
    public R updateTopic(TopicDto topicDto) {
//        删除缓存
        stringRedisTemplate.opsForHash().delete(TOPIC_KEY + topicDto.getId());

        Topic topic = BeanUtil.copyProperties(topicDto, Topic.class);
//        修改人
        topic.setUpdater(UserContext.getUser());
//        修改时间
        topic.setUpdateDate(DateTime.now());
        topicMapper.updateById(BeanUtil.copyProperties(topicDto, Topic.class));

        return R.ok();
    }

    /**
     * 添加题目
     * @param topicDto
     * @return
     */
    @Override
    public R save(TopicDto topicDto) {
//        转化数据类型
        Topic topic = ConvertUtils.sourceToTarget(topicDto, Topic.class);
        topicMapper.insert(topic);
        return R.ok();
    }


    /**
     * 根据题目类型查询题目
     * @param ids
     * @return
     */
    @Override
    public R selectTopicsByTypes(Set<Long> ids) {
        String typeIds = "";
        Iterator<Long> iterator = ids.iterator();
//        保存类型信息, 转化为字符串
        while (iterator.hasNext()) {
            typeIds += "," + iterator.next();
        }
        typeIds = typeIds.replaceFirst(",", "");
        List<Topic> topics = topicMapper.selectTopicByType(typeIds);

        return R.ok(topics);
    }

    @Override
    public R selectByPage(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<Topic> lam = new LambdaQueryWrapper<>();
        //分页查询
        Page<Topic> page = new Page<>(pageNum, pageSize);
        page = page(page, lam);
        //封装查询结果
        return R.ok(page);
    }


}
