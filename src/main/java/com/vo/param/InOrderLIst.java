package com.vo.param;

import lombok.Getter;
import org.apache.catalina.util.Introspection;

import java.util.List;

@lombok.Data
@Getter
public class InOrderLIst {
    /**
     * 入库单ID列表
     */
    private List<Long> inOrderList;
}