package com.wly.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.domain.Bank;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
* @author Slience
* @description 针对表【topic_bank(题单表)】的数据库操作Mapper
* @createDate 2024-08-09 08:45:12
* @Entity generator.domain.TopicBank
*/
@Mapper
public interface BankMapper extends BaseMapper<Bank> {

    /**
     *
     * @param bankId  提单ID
     * @param ids  要删除的题目id  批量删除
     */
    void deleteBankTopics(Long bankId, String ids);

    void insertBankTopics(Long bankId,Set<Long> ids);

    @Delete("delete from bank_topic where bank_id = #{bankId}")
    void deleteBankAllTopics(@Param("bankId")Long bankId);
}
