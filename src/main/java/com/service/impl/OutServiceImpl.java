package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.StockOut;
import com.mapper.OutMapper;
import com.service.OutService;
import com.vo.R;
import com.vo.param.OutParam;
import org.springframework.stereotype.Service;

@Service
public class OutServiceImpl extends ServiceImpl<OutMapper, StockOut> implements OutService {

    // 出库请求
    @Override
    public R outStock(OutParam outParam){
        return R.ok();
    }

    // 获取出库记录表格
    @Override
    public R getOutTable(String token){
        return R.ok();
    }
}
