package com.service;

import com.vo.R;
import com.vo.param.SearchParcelParam;
import org.springframework.web.bind.annotation.RequestParam;

public interface ParcelService {


    R searchParcel(String id, SearchParcelParam searchParcelParam);

    public R searchParcelDetail(String id,String parcelId);

    public R deleteParcel ( String id,String pacelId);
    public R searchAllParcel( String id) ;
}
