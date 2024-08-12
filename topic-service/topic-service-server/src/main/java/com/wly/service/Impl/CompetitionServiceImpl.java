package com.wly.service.Impl;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.*;
import com.wly.dto.CompetitionTopicDto;
import com.wly.mapper.*;
import com.wly.service.CompetitionService;
import com.wly.user.domain.User;
import com.wly.user.mapper.UserMapper;
import com.wly.utils.IdGenerator;
import com.wly.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.wly.redis.RedisConstants.*;

/**
 * @author Slience
 * @description 针对表【competition(比赛表)】的数据库操作Service实现
 * @createDate 2024-08-10 14:40:51
 */
@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition>
        implements CompetitionService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CompetitionMapper competitionMapper;

    @Resource
    private IdGenerator idGenerator;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JudgeTopicResultMapper judgeTopicResultMapper;

    @Autowired
    private CompetitionTopicMapper competitionTopicMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private UserWarehouseMapper userWarehouseMapper;

    @Autowired
    private TopicCommitLogMapper topicCommitLogMapper;

    @Autowired
    private CompetitionParticipantMapper competitionParticipantMapper;


    /**
     * 以ID获取比赛ID
     *
     * @param id
     * @return
     */
    @Override
    public R getCompetitionById(Long id) {

        String jsonTopic = stringRedisTemplate.opsForValue().get(COMPETITION_KEY + id);

        if (StrUtil.isNotBlank(jsonTopic)) {
            Competition competition = JSONUtil.toBean(jsonTopic, Competition.class);
            return R.ok(competition);
        }

        if (jsonTopic != null) {
            return R.error("比赛不存在!");
        }

        Competition competition = competitionMapper.selectById(id);

        if (Objects.isNull(competition)) {
            stringRedisTemplate.opsForValue().set(COMPETITION_KEY + id, "", Duration.ofMillis(CACHE_NULL_TTL));
            return R.error("比赛不存在!");
        }

        String jsonStr = JSONUtil.toJsonStr(competition);
        stringRedisTemplate.opsForValue().set(COMPETITION_KEY + id, jsonStr);
        int expireTime = RandomUtil.randomInt(COMPETITION_MIN_TTL, COMPETITION_MAX_TTL);
        stringRedisTemplate.expire(COMPETITION_KEY + id, Duration.ofMillis(expireTime));

        return R.ok(competition);
    }

    /**
     * 删除比赛
     *
     * @param id
     * @return
     */
    @Override
    public R deleteById(Long id) {
        stringRedisTemplate.delete(COMPETITION_KEY + id);
        competitionMapper.deleteById(id);
        return R.ok("删除成功!");
    }

    /**
     * 修改比赛
     *
     * @param competition
     * @return
     */
    @Override
    public R updateCompetition(Competition competition) {
        competition.setUpdater(UserContext.getUser());
        competition.setUpdateDate(DateTime.now());

        stringRedisTemplate.delete(COMPETITION_KEY + competition.getId());

        LambdaQueryWrapper<Competition> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(Competition::getId, competition.getId());
        competitionMapper.update(competition, lam);
        return R.ok();
    }

    /**
     * 添加比赛
     *
     * @param competition
     * @return
     */
    @Override
    public R saveCompetition(Competition competition) {
//        保证比赛名称唯一
        LambdaQueryWrapper<Competition> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(Competition::getCompetitionName, competition.getCompetitionName());
        Competition selectOne = competitionMapper.selectOne(lam);
        if (!Objects.isNull(selectOne)) {
            return R.error("比赛名称已存在!");
        }
        long competitionId = idGenerator.nextId(COMPETITION_ID);
        competition.setId(competitionId);
        System.out.println(UserContext.getUser());
        competition.setCreator(UserContext.getUser());
        competition.setCreateDate(DateTime.now());
//        转化为JSON字符串
        String jsonStr = JSONUtil.toJsonStr(competition);
        stringRedisTemplate.opsForValue().set(COMPETITION_KEY + competitionId, jsonStr);
//        保存道数据库
        competitionMapper.insert(competition);
        return R.ok("添加成功!");
    }

    /**
     * 比赛题目提交
     *
     * @param competitionId
     * @param topicId
     * @param result
     * @return
     */
    @Override
    public R commitTopic(Long competitionId, Long topicId, String result) {

        //        查看本题结果
        LambdaQueryWrapper<JudgeTopicResult> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(JudgeTopicResult::getTopicId, topicId);
        JudgeTopicResult judgeTopicResult = judgeTopicResultMapper.selectOne(lam);

//        以用户ID获取仓库ID
        LambdaQueryWrapper<UserWarehouse> lamUserWarehouse = new LambdaQueryWrapper<>();
        lamUserWarehouse = lamUserWarehouse.eq(UserWarehouse::getUserId, UserContext.getUser());
        UserWarehouse userWarehouse = userWarehouseMapper.selectOne(lamUserWarehouse);


//        获得用户仓库id  创建仓库  设置仓库基本信息
        Long warehouseId = userWarehouse.getWarehouseId();
        Warehouse warehouse = new Warehouse();
        warehouse.setTopicId(topicId);
        warehouse.setWarehouseId(warehouseId);
//        查询对应题目在仓库是否存在
//        题目缓存放置在查看题目时 所以一定有缓存
//        1.查询缓存
        String status = stringRedisTemplate.opsForValue().get(WAREHOUSE_KEY + UserContext.getUser() + topicId);
        if (judgeTopicResult.getTopicResult().equals(result)) {
//            回答成功, 计入状态为已通过
            warehouse.setStatus("AC");


//            为空 缓存不存在  第一次提交
            if (StrUtil.isBlank(status)) {
                //            给用户添加积分
//             获取用户原本积分
                LambdaQueryWrapper<CompetitionParticipant> lam1 = new LambdaQueryWrapper<>();
                lam1 = lam1.eq(CompetitionParticipant::getUserId, UserContext.getUser());
                CompetitionParticipant competitionParticipant = competitionParticipantMapper.selectOne(lam1);
                Integer score = competitionParticipant.getScore();
                competitionParticipant.setScore(score + 100);
//            添加用户积分
                competitionParticipantMapper.updateById(competitionParticipant);
//            写入仓库数据
                warehouseMapper.insert(warehouse);
            } else {
//          缓存存在
                if (!"AC".equals(status)) {
//                原本是错误的状态, 修改其状态, 并删除缓存
                    stringRedisTemplate.delete(WAREHOUSE_KEY + UserContext.getUser() + topicId);
                    warehouseMapper.update(
                            new LambdaQueryWrapper<Warehouse>().eq(Warehouse::getWarehouseId, warehouseId)
                                    .eq(Warehouse::getTopicId, topicId)
                    );
                }
//                原本就一致, 那么不再更改状态
            }
        } else {
            warehouse.setStatus("WA");
            //        未提交过
            if (StrUtil.isBlank(status)) {
                warehouseMapper.insert(warehouse);
            }
        }
            //        计入日志信息
        TopicCommitLog log = new TopicCommitLog();
        log.setTopicId(topicId);
        log.setCommitFile(result);
        log.setTopicName(topicMapper.selectById(topicId).getTopicName());
        log.setCreator(UserContext.getUser());
        log.setCreateDate(DateTime.now());
        topicCommitLogMapper.insert(log);

        return R.ok();
    }

    /**
     * 直接保存题目到本地题目
     *
     * @param topic
     * @param id    比赛id
     * @return
     */
    @Override
    public R saveCompetitionTopic(Set<CompetitionTopicDto> topic, Long id) {
//        添加批量比赛题目
//        获取当前添加题目的用户的信息
        Long userId = UserContext.getUser();
        User user = userMapper.selectById(userId);
        Iterator<CompetitionTopicDto> iterator = topic.iterator();
        CompetitionTopicDto next = iterator.next();

//        获取对应的题目ID
        long topicId = idGenerator.nextId("topic");
        next.getTopic().setId(topicId);
        topicMapper.insert(next.getTopic());

//        添加比赛结果
        long topicResultId = idGenerator.nextId("topicResult");
        JudgeTopicResult result = next.getResult();
        result.setTopicId(topicResultId);
        result.setTopicName(next.getTopic().getTopicName());
        judgeTopicResultMapper.insert(result);


//        设置比赛以及题目之间的关系
        CompetitionTopic competitionTopic = new CompetitionTopic();
        competitionTopic.setTopicId(topicId);
        competitionTopic.setCompetitionId(id);
        competitionTopicMapper.insert(competitionTopic);
        return R.ok();
    }


    /**
     * 获取比赛的所有题目
     *
     * @param competitionId
     * @return
     */
    @Override
    public R getCompetitionTopics(Long competitionId) {
//        获取比赛题目的ID
        LambdaQueryWrapper<CompetitionTopic> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(CompetitionTopic::getCompetitionId, competitionId);
        List<CompetitionTopic> competitionTopics = competitionTopicMapper.selectList(lam);
        String topicIds = "";
        for (int i = 0; i < competitionTopics.size(); i++) {
            topicIds += "," + competitionTopics.get(i).getTopicId();
        }
        topicIds = topicIds.replaceFirst(",", "");

//        根据ID查询对应的题目
        LambdaQueryWrapper<Topic> lam1 = new LambdaQueryWrapper<>();
        lam1 = lam1.in(Topic::getId, topicIds);
        List<Topic> topics = topicMapper.selectList(lam1);
        return R.ok(topics);
    }
}
