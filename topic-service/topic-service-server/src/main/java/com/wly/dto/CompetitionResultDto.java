package com.wly.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CompetitionResultDto {
    /**
     * id
     */
    private Long id;

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户比赛得分
     */
    private Integer score;

    private static final long serialVersionUID = 1L;
}
