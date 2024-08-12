package com.wly.gateway.filter;

import com.wly.exception.UnauthorizedException;
import com.wly.gateway.config.AuthProperties;


import com.wly.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
//生成对于final类型的实体类的构造函数
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

//    匹配放行路径
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

//    校验并且解析token

//    获取对应放行与否的路径
    private final AuthProperties authProperties;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//        1. 获取request
        ServerHttpRequest request = exchange.getRequest();

//        2. 判断当前的网站是否需要进行拦截

        if(isExclude(request.getPath().toString())){
//            如果满足条件, 其可以放行就直接放行
            return chain.filter(exchange);
        }

//        3. 获取token
        String token = null;
        List<String> headers = request.getHeaders().get("token");
        if (headers!=null && !headers.isEmpty()){
            token = headers.get(0);
        }
        System.out.println(token);

//        4. 校验并且解析token 从而得到对用的用户ID
        Long userId = null;
//        同时也有可能在JWTTOOL当中抛异常, 为了体现出是登录出现的异常, 我们需要对其进行抛异常. 并且为标识 登录未成功异常
//        try {
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            System.out.println(e);
//            拦截 设置状态码为, 没有登录成功的状态码 401
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            设置完结, 之后的过滤器就不会进行
            return response.setComplete();
        }
        System.out.println(claims.getSubject());
        userId = Long.valueOf(claims.getSubject());
//            userId = jwtTool.parseToken(token);
//        }catch (UnauthorizedException e){
//            System.out.println(e);
////            拦截 设置状态码为, 没有登录成功的状态码 401
//            ServerHttpResponse response = exchange.getResponse();
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
////            设置完结, 之后的过滤器就不会进行
//            return response.setComplete();
//        }
//        5 传递用户信息  将用户的ID放置在头部
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo)).build();

//        6 放行 将新的WebChange进行传递
        return chain.filter(swe);
    }

    private boolean isExclude(String path){
        System.out.println("path: "+path);
        for (String pathParttern : authProperties.getExcludePaths()) {
            System.out.println(pathParttern);
//            但是在这这里, 我们的路径配置不是一般的, 仅仅只有个体的路径匹配, 而是一个代表多个路径的匹配
            if (antPathMatcher.match(pathParttern,path)){

                return true;
            }
        }
        return false;
    }

//    设置此拦截器的加载顺序
    @Override
    public int getOrder() {
        return 0;
    }
}
