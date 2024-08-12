package com.wly.dto;

import lombok.Data;

@Data
public class CodeSubmissionDto {
    // 定义一个类来接收前端发送的JSON数据

    /**
     * 语言类型 默认为JAVA
     */
    private String language = "JAVA";

    /**
     * 代码模块
     */
    private String code;

    /**
     * 题目ID
     */
    private String problemId;
}
