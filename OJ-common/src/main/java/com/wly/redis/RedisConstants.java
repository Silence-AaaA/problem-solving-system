package com.wly.redis;

public class RedisConstants {

//    比赛ID
    public static final String COMPETITION_ID = "competition";
//    比赛
    public static final String COMPETITION_KEY = "oj:competition:";
    //    比赛存储最低时长
    public static final int COMPETITION_MIN_TTL = 4000;
    //    比赛存储最大时长
    public static final int COMPETITION_MAX_TTL = 16000;

    //    题目
    public static final String TOPIC_KEY = "topic:id:";
    //    题目存储最低时长
    public static final int TOPIC_MIN_TTL = 4000;
    //    题目存储最大时长
    public static final int TOPIC_MAX_TTL = 16000;

    //    题目提交记录
    public static final String COMMIT_LOG_KEY = "commit:log:";

    //    提交记录最低时长
    public static final int COMMIT_MIN_TTL = 4000;
    //    提交记录最大时长
    public static final int COMMIT_MAX_TTL = 16000;

    //    仓库
    public static final String WAREHOUSE_KEY = "warehouse:user:";

    //    缓存空值时间长度
    public static final Long CACHE_NULL_TTL = 2L;
    //    设置逻辑过期KEY
    public static final String CACHE_LOGICAL_KEY = "cache:logical:";

}
