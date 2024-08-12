package com.wly.user.expression;

import com.wly.user.domain.LoginDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ex")
//自定义授权
public class WlyExpressionRoot {
    public boolean hasAuthority(String authority){
//        首先我们需要拿到用户的信息
//        这里我们通过SecurityContextHolder 获取对应信息, 是因为我们在JWT拦截器当中将对应的信息保存在了其中
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        然后获取用户详细信息当中的权限信息
        LoginDetails principal = (LoginDetails) authentication.getPrincipal();

        List<String> procession = principal.getProcession();

        if (procession.contains(authority)){
            return true;
        }
        return false;
    }
}
