package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.entity.Area;
import com.entity.Shelf;
import com.mapper.AreaMapper;
import com.mapper.ShelfMapper;
import com.service.PeopleService;
import com.service.RegionAndShelfService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RegionAndShelfImpl implements RegionAndShelfService {
    @Autowired
    private AreaMapper areaMapper;
    private ShelfMapper shelfMapper;


    public R searchRegion(String id, searchRegionparam params) {
        R r = new R();
        r.data("status_code",false);

        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("areaname", params.getRegionName());

        List<Area> areas = areaMapper.selectList(queryWrapper);

        List<Map<String, Object>> ParamList = new ArrayList<>();

        for (Area area : areas) {
            Map<String, Object> param = new HashMap<>();
            param.put("regionId", area.getAreaid());
            param.put("shelfNumber", area.getShelfcount());
            param.put("regionName", area.getAreaname());

            ParamList.add(param);
        }

        r.data("status_code",true);
        r.data("inBoundPeopleList", ParamList);
        return r;
    }

    public R searchShelf(String id, searchShelfparam params) {
        R r = new R();
        r.data("status_code",false);

        QueryWrapper<Shelf> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("areaname", params.getShelfId());

        List<Shelf> shelfs = shelfMapper.selectList(queryWrapper);

        if(shelfs.size()>0){
            r.data("status_code",true);
        }

        List<Map<String, Object>> ParamList = new ArrayList<>();

        for (Shelf shelf : shelfs) {
            Map<String, Object> param = new HashMap<>();
            param.put("regionId", shelf.getAreaid());
            param.put("shelfId", shelf.getShelfid());
            param.put("shelfCapacity", shelf.getCapacity());
            param.put("shelfAllowance", shelf.getRemainingcapacity());

            ParamList.add(param);
        }

        r.data("inBoundPeopleList", ParamList);
        return r;
    }

    //对应库区表货架数加一，货架表创建一个新货架,新货架默认容量为100
    public R addShelf(String id, addShelfparam params) {
        R r = new R();
        r.data("status_code",false);

        QueryWrapper<Area> areaQueryWrapper = new QueryWrapper<>();
        areaQueryWrapper.eq("areaid", params.getRegionId());

        Area area = areaMapper.selectOne(areaQueryWrapper);

        if (area != null) {
            r.data("status_code",true);

            int newShelfCount = Math.toIntExact(area.getShelfcount() + 1);

            UpdateWrapper<Area> areaUpdateWrapper = new UpdateWrapper<>();
            areaUpdateWrapper.set("shelfcount", newShelfCount)
                    .eq("areaid", params.getRegionId());

            areaMapper.update(null, areaUpdateWrapper);

            Shelf newShelf = new Shelf();
            newShelf.setAreaid(Long.parseLong(params.getRegionId()));
            newShelf.setCapacity(100);
            newShelf.setRemainingcapacity(100);

            shelfMapper.insert(newShelf);
        }

        return r;
    }

    public R allShelf(String id, allShelfparam params) {
        R r = new R();
        r.data("status_code",false);

        List<Map<String, Object>> shelfList = new ArrayList<>();

        QueryWrapper<Shelf> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("areaid", params.getRegionId());

        List<Shelf> shelves = shelfMapper.selectList(queryWrapper);

        if(shelves.size()>0){
            r.data("status_code",true);
        }

        for (Shelf shelf : shelves) {
            Map<String, Object> shelfInfo = new HashMap<>();
            shelfInfo.put("regionId", params.getRegionId());
            shelfInfo.put("shelfId", shelf.getShelfid());
            shelfInfo.put("capacity", shelf.getCapacity());
            shelfInfo.put("remainingCapacity", shelf.getRemainingcapacity());

            shelfList.add(shelfInfo);
        }

        r.data("shelfList", shelfList);
        return r;
    }

    public R deleteShelf(String id, deleteShelfparam params) {
        R r = new R();
        r.data("status_code",false);

        UpdateWrapper<Shelf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("shelfid", params.getShelfId());

        int affectedRows = shelfMapper.delete(updateWrapper);

        if(affectedRows>0){
            r.data("status_code",true);
        }

        return r;
    }



}