package com.wly.user.domain;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data

public class LoginDetails implements UserDetails {

    //    REDIS序列化
    private static final long serialVersionUID = 1L;

//      用户信息
    private  User user;

//    设置权限信息
    private List<String> procession;

//    创建有关两者的构造函数
    public LoginDetails(User user, List<String> procession) {
        this.user = user;
        this.procession = procession;
    }

//    放置我们获取到的权限信息被Redis序列化操作
    @JSONField(serialize = false)
    //    存储权限信息  我们将其先提取出来, 用来方便我们查看是否已经存储信息, 减少查询时间
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        获取权限信息
//        函数形式的编程!
        if (Objects.isNull(authorities)){
            authorities = procession.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return authorities;
        }
//        已经有了权限信息 直接返回即可
        return authorities;
    }

    @Override
    public String getPassword() {
//        获取密码
        return user.getPassword();
    }

    @Override
    public String getUsername() {
//        获取用户名称
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
