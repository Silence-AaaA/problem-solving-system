package com.wly.interceptors;

import cn.hutool.core.util.StrUtil;
import com.wly.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//获取用户信息
public class UserInfoInterceptor implements HandlerInterceptor {

//    在接收之前对其进行处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        获取登录用户信息
//        网关已经将其放置在请求头当中了
        String userInfo = request.getHeader("user-info");
        if (StrUtil.isNotBlank(userInfo)){
//            放入ThreadLocal
            UserContext.setUser(Long.valueOf(userInfo));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//          清理用户
        UserContext.removeUser();
    }
}
