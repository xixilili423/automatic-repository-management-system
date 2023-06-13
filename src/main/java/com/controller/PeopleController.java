package com.controller;

import com.annotation.UserLoginToken;
import com.service.PeopleService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peopleManage")
//@CrossOrigin
@AllArgsConstructor
public class PeopleController {
    @Autowired
    private final PeopleService peopleService;

    @PostMapping("checkInBoundPeopleInformation")
    @UserLoginToken
    public R checkInBoundPeopleInformation(@RequestAttribute(value="id") String id,@RequestBody checkInBoundPeopleInformationParam param)
    {
        return peopleService.checkInBoundPeopleInformation(id, param);
    }

    @PostMapping("checkFetchOutPeopleInformation")
    @UserLoginToken
    public R checkFetchOutPeopleInformation(@RequestAttribute(value="id") String id,@RequestBody checkFetchOutPeopleInformationParam params)
    {
        return peopleService.checkFetchOutPeopleInformation(id, params);
    }

    @PostMapping("checkCustomInformation")
    @UserLoginToken
    public R checkCustomInformation(@RequestAttribute(value="id") String id,@RequestBody checkCustomInformationParam params){return peopleService.checkCustomInformation(id, params);}

    @PostMapping("checkCustomTransaction")
    @UserLoginToken
    public R checkCustomTransaction(@RequestAttribute(value="id") String id,@RequestBody checkCustomTransactionParam params) {
        System.out.println("查看客户流水信息接口响应");
        System.out.println("传过来的id：" + params.getCustomId());
        return peopleService.checkCustomTransaction(id, params);
    }

    @DeleteMapping("delCustomInformation")
    @UserLoginToken
    public R delCustomInformation(@RequestAttribute(value="id") String id,@RequestBody String customId) {return peopleService.delCustomInformation(id, customId); }

    @DeleteMapping("delFetchInPeopleInformation")
    @UserLoginToken
    public R delFetchInPeopleInformation(@RequestAttribute(value="id") String id,@RequestBody String inBoundPersonId) {return peopleService.delFetchInPeopleInformation(id, inBoundPersonId); }

    @DeleteMapping("delFetchOutPeopleInformation")
    @UserLoginToken
    public R delFetchOutPeopleInformation(@RequestAttribute(value="id") String id,@RequestBody String outBoundPresonId) {return peopleService.delFetchOutPeopleInformation(id, outBoundPresonId); }

    @PostMapping("incAccountsPayment")
    @UserLoginToken
    public R incAccountsPayment(@RequestAttribute(value="id") String id,@RequestBody incAccountsPaymentparam params) {
        System.out.println("增加客户应付款接口响应");
        System.out.println("id: " + params.getCustomId());
        return peopleService.incAccountsPayment(id, params);
    }

    @PostMapping("balanceAccountsPayment")
    @UserLoginToken
    public R balanceAccountsPayment(@RequestAttribute(value="id") String id,@RequestBody balanceAccountsPaymentparam params) {
        System.out.println("客户结款接口响应");
        System.out.println("id" + params.getCustomId());
        return peopleService.balanceAccountsPayment(id, params);
    }

    @PostMapping("checkStaffInformation")
    @UserLoginToken
    public R checkStaffInformation(@RequestAttribute(value="id") String id,@RequestBody checkStaffInformationparam params) {return peopleService.checkStaffInformation(id, params); }

    @PostMapping("addInBoundPeople")
    @UserLoginToken
    public R addInBoundPeople(@RequestAttribute(value="id") String id,@RequestBody addInBoundPeopleparam params) {
        System.out.println("增加入库人Controller响应");
        return peopleService.addInBoundPeople(id, params);
    }

    @PostMapping("addFetchOutPeople")
    @UserLoginToken
    public R addFetchOutPeople(@RequestAttribute(value="id") String id,@RequestBody addFetchOutPeopleparam params) {return peopleService.addFetchOutPeople(id, params); }

    @DeleteMapping("delStaffInformation")
    @UserLoginToken
    public R delStaffInformation(@RequestAttribute(value="id") String id,@RequestBody String userName) {return peopleService.delStaffInformation(id, userName); }

    @GetMapping("getInBoundPeopleInformationAll")
    @UserLoginToken
    public R getInBoundPeopleInformationAll(@RequestAttribute(value="id") String id) {return peopleService.getInBoundPeopleInformationAll(id); }

    @GetMapping("getFetchOutPeopleInformationAll")
    @UserLoginToken
    public R getFetchOutPeopleInformationAll(@RequestAttribute(value="id") String id) {
        return peopleService.getFetchOutPeopleInformationAll(id);
    }

    @GetMapping("getStaffInformationAll")
    @UserLoginToken
    public R getStaffInformationAll(@RequestAttribute(value="id") String id) {return peopleService.getStaffInformationAll(id); }

    @GetMapping("getCustomInformationAll")
    @UserLoginToken
    public R getCustomInformationAll(@RequestAttribute(value="id") String id) {
        return peopleService.getCustomInformationAll(id);
    }

    @GetMapping("getStaffNameList")
    @UserLoginToken
    public R getStaffNameList(@RequestAttribute(value="id") String id) {return peopleService.getStaffNameList(id); }

    @PostMapping("addCustom")
    @UserLoginToken
    public R addCustom(@RequestAttribute(value="id") String id,@RequestBody addCustomparam params) {return peopleService.addCustom(id, params); }

    @GetMapping("getCustomNameList")
    @UserLoginToken
    public R getCustomNameList(@RequestAttribute(value="id") String id) {return peopleService.getCustomNameList(id); }







}
