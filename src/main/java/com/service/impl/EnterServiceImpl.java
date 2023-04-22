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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EnterServiceImpl extends ServiceImpl<EnterMapper, StockIn> implements EnterService {

    private final UserMapper userMapper;
    private final EnterMapper enterMapper;

    private final OutMapper outMapper;
    private final PlaceMapper placeMapper;



    // 待改正
    @Autowired
    private final WareMapper wareMapper;

    // 筛选包裹（出入库不太一样）
    private Parcel[] select(Parcel[] parcel , String token){
        /**
         * 读数据库，剔除不可入库包裹，并将parcelList的id、status填写
         * 不可入库包裹：在入库表里且没出库
         */
        // 获取对应warehouse和warehouse对应的stockIn列表，stockOut列表
        String username = JWT.decode(token).getAudience().get(0);
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Warehouse warehouse = wareMapper.selectOne(queryWrapper);
        QueryWrapper<StockIn> queryWrapper1 = new QueryWrapper<>();
        List<StockIn> stockIns = enterMapper.selectList(queryWrapper1);
        QueryWrapper<StockOut> queryWrapper2 = new QueryWrapper<>();
        List<StockOut> stockOuts = outMapper.selectList(queryWrapper2);

        // 循环判断，获取可入库包裹

        // 返回可入库包裹
        return parcel;
    }
    //创建小车列表
    private Avg[] createAvg(int avgNumber){
        Avg[] avgList = new Avg[avgNumber];
        /**
         * 生成小车列表（id、status）
         * status 表示是否可以用，true表示可以用
         */
        for(int i=0;i<avgNumber;i++){
            avgList[i].setId(i);
            avgList[i].setStatus(true);
        }

        return avgList;
    }
    //包裹分类
    private List<Parcel[]> divide(Parcel[] parcel){
        List<Parcel[]> afterParcel =  new ArrayList<>();
        /**
         * 将parcelList按place分类
         */


        return afterParcel;
    }

    //给一个系列的包裹分配货架
    private parcelReturn[] distributeLocation(Parcel[] parcel, int[][][] warehouse, String token){
        /**
         * 返回parcelList【{id,status,location_x,location_y}】
         */
        parcelReturn[] result = new parcelReturn[parcel.length];

        for(int i = 0; i < parcel.length; i++){
            QueryWrapper<Place> qw = new QueryWrapper<>();
            qw.eq("place_Name", parcel[i].getPlace());
            Place place = placeMapper.selectOne(qw);

            for (int k = 1; k < warehouse.length; k++) {
                for (int j = 0; j < warehouse[0].length; j++) {
                    if(warehouse[i][j][0] == place.getId()){//找到编号对得上的货架就分给它,可加入更多判断
                        int num = Integer.parseInt(parcel[i].getId());

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
        int[][][] warehouse_structure = initStock.Generate_shelvesx(warehouse.getX(),warehouse.getY());
        // 得到可入库的包裹序列

        PacelList[] pacelList = new PacelList[enterParam.getParcelInList().length];
        Parcel[] p = select(enterParam.getParcelInList() , enterParam.getToken()); // 这行待修改
        //得到分类后的多个包裹序列
        List<Parcel[]> divideParcel = divide(p);
        //分配小车，即avgList中的parcelList、status
        for (Parcel[] parcels : divideParcel) {
            //分配一辆车后（改变小车状态），马上对其所载包裹分配货架


            //将返回结果赋给该小车的parcelReturn[]
            distributeLocation(parcels, warehouse_structure, enterParam.getToken());
        }

        //给每辆车路径规划
        for (int i=0; i<divideParcel.size();i++){
            //将路径规划结果返回赋给该车的route[][]
//            avgList[i].setRoute(FindPath.findPath(initStock, new int[]{0, 0}, );



        }
        // 返回小车列表,包裹列表，是否正常响应
        r.data("avgList",avgList);
        r.data("pacelList",pacelList);
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
                    String location_xy = x + "," + y;
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

    @Override
    public R checkParcel(CheckParcelParam checkParcelParam) { return R.ok(); }

}
