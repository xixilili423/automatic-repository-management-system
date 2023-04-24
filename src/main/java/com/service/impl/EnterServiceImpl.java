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
import com.service.impl.FindPath;
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
    private List<Avg> createAvg(int avgNumber){
        List<Avg> avgList = new ArrayList<>();
        for(int i=0;i<avgNumber;i++){
            Avg a = new Avg(i);
            avgList.add(a) ;
        }
        for (int i = 0;i<avgList.size();i++){
            System.out.println("id:"+avgList.get(i).getId());
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

        return afterParcel;
    }

    // 给一个系列的包裹分配货架

    private  List<parcelReturn> distributeLocation(List<Parcel> parcels, int[][][] warehouse, String token) {
        int t = parcels.size();
        List<parcelReturn> result = new ArrayList<>();
        for (int i=0; i<parcels.size();i++){
            System.out.println("onrParcel");

            for (int k = 1; k < warehouse.length; k++) {
                for (int j = 0; j < warehouse[0].length; j++) {
                    // 找到编号对得上的货架就分给它,可加入更多判断
                    if(warehouse[k][j][0] == Integer.parseInt(parcels.get(i).getPlace())){
                        int num = Integer.parseInt(parcels.get(i).getId());
                        parcelReturn temp = new parcelReturn(num,true,parcels.get(i).getPlace());
                        temp.setLocation_x(k);
                        temp.setLocation_y(j);
                        result.add(temp);
                        //写入数据库
                        System.out.println("en");
                        break;
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
        List<Avg> avgList = createAvg(avg);

        // 获取仓库结构
        InitStockImpl initStock = new InitStockImpl(wareMapper);
        int[][][] warehouse_structure = InitStockImpl.Generate_shelvesx(warehouse.getX(),warehouse.getY());
        // 输出仓库
        for (int i = 0; i < warehouse_structure.length; i++) {
            for (int j = 0; j < warehouse_structure[0].length; j++) {
                System.out.print(warehouse_structure[i][j][0] + " ");
            }
            System.out.println();
        }
        // 得到可入库的包裹序列
        ParcelList[] parcelList = select(enterParam.getParcelInList(), enterParam.getToken());
        // 得到分类后的多个包裹序列
        List<List<Parcel>> divideParcel = divide(parcelList);
        // 分配小车，即avgList中的parcelList、status
        List<Avg> avgListResult = new ArrayList<>();
        for (List<Parcel> parcels : divideParcel) {
            System.out.println("parcelList");
            for (int i =0 ;i<avgList.size();i++){
                if(avgList.get(i).getStatus()){
                    avgList.get(i).setStatus(false);//什么时候改回来
                    List<parcelReturn> parcelReturn = distributeLocation(parcels,warehouse_structure,enterParam.getToken());
                    avgList.get(i).setParcelList(parcelReturn);
                    int[] start={0,0};
                    int x = parcelReturn.size();
                    int[][] goals = new int[x][];
                    for (int j=0;j<parcelReturn.size();j++){
                        int[] temp={parcelReturn.get(j).getLocation_x(),parcelReturn.get(j).getLocation_y()};
                        goals[j]=temp;
                    }
                    avgList.get(i).setRoute(FindPath.findPath(warehouse_structure,start,goals));
                    avgListResult.add(avgList.get(i));
                    break;
                }
            }

        }

        // 返回小车列表,包裹列表，是否正常响应
        r.data("avgList",avgListResult);
        r.data("parcelList",parcelList);
        r.data("status_code",true);

        // 将数据写入数据库
        StockIn[] stockIn = new StockIn[parcelList.length];
        for (int j=0;j< parcelList.length;j++){
            stockIn[j].setParcel(parcelList[j].getParcel_id());
//            stockIn[j].setWarehouse(parcelList[j]);
//            avgList[j].getRoute();
//            stockIn[j].setX();
//            stockIn[j].setY();
//            stockIn[j].setAddress();
//            stockIn[j].setTime();
        }
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
        }
        // 鉴权，获取username
        String username = JWT.decode(checkParcelParam.getToken()).getAudience().get(0);
        System.out.println(username); // 测试用
        if(username != null){
            // 根据username获取对应Warehouse，获取warehouse_id
            try{
                QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("username",username);
                Warehouse warehouse = wareMapper.selectOne(queryWrapper);
                System.out.println(warehouse.getUsername()); // 测试用
                // 判断在入库表还是在出库表中
                QueryWrapper<StockIn> queryWrapper1 = new QueryWrapper<>();
                // 在enterMapper表里面，是否存在warehouse.id = xx id 和 parce = xx id的包裹
                boolean in = enterMapper.exists(queryWrapper1.eq("warehouse",warehouse.getId()).eq("parcel",checkParcelParam.getId()));
                QueryWrapper<StockOut> queryWrapper2 = new QueryWrapper<>();
                boolean out = outMapper.exists(queryWrapper2.eq("warehouse",warehouse.getId()).eq("parcel",checkParcelParam.getId()));
                System.out.println("hihihi!"); // 测试用
                if(in) {
                    System.out.println("在入库表内");
                    List<StockIn> stockIns = enterMapper.selectList(queryWrapper1);
                    for(int i=0;i<stockIns.size();i++){
                        if(checkParcelParam.getId().equals(stockIns.get(i).getParcel())){
                            r.data("in_time",stockIns.get(i).getTime());
                            r.data("out_time",null);
                            r.data("place", stockIns.get(i).getAddress());
                            r.data("id",checkParcelParam.getId());
                            int x = stockIns.get(i).getX();
                            int y = stockIns.get(i).getY();
                            String location_xy = "("+ x + "," + y +")";
                            r.data("location_xy",location_xy);
                            r.data("status","在库中");
                            r.data("status_code",true);
                            return r;
                        }
                    }
                }
                if(out){
                    System.out.println("在出库表内");
                    List<StockOut> stockOuts = outMapper.selectList(queryWrapper2);
                    for (int i=0;i<stockOuts.size();i++){
                        r.data("in_time",null);
                        r.data("out_time",stockOuts.get(i).getTime());
                        r.data("place",stockOuts.get(i).getAddress());
                        r.data("id",checkParcelParam.getId());
                        int x = stockOuts.get(i).getX();
                        int y = stockOuts.get(i).getY();
                        String location_xy = "("+ x + "," + y +")";
                        r.data("location_xy",location_xy);
                        r.data("status","已出库");
                        r.data("status_code",true);
                        return r;
                    }
                }else{
                    System.out.println("无该包裹");
                    r.data("in_time",null);
                    r.data("out_time",null);
                    r.data("place",null);
                    r.data("id",checkParcelParam.getId());
                    r.data("location_xy",null);
                    r.data("status","无该包裹");
                    r.data("status_code",false);
                }
            }catch (Exception E){
                System.out.println(E);
                r.data("status_code",false);
            }
        }
        return r;
    }
}
