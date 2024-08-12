package com.wly.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.wly.domain.R;
import com.wly.user.domain.User;
import com.wly.user.dto.UserDto;
import com.wly.user.dto.UserLoginDto;
import com.wly.user.service.UserService;
import com.wly.user.vo.UserVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/hello")
//  @PreAuthorize("hasAuthority('system:test:list')")
//    @PreAuthorize("@ex.hasAuthority('system:test:list')")
    public String hello(){
        return "hello";
    }

    /**
     *
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public R login(@RequestBody UserLoginDto userLoginDto){
        return userService.login(userLoginDto);
    }

//    注销功能的实现
    @RequestMapping("/loginout")
    public R logout(){
        return userService.logout();
    }

    /**
     * 根据id获取用户信息
     * @param id 用户id
     * @return
     */
    @GetMapping("{id}")
    public R getUserMessage(@Param("id")@PathVariable Long id){
        User user = userService.getById(id);
        System.out.println(user);
//        转化为DTO
        return R.ok(BeanUtil.copyProperties(user, UserVo.class));
    }

}
