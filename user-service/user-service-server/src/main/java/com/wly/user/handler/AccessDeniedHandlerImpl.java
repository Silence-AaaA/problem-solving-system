package com.wly.user.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.wly.domain.R;
import com.wly.user.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 自定义授权失败
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R result = new R(HttpStatus.HTTP_FORBIDDEN,"您的权限不足!",null);
        String json = JSON.toJSONString(result);
//        处理异常
        WebUtils.renderString(response,json);
    }
}
