package com.vo.param;

@lombok.Data
public class SearchInParam {
    /**
     * 入库单id
     */
    private String inID;
    /**
     * 入库交接人姓名
     */
    private String inPeopleName;
    /**
     * 入库单状态
     */
    private String inStatus;
    /**
     * 订单id
     */
    private String orderID;
}