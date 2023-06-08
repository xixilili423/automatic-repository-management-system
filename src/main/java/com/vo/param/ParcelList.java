package com.vo.param;

import lombok.Getter;
import lombok.Setter;

@lombok.Data
@Getter
@Setter
public
class ParcelList {
    private String fromAddr;
    private String fromPeople;
    private String fromPhone;
    private long parcelID;
    private String toAddr;
    private String toPeople;
    private String toPhone;
}
