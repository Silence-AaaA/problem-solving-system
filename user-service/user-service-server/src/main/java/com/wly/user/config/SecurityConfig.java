package com.wly.user.config;


import com.wly.user.filters.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private  JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//    认证处理
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
//  授权处理
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


//    配置放行路径以及相关信息
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                关闭csrf
                .csrf().disable()
//                不通过Session获取SecuriyuContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                对于登录的接口, 允许直接进行匿名访问
                .antMatchers("/users/login","/users/register").anonymous()
//                除了上面外的请求全部都需要进行授权认证
                .anyRequest().authenticated();
//        设置过滤器的位置, 这里我们应该将对应的JWT过滤器放置在我们的UserNameandpassword之前
        http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
//        配置自定义异常处理
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
//        开启跨域模式
//        http.csrf();
    }

    //    修改加密放置
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    创建容器
@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }





}
