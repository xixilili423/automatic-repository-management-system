package com.service;

import com.vo.R;
import com.vo.param.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


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
    R InDelMultitude( String id, int[] inOrderLIst) ;
  R OutDelMultitude( String id,int[] outOrderLIst);
   R ExamineIn(String id, int[] outOrderLIst) ;
   R ExamineOut( String id, ExamineInParam examineInParam) ;
    R InNeedTocheck(String id);
    R OutNeedTocheck( String id) ;
 R singleInOrderDetail(String id, String InID) ;
    R singleOutOrderDetail(String id, String OutID);
   R InNeedToEnter( String id);
   R OutNeedToOut( String id) ;
}
