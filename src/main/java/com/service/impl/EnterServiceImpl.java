package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.Place;
import com.entity.StockIn;
import com.entity.StockOut;
import com.entity.Warehouse;
import com.mapper.*;
import com.service.EnterService;
import com.vo.R;
import com.vo.param.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.vo.param.CheckParcelParam;
import com.vo.param.EnterParam;
import com.vo.param.TableData;
import com.vo.param.Parcel;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vo.param.Avg;
import com.vo.param.parcelReturn;
import com.vo.param.ParcelList;
import com.entity.StockOut;
import com.service.impl.InitStockImpl;


@Service
@AllArgsConstructor
public class EnterServiceImpl extends ServiceImpl<EnterMapper, StockIn> implements EnterService {
    private final UserMapper userMapper;

    private final EnterMapper enterMapper;

    private final OutMapper outMapper;

    private final PlaceMapper placeMapper;


    @Autowired
    private final WareMapper wareMapper;

    //创建小车列表
    private Avg[] createAvg(int avgNumber){
        Avg[] avgList = new Avg[avgNumber];
        for(int i=0;i<avgNumber;i++){
            Avg a = new Avg(i);
            avgList[i] = a;
        }
        for (int i = 0;i<avgList.length;i++){
            System.out.println("id:"+avgList[i].getId());
        }

        return avgList;
    }
    // 筛选包裹（出入库不太一样）
    // 入库版本
    private ParcelList[] select(Parcel[] parcel , String token){
        // 获取对应warehouse
        String username = JWT.decode(token).getAudience().get(0);
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Warehouse warehouse = wareMapper.selectOne(queryWrapper);
        // 循环判断，获取可入库包裹
        ParcelList[] parcelLists = new ParcelList[parcel.length];

        for(int i = 0;i<parcel.length;i++){
            // 可以入库的：不在入库表里，或者出库表内
            QueryWrapper<StockIn> queryWrapper1 = new QueryWrapper<>();
            boolean in = enterMapper.exists(queryWrapper1.eq("warehouse",warehouse.getId()).eq("parcel",parcel[i].getId()));
            QueryWrapper<StockOut> queryWrapper2 = new QueryWrapper<>();
            boolean out = outMapper.exists(queryWrapper2.eq("warehouse",warehouse.getId()).eq("parcel",parcel[i].getId()));
            if (!in || out){
                ParcelList p = new ParcelList(parcel[i].getId(),true, parcel[i].getPlace());
                parcelLists[i]=p;
            } else {
                ParcelList p = new ParcelList(parcel[i].getId(),false, parcel[i].getPlace());
                parcelLists[i]=p;
            }
        }
        for (int k=0;k< parcelLists.length;k++) {
            System.out.println("parcelLists[k].id: " + parcelLists[k].getParcel_id() + "parcelLists[k].status: " + parcelLists[k].isStatus());
        }
        // 返回可入库包裹
        return parcelLists;
    }

    // 冒泡排序
    private ParcelList[] BubbleSort(ParcelList p[],int length){
        for(int i=0;i<length;i++){
            for (int j=0;j<length - i-1;j++){
                if(Integer.parseInt(p[j].getPlace()) > Integer.parseInt(p[j+1].getPlace())){
                    ParcelList temp;
                    temp = p[j+1];
                    p[j+1] = p[j];
                    p[j] = temp;
                }
            }
        }
        for (int i=0;i<p.length;i++){
            System.out.println(p[i].getParcel_id()+"排序"+p[i].getPlace());
        }
        return p;
    }

