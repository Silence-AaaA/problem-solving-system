package com.wly.service.Impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.Bank;
import com.wly.domain.R;
import com.wly.dto.BankDto;
import com.wly.mapper.BankMapper;
import com.wly.mapper.TopicMapper;

import com.wly.service.BankService;
import com.wly.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Slience
 * @description 针对表【topic_bank(题单表)】的数据库操作Service实现
 * @createDate 2024-08-09 08:45:12
 */
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements BankService {


    @Autowired
    private BankMapper bankMapper;

    @Autowired
    private TopicMapper topicMapper;


    /**
     * 添加题单
     * @param bankDto
     * @return
     */
    @Override
    public R saveTopicBank(BankDto bankDto) {
//        转化
        Bank bank = BeanUtil.copyProperties(bankDto, Bank.class);
//        获取当前用户id
        String userId = String.valueOf(UserContext.getUser());
//        添加基本信息
        bank.setCreator(userId);
        bank.setCreateDate(DateTime.now());

//        添加数据到数据库
        bankMapper.insert(bank);

        return R.ok();
    }

    /**
     * 批量删除提单中的题目
     * @param ids
     * @param bankId
     * @return
     */
    @Override
    public R deleteBankTopics(Set<Long> ids, Long bankId) {
        String topicIds = "";
        Iterator<Long> iterator = ids.iterator();
        while (iterator.hasNext()) {
            topicIds += "," + iterator.next();
        }
        topicIds = topicIds.replaceFirst(",", "");
        bankMapper.deleteBankTopics(bankId, topicIds);
        return null;
    }

    /**
     * 删除题单
     * @param id
     * @return
     */
    @Override
    public R removeBank(Long id) {
//        删除对应的提单当中的题目
        bankMapper.deleteBankAllTopics(id);

//        删除对应的提单
        bankMapper.deleteById(id);

        return R.ok("删除成功!");
    }

    /**
     * 添加题单
     * @param ids
     * @param bankId
     * @return
     */
    @Override
    public R saveBankTopics(Set<Long> ids, Long bankId) {
        //        检查题目是否都存在
        String topicIds = "";
        Iterator<Long> iterator = ids.iterator();
        while (iterator.hasNext()) {
            topicIds += "," + iterator.next();
        }
        topicIds = topicIds.replaceFirst(",", "");

        Long topicNum = topicMapper.selectTopicsNum(topicIds);
        System.out.println(topicNum);

        if (topicNum != ids.size()) {
//            题目都不存在, 无法添加!
            return R.error("请确保添加的题目为已经存在的!不存在的题目请先行添加!");
        }

//        题目都存在 可以添加
        bankMapper.insertBankTopics(bankId, ids);

        return R.ok();
    }
}
