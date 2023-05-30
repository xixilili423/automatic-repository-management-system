package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.Inbound;
import com.vo.R;
import com.vo.param.CheckParcelParam;
import com.vo.param.EnterParam;

public interface EnterService extends IService<Inbound> {

    /**
     * 1.入库请求
     * @param enterParam（）
     * @return
     */
    R enterStock(EnterParam enterParam);

    /**
     * 4.获取入库记录表格
     * @get getInTable(token)
     * @return
     */
    R getInTable(String token);

    /**
     * 根据用户id查询包裹请求
     * @param checkParcel(enterParam)
     * @return
     */
    R checkParcel(CheckParcelParam checkParcelParam);
}
