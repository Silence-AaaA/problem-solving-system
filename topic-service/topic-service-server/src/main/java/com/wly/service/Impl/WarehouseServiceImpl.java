package com.wly.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.Warehouse;
import com.wly.service.WarehouseService;
import com.wly.mapper.WarehouseMapper;
import org.springframework.stereotype.Service;

/**
 * @author Slience
 * @description 针对表【warehouse】的数据库操作Service实现
 * @createDate 2024-08-10 00:42:16
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse>
        implements WarehouseService {

}
