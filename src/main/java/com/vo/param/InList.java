package com.vo.param;


@lombok.Data
public class InList {
    /**
     * 入库单号
     */
    private String inID;
    /**
     * 入库交接人姓名
     */
    private String inPeopleName;
    /**
     * 入库状态，已入库/待入库
     */
    private String inStatus;
    /**
     * 出库时间
     */
    private String inTime;
    /**
     * 订单号
     */
    private String orderID;
    /**
     * 申请人姓名
     */
    private String userName;
}
