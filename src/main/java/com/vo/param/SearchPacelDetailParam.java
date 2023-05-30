package com.vo.param;

@lombok.Data
public class SearchPacelDetailParam {
    /**
     * 入库时间
     */
    private String inTime;
    /**
     * 出库时间
     */
    private String outTime;
    /**
     * 包裹id
     */
    private String parceld;
    /**
     * 包裹状态
     */
    private String parcelState;
    /**
     * 所在货架的id
     */
    private String shelfId;
    /**
     * 所在货架的位置编号
     */
    private String shelfNumber;
}