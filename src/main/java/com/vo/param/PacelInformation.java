package com.vo.param;

import lombok.Getter;
import lombok.Setter;

@lombok.Data
@Getter
@Setter
public class PacelInformation {
    /**
     * 包裹id
     */
    private String parcelId;
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
