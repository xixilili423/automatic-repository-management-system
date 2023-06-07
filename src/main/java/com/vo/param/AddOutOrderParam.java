// AddOutOrderParam.java

package com.vo.param;

@lombok.Data
public class AddOutOrderParam {
    private String orderID;
    private String outID;
    private String outPeopleName;
    private ParcelList[] parcelList;
}



