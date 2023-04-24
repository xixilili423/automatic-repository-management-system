package com.vo.param;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Auther HYT
 * @Date 2023/4/22
 * @Desc
 */
@Setter
@Getter
public class Avg {
    private int id;
    private boolean status;//表示是否空闲
    private List<parcelReturn> parcelList;
    private List<int[]> route;

    public Avg(int id){
        this.id = id;
        this.status = true;
    }
    public boolean getStatus(){
        return this.status;
    }
}
