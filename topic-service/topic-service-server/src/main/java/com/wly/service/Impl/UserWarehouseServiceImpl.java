package com.wly.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.R;
import com.wly.domain.UserWarehouse;
import com.wly.domain.Warehouse;
import com.wly.mapper.UserWarehouseMapper;
import com.wly.mapper.WarehouseMapper;
import com.wly.service.UserWarehouseService;
import com.wly.service.WarehouseService;
import com.wly.utils.UserContext;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.wly.redis.RedisConstants.*;

/**
 * @author Slience
 * @description 针对表【user_warehouse】的数据库操作Service实现
 * @createDate 2024-08-10 00:11:22
 */
@Service
public class UserWarehouseServiceImpl extends ServiceImpl<UserWarehouseMapper, UserWarehouse> implements UserWarehouseService {


    @Autowired
    private UserWarehouseMapper userWarehouseMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 获取用户仓库
     * @return
     */
    @Override
    public R getWarehouse() {
//        首先用户对应的仓库ID
        LambdaQueryWrapper<UserWarehouse> lam = new LambdaQueryWrapper<>();
        lam = lam.eq(UserWarehouse::getUserId, UserContext.getUser());
        Long warehouseId = userWarehouseMapper.selectOne(lam).getWarehouseId();
//        获取仓库信息
        LambdaQueryWrapper<Warehouse> lamWarehouse = new LambdaQueryWrapper<>();
        lamWarehouse = lamWarehouse.eq(Warehouse::getWarehouseId, warehouseId);
        List<Warehouse> warehouses = warehouseMapper.selectList(lamWarehouse);
        return R.ok(warehouses);
    }
}
