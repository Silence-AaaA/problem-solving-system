package com.wly.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.Bank;
import com.wly.domain.R;
import com.wly.dto.BankDto;

import java.util.Set;

/**
 * @author Slience
 * @description 针对表【bank(题单表)】的数据库操作Service
 * @createDate 2024-08-09 17:03:31
 */
public interface BankService extends IService<Bank> {

    R saveTopicBank(BankDto bankDto);

    R deleteBankTopics(Set<Long> ids, Long bankId);

    R removeBank(Long id);

    R saveBankTopics(Set<Long> ids, Long bankId);
}
