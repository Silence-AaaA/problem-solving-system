package com.wly.user.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.wly.domain.R;
import com.wly.user.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 自定义认证失败
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R r = new R(HttpStatus.HTTP_UNAUTHORIZED,"用户认证失败, 请重新登录您的用户!",null);
        String jsonString = JSON.toJSONString(r);
//        处理异常
        WebUtils.renderString(response,jsonString);
    }
}
