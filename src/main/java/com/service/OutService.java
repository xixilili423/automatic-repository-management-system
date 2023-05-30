package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.Outbound;
import com.vo.R;
import com.vo.param.OutParam;

public interface OutService extends IService<Outbound> {
    /**
     * 出库请求
     * @param outParam()
     * @return
     */
    R outStock(OutParam outParam);

    /**
     * 5.获取出库记录表格
     * @get getOutTable(token)
     * @return
     */
    R getOutTable(String token);

}
