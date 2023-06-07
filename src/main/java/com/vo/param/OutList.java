package com.vo.param;

@lombok.Data
public class OutList {
    private String orderID;
    private String outID;
    /**
     * 出库交接人姓名
     */
    private String outPeopleName;
    /**
     * 状态
     */
    private String outStatus;
    private String outTime;
    /**
     * 申请人姓名
     */
    private String userName;
}
