package com.service;

import com.vo.R;
import com.vo.param.*;


public interface OutAndInService {
     R searchIn(String id,SearchInParam searchInParam);
    R searchOut(String id, SearchOutParam searchoutParam) ;
     R showIn( String id);
     R showOut(String id) ;
    R addInOrder(String id,AddInOrderParam addInOrderParam) ;

   R   addOutOrder(String id, AddOutOrderParam addOutOrderParam) ;
   R showParcel( String id) ;
    R fetchInPeopleNameList( String id);
   R fetchOutPeopleNameList( String id) ;
    R InDelMultitude(String id, InOrderLIst inOrderLIst) ;
  R OutDelMultitude( String id,InOrderLIst  outOrderLIst);

    R ExamineIn(String id, ExamineInParam examineInParam);

    R ExamineOut(String id, ExamineOutParam examineOutParam);

    R InNeedTocheck(String id);
    R OutNeedTocheck( String id) ;
 R singleInOrderDetail(String id, String InID) ;
    R singleOutOrderDetail(String id, String OutID);
   R InNeedToEnter( String id);
   R OutNeedToOut( String id) ;
}
