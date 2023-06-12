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
        System.out.println("成功");
        return parcelService.searchParcel(id,searchParcelParam);

    }
    @GetMapping("searchParcelDetail")
    @UserLoginToken
    public R searchParcelDetail(@RequestAttribute(value="id") String id, @RequestParam("parcelId") String parcelId){
        System.out.println(parcelId);
        return parcelService.searchParcelDetail(id,parcelId);
//        return R.ok();
    }
   @DeleteMapping("deleteParcel")
   @UserLoginToken
    public R deleteParcel (@RequestAttribute(value="id") String id,@RequestBody String parcelId){

        return parcelService.deleteParcel(id,parcelId);
    }
    @GetMapping("allParcel")
    @UserLoginToken
    public R searchAllParcel(@RequestAttribute(value="id") String id) {
        return parcelService.searchAllParcel(id);
    }
}
