package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.*;
import com.entity.Package;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mapper.*;
import com.vo.param.*;
import com.service.ParcelService;
import com.vo.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    @Autowired
    private final ParcelMapper parcelMapper;
    @Autowired
    private  final ShelfitemMapper shelfitemMapper;
    @Autowired
    private  final AreaMapper areaMapper;
    @Autowired
    private  final ShelfMapper shelfMapper;
    @Autowired
    private  final OrderMapper orderMapper;
    @Autowired
    private  final InboundMapper inboundMapper;
    @Autowired
    private  final OutboundMapper outboundMapper;
    @Override
    public R searchParcel(String id, SearchParcelParam searchParcelParam) {
        System.out.println("成功函数");
        R r = new R();
        r.data("status_code",false);
        QueryWrapper<Package> queryWrapper = new QueryWrapper<>();
        System.out.println(searchParcelParam.getParcelId());
        if (searchParcelParam.getParcelId() != null) {
            queryWrapper.eq("packageid", searchParcelParam.getParcelId());
        }
        if (searchParcelParam.getShelfID() != null) {
            QueryWrapper<Shelfitem> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("shelfid", searchParcelParam.getShelfID());
            List<Shelfitem> si = shelfitemMapper.selectList(queryWrapper3);
            if (!si.isEmpty()) {
                Shelfitem shelfitem = si.get(0);
                queryWrapper.eq("packageid", shelfitem.getPackageid());
            }
        }
        if (searchParcelParam.getRegionName() != null) {
            QueryWrapper<Shelf> queryWrapper2 = new QueryWrapper<>();
            QueryWrapper<Area> queryWrapper1 = new QueryWrapper<>();
            queryWrapper2.eq("areaid", queryWrapper1.select("areaid").eq("areaname", searchParcelParam.getRegionName()));
            List<Shelf> s = shelfMapper.selectList(queryWrapper2);
            if (!s.isEmpty()) {
                List<String> shelfIds = s.stream().map(Shelf::getShelfid).collect(Collectors.toList());
                QueryWrapper<Shelfitem> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.in("shelfid", shelfIds);
                List<Shelfitem> si = shelfitemMapper.selectList(queryWrapper3);
                if (!si.isEmpty()) {
                    List<Long> parcelIds;
                    List<Long> list = new ArrayList<>();
                    for (Shelfitem shelfitem : si) {
                        Long packageid = shelfitem.getPackageid();
                        list.add(packageid);
                    }
                    parcelIds = list;
                    queryWrapper.in("packageid", parcelIds);
                }
            }
        }
        if (searchParcelParam.getPacelState() != null) {
            queryWrapper.eq("status", searchParcelParam.getPacelState());
        }
        List<Package> p = parcelMapper.selectList(queryWrapper);
        if (!p.isEmpty()) {
            Package parcel = p.get(0);
            PacelInformation pacelInformation = new PacelInformation();
            pacelInformation.setParcelId(String.valueOf(parcel.getPackageid()));
            pacelInformation.setConsigneeAddress(parcel.getConsigneeaddress());
            pacelInformation.setConsigneePhone(parcel.getConsigneecontact());
            pacelInformation.setConsigneeName(parcel.getConsigneename());
            pacelInformation.setShipperPhone(parcel.getShippercontact());
            pacelInformation.setShipperAddress(parcel.getShipperaddress());
            pacelInformation.setShipperName(parcel.getShippername());
            r.data("parcelInformation", pacelInformation);
            r.data("status_code", true);
        }
       System.out.println(r);
    return r;
    }

    @Override
    public R searchParcelDetail(String id, String parcelId) {
        System.out.println(parcelId);
        R r = new R();
// 创建 ObjectMapper 对象，用于解析 JSON 字符串
//        ObjectMapper objectMapper = new ObjectMapper();
//// 解析 JSON 字符串，转换为一个 Map 对象
//        Map<String, String> map = null;
//        try {
//            map = objectMapper.readValue(parcelId, Map.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
// 获取 pacelId 属性值，并将其转换为 Long 类型
        //Long parcelid = Long.parseLong(map.get("parcelId"));
        Long parcelid = Long.parseLong(parcelId);
      SearchPacelDetailParam s=new SearchPacelDetailParam();
        QueryWrapper<Shelfitem> queryWrapper3 = new QueryWrapper<>();
        QueryWrapper<Ordertable> queryWrapper = new QueryWrapper<>();
        queryWrapper3.eq("packageid",parcelid);
        queryWrapper.eq("packageid",parcelid);
        r.data("parcelId",parcelid);
        Shelfitem shelf=shelfitemMapper.selectOne(queryWrapper3);
        if(shelf!=null) {
            r.data("shelfNumber", shelf.getLocationid());
            r.data("shelfId", shelf.getShelfid());
            List<Ordertable> o = orderMapper.selectList(queryWrapper);
            for (Ordertable ordertable : o) {
                QueryWrapper<Inbound> queryWrapper1 = new QueryWrapper<>();
                QueryWrapper<Outbound> queryWrapper2 = new QueryWrapper<>();
                queryWrapper1.eq("orderid", ordertable.getOrderid());
                queryWrapper2.eq("orderid", ordertable.getOrderid());
                Inbound inbound = inboundMapper.selectOne(queryWrapper1);
                Outbound outbound = outboundMapper.selectOne(queryWrapper2);
                r.data("inTime", inbound.getInboundtime().toString());
                r.data("outTime", outbound.getOutboundtime().toString());
                r.data("parcelState", inbound.getStatus());
                r.data("parcelState", outbound.getStatus());
            }
        }
        else
        {
            r.setMsg("查询不到货架信息");
        }

        return r;
    }

    @Override
    public R deleteParcel(String id, String pacelId) {
        R r = new R();
        r.data("status_code",false);
        QueryWrapper<Package> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("packageid", pacelId);
        Package parcel = parcelMapper.selectOne(queryWrapper);
        if (parcel != null) {
            // 删除包裹信息
            parcelMapper.delete(queryWrapper);
            // 删除货架商品关系信息
            QueryWrapper<Shelfitem> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("packageid", pacelId);
            Shelfitem shelfitem = shelfitemMapper.selectOne(queryWrapper2);
            if (shelfitem != null) {
                shelfitemMapper.delete(queryWrapper2);
                // 货架剩余容量加1
                String shelfId = shelfitem.getShelfid();
                QueryWrapper<Shelf> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("shelfid", shelfId);
                Shelf shelf = shelfMapper.selectOne(queryWrapper3);
                if (shelf != null) {
                    long remainingCapacity = shelf.getRemainingcapacity();
                    shelf.setRemainingcapacity(remainingCapacity + 1);
                    shelfMapper.updateById(shelf);
                    r.data("status_code",true);
                }
            }
        }
        return r;
    }

    @Override
    public R searchAllParcel(String id) {
        List<Package> packageList = parcelMapper.selectList(null);
        PacelInformation[] parcelInformation = new PacelInformation[packageList.size()];
        int i = 0;
        for (Package p : packageList) {
            PacelInformation parcel =new PacelInformation();
            parcel.setParcelId( String.valueOf(p.getPackageid()));
            parcel.setShipperName( p.getShippername());
            parcel.setShipperPhone(p.getShippercontact());
            parcel.setShipperAddress( p.getShipperaddress());
            parcel.setConsigneeName(p.getConsigneename());
            parcel.setConsigneePhone(p.getConsigneecontact());
            parcel.setConsigneeAddress(p.getConsigneeaddress());
            parcelInformation[i]=parcel;
            i++;
        }
        R r = new R();
        r.data("status_code", true);
        r.data("parcelInformation", parcelInformation);
        return r;
    }
}
