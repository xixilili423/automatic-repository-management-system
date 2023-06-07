package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.Warehouse;
import com.vo.R;


/**
 * FileName:  InitStockService
 * Date: 2023/04/18
 */

public interface InitStockService extends IService<Warehouse> {
    /**
     * 3.仓库初始化 initStock(x,y,闸机数，小车数)
     * @return
     */
    R initStock(InitStockParam initStockParam);

    R getOldInitStock(String token);
}
