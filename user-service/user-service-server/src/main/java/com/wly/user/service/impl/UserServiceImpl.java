package com.wly.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.user.domain.LoginDetails;
import com.wly.domain.R;
import com.wly.user.domain.User;
import com.wly.user.dto.UserLoginDto;
import com.wly.user.mapper.UserMapper;
import com.wly.user.service.UserService;
import com.wly.user.utils.PasswordValidator;
import com.wly.user.utils.RedisCache;
import com.wly.user.utils.RegexUtils;
import com.wly.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public R login(UserLoginDto user) {

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePassword);

        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户不存在!");
        }

        LoginDetails loginDetails = (LoginDetails)authenticate.getPrincipal();

        String userId = loginDetails.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userId);

        Map<String,String> msg = new HashMap<>();
        msg.put("token",jwt);

//        存储用户信息到我们的Redis
        redisCache.setCacheObject("userId:"+userId,loginDetails);

//

        return new R(200,"OK!",msg);
    }

//    退出登录
    @Override
    public R logout() {
//        获取到Authentication其中的ID  强转
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        获取ID
        LoginDetails  loginDetails= (LoginDetails) authentication.getPrincipal();
        Long id = loginDetails.getUser().getId();
        System.out.println(id);
//        清除Redis当中的数据
        redisCache.deleteObject("userId:"+id);
        System.out.println("====================================");
        return new R(200,"删除成功!",null);
    }

    @Override
    public R register(User user) {
//        检测用户名是否存在
        LambdaQueryWrapper<User> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(User::getUserName,user.getUserName());
        User selectOne = userMapper.selectOne(lam);

        if (!Objects.isNull(selectOne)){
            return R.error("用户名已存在");
        }
//        TODO 检验密码是否符合要求

        if (!PasswordValidator.validatePassword(user.getPassword())){
            return R.error(PasswordValidator.validateMsg(user.getPassword()));
        }
//        passwordEncoder.encode(user.getPassword())
//        检验手机号是否符合要求

        if (!RegexUtils.isPhoneInvalid(user.getPhonenumber())){
            return R.error("手机号不符合要求!");
        }
//        添加用户
        userMapper.insert(user);

        userMapper.insertUserRole(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName,user.getUserName())).getId(),user.getUserType());

        return R.ok("注册成功!");
    }
}
