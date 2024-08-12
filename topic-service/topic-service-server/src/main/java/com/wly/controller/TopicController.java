package com.wly.controller;


import com.wly.domain.R;
import com.wly.domain.Topic;
import com.wly.dto.TopicDto;
import com.wly.service.TopicService;
import com.wly.utils.UserContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/topic")

public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * 根据ID查看题目
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R topic(@Param("题目id") @PathVariable Long id) {
        return topicService.selectTopicById(id);
    }

    /**
     * 根据ID删除题目
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('oj:topic:manager')")
    public R deleteTopic(@Param("题目id") @PathVariable Long id) {
        return topicService.removeTopicById(id);
    }

    /*
    修改题目信息
     */

    @PutMapping
    @PreAuthorize("hasAuthority('oj:topic:manager')")
    public R updateTopic(@Valid @RequestBody TopicDto topicDto) {
        return topicService.updateTopic(topicDto);
    }

    /*
    根据题目类型查询题目
    使用SET集合去重
     */
    @GetMapping("/type/topics")
    public R selectTopicsByNameOrType(@RequestParam("ids") Set<Long> ids) {
        return topicService.selectTopicsByTypes(ids);
    }

    /*
    添加题目
     */

    @PostMapping
    @PreAuthorize("hasAuthority('oj:topic:manager')")
    public R addTopic(@Valid @RequestBody TopicDto topicDto) {
        return topicService.save(topicDto);
    }

    /*
    题目分页查询
     */
    @GetMapping("{page}/{size}")
    public R getTopicByPage(@Param("当前页数") @PathVariable Long page, @Param("展示数量") @PathVariable Long size) {
//        TODO 判断当前页面数量是否跟实际页数相符合
        return topicService.selectByPage(page, size);
    }


}
