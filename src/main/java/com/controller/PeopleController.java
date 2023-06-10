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
    public R checkCustomTransaction(@RequestAttribute(value="id") String id,@RequestBody checkCustomTransactionParam params) {return peopleService.checkCustomTransaction(id, params); }

    @DeleteMapping("delCustomInformation")
    @UserLoginToken
    public R delCustomInformation(@RequestAttribute(value="id") String id,@RequestBody delCustomInformationparam params) {return peopleService.delCustomInformation(id, params); }

    @DeleteMapping("delFetchInPeopleInformation")
    @UserLoginToken
    public R delFetchInPeopleInformation(@RequestAttribute(value="id") String id,@RequestBody delFetchInPeopleInformationparam params) {return peopleService.delFetchInPeopleInformation(id, params); }

    @DeleteMapping("delFetchOutPeopleInformation")
    @UserLoginToken
    public R delFetchOutPeopleInformation(@RequestAttribute(value="id") String id,@RequestBody delFetchOutPeopleInformationparam params) {return peopleService.delFetchOutPeopleInformation(id, params); }

    @PostMapping("incAccountsPayment")
    @UserLoginToken
    public R incAccountsPayment(@RequestAttribute(value="id") String id,@RequestBody incAccountsPaymentparam params) {return peopleService.incAccountsPayment(id, params); }

    @PostMapping("balanceAccountsPayment")
    @UserLoginToken
    public R balanceAccountsPayment(@RequestAttribute(value="id") String id,@RequestBody balanceAccountsPaymentparam params) {return peopleService.balanceAccountsPayment(id, params); }

    @PostMapping("checkStaffInformation")
    @UserLoginToken
    public R checkStaffInformation(@RequestAttribute(value="id") String id,@RequestBody checkStaffInformationparam params) {return peopleService.checkStaffInformation(id, params); }

    @PostMapping("addInBoundPeople")
    @UserLoginToken
    public R addInBoundPeople(@RequestAttribute(value="id") String id,@RequestBody addInBoundPeopleparam params) {return peopleService.addInBoundPeople(id, params); }

    @PostMapping("addFetchOutPeople")
    @UserLoginToken
    public R addFetchOutPeople(@RequestAttribute(value="id") String id,@RequestBody addFetchOutPeopleparam params) {return peopleService.addFetchOutPeople(id, params); }

    @DeleteMapping("delStaffInformation")
    @UserLoginToken
    public R delStaffInformation(@RequestAttribute(value="id") String id,@RequestBody delStaffInformationparam params) {return peopleService.delStaffInformation(id, params); }

    @GetMapping("getInBoundPeopleInformationAll")
    @UserLoginToken
    public R getInBoundPeopleInformationAll(@RequestAttribute(value="id") String id) {return peopleService.getInBoundPeopleInformationAll(id); }

    @GetMapping("getFetchOutPeopleInformationAll")
    @UserLoginToken
    public R getFetchOutPeopleInformationAll(@RequestAttribute(value="id") String id) {return peopleService.getFetchOutPeopleInformationAll(id); }

    @GetMapping("getStaffInformationAll")
    @UserLoginToken
    public R getStaffInformationAll(@RequestAttribute(value="id") String id) {return peopleService.getStaffInformationAll(id); }

    @GetMapping("getCustomInformationAll")
    @UserLoginToken
    public R getCustomInformationAll(@RequestAttribute(value="id") String id) {return peopleService.getCustomInformationAll(id); }

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