    // 包裹分类
    private List<List<Parcel>> divide(ParcelList[] parcelLists){
        List<List<Parcel>> afterParcel =  new ArrayList<>();
        /**
         * 将 parcelList 按 place 分类
         * place 是数字
         * 先排序：冒泡排序
         * 循环分类
         */
        // 冒泡排序,按照place从小到大排序
        parcelLists = BubbleSort(parcelLists, parcelLists.length);

        for(int i = 0;i < parcelLists.length; i++){
            List<Parcel> temp = new ArrayList<>();
            Parcel p = new Parcel(parcelLists[i].getParcel_id(),parcelLists[i].getPlace());
            temp.add(p);
            for (int j =1 ; j<parcelLists.length;j++){
                if(parcelLists[i].getPlace().equals(parcelLists[j].getPlace())){
                    Parcel p1 = new Parcel(parcelLists[j].getParcel_id(),parcelLists[j].getPlace());
                    temp.add(p1);
                    i=i+1;
                } else {
                     break;
                }
            }
            System.out.println("temp"+temp.size());
            afterParcel.add(temp);
        }
//        System.out.println("after"+afterParcel.size());
//        for (int i =0;i<afterParcel.size();i++){
//            System.out.println("ok");
//            for(int j=0 ; j<afterParcel.get(i).size();j++){
//                System.out.println(afterParcel.get(i).get(j).getId()+"place"+afterParcel.get(i).get(j).getPlace());
//            }
//
//        }
        return afterParcel;
    }

    // 给一个系列的包裹分配货架
//    private parcelReturn[] distributeLocation(Parcel[] parcel, int[][][] warehouse, String token){
    private parcelReturn[] distributeLocation(List<Parcel> parcels, int[][][] warehouse, String token) {
        /**
         * 返回parcelList【{id,status,location_x,location_y}】
         */
//        parcelReturn[] result = new parcelReturn[parcel.length];

        parcelReturn[] result = new parcelReturn[parcels.size()];
//        for(int i = 0; i < parcel.length; i++){
        for (int i=0; i<parcels.size();i++){
            QueryWrapper<Place> qw = new QueryWrapper<>();
//            qw.eq("place_Name", parcel[i].getPlace());
            qw.eq("place_Name",parcels.get(i).getPlace());
            Place place = placeMapper.selectOne(qw);

            for (int k = 1; k < warehouse.length; k++) {
                for (int j = 0; j < warehouse[0].length; j++) {
                    // 找到编号对得上的货架就分给它,可加入更多判断
                    if(warehouse[i][j][0] == place.getId()){
//                        int num = Integer.parseInt(parcel[i].getId());
                        int num = Integer.parseInt(parcels.get(i).getId());
                        result[i].setId(num);
                        result[i].setLocation_x(k);
                        result[i].setLocation_y(j);
                        result[i].setStatus(true);
                    }
                }
            }
        }

        return result;
    }

    // 入库请求
    @Override
    public R enterStock(EnterParam enterParam){
        R r = new R();
        // 给多个包裹信息,token,获取对应仓库
        String username = JWT.decode(enterParam.getToken()).getAudience().get(0);
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Warehouse warehouse = wareMapper.selectOne(queryWrapper);
        // 读取数据库，获取avg数量
        int avg = warehouse.getAvg() ;
        // 创建小车列表，应该放在初始化仓库部分
        Avg[] avgList = createAvg(avg);
        // 获取仓库结构
        InitStockImpl initStock = new InitStockImpl(wareMapper);
        int[][][] warehouse_structure = InitStockImpl.Generate_shelvesx(warehouse.getX(),warehouse.getY());
        // 得到可入库的包裹序列
        ParcelList[] parcelList = select(enterParam.getParcelInList(), enterParam.getToken());
        // 得到分类后的多个包裹序列
        List<List<Parcel>> divideParcel = divide(parcelList);
        // 分配小车，即avgList中的parcelList、status
        for (List<Parcel> parcels : divideParcel) {
            // 分配一辆车后（改变小车状态），马上对其所载包裹分配货架
            // 将返回结果赋给该小车的parcelReturn[]
            parcelReturn[] parcelReturn = distributeLocation(parcels,warehouse_structure,enterParam.getToken());
        }
//        for (Parcel[] parcels : divideParcel) {
//            // 分配一辆车后（改变小车状态），马上对其所载包裹分配货架
//            // 将返回结果赋给该小车的parcelReturn[]
//            parcelReturn[] parcelReturn = distributeLocation(parcels, warehouse_structure, enterParam.getToken());
//        }
        //给每辆车路径规划
        for (int i=0; i<divideParcel.size();i++){
            //将路径规划结果返回赋给该车的route[][]
        }
        // 返回小车列表,包裹列表，是否正常响应
        r.data("avgList",avgList);
        r.data("parcelList",parcelList);
        r.data("status_code",true);

        return r;
    }



