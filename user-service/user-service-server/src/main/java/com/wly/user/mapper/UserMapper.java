package com.wly.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.user.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("insert into user_role values (#{userId},#{userRole})")
    void insertUserRole(@Param("userId")Long userId, @Param("userRole") String userRole);
}
