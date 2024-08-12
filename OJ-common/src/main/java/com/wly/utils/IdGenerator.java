package com.wly.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

//  用来产生ID
@Component
public class IdGenerator {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //    2024.1.1  0:0:0 时间
    private static final long BEGIN_TIMESTAMP = 1704067200;
    private static final long COUNT_BITS = 32;

    public long nextId(String keyPrefix){
//        生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timeStamp = nowSecond - BEGIN_TIMESTAMP;

//        生成序列号
        String date = now.format(DateTimeFormatter.ofPattern("yy:MM:dd"));
//        这里为了避免所有的下单数都保存在同一个的key当中, 我们可以使用 添加变动的 日期, 以确KEY值的保存不超过其最大值
        long count = stringRedisTemplate.opsForValue().increment("icr:" + keyPrefix + ":" + date);

//        3.拼接并返回
        return timeStamp << COUNT_BITS | count;
    }
}
