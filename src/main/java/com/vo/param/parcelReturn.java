package com.vo.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther HYT
 * @Date 2023/4/22
 * @Desc
 */
@Setter
@Getter
public class parcelReturn {
    private int id;
    private boolean status;
    private int location_x;
    private int location_y;

    public parcelReturn(int id , boolean status){
        this.id = id;
        this.status = status;
    }

    public parcelReturn() {

    }
}
