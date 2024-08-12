package com.wly.config;

import com.wly.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//注册拦截器
@Configuration

//设置装配的条件, 因为有些时候, 这里在网关当中, 实际上其是不能够生效的, 网关当中是没有SpringMVC的相关配置的
//但是其他的微服务下是可能够存在的, 所以我们就需要设置对应包的扫描
//SpringMVC特有的底层就是   DispatcherServlet
@ConditionalOnClass(DispatcherServlet.class)
//作为条件, 设置对应的生效环境
//还必须让我们的MvcConfig被扫到
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
