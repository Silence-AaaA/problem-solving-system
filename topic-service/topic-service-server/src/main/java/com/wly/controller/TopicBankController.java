package com.wly.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wly.domain.Bank;
import com.wly.domain.R;
import com.wly.dto.BankDto;
import com.wly.service.BankService;
import com.wly.utils.UserContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/topicBank")
public class TopicBankController {
    @Autowired
    private BankService bankService;

    /*
    查看所有提单
     */
    @GetMapping
    public R selectAll() {
        return R.ok(bankService.list());
    }

    /*
    根据名称查询题单
     */
    @GetMapping("/topicName")
    public R selectByName(@Param("题单名称") @RequestParam String topicName) {
        LambdaQueryWrapper<Bank> lam = new LambdaQueryWrapper<>();
        lam = lam.like(Bank::getBankName, topicName);
        return R.ok(bankService.list(lam));
    }

    /*
    根据ID查询题单
     */
    @GetMapping("{id}")
    public R selectById(@Param("id") @PathVariable Long id) {
        return R.ok(bankService.getById(id));
    }

    /*
    添加提单  仅仅只添加提单
     */
    @PostMapping
//    @PreAuthorize("hasAuthority('oj:topic:manager')")
    public R saveTopicBank(@Valid @RequestBody BankDto bankDto) {
        return bankService.saveTopicBank(bankDto);
    }


    /**
     * 删除提单
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
//    @PreAuthorize("hasAuthority('oj:topic:manager')")
    public R deleteBank(@Param("id") @PathVariable Long id) {
        return bankService.removeBank(id);
    }

    /**
     * 修改提单信息
     * @param bankDto
     * @return
     */
    @PutMapping
//    @PreAuthorize("hasAuthority('oj:topic:manager')")
    public R updateTopicBank(@Valid @RequestBody BankDto bankDto) {
        Bank bank = BeanUtil.copyProperties(bankDto, Bank.class);
        bank.setUpdater(String.valueOf(UserContext.getUser()));
        bank.setUpdateDate(DateTime.now());
        return R.ok(bankService.updateById(bank));
    }

    /**
     * @param ids    根据ID删除题目
     * @param bankId 题单id
     * @return
     */
    @DeleteMapping("/topic")
//    @PreAuthorize("hasAuthority('oj:topic:manager')")
    public R removeBankTopics(@RequestParam Set<Long> ids, @RequestParam Long bankId) {
        return bankService.deleteBankTopics(ids, bankId);
    }

    /**
     * 添加提单当中的题目
     * @param ids
     * @param bankId
     * @return
     */
    @PostMapping("/bank/topic")
//    @PreAuthorize("hasAuthority('oj:topic:manager')")
    public R saveBankTopics(@RequestParam Set<Long> ids, @RequestParam Long bankId) {
        return bankService.saveBankTopics(ids, bankId);
    }

}
