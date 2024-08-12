package com.wly.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wly.domain.R;
import com.wly.domain.UserWarehouse;

/**
* @author Slience
* @description 针对表【user_warehouse】的数据库操作Service
* @createDate 2024-08-10 00:11:22
*/
public interface UserWarehouseService extends IService<UserWarehouse> {

    R getWarehouse();

}
