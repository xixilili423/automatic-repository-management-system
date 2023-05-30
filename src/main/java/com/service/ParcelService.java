package com.service;

import com.annotation.UserLoginToken;
import com.vo.R;
import com.vo.param.SearchParcelParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

public interface ParcelService {
    public R searchParcel(String id, SearchParcelParam searchParcelParam);

    public R searchPacelDetail(String id,String pacelId);

    public R deleteParcel ( String id,String pacelId);
}
