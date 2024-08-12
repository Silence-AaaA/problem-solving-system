package com.wly.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "com.auth")
public class AuthProperties {
//    判断是否需要拦截
    private List<String> includePaths;
    private List<String> excludePaths;
}
