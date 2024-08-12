package com.wly.dto;

import lombok.Data;

@Data
public class BankDto {

    /**
     * id
     */
    private Long id;

    /**
     * 题库名称
     */
    private String bankName;

    /**
     * 题目描述
     */
    private String bankDescription;


    private static final long serialVersionUID = 1L;
}
