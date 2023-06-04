package com.controller;

import com.annotation.UserLoginToken;
import com.service.ParcelService;
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

    @PostMapping("checkInBoundPeorsonInformation")
    @UserLoginToken
    public R checkInBoundPeorsonInformation(@RequestAttribute(value="id") String id, checkInBoundPeopleInformationParam param)
    {
        return peopleService.checkInBoundPeorsonInformation(id, param);
    }

    @PostMapping("checkFetchOutPeopleInformation")
    @UserLoginToken
    public R checkFetchOutPeopleInformation(@RequestAttribute(value="id") String id, checkFetchOutPeopleInformationParam params)
    {
        return peopleService.checkFetchOutPeopleInformation(id, params);
    }

    @PostMapping("checkCustomInformation")
    @UserLoginToken
    public R checkCustomInformation(@RequestAttribute(value="id") String id, checkCustomInformationParam params){return peopleService.checkCustomInformation(id, params);}

    @PostMapping("checkCustomTransaction")
    @UserLoginToken
    public R checkCustomTransaction(@RequestAttribute(value="id") String id, checkCustomTransactionParam params) {return peopleService.checkCustomTransaction(id, params); }

    @PostMapping("delCustomInformation")
    @UserLoginToken
    public R delCustomInformation(@RequestAttribute(value="id") String id, delCustomInformationparam params) {return peopleService.delCustomInformation(id, params); }

    @PostMapping("delFetchInPeopleInformation")
    @UserLoginToken
    public R delFetchInPeopleInformation(@RequestAttribute(value="id") String id, delFetchInPeopleInformationparam params) {return peopleService.delFetchInPeopleInformation(id, params); }

    @PostMapping("delFetchOutPeopleInformation")
    @UserLoginToken
    public R delFetchOutPeopleInformation(@RequestAttribute(value="id") String id, delFetchOutPeopleInformationparam params) {return peopleService.delFetchOutPeopleInformation(id, params); }

    @PostMapping("incAccountsPayment")
    @UserLoginToken
    public R incAccountsPayment(@RequestAttribute(value="id") String id, incAccountsPaymentparam params) {return peopleService.incAccountsPayment(id, params); }

    @PostMapping("balanceAccountsPayment")
    @UserLoginToken
    public R balanceAccountsPayment(@RequestAttribute(value="id") String id, balanceAccountsPaymentparam params) {return peopleService.balanceAccountsPayment(id, params); }

    @PostMapping("checkStaffInformation")
    @UserLoginToken
    public R checkStaffInformation(@RequestAttribute(value="id") String id, checkStaffInformationparam params) {return peopleService.checkStaffInformation(id, params); }

    @PostMapping("addInBoundPeople")
    @UserLoginToken
    public R addInBoundPeople(@RequestAttribute(value="id") String id, addInBoundPeopleparam params) {return peopleService.addInBoundPeople(id, params); }

    @PostMapping("addFetchOutPeople")
    @UserLoginToken
    public R addFetchOutPeople(@RequestAttribute(value="id") String id, addFetchOutPeopleparam params) {return peopleService.addFetchOutPeople(id, params); }

    @PostMapping("delStaffInformation")
    @UserLoginToken
    public R delStaffInformation(@RequestAttribute(value="id") String id, delStaffInformationparam params) {return peopleService.delStaffInformation(id, params); }


}
