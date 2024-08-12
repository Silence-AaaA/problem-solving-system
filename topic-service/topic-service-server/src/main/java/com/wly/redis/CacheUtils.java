package com.wly.redis;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wly.domain.RedisDate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.wly.redis.RedisConstants.CACHE_LOGICAL_KEY;
import static com.wly.redis.RedisConstants.CACHE_NULL_TTL;

@Component
public class CacheUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /*
    设置普通KEY值
     */
    public void set(String key, Object value, Long time, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    /*
    设置逻辑过期KEY值
     */
    public void setLogicalKey(String key, Object value, Long time, TimeUnit unit) {
        RedisDate redisDate = new RedisDate();
//        在当前的时间基础之上添加时间 单位为 S
        redisDate.setExpire(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        redisDate.setData(value);
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisDate), time, unit);
    }

    /*
    解决缓存穿透
     */
    public <R, ID> R queryWithPassThough(String keyPrefix, ID id, Long time, TimeUnit unit, Class<R> type, Function<ID, R> dbFallBack) {
        String json = stringRedisTemplate.opsForValue().get(keyPrefix + id);

        if (StrUtil.isNotBlank(json)) {
            R r = JSONUtil.toBean(json, type);
            return r;
        }

        if (json != null) {
            return null;
        }

        R r = dbFallBack.apply(id);
        if (Objects.isNull(r)) {
//                默认空值记录时间: 2min
            stringRedisTemplate.opsForValue().set(keyPrefix + id, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
            return r;
        }

        stringRedisTemplate.opsForValue().set(keyPrefix + id, JSONUtil.toJsonStr(r), time, unit);
        return r;
    }

    /*
    解决缓存穿透
    默认开启10个线程
     */
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    //      使用逻辑过期解决缓存穿透模板
    public <R, ID,T> R queryWithRuleTime(String lockPrefix, String keyPrefix,ID id, Long time, Class<R> type, Function<ID, R> dbFallBack) {
        String json = stringRedisTemplate.opsForValue().get(keyPrefix + id);

        if (StrUtil.isBlank(json)) {
            return null;
        }

        RedisDate redisDate = JSONUtil.toBean(json, RedisDate.class);
        R r = JSONUtil.toBean((JSONObject)redisDate.getData(), type);
        System.out.println(redisDate);

//        未超时, 直接返还
        if (redisDate.getExpire().isAfter(LocalDateTime.now())) {
            return r;
        }

        if (tryLock(lockPrefix+id)){
            return r;
        }

        CACHE_REBUILD_EXECUTOR.submit(() -> {
            try {
                this.save2Redis(id, time, dbFallBack);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                unlock(keyPrefix + id);
            }
        });
        return r;
    }

    /*
    获取逻辑锁
     */
    public Boolean tryLock(String key) {
        Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 2, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(absent);
    }

    /*
    释放互斥锁
     */
    public void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

    /*
    生成逻辑过期对象
     */
    public <R, ID> void save2Redis(ID id, Long expireTime, Function<ID, R> dbFallBack) throws InterruptedException {
        R r = dbFallBack.apply(id);
        RedisDate redisDate = new RedisDate();
        redisDate.setData(r);
        redisDate.setExpire(LocalDateTime.now().plusSeconds(expireTime));
        stringRedisTemplate.opsForValue().set(CACHE_LOGICAL_KEY + id, JSONUtil.toJsonStr(redisDate));
    }

}
