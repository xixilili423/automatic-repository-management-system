// SingleOutOrderDetailParam.java

package com.vo.param;

@lombok.Data
public class SingleOutOrderDetailParam {
    /**
     * 审核人姓名
     */
    private String managerName;
    private String orderID;
    private String outID;
    private String outPeopleName;
    private String outStatus;
    private String outTime;
    /**
     * 包裹列表
     */
    private ParcelList[] parcelList;
    private boolean statusCode;
    /**
     * 申请人姓名
     */
    private String userName;
}

