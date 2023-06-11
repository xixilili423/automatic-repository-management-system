package com.service;

import com.vo.R;
import com.vo.param.*;

public interface PeopleService {
    R checkInBoundPeopleInformation(String id, checkInBoundPeopleInformationParam param);

    R checkFetchOutPeopleInformation(String id, checkFetchOutPeopleInformationParam params);

    R checkCustomInformation(String id, checkCustomInformationParam params);

    R checkCustomTransaction(String id, checkCustomTransactionParam params);

    R delCustomInformation(String id, String params);

    R delFetchInPeopleInformation(String id, String inBoundPersonId);

    R delFetchOutPeopleInformation(String id, String outBoundPresonId);

    R incAccountsPayment(String id, incAccountsPaymentparam params);

    R balanceAccountsPayment(String id, balanceAccountsPaymentparam params);

    R checkStaffInformation(String id, checkStaffInformationparam params);

    R addInBoundPeople(String id, addInBoundPeopleparam params);

    R addFetchOutPeople(String id, addFetchOutPeopleparam params);

    R delStaffInformation(String id, String userName);

    R getInBoundPeopleInformationAll(String id);

    R getFetchOutPeopleInformationAll(String id);

    R getCustomInformationAll(String id);

    R getStaffInformationAll(String id);

    R getStaffNameList(String id);

    R getCustomNameList(String id);

    R addCustom(String id, addCustomparam params);
}
