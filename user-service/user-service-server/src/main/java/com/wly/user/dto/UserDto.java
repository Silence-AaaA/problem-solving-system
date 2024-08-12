package com.wly.user.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserDto {

    /**
     * 主键  添加注解
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;


    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型（0 管理员，1普通用户）
     */
    private String userType;

}
