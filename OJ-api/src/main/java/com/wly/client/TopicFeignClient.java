package com.wly.client;

import com.wly.domain.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient("topic-service")
public interface TopicFeignClient {

//    @PreAuthorize("hasAuthority('oj:topic:user')")
//    public R topicCommit(@Param("id") @PathVariable Long id, @RequestParam("result") String result) {
//        return judgeTopicResultService.commit(id, result);
//    }

}
