package com.service;

import com.vo.R;
import com.vo.param.*;

public interface PeopleService {
    R checkInBoundPeorsonInformation(String id, checkInBoundPeopleInformationParam param);

    R checkFetchOutPeopleInformation(String id, checkFetchOutPeopleInformationParam params);

    R checkCustomInformation(String id, checkCustomInformationParam params);

    R checkCustomTransaction(String id, checkCustomTransactionParam params);

    R delCustomInformation(String id, delCustomInformationparam params);

    R delFetchInPeopleInformation(String id, delFetchInPeopleInformationparam params);

    R delFetchOutPeopleInformation(String id, delFetchOutPeopleInformationparam params);

    R incAccountsPayment(String id, incAccountsPaymentparam params);

    R balanceAccountsPayment(String id, balanceAccountsPaymentparam params);

    R checkStaffInformation(String id, checkStaffInformationparam params);

    R addInBoundPeople(String id, addInBoundPeopleparam params);

    R addFetchOutPeople(String id, addFetchOutPeopleparam params);

    R delStaffInformation(String id, delStaffInformationparam params);
}
