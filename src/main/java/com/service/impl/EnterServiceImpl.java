package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.StockIn;
import com.mapper.EnterMapper;
import com.service.EnterService;
import com.vo.R;
import com.vo.param.EnterParam;
import org.springframework.stereotype.Service;

@Service
public class EnterServiceImpl extends ServiceImpl<EnterMapper, StockIn> implements EnterService {

    // 入库
    @Override
    public R enterStock(EnterParam enterParam){
        return R.ok();
    }

    // 获取入库记录表格
    @Override
    public R getInTable(String token){

        return R.ok();
    }


}
