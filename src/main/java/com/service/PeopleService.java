package com.service;

import com.vo.R;
import com.vo.param.checkCustomInformationParam;
import com.vo.param.checkCustomTransactionParam;
import com.vo.param.checkFetchOutPeopleInformationParam;
import com.vo.param.checkInBoundPeopleInformationParam;

public interface PeopleService {
    public R checkInBoundPeorsonInformation(String id, checkInBoundPeopleInformationParam param);

    public R checkFetchOutPeopleInformation(String id, checkFetchOutPeopleInformationParam params);

    public R checkCustomInformation(String id, checkCustomInformationParam params);

    R checkCustomTransaction(String id, checkCustomTransactionParam params);

    public R delCustomInformation(int customerId, String userName);

    R delFetchInPeopleInformation(int inBoundPersonId);

    R delFetchOutPeopleInformation(int inBoundPersonId);

    R incAccountsPayment(String customerId, String incAccounts, String notes);

    R balanceAccountsPayment(String customerId, String balAccounts, String notes);

}
