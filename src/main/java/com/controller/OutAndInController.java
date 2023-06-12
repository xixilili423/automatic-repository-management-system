package com.controller;

import com.annotation.UserLoginToken;
import com.service.OutAndInService;
import com.vo.R;
import com.vo.param.SearchInParam;
import com.vo.param.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outAndIn")
public class OutAndInController {
    @Autowired
    OutAndInService outAndInService;
    @PostMapping("searchIn")
    @UserLoginToken
    public R searchIn(@RequestAttribute(value="id") String id,@RequestBody SearchInParam searchInParam) {


        return outAndInService.searchIn(id,searchInParam);
    }
  @PostMapping("searchOut")
  @UserLoginToken
  public R searchOut(@RequestAttribute(value="id") String id,@RequestBody SearchOutParam searchoutParam) {


      return outAndInService.searchOut(id,searchoutParam);
  }
  @GetMapping("showIn")
  @UserLoginToken
  public R showIn(@RequestAttribute(value="id") String id) {


      return outAndInService.showIn(id);
  }
    @GetMapping("showOut")
    @UserLoginToken
    public R showOut(@RequestAttribute(value="id") String id) {


        return outAndInService.showOut(id);
    }
    @PostMapping("addInOrder")
    @UserLoginToken
    public R addInOrder(@RequestAttribute(value="id") String id,@RequestBody AddInOrderParam addInOrderParam) {


        return outAndInService.addInOrder(id,addInOrderParam);
    }
    @PostMapping("addOutOrder")
    @UserLoginToken
    public R   addOutOrder(@RequestAttribute(value="id") String id,@RequestBody AddOutOrderParam addOutOrderParam) {


        return outAndInService.addOutOrder(id,addOutOrderParam);
    }
    @GetMapping("showParcel")
    @UserLoginToken
    public R showParcel(@RequestAttribute(value="id") String id) {


        return outAndInService.showParcel(id);
    }
    @GetMapping("fetchInPeopleNameList")
    @UserLoginToken
    public R fetchInPeopleNameList(@RequestAttribute(value="id") String id) {


        return outAndInService.fetchInPeopleNameList(id);
    }
    @GetMapping("fetchOutPeopleNameList")
    @UserLoginToken
    public R fetchOutPeopleNameList(@RequestAttribute(value="id") String id) {


        return outAndInService.fetchOutPeopleNameList(id);
    }
    @DeleteMapping("InDelMultitude")
    @UserLoginToken
    public R InDelMultitude(@RequestAttribute(value="id") String id,@RequestBody Long[] InOrderList) {


        return outAndInService.InDelMultitude(id,InOrderList);
    }
    @DeleteMapping("OutDelMultitude")
    @UserLoginToken
    public R OutDelMultitude(@RequestAttribute(value="id") String id,@RequestBody Long[] OutOrderList) {


        return outAndInService.OutDelMultitude(id,OutOrderList);
    }
    @PutMapping("ExamineIn")
    @UserLoginToken
    public R ExamineIn(@RequestAttribute(value="id") String id,@RequestBody ExamineInParam examineInParam) {


        return outAndInService.ExamineIn(id,examineInParam);
    }
    @PutMapping("ExamineOut")
    @UserLoginToken
    public R ExamineOut(@RequestAttribute(value="id") String id,@RequestBody ExamineOutParam examineOutParam) {


        return outAndInService.ExamineOut(id,examineOutParam);
    }
    @GetMapping("InNeedTocheck")
    @UserLoginToken
    public R InNeedTocheck(@RequestAttribute(value="id") String id) {


        return outAndInService.InNeedTocheck(id);
    }
    @GetMapping("OutNeedTocheck")
    @UserLoginToken
    public R OutNeedTocheck(@RequestAttribute(value="id") String id) {


        return outAndInService.OutNeedTocheck(id);
    }
    @GetMapping("singleInOrderDetail")
    @UserLoginToken
    public R singleInOrderDetail(@RequestAttribute(value="id") String id,@RequestParam("InID") String InID) {


        return outAndInService.singleInOrderDetail(id,InID);
    }
    @GetMapping("singleOutOrderDetail")
    @UserLoginToken
    public R singleOutOrderDetail(@RequestAttribute(value="id") String id,@RequestParam("OutID") String OutID) {


        return outAndInService.singleOutOrderDetail(id,OutID);
    }
    @GetMapping("InNeedToEnter")
    @UserLoginToken
    public R InNeedToEnter(@RequestAttribute(value="id") String id) {


        return outAndInService.InNeedToEnter(id);
    }
    @GetMapping("OutNeedToOut")
    @UserLoginToken
    public R OutNeedToOut(@RequestAttribute(value="id") String id) {
        return outAndInService.OutNeedToOut(id);
    }
}