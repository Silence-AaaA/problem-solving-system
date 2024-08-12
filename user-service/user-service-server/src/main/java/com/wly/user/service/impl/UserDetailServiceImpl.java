package com.wly.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wly.user.domain.LoginDetails;
import com.wly.user.domain.User;
import com.wly.user.mapper.MenuMapper;
import com.wly.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
* @author Slience
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-07-18 15:08:16
*/
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private final MenuMapper menuMapper;

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> lam =new LambdaQueryWrapper<>();
        lam = lam.eq(User::getUserName,username);
        User user = userMapper.selectOne(lam);

        System.out.println(username);

        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("用户不存在!");
        }
        List<String> strings = menuMapper.selectAllMenu(user.getId());
        return new LoginDetails(user,strings);
    }
}
