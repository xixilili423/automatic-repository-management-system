package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vo.R;
import org.apache.catalina.User;
import com.vo.param.EnterParam;
import com.vo.param.OutParam;
import com.vo.param.CheckParcelParam;


public interface OtherService extends IService<User> {
    /**
     * 1.入库请求
     * @param enterParam（）
     * @return
     */
    R enterStock(EnterParam enterParam);

    /**
     * 2.出库请求
     * @param outParam()
     * @return
     */
    R outStock(OutParam outParam);

    /**
     * 3.根据用户id查询包裹请求
     * @param checkParcelParam()
     * @return
     */
    R checkParcel(CheckParcelParam checkParcelParam);

    /**
     * 4.获取入库记录表格
     * @param dbrecordInParam()
     * @return
     */
    // R getInTable(DBrecordInParam dbrecordInParam);

    /**
     * 5.获取出库记录表格
     * @param dbrecordOutParam()
     * @return
     */
    // R getOutTable(DBrecordOutParam dbrecordOutParam);

}
