package com.vo.param;

@lombok.Data
public class SearchOutParam {
    private String orderID;
    private String outID;
    private String outPeopleName;
    /**
     * 出库单状态
     */
    private String outStatus;
}