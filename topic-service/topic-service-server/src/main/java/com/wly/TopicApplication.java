package com.wly;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@SpringBootConfiguration
//设置扫描包
@MapperScan("com.wly.mapper")
@EnableFeignClients
public class TopicApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicApplication.class,args);
    }
}
