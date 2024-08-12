package com.wly.service.Impl;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wly.domain.*;
import com.wly.mapper.JudgeTopicResultMapper;
import com.wly.mapper.UserWarehouseMapper;
import com.wly.mapper.WarehouseMapper;
import com.wly.mapper.TopicCommitLogMapper;
import com.wly.mapper.TopicMapper;
import com.wly.service.JudgeTopicResultService;
import com.wly.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

import static com.wly.redis.RedisConstants.*;

/**
 * @author Slience
 * @description 针对表【judge_topic_result(判断题题目结果表)】的数据库操作Service实现
 * @createDate 2024-08-09 23:46:12
 */
@Service
public class JudgeTopicResultServiceImpl extends ServiceImpl<JudgeTopicResultMapper, JudgeTopicResult> implements JudgeTopicResultService {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JudgeTopicResultMapper judgeTopicResultMapper;

    @Autowired
    private UserWarehouseMapper userWarehouseMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private TopicCommitLogMapper topicCommitLogMapper;

    @Autowired
    private TopicMapper topicMapper;


    /***
     * 提交题目结果
     * @param topicId
     * @param result
     * @return
     */
    @Override
    public R commit(Long topicId, String result) {
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

        return R.ok(warehouse.getStatus());
    }
}
