package com.wly.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.user.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Slience
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2024-07-23 08:35:29
* @Entity generator.domain.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectAllMenu(Long userId);
}
