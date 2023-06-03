package com.service;

import com.vo.R;
import com.vo.param.SearchParcelParam;

public interface ParcelService {
    public R searchParcel(String id, SearchParcelParam searchParcelParam);

    public R searchPacelDetail(String id,String pacelId);

    public R deleteParcel ( String id,String pacelId);
}
