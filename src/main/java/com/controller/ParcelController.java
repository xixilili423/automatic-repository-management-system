package com.controller;

import com.annotation.UserLoginToken;
import com.service.ParcelService;
import com.vo.R;
import com.vo.param.ChangeParam;
import com.vo.param.SearchParcelParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcel")
//@CrossOrigin
@AllArgsConstructor
public class ParcelController {
    @Autowired
    private final ParcelService parcelService;
    @PostMapping("searchParcel")
    @UserLoginToken
    public R searchParcel(@RequestAttribute(value="id") String id, @RequestBody SearchParcelParam searchParcelParam)
    {
        return parcelService.searchParcel(id,searchParcelParam);
    }
    @GetMapping("searchPacelDetail")
    @UserLoginToken
    public R searchPacelDetail(@RequestAttribute(value="id") String id,@RequestBody String pacelId){

        return parcelService.searchPacelDetail(id,pacelId);
    }
   @DeleteMapping("deleteParcel")
   @UserLoginToken
    public R deleteParcel (@RequestAttribute(value="id") String id,@RequestBody String pacelId){

        return parcelService.deleteParcel(id,pacelId);
    }
}
