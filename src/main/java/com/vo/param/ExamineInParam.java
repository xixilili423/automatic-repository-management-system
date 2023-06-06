// ExamineInParam.java

package com.vo.param;

@lombok.Data
public class ExamineInParam {
    private String inID;
    private String inPeopleName;
    private String inStatus;
    private String inTime;
    /**
     * 审核人姓名
     */
    private String managerName;
    private String orderID;
    /**
     * 包裹列表
     */
    private ParcelList[] parcelList;
    /**
     * 申请人姓名
     */
    private String userName;
}


