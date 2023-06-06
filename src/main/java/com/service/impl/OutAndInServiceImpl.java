package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.entity.*;
import com.entity.Package;
import com.mapper.*;
import com.service.OutAndInService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class OutAndInServiceImpl implements OutAndInService {
    @Autowired
    private InboundMapper inboundMapper;

    @Autowired
    private OutboundMapper outboundMapper;
    @Autowired
    private OutboundpersonMapper outboundpersonMapper;

    @Autowired
    private WarehousepersonMapper warehousepersonMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private ParcelMapper packageMapper;
    @Override
    public R searchIn(String id,SearchInParam searchInParam) {

        R r = new R();
        // 从请求参数中获取查询条件
        Long inID = Long.parseLong(searchInParam.getInID());
        String inPeopleName = searchInParam.getInPeopleName();
        String inStatus = searchInParam.getInStatus();
        String orderID = searchInParam.getOrderID();

        // 构造查询条件
        QueryWrapper<Inbound> queryWrapper = new QueryWrapper<>();
        if (inID!=null) {
            queryWrapper.eq("inboundid", inID);
        }
        if (StringUtils.isNotBlank(inPeopleName)) {
            queryWrapper.like("warehousepersonname", inPeopleName);
        }
        if (StringUtils.isNotBlank(inStatus)) {
            queryWrapper.eq("status", inStatus);
        }
        if (StringUtils.isNotBlank(orderID)) {
            queryWrapper.eq("orderid", orderID);
        }

        // 执行查询操作，并将结果封装到 Response 对象中返回
        List<Inbound> inboundList = inboundMapper.selectList(queryWrapper);
        List<InList> inList = new ArrayList<>();
        for (Inbound inbound : inboundList) {
            InList item = new InList();
            item.setInID(String.valueOf(inbound.getInboundid()));
            item.setOrderID(inbound.getOrderid());
            item.setInStatus(inbound.getStatus());
            item.setInTime(inbound.getInboundtime().toString());

            Warehouseperson warehouseperson = warehousepersonMapper.selectById(inbound.getWarehousepersonid());
            if (warehouseperson != null) {
                item.setUserName(warehouseperson.getUsername());
                item.setInPeopleName(warehouseperson.getName());
            }

            inList.add(item);
        }

        r.data("status_code", true);
        r.data("inList", inList);
        return r;
    }


    @Override
    public R searchOut(String id, SearchOutParam searchOutParam) {
        // 从请求参数中获取查询条件
        String outID = searchOutParam.getOutID();
        String outPeopleName = searchOutParam.getOutPeopleName();
        String outStatus = searchOutParam.getOutStatus();
        String orderID = searchOutParam.getOrderID();

        // 构造查询条件
        QueryWrapper<Outbound> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(outID)) {
            queryWrapper.eq("outboundid", outID);
        }
        if (StringUtils.isNotBlank(outPeopleName)) {
            queryWrapper.like("outboundpersonname", outPeopleName);
        }
        if (StringUtils.isNotBlank(outStatus)) {
            queryWrapper.eq("status", outStatus);
        }
        if (StringUtils.isNotBlank(orderID)) {
            queryWrapper.eq("orderid", orderID);
        }

        // 执行查询操作，并将结果封装到 Response 对象中返回
        List<Outbound> outboundList = outboundMapper.selectList(queryWrapper);
        List<Map<String, Object>> outList = new ArrayList<>();
        for (Outbound outbound : outboundList) {
            Map<String, Object> item = new HashMap<>();
            item.put("outID", String.valueOf(outbound.getOutboundid()));
            item.put("orderID", outbound.getOrderid());
            item.put("outPeopleName", "");
            item.put("outStatus", outbound.getStatus());
            item.put("outTime", outbound.getOutboundtime().toString());

            Outboundperson warehouseperson = outboundpersonMapper.selectById(outbound.getOutboundpersonid());
            if (warehouseperson != null) {
                item.put("userName", warehouseperson.getUsername());
                item.put("outPeopleName", warehouseperson.getName());
            }

            Ordertable ordertable = orderMapper.selectById(outbound.getOrderid());
            if (ordertable != null) {
                item.put("userName", ordertable.getUsername());
            }

            outList.add(item);
        }
        R r = new R();
        r.data("status_code", true);
        r.data("outList", outList);
        return r;
    }


    @Override
    public R showIn(String id) {
       User user = userMapper.selectById(id);
        R r = new R();
        if (user == null) {
            r.data("status_code", false);
            return r.setMsg("用户不存在");
        }
        long warehouseID = user.getWarehouseid();

        // 构造查询条件
        QueryWrapper<Inbound> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("warehouseid", warehouseID);

        // 执行查询操作，并将结果封装到 Response 对象中返回
        List<Inbound> inboundList = inboundMapper.selectList(queryWrapper);
        List<Map<String, Object>> inList = new ArrayList<>();
        for (Inbound inbound : inboundList) {
            Map<String, Object> item = new HashMap<>();
            item.put("inID", String.valueOf(inbound.getInboundid()));
            item.put("orderID", inbound.getOrderid());
            item.put("inPeopleName", "");
            item.put("inStatus", inbound.getStatus());
            item.put("inTime", inbound.getInboundtime().toString());

            Warehouseperson wp = warehousepersonMapper.selectById(inbound.getWarehousepersonid());
            if (wp != null) {
                item.put("inPeopleName", wp.getName());
            }

            Ordertable ordertable = orderMapper.selectById(inbound.getOrderid());
            if (ordertable != null) {
                item.put("userName", ordertable.getUsername());
            }

            inList.add(item);
        }
        r.data("status_code", true);
        r.data("inList", inList);
        return r;

    }

    @Override
    public R showOut(String id) {
        User user = userMapper.selectById(id);
        R r = new R();
        if (user == null) {
           r.data("status_code", false);
            return r.setMsg("用户不存在");
        }
        Long warehouseID = user.getWarehouseid();

        // 构造查询条件
        QueryWrapper<Outbound> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("warehouseid", warehouseID);

        // 执行查询操作，并将结果封装到 Response 对象中返回
        List<Outbound> outboundList = outboundMapper.selectList(queryWrapper);
        List<Map<String, Object>> outList = new ArrayList<>();
        for (Outbound outbound : outboundList) {
            Map<String, Object> item = new HashMap<>();
            item.put("outID", String.valueOf(outbound.getOutboundid()));
            item.put("orderID", outbound.getOrderid());
            item.put("outStatus", outbound.getStatus());
            item.put("outTime", outbound.getOutboundtime().toString());
             Outboundperson wp = outboundpersonMapper.selectById(outbound.getOutboundpersonid());
            if (wp != null) {
                item.put("outPeopleName", wp.getName());
            }

            Ordertable ordertable = orderMapper.selectById(outbound.getOrderid());
            if (ordertable != null) {
                item.put("userName", ordertable.getUsername());
            }

            outList.add(item);
        }

        r.data("status_code", true);
        r.data("outList", outList);
        return r;
    }


    @Override
    public R addInOrder(String id, AddInOrderParam addInOrderParam) {
        User user = userMapper.selectById(id);
        R r = new R();
        if (user == null) {

            r.data("status_code", false);
            r.data("massage", "用户不存在");
            return r;
        }
        Long warehouseID = user.getWarehouseid();

        // 遍历包裹列表，查询包裹是否已经在库中
        ParcelList[] parcelList = addInOrderParam.getParcelList();
        for (ParcelList parcel : parcelList) {
            Package p = packageMapper.selectById(parcel.getParcelID());
            if (p != null) {
                r.data("status_code", false);
                return r.data("massage", "包裹已经在库中");
            }
            QueryWrapper<Warehouseperson> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", addInOrderParam.getInPeopleName());
            // 插入入库记录
            Inbound inbound = new Inbound();
            inbound.setOrderid(addInOrderParam.getOrderID());
            inbound.setInboundid(Long.parseLong(addInOrderParam.getInID()));
            inbound.setWarehousepersonid(warehousepersonMapper.selectOne(queryWrapper).getWarehousepersonid());
            inbound.setStatus("待入库");
            inbound.setInboundtime((Timestamp) new Date());
            inbound.setUserid(id);
            inboundMapper.insert(inbound);

            // 插入订单记录
            Ordertable ordertable = new Ordertable();
            ordertable.setOrderid(addInOrderParam.getOrderID());
            ordertable.setUsername(id);
            ordertable.setPackageid(p.getPackageid());
            orderMapper.insert(ordertable);
        }
        // 更新包裹信息
        for (ParcelList parcel : parcelList) {
            Package p ;
            //= packageMapper.selectById(parcel.getParcelID());
           // if (p != null) {
               // packageMapper.updateById(p);
          //  } else {
                p = new Package();
                p.setPackageid(Long.parseLong(parcel.getParcelID()));
                p.setShippername(parcel.getFromPeople());
                p.setShippercontact(parcel.getFromPhone());
                p.setShipperaddress(parcel.getFromAddr());
                p.setConsigneename(parcel.getToPeople());
                p.setConsigneecontact(parcel.getToPhone());
                p.setConsigneeaddress(parcel.getToAddr());
                packageMapper.insert(p);
            }

        r.data("status_code", true);
        r.data("massage", "入库成功");
        return r;
    }


    @Override
    public R addOutOrder(String id, AddOutOrderParam addOutOrderParam) {
        User user = userMapper.selectById(id);
        R r = new R();
        if (user == null) {
            r.data("status_code", false);
            return r.setMsg("用户不存在");
        }
        Long warehouseID = user.getWarehouseid();
        // 遍历包裹列表，查询包裹是否在库中
        ParcelList[]  parcelList = addOutOrderParam.getParcelList();
        for ( ParcelList parcel : parcelList) {
            Package p = packageMapper.selectById(parcel.getParcelID());
            if (p == null) {
                r.data("status_code", false);
                return r.setMsg("包裹不在库中");
            }

            QueryWrapper<Outboundperson> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", addOutOrderParam.getOutPeopleName());
            // 插入出库记录
            Outbound outbound = new Outbound();
            outbound.setOrderid(addOutOrderParam.getOrderID());
            outbound.setOutboundpersonid(outboundpersonMapper.selectOne(queryWrapper).getOutboundpersonid());
            outbound.setStatus("待出库");
            outbound.setOutboundtime((Timestamp) new Date());
            outbound.setUserid(id);
            outboundMapper.insert(outbound);
            // 插入订单记录
            Ordertable ordertable = new Ordertable();
            ordertable.setOrderid(addOutOrderParam.getOrderID());
            ordertable.setUsername(id);
            ordertable.setPackageid(p.getPackageid());
            orderMapper.insert(ordertable);
        }
        // 更新包裹信息
       // for (ParcelList  parcel : parcelList) {
          //  Package p = packageMapper.deleteById(parcel.getParcelID());

           // packageMapper.updateById(p);
       // }
        r.data("status_code", true);
        return r;

    }

    @Override
    public R showParcel(String id) {
        User user = userMapper.selectById(id);
        R r = new R();
        if (user == null) {
            r.data("status_code", false);
            return r.setMsg("用户不存在");
        }
        Long warehouseID = user.getWarehouseid();
        QueryWrapper<Inbound> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("warehousepersonid", warehouseID);
        queryWrapper.eq("status", "待出库");
        List<Inbound> inboundList = inboundMapper.selectList(queryWrapper);
        List<ParcelList> parcelList = new ArrayList<>();
        for (Inbound inbound : inboundList) {
            String orderId = inbound.getOrderid();
            QueryWrapper<Ordertable> orderWrapper = new QueryWrapper<>();
            orderWrapper.eq("orderid", orderId);
            Ordertable order = orderMapper.selectOne(orderWrapper);
            if (order != null) {
                Long packageId = order.getPackageid();
                Package p = packageMapper.selectById(packageId);
                if (p != null) {
                    ParcelList parcel = new ParcelList();
                    parcel.setParcelID(String.valueOf(p.getPackageid()));
                    parcel.setFromPeople(p.getShippername());
                    parcel.setFromPhone(p.getShippercontact());
                    parcel.setFromAddr(p.getShipperaddress());
                    parcel.setToPeople(p.getConsigneename());
                    parcel.setToPhone(p.getConsigneecontact());
                    parcel.setToAddr(p.getConsigneeaddress());
                    parcelList.add(parcel);
                }
            }
        }
        r.data("status_code", true);
        r.data("parcelList", parcelList);
        return r;
    }

    @Override
    public R fetchInPeopleNameList(String id) {
        QueryWrapper<Warehouseperson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", id);
        List<Warehouseperson> warehousepersonList = warehousepersonMapper.selectList(queryWrapper);
        List<String> inPeopleNameList = new ArrayList<>();
        for (Warehouseperson warehouseperson : warehousepersonList) {
            inPeopleNameList.add(warehouseperson.getName());
        }
        R r = new R();
        r.data("status_code", true);
        r.data("inPeopleNameList", inPeopleNameList);
        return r;
    }

    @Override
    public R fetchOutPeopleNameList(String id) {
        QueryWrapper<Outboundperson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", id);
        List<Outboundperson> outboundpersonList = outboundpersonMapper.selectList(queryWrapper);
        List<String> outPeopleNameList = new ArrayList<>();
        for (Outboundperson outboundperson : outboundpersonList) {
            outPeopleNameList.add(outboundperson.getName());
        }
        R r = new R();
        r.data("status_code", true);
        r.data("outPeopleNameList", outPeopleNameList);
        return r;
    }

    @Override
    public R InDelMultitude(String id, int[] inOrderList) {
        List<Long> inboundIdList = new ArrayList<>();
        for (int i : inOrderList) {
            inboundIdList.add(Long.valueOf(i));
        }
        QueryWrapper<Inbound> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("inboundid", inboundIdList);
        queryWrapper.eq("status", "待入库");
        inboundMapper.delete(queryWrapper);
        R r = new R();
        r.data("status_code", true);
        return r;
    }

    @Override
    public R OutDelMultitude(String id, int[] outOrderList) {
        List<Long> outboundIdList = new ArrayList<>();
        for (int i : outOrderList) {
            outboundIdList.add(Long.valueOf(i));
        }
        QueryWrapper<Outbound> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("outboundid", outboundIdList);
        queryWrapper.eq("status", "待出库");
        outboundMapper.delete(queryWrapper);
        R r = new R();
        r.data("status_code", true);
        return r;
    }

    @Override
    public R ExamineIn(String id, ExamineInParam examineInParam) {
        R r = new R();
        Inbound inbound = inboundMapper.selectById(examineInParam.getInID());
        if (inbound == null) {
            return r.setMsg("入库单不存在");
        }

        // 更新入库单信息
        if (StringUtils.isNotBlank(examineInParam.getOrderID())) {
            inbound.setOrderid(examineInParam.getOrderID());
        }
        if (StringUtils.isNotBlank(examineInParam.getInPeopleName())) {
            Warehouseperson user = warehousepersonMapper.selectOne(new QueryWrapper<Warehouseperson>().eq("name", examineInParam.getInPeopleName()));
            if (user != null) {
                inbound.setWarehousepersonid(user.getWarehousepersonid());
            } else {
                return r.setMsg("入库交接人不存在");
            }
        }
        if (StringUtils.isNotBlank(examineInParam.getInStatus())) {
            inbound.setStatus(examineInParam.getInStatus());
        }
        if (StringUtils.isNotBlank(examineInParam.getInTime())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(examineInParam.getInTime());
                inbound.setInboundtime(new Timestamp(date.getTime()));
            } catch (ParseException e) {
                return r.setMsg("入库时间格式错误");
            }
        }
        inboundMapper.updateById(inbound);

        // 更新包裹信息
        ParcelList[] parcelList = examineInParam.getParcelList();
        if (parcelList != null ) {
            for (ParcelList parcel : parcelList) {
                Package p = packageMapper.selectById(parcel.getParcelID());
                if (p != null) {
                    if (StringUtils.isNotBlank(parcel.getFromPeople())) {
                        p.setShippername(parcel.getFromPeople());
                    }
                    if (StringUtils.isNotBlank(parcel.getFromPhone())) {
                        p.setShippercontact(parcel.getFromPhone());
                    }
                    if (StringUtils.isNotBlank(parcel.getFromAddr())) {
                        p.setShipperaddress(parcel.getFromAddr());
                    }
                    if (StringUtils.isNotBlank(parcel.getToPeople())) {
                        p.setConsigneename(parcel.getToPeople());
                    }
                    if (StringUtils.isNotBlank(parcel.getToPhone())) {
                        p.setConsigneecontact(parcel.getToPhone());
                    }
                    if (StringUtils.isNotBlank(parcel.getToAddr())) {
                        p.setConsigneeaddress(parcel.getToAddr());
                    }
                    packageMapper.updateById(p);
                }
            }
        }

        // 更新订单信息
        Ordertable orderTable = orderMapper.selectOne(new QueryWrapper<Ordertable>().eq("orderid", examineInParam.getOrderID()));
        if (orderTable != null) {
            if (StringUtils.isNotBlank(examineInParam.getUserName())) {
                orderTable.setUsername(examineInParam.getUserName());
            }
            if (StringUtils.isNotBlank(examineInParam.getManagerName())) {
                User user = userMapper.selectOne(new QueryWrapper<User>().eq("name", examineInParam.getManagerName()));
                if (user != null) {
                    orderTable.setUsername(user.getName());
                } else {
                    return r.setMsg("订单管理员不存在");
                }
            }
            orderMapper.updateById(orderTable);
        }

        r.data("status_code", true);
        return r;
    }

    @Override
    public R ExamineOut(String id, ExamineOutParam examineOutParam) {
        return null;
    }

    @Override
    public R InNeedTocheck(String id) {
        return null;
    }

    @Override
    public R OutNeedTocheck(String id) {
        return null;
    }

    @Override
    public R singleInOrderDetail(String id, String InID) {
        return null;
    }

    @Override
    public R singleOutOrderDetail(String id, String OutID) {
        return null;
    }

    @Override
    public R InNeedToEnter(String id) {
        return null;
    }

    @Override
    public R OutNeedToOut(String id) {
        return null;
    }
}