    // 获取入库记录表格 get
    @Override
    public R getInTable(String token){
        System.out.println("*************"+token);
        R r = new R();
        if(token.equals("")){
            r.data("status_code",false);
            return r;
        }
        // 鉴权，获取username
        String username = JWT.decode(token).getAudience().get(0);
        System.out.println(username);
        // 判断该username是否存在
        if(username != null){
            // 根据username获取对应Warehouse，获取warehouse_id
            try{
                QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("username",username);
                Warehouse warehouse = wareMapper.selectOne(queryWrapper);
                int warehouse_id = warehouse.getId();
                System.out.println("1.warehouse_id: " + warehouse_id);
                // 根据warehouse_id获取对应StockIn,获取package_id
                QueryWrapper<StockIn> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("warehouse",warehouse_id);
//                queryWrapper1.eq("1",warehouse_id); // 测试用
                List<StockIn> stockIns= enterMapper.selectList(queryWrapper1);
                TableData[] tableData = new TableData[stockIns.size()];
                for (int i=0;i<stockIns.size();i++){
                    String package_id = stockIns.get(i).getParcel();
                    String time = stockIns.get(i).getTime();
                    int x = stockIns.get(i).getX();
                    int y = stockIns.get(i).getY();
                    String location_xy = "("+ x + "," + y +")";
                    String address = stockIns.get(i).getAddress();
                    tableData[i] = new TableData(package_id,time,location_xy,address);
                }
                r.data("status_code",true);
                r.data("inTableData", tableData);
            }catch (Exception E){
                System.out.println(E);
                r.data("status_code",false);
            }
            // 测试结果里面 package_id，in_time，location_x和location_y都获取不到值，但address能获取
            // 就是数据的问题
        }
        return r;
    }

    // 根据id查询包裹请求
    @Override
    public R checkParcel(CheckParcelParam checkParcelParam) {
        R r = new R();
        if(checkParcelParam.getToken().equals("")){
            r.data("status_code",false);
            return r;
        }
        // 鉴权，获取username
        String username = JWT.decode(checkParcelParam.getToken()).getAudience().get(0);
        System.out.println(username);
        if(username != null){
            // 根据username获取对应Warehouse，获取warehouse_id
            try{
                QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("username",username);
                Warehouse warehouse = wareMapper.selectOne(queryWrapper);
                // 判断在入库表还是在出库表中
                QueryWrapper<StockIn> queryWrapper1 = new QueryWrapper<>();
                boolean in = enterMapper.exists(queryWrapper1.eq("warehouse",warehouse.getId()).eq("parcel",checkParcelParam.getId()));
                QueryWrapper<StockOut> queryWrapper2 = new QueryWrapper<>();
                boolean out = outMapper.exists(queryWrapper2.eq("warehouse",warehouse.getId()).eq("parcel",checkParcelParam.getId()));
                if (in) {
                    StockIn stockIn = enterMapper.selectById(checkParcelParam.getId());
                    r.data("in_time",stockIn.getTime());
                    r.data("out_time",null);
                    r.data("place",stockIn.getAddress());
                    r.data("id",checkParcelParam.getId());
                    int x = stockIn.getX();
                    int y = stockIn.getY();
                    String location_xy = "("+ x + "," + y +")";
                    r.data("location_xy",location_xy);
                    r.data("status","在库中");
                    r.data("status_code",true);
                }
                if(out){
                    StockOut stockOut = outMapper.selectById(checkParcelParam.getId());
                    r.data("in_time",null);
                    r.data("out_time",stockOut.getTime());
                    r.data("place",stockOut.getAddress());
                    r.data("id",checkParcelParam.getId());
                    int x = stockOut.getX();
                    int y = stockOut.getY();
                    String location_xy = "("+ x + "," + y +")";
                    r.data("location_xy",location_xy);
                    r.data("status","已出库");
                    r.data("status_code",true);
                }

            }catch (Exception E){
                System.out.println(E);
                r.data("status_code",false);
            }
        }
        return r;
    }
}
