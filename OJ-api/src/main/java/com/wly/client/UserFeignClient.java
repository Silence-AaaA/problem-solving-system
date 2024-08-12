package com.wly.client;

import cn.hutool.core.bean.BeanUtil;
import com.wly.domain.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    R getUserMessage(@Param("id")@PathVariable Long id);

}
