package com.vo.param;

@lombok.Data
public class PacelInformation {
    /**
     * 收货人地址
     */
    private String consigneeAddress;
    /**
     * 收货人姓名
     */
    private String consigneeName;
    /**
     * 收货人电话
     */
    private String consigneePhone;
    /**
     * 发货人地址
     */
    private String shipperAddress;
    /**
     * 发货人姓名
     */
    private String shipperName;
    /**
     * 发货人电话
     */
    private String shipperPhone;
}
