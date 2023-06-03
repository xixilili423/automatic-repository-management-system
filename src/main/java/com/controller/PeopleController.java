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
    public R checkCustomTransaction(String id, checkCustomTransactionParam params) {return peopleService.checkCustomTransaction(id, params); }

    @PostMapping("delCustomInformation")
    @UserLoginToken
    public R delCustomInformation(int customerId, String userName) {return peopleService.delCustomInformation(customerId, userName); }

    @PostMapping("delFetchInPeopleInformation")
    @UserLoginToken
    public R delFetchInPeopleInformation(int inBoundPersonId) {return peopleService.delFetchInPeopleInformation(inBoundPersonId); }

    @PostMapping("delFetchOutPeopleInformation")
    @UserLoginToken
    public R delFetchOutPeopleInformation(int outBoundPresonId) {return peopleService.delFetchOutPeopleInformation(outBoundPresonId); }

    @PostMapping("incAccountsPayment")
    @UserLoginToken
    public R incAccountsPayment(String customerId, String incAccounts, String notes) {return peopleService.incAccountsPayment(customerId, incAccounts, notes); }

    @PostMapping("balanceAccountsPayment")
    @UserLoginToken
    public R balanceAccountsPayment(String customerId, String balAccounts, String notes) {return peopleService.balanceAccountsPayment(customerId, balAccounts, notes); }


}
