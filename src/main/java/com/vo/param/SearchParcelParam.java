package com.vo.param;

@lombok.Data
public class SearchParcelParam {
    /**
     * 包裹状态
     * (未入库、已入库、出库、即将出库）
     */
    private String pacelState;
    /**
     * 包裹id
     */
    private String parcelId;
    /**
     * 库区名称
     */
    private String regionName;
    /**
     * 货架id
     */
    private String shelfID;
}