package com.controller;

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
    public R searchIn(@RequestAttribute(value="id") String id,@RequestBody SearchInParam searchInParam) {


        return outAndInService.searchIn(id,searchInParam);
    }
  @PostMapping("searchOut")
  public R searchOut(@RequestAttribute(value="id") String id,@RequestBody SearchOutParam searchoutParam) {


      return outAndInService.searchOut(id,searchoutParam);
  }
  @GetMapping("showIn")
  public R showIn(@RequestAttribute(value="id") String id) {


      return outAndInService.showIn(id);
  }
    @GetMapping("showOut")
    public R showOut(@RequestAttribute(value="id") String id) {


        return outAndInService.showOut(id);
    }
    @PostMapping("addInOrder")
    public R addInOrder(@RequestAttribute(value="id") String id,@RequestBody AddInOrderParam addInOrderParam) {


        return outAndInService.addInOrder(id,addInOrderParam);
    }
    @PostMapping("addOutOrder")
    public R   addOutOrder(@RequestAttribute(value="id") String id,@RequestBody AddOutOrderParam addOutOrderParam) {


        return outAndInService.addOutOrder(id,addOutOrderParam);
    }
    @GetMapping("showParcel")
    public R showParcel(@RequestAttribute(value="id") String id) {


        return outAndInService.showParcel(id);
    }
    @GetMapping("fetchInPeopleNameList")
    public R fetchInPeopleNameList(@RequestAttribute(value="id") String id) {


        return outAndInService.fetchInPeopleNameList(id);
    }
    @GetMapping("fetchOutPeopleNameList")
    public R fetchOutPeopleNameList(@RequestAttribute(value="id") String id) {


        return outAndInService.fetchOutPeopleNameList(id);
    }
    @DeleteMapping("InDelMultitude")
    public R InDelMultitude(@RequestAttribute(value="id") String id,@RequestBody int[] inOrderLIst) {


        return outAndInService.InDelMultitude(id,inOrderLIst);
    }
    @DeleteMapping("OutDelMultitude")
    public R OutDelMultitude(@RequestAttribute(value="id") String id,@RequestBody int[] outOrderLIst) {


        return OutDelMultitude(id,outOrderLIst);
    }
    @PutMapping("ExamineIn")
    public R ExamineIn(@RequestAttribute(value="id") String id,@RequestBody ExamineInParam examineInParam) {


        return outAndInService.ExamineIn(id,examineInParam);
    }
    @PutMapping("ExamineOut")
    public R ExamineOut(@RequestAttribute(value="id") String id,@RequestBody ExamineOutParam examineOutParam) {


        return outAndInService.ExamineOut(id,examineOutParam);
    }
    @GetMapping("InNeedTocheck")
    public R InNeedTocheck(@RequestAttribute(value="id") String id) {


        return outAndInService.InNeedTocheck(id);
    }
    @GetMapping("OutNeedTocheck")
    public R OutNeedTocheck(@RequestAttribute(value="id") String id) {


        return outAndInService.OutNeedTocheck(id);
    }
    @GetMapping("singleInOrderDetail")
    public R singleInOrderDetail(@RequestAttribute(value="id") String id,@RequestParam("InID") String InID) {


        return outAndInService.singleInOrderDetail(id,InID);
    }
    @GetMapping("singleOutOrderDetail")
    public R singleOutOrderDetail(@RequestAttribute(value="id") String id,@RequestParam("OutID") String OutID) {


        return outAndInService.singleOutOrderDetail(id,OutID);
    }
    @GetMapping("InNeedToEnter")
    public R InNeedToEnter(@RequestAttribute(value="id") String id) {


        return outAndInService.InNeedToEnter(id);
    }
    @GetMapping("OutNeedToOut")
    public R OutNeedToOut(@RequestAttribute(value="id") String id) {


        return outAndInService.OutNeedToOut(id);
    }
}