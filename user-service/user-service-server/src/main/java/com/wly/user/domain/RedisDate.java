package com.wly.user.domain;

import lombok.Data;

import java.time.LocalDateTime;

//封装逻辑过期时间
@Data
public class RedisDate {
    private LocalDateTime expire;
//    封装数据
    private Object data;
}
