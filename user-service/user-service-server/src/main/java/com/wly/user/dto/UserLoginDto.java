package com.wly.user.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;
}
