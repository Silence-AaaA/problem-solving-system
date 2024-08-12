package com.wly.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.R;
import com.wly.user.domain.User;
import com.wly.user.dto.UserLoginDto;

public interface UserService extends IService<User> {
    R login(UserLoginDto user);

    R logout();

    R register(User user);
}
