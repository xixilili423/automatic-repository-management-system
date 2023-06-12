package com.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    @Autowired
    private ShelfMapper shelfMapper;
    @Autowired
    private  ShelfitemMapper shelfitemMapper;

    @Override
    public R searchIn(String id,SearchInParam searchInParam) {
        R r = new R();
        // 从请求参数中获取查询条件
        // 构造查询条件
        QueryWrapper<Inbound> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchInParam.getInID())) {
            Long inID = Long.parseLong(searchInParam.getInID());
            queryWrapper.eq("inboundid", inID);
        }
        if (StringUtils.isNotBlank(searchInParam.getInPeopleName())) {
            String inPeopleName = searchInParam.getInPeopleName();
            queryWrapper.eq("warehousepersonid", warehousepersonMapper.selectOne(new QueryWrapper<Warehouseperson>().eq("name",inPeopleName)).getWarehousepersonid());
        }
        if (StringUtils.isNotBlank(searchInParam.getInStatus())) {
            String inStatus = searchInParam.getInStatus();
            queryWrapper.eq("status", inStatus);
        }
        if (StringUtils.isNotBlank(searchInParam.getOrderID())) {
            String orderID = searchInParam.getOrderID();
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
                item.setUserName(userMapper.selectById(inbound.getUserid()).getName());
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
        // 构造查询条件
        QueryWrapper<Outbound> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchOutParam.getOutID())) {
            String outID = searchOutParam.getOutID();
            queryWrapper.eq("outboundid", outID);
        }
        if (StringUtils.isNotBlank(searchOutParam.getOutPeopleName())) {
            String outPeopleName = searchOutParam.getOutPeopleName();
            queryWrapper.eq("outboundpersonid",outboundpersonMapper.selectOne(new QueryWrapper<Outboundperson>().eq("name",outPeopleName)).getOutboundpersonid());
        }
        if (StringUtils.isNotBlank(searchOutParam.getOutStatus())) {
            String outStatus = searchOutParam.getOutStatus();
            queryWrapper.eq("status", outStatus);
        }
        if (StringUtils.isNotBlank(searchOutParam.getOrderID())) {
            String orderID = searchOutParam.getOrderID();
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
                item.put("userName", userMapper.selectById(outbound.getUserid()).getName());
                item.put("outPeopleName", warehouseperson.getName());
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
        R r = new R();
        QueryWrapper<Inbound> queryWrapper = new QueryWrapper<>();
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
                item.put("userName", userMapper.selectById(inbound.getUserid()).getName());
            }
            inList.add(item);
        }
        r.data("status_code", true);
        r.data("inList", inList);
        return r;

    }

    @Override
    public R showOut(String id) {
        R r = new R();
        // 构造查询条件
        QueryWrapper<Outbound> queryWrapper = new QueryWrapper<>();
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
                item.put("userName", userMapper.selectById(outbound.getUserid()).getName());
            }
            outList.add(item);
        }
        r.data("status_code", true);
        r.data("outList", outList);
        return r;
    }


    @Override
    public R addInOrder(String id, AddInOrderParam addInOrderParam) {
        R r = new R();
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
            inbound.setStatus("待审核");
            inbound.setInboundtime(Timestamp.valueOf(LocalDateTime.now()));
            inbound.setUserid(id);
            try {
                inboundMapper.insert(inbound);
            } catch (Exception e) {
                // 处理异常
               return r.setMsg( e.toString());
            }
            // 插入订单记录
            Ordertable ordertable = new Ordertable();
            ordertable.setOrderid(addInOrderParam.getOrderID());
            ordertable.setUsername(userMapper.selectById(id).getName());
            ordertable.setPackageid(parcel.getParcelID());
            try {
                orderMapper.insert(ordertable);
            }catch (Exception e) {
                // 处理异常
                return r.setMsg( e.toString());
            }
        }
        // 更新包裹信息
       for (ParcelList parcel : parcelList) {
            Package p ;
            //= packageMapper.selectById(parcel.getParcelID());
           // if (p != null) {
               // packageMapper.updateById(p);
          //  } else {
                p = new Package();
                p.setPackageid(parcel.getParcelID());
                p.setShippername(parcel.getFromPeople());
                p.setShippercontact(parcel.getFromPhone());
                p.setShipperaddress(parcel.getFromAddr());
                p.setConsigneename(parcel.getToPeople());
                p.setConsigneecontact(parcel.getToPhone());
                p.setConsigneeaddress(parcel.getToAddr());
                p.setStatus("待审核");
                packageMapper.insert(p);
            }
        r.data("status_code", true);
        r.data("massage", "入库单添加成功");
        return r;
    }


    @Override
    public R addOutOrder(String id, AddOutOrderParam addOutOrderParam) {
      //  User user = userMapper.selectById(Long.parseLong(id));
        R r = new R();
       /* if (user == null) {
            r.data("status_code", false);
            return r.setMsg("用户不存在");
        }
        */

        //Long warehouseID = user.getWarehouseid();
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
            outbound.setOutboundid(Long.parseLong(addOutOrderParam.getOutID()));
            try{
            outbound.setOutboundpersonid(outboundpersonMapper.selectOne(queryWrapper).getOutboundpersonid());}
            catch (Exception e)
            {
                return r.setMsg(e.toString());
            }
            outbound.setStatus("待审核");
            outbound.setOutboundtime(Timestamp.valueOf(LocalDateTime.now()));
            outbound.setUserid(id);
            try {
                outboundMapper.insert(outbound);
            } catch (Exception e) {
                // 处理异常
                return r.setMsg( e.toString());
            }
            // 插入订单记录
            Ordertable ordertable = new Ordertable();
            ordertable.setOrderid(addOutOrderParam.getOrderID());
            ordertable.setUsername(userMapper.selectById(id).getName());
            ordertable.setPackageid(parcel.getParcelID());
            try {
                orderMapper.insert(ordertable);
            }catch (Exception e) {
                // 处理异常
                return r.setMsg( e.toString());
            }
        }
        // 更新包裹信息
        for (ParcelList  parcel : parcelList) {
            Package p ;
            //= packageMapper.selectById(parcel.getParcelID());
            // if (p != null) {
            // packageMapper.updateById(p);
            //  } else {
            p = new Package();
            p.setPackageid(parcel.getParcelID());
            p.setShippername(parcel.getFromPeople());
            p.setShippercontact(parcel.getFromPhone());
            p.setShipperaddress(parcel.getFromAddr());
            p.setConsigneename(parcel.getToPeople());
            p.setConsigneecontact(parcel.getToPhone());
            p.setConsigneeaddress(parcel.getToAddr());
            p.setStatus("待审核");
            UpdateWrapper<Package> updateWrapper=new UpdateWrapper();
            updateWrapper.eq("packageid",parcel.getParcelID());
            packageMapper.update(p,updateWrapper);
        }
        r.data("status_code", true);
        r.data("massage", "出库单添加成功");
        return r;
    }

    @Override
    public R showParcel(String id) {
        R r = new R();
        /*
        User user = userMapper.selectById(id);
        if (user == null) {
            r.data("status_code", false);
            return r.setMsg("用户不存在");
        }
        */
        QueryWrapper<Package> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "已入库");
        List<Package> List1 = packageMapper.selectList(queryWrapper);
        List<ParcelList> parcelList = new ArrayList<>();
        for (Package p: List1) {
                if (p != null) {
                    ParcelList parcel = new ParcelList();
                    parcel.setParcelID(p.getPackageid());
                    parcel.setFromPeople(p.getShippername());
                    parcel.setFromPhone(p.getShippercontact());
                    parcel.setFromAddr(p.getShipperaddress());
                    parcel.setToPeople(p.getConsigneename());
                    parcel.setToPhone(p.getConsigneecontact());
                    parcel.setToAddr(p.getConsigneeaddress());
                    parcelList.add(parcel);
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
    public R InDelMultitude(String id, Long[] inOrderList) {
        List<Long> inboundIdList = new ArrayList<>();
        for (long i : inOrderList) {
            inboundIdList.add(i);
        }
        QueryWrapper<Inbound> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("inboundid", inboundIdList);
        inboundMapper.delete(queryWrapper);
        R r = new R();
        r.data("status_code", true);
        return r;
    }

    @Override
    public R OutDelMultitude(String id, Long[]  outOrderList) {
        List<Long> outboundIdList = new ArrayList<>();
        for (long i : outOrderList) {
            outboundIdList.add(i);
        }
        QueryWrapper<Outbound> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("outboundid", outboundIdList);
        outboundMapper.delete(queryWrapper);
        R r = new R();
        r.data("status_code", true);
        return r;
    }

    @Override
    public R ExamineIn(String id, ExamineInParam examineInParam) {
        R r = new R();
        Inbound inbound = inboundMapper.selectById(Long.parseLong(examineInParam.getInID()));
        if (inbound == null) {
            return r.setMsg("入库单不存在");
        }
        inbound.setUserid(id);
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
            inbound.setInboundtime(Timestamp.valueOf(LocalDateTime.now()));
        }
        inbound.setManagerid(id);
        UpdateWrapper<Inbound> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("inboundid", inbound.getInboundid());
        // 更新 Inbound 对象
        inboundMapper.update(inbound, updateWrapper);
        // 更新包裹信息
        ParcelList[] parcelList = examineInParam.getParcelList();
        if (parcelList != null && "已入库".equals(examineInParam.getInStatus())) {
            for (ParcelList parcel : parcelList) {
                List<Shelf> availableShelves = selectAvailableShelves();//检查货架是否可用
                if (availableShelves.isEmpty()) {
                    // 如果没有可用的货架，返回错误信息
                    return r.setMsg("No available shelf");
                }
                // 选择第一个可用的货架
                Package p = new Package();
                p.setPackageid(parcel.getParcelID());
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
                p.setStatus(examineInParam.getInStatus());
                UpdateWrapper<Package> updateWrapper1=new UpdateWrapper();
                updateWrapper1.eq("packageid", parcel.getParcelID());
                packageMapper.update(p,updateWrapper1);
                Shelf selectedShelf = availableShelves.get(0);
                // 更新货架信息
                selectedShelf.setRemainingcapacity(selectedShelf.getRemainingcapacity() - 1);
                UpdateWrapper<Shelf> shelfUpdateWrapper = new UpdateWrapper<>();
                shelfUpdateWrapper.eq("shelfid", selectedShelf.getShelfid());
                shelfMapper.update(selectedShelf, shelfUpdateWrapper);
                // 插入货物和包裹的对应信息
                Shelfitem newShelfitem = new Shelfitem();
                newShelfitem.setPackageid(p.getPackageid());
                newShelfitem.setShelfid(selectedShelf.getShelfid());
                newShelfitem.setLocationid(selectedShelf.getCapacity() - selectedShelf.getRemainingcapacity()); // 这里假设货物放在货架的第一个位置
                shelfitemMapper.insert(newShelfitem);
                // 返回成功信息
            }
            r.setMsg("入库单入库成功");
            return   r.data("status_code", true);
        } else {
            for (ParcelList parcel : parcelList) {
                if ("已拒绝".equals(examineInParam.getInStatus())) {
                    packageMapper.delete(new QueryWrapper<Package>().eq("packageid", parcel.getParcelID()));
                    r.setMsg("入库单拒绝成功");
                } else {
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
                        p.setStatus(examineInParam.getInStatus());
                        UpdateWrapper<Package> packageUpdateWrapper = new UpdateWrapper<>();
                        packageUpdateWrapper.eq("packageid", p.getPackageid());
                        packageMapper.update(p, packageUpdateWrapper);
                        r.setMsg("入库单修改成功");
                    }

                }
            }
        }
        r.data("status_code", true);
        return r;
    }


    @Override
    public R ExamineOut(String id, ExamineOutParam examineOutParam) {
        R r = new R();
        Outbound outbound = outboundMapper.selectById(Long.parseLong(examineOutParam.getOutID()));
        if (outbound == null) {
            return r.setMsg("出库单不存在");
        }
        // 更新出库单信息
        if (StringUtils.isNotBlank(examineOutParam.getOrderID())) {
            outbound.setOrderid(examineOutParam.getOrderID());
            outbound.setUserid(id);
        }
        if (StringUtils.isNotBlank(examineOutParam.getOutPeopleName())) {
            Outboundperson user = outboundpersonMapper.selectOne(new QueryWrapper<Outboundperson>().eq("name", examineOutParam.getOutPeopleName()));
            if (user != null) {
                outbound.setOutboundpersonid(user.getOutboundpersonid());            }
            else {
                return r.setMsg("出库交接人不存在");
            }
        }
        if (StringUtils.isNotBlank(examineOutParam.getOutStatus())) {
            outbound.setStatus(examineOutParam.getOutStatus());
        }
        if (StringUtils.isNotBlank(examineOutParam.getOutTime())) {
            outbound.setOutboundtime(Timestamp.valueOf(LocalDateTime.now()));

        }
        outbound.setManagerid(id);
        UpdateWrapper<Outbound> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("outboundid", outbound.getOutboundid());
        // 更新 outbound 对象
        outboundMapper.update(outbound, updateWrapper);
        // 更新包裹信息
        ParcelList[] parcelList = examineOutParam.getParcelList();
        if (parcelList != null &&!"已出库".equals(examineOutParam.getOutStatus())) {
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
                    p.setStatus(examineOutParam.getOutStatus());
                    UpdateWrapper<Package> packageUpdateWrapper = new UpdateWrapper<>();
                    packageUpdateWrapper.eq("packageid", p.getPackageid());
                    packageMapper.update(p, packageUpdateWrapper);
                }
            }
            r.setMsg("出库单审核成功");
            r.data("status_code", true);
        }
        // 删除包裹表，删除货架表
       else if (parcelList != null&&"已出库".equals(examineOutParam.getOutStatus())) {
            for (ParcelList parcel : parcelList) {
                packageMapper.delete(new QueryWrapper<Package>().eq("packageid", parcel.getParcelID()));
            Shelfitem shelfitem = shelfitemMapper.selectOne(new QueryWrapper<Shelfitem>().eq("packageid", parcel.getParcelID()));
            if (shelfitem == null) {
                // 如果 Shelfitem 表中不存在对应记录，返回错误信息
                return r.setMsg("Shelfitem not found");
            }
            // 查询 Shelf 表获取货架信息
            Shelf shelf = shelfMapper.selectById(Long.parseLong(shelfitem.getShelfid()));
            if (shelf == null) {
                // 如果 Shelf 表中不存在对应记录，返回错误信息
                return r.setMsg("Shelf not found");
            }
            // 更新货架信息
            shelf.setRemainingcapacity(shelf.getRemainingcapacity() + 1);
            UpdateWrapper<Shelf> shelfUpdateWrapper = new UpdateWrapper<>();
            shelfUpdateWrapper.eq("shelfid", shelf.getShelfid());
            shelfMapper.update(shelf, shelfUpdateWrapper);
            // 删除货物和包裹的对应信息
            shelfitemMapper.delete(new QueryWrapper<Shelfitem>().eq("packageid", parcel.getParcelID()));
            // 返回成功信息
            }
            r.setMsg("出库单出库成功");
            r.data("status_code", true);
        }
        else {
            return r.setMsg("包裹不存在");
        }

        return r;
    }

    @Override
    public R InNeedTocheck(String id) {
        R r = new R();
        List<Inbound> inboundList = inboundMapper.selectList(new QueryWrapper<Inbound>().eq("status", "待审核"));
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Inbound inbound : inboundList) {
            Map<String, Object> map = new HashMap<>();
            map.put("inID", inbound.getInboundid());
            map.put("orderID", inbound.getOrderid());
            Warehouseperson warehouseperson = warehousepersonMapper.selectById(inbound.getWarehousepersonid());
            if (warehouseperson != null) {
                map.put("inPeopleName", warehouseperson.getName());
            }
            map.put("inStatus", inbound.getStatus());
            map.put("inTime", inbound.getInboundtime().toString());
            User orderTable = userMapper.selectOne(new QueryWrapper<User>().eq("id", inbound.getUserid()));
            if (orderTable != null) {
                map.put("userName", orderTable.getName());
            }
            resultList.add(map);
        }
        r.data("inList", resultList);
        r.data("status_code", true);
        return r;
    }

    @Override
    public R OutNeedTocheck(String id) {
        R r = new R();
        List<Outbound> outboundList = outboundMapper.selectList(new QueryWrapper<Outbound>().eq("status", "待审核"));
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Outbound outbound : outboundList) {
            Map<String, Object> map = new HashMap<>();
            map.put("outID", outbound.getOutboundid());
            map.put("orderID", outbound.getOrderid());
            Outboundperson outboundperson = outboundpersonMapper.selectById(outbound.getOutboundpersonid());
            if (outboundperson != null) {
                map.put("outPeopleName", outboundperson.getName());
            }
            map.put("outStatus", outbound.getStatus());
            map.put("outTime", outbound.getOutboundtime().toString());
            User orderTable = userMapper.selectOne(new QueryWrapper<User>().eq("id", outbound.getUserid()));
            if (orderTable != null) {
                map.put("userName", orderTable.getName());
            }
            resultList.add(map);
        }
        r.data("outList", resultList);
        r.data("status_code", true);
        return r;
    }

    @Override
    public R singleInOrderDetail(String id, String InID) {

        R r = new R();
        Inbound inbound = inboundMapper.selectById(Long.parseLong(InID));
        if (inbound == null) {
            r.data("status_code", false);
            r.data("message", "入库单不存在");
            return r;
        }
        r.data("inID", inbound.getInboundid());
        r.data("orderID", inbound.getOrderid());
        Warehouseperson warehouseperson = warehousepersonMapper.selectById(inbound.getWarehousepersonid());
        if (warehouseperson != null) {
            r.data("inPeopleName", warehouseperson.getName());
        }
        r.data("inStatus", inbound.getStatus());
        r.data("inTime", inbound.getInboundtime().toString());
        Ordertable orderTable = orderMapper.selectOne(new QueryWrapper<Ordertable>().eq("orderid", inbound.getOrderid()));
        if (orderTable != null) {
            r.data("userName", orderTable.getUsername());
        }
        if(inbound.getManagerid()!=null)
        {
        User user = userMapper.selectById(inbound.getManagerid());

        }
        List<Package> packageList = packageMapper.selectList(new QueryWrapper<Package>().eq("packageid", orderTable.getPackageid()));
        List<Map<String, Object>> parcelMapList = new ArrayList<>();
        for (Package p : packageList) {
            Map<String, Object> parcelMap = new HashMap<>();
            parcelMap.put("parcelID", p.getPackageid());
            parcelMap.put("fromPeople", p.getShippername());
            parcelMap.put("fromPhone", p.getShippercontact());
            parcelMap.put("fromAddr", p.getShipperaddress());
            parcelMap.put("toPeople", p.getConsigneename());
            parcelMap.put("toPhone", p.getConsigneecontact());
            parcelMap.put("toAddr", p.getConsigneeaddress());
            parcelMapList.add(parcelMap);
        }
        r.data("parcelList", parcelMapList);
        r.data("status_code", true);
        return r;
    }

    @Override
    public R singleOutOrderDetail(String id, String OutID) {
        R r = new R();
        Outbound outbound = outboundMapper.selectById(Long.parseLong(OutID));
        if (outbound == null) {
            r.data("status_code", false);
            r.data("message", "出库单不存在");
            return r;
        }
        r.data("outID", outbound.getOutboundid());
        r.data("orderID", outbound.getOrderid());
        Warehouseperson warehouseperson = warehousepersonMapper.selectById(outbound.getOutboundpersonid());
        if (warehouseperson != null) {
            r.data("outPeopleName", warehouseperson.getName());
        }
        r.data("outStatus", outbound.getStatus());
        r.data("outTime", outbound.getOutboundtime().toString());
        Ordertable orderTable = orderMapper.selectOne(new QueryWrapper<Ordertable>().eq("orderid", outbound.getOrderid()));
        if (orderTable != null) {
            r.data("userName", orderTable.getUsername());
        }
        if(outbound.getManagerid()!=null) {
            User user = userMapper.selectById(outbound.getManagerid());
            r.data("managerName", user.getName());
        }
        List<Package> packageList = packageMapper.selectList(new QueryWrapper<Package>().eq("packageid", orderTable.getPackageid()));
        List<Map<String, Object>> parcelMapList = new ArrayList<>();
        for (Package p : packageList) {
            Map<String, Object> parcelMap = new HashMap<>();
            parcelMap.put("parcelID", p.getPackageid());
            parcelMap.put("fromPeople", p.getShippername());
            parcelMap.put("fromPhone", p.getShippercontact());
            parcelMap.put("fromAddr", p.getShipperaddress());
            parcelMap.put("toPeople", p.getConsigneename());
            parcelMap.put("toPhone", p.getConsigneecontact());
            parcelMap.put("toAddr", p.getConsigneeaddress());
            parcelMapList.add(parcelMap);
        }
        r.data("parcelList", parcelMapList);
        r.data("status_code", true);
        return r;
    }

    @Override
    public R InNeedToEnter(String id) {
        R r = new R();
        List<Inbound> inboundList = inboundMapper.selectList(new QueryWrapper<Inbound>()
                .eq("userid", id)
                .eq("status", "待入库"));
        List<Map<String, Object>> inMapList = new ArrayList<>();
        for (Inbound inbound : inboundList) {
            Map<String, Object> inMap = new HashMap<>();
            inMap.put("inID", inbound.getInboundid());
            inMap.put("orderID", inbound.getOrderid());
            Warehouseperson warehouseperson = warehousepersonMapper.selectById(inbound.getWarehousepersonid());
            if (warehouseperson != null) {
                inMap.put("inPeopleName", warehouseperson.getName());
            }
            inMap.put("inStatus", inbound.getStatus());
            inMap.put("inTime", inbound.getInboundtime().toString());
            Ordertable orderTable = orderMapper.selectOne(new QueryWrapper<Ordertable>().eq("orderid", inbound.getOrderid()));
            if (orderTable != null) {
                inMap.put("userName", orderTable.getUsername());
            }
            inMapList.add(inMap);
        }
        r.data("inList", inMapList);
        r.data("status_code", true);
        return r;
    }

    @Override
    public R OutNeedToOut(String id) {
        R r = new R();
        List<Outbound> outboundList = outboundMapper.selectList(new QueryWrapper<Outbound>()
                .eq("userid", id)
                .eq("status", "待出库"));
        List<Map<String, Object>> outMapList = new ArrayList<>();
        for (Outbound outbound : outboundList) {
            Map<String, Object> outMap = new HashMap<>();
            outMap.put("outID", outbound.getOutboundid());
            outMap.put("orderID", outbound.getOrderid());
            Warehouseperson warehouseperson = warehousepersonMapper.selectById(outbound.getOutboundpersonid());
            if (warehouseperson != null) {
                outMap.put("outPeopleName", warehouseperson.getName());
            }
            outMap.put("outStatus", outbound.getStatus());
            outMap.put("outTime", outbound.getOutboundtime().toString());
            Ordertable orderTable = orderMapper.selectOne(new QueryWrapper<Ordertable>().eq("orderid", outbound.getOrderid()));
            if (orderTable != null) {
                outMap.put("userName", orderTable.getUsername());
            }
            outMapList.add(outMap);
        }
        r.data("outList", outMapList);
        r.data("status_code", true);
        return r;
    }
    public List<Shelf> selectAvailableShelves() {
        return shelfMapper.selectList(new QueryWrapper<Shelf>().gt("remainingcapacity", 0));
    }
}
