package com.wly.user.filters;

import com.wly.user.domain.LoginDetails;
import com.wly.user.utils.RedisCache;
import com.wly.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private RedisCache redisCache;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1.获取Token
        String token = request.getHeader("token");
        // 没有token
        if (!StringUtils.hasText(token)) {
            // 直接放行
            filterChain.doFilter(request, response);
          // 这里return的原因是，放行后，逻辑执行完之后，还会回到过滤器，执行下面的方法，为了避免直接return
          return;
        }

        // 2.解析Token
        String userid = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
//            将用户ID进行赋值
            userid = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }



        System.out.println(userid);

        // 3.从redis中获取用户信息
        LoginDetails loginDetails = redisCache.getCacheObject("userId:" + userid);
        System.out.println(userid);
        if (Objects.isNull(loginDetails)){
            throw new RuntimeException("用户未登录");
        }

        // 4.存入SecurityContextHolder
        // 使用有三个参数的构造器，第三个参数可以设置为已认证状态
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDetails,null,loginDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}
