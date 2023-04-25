package com.service.impl;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.StockIn;
import com.entity.StockOut;
import com.entity.User;
import com.entity.Warehouse;
import com.mapper.EnterMapper;
import com.mapper.OutMapper;
import com.mapper.UserMapper;
import com.mapper.WareMapper;
import com.service.OutService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@Service
@AllArgsConstructor
public class OutServiceImpl extends ServiceImpl<OutMapper, StockOut> implements OutService {

    private final UserMapper userMapper;

    EnterMapper enterMapper;
    OutMapper outMapper;

    WareMapper wareMapper;

    List<StockOut> stock_out;
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
    private List<List<Parcel>> divide(ParcelList[] parcelLists) {
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

    // 筛选包裹（出入库不太一样）
    // 出库版本
    private ParcelList[] select(Parcel[] parcel, String token) {
        // 获取对应warehouse
        String username = JWT.decode(token).getAudience().get(0);
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Warehouse warehouse = wareMapper.selectOne(queryWrapper);
        // 循环判断，获取可出库包裹
        ParcelList[] parcelLists = new ParcelList[parcel.length];

        for(int i = 0;i<parcel.length;i++){
            // 可以出库的：在入库表里且不在出库表内
            QueryWrapper<StockIn> queryWrapper1 = new QueryWrapper<>();
            boolean in = enterMapper.exists(queryWrapper1.eq("warehouse",warehouse.getId()).eq("parcel",parcel[i].getId()));
            QueryWrapper<StockOut> queryWrapper2 = new QueryWrapper<>();
            boolean out = outMapper.exists(queryWrapper2.eq("warehouse",warehouse.getId()).eq("parcel",parcel[i].getId()));
            if(in && !out){
                ParcelList p = new ParcelList(parcel[i].getId(),true, parcel[i].getPlace());
                parcelLists[i]=p;
            }else {
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

    // 创建小车列表
    private Avg[] createAvg(int avgNumber) {
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

    // 出库请求
    @Override
    public R outStock(OutParam outParam){
        R r = new R();
        // 给多个包裹信息,token,获取对应仓库
        String username = JWT.decode(outParam.getToken()).getAudience().get(0);
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Warehouse warehouse = wareMapper.selectOne(queryWrapper);
        // 读取数据库，获取avg数量
        int avg = warehouse.getAvg();
        // 创建小车列表，应该放在初始化仓库部分
        Avg[] avgList = createAvg(avg);
        // 获取仓库结构
        InitStockImpl initStock = new InitStockImpl(wareMapper);
        int[][][] warehouse_structure = InitStockImpl.Generate_shelvesx(warehouse.getX(),warehouse.getY());
        // 得到可出库的包裹序列
        ParcelList[] parcelList = select(outParam.getParcelOutList(), outParam.getToken());
        // 得到分类后的多个包裹序列
        List<List<Parcel>> divideParcel = divide(parcelList);
        // 分配小车，即avgList中的parcelList、status
//        for (List<Parcel> parcels : divideParcel) {
//            // 分配一辆车后（改变小车状态），马上对其所载包裹分配货架
//            // 将返回结果赋给该小车的parcelReturn[]
//            parcelReturn[] parcelReturn = distributeLocation(parcels,warehouse_structure,enterParam.getToken());
//        }

        //给每辆车路径规划
        for (int i=0; i < divideParcel.size();i++){
            //将路径规划结果返回赋给该车的route[][]
//            List<int[]> route = findPath(warehouse_structure,)
//            avgList[i].setRoute(route);
        }

        // 返回小车列表,包裹列表，是否正常响应
        r.data("avgList",avgList);
        r.data("parcelList",parcelList);
        r.data("status_code",true);
        return r;
    }

    // 获取出库记录表格
    @Override
    public R getOutTable(String token){
        R r= new R();
        if(token.equals("")){
            r.data("status_code",false);
            return r;
        }
        QueryWrapper<User> queryWrapper1=new QueryWrapper<>();
        String username = JWT.decode(token).getAudience().get(0);
        queryWrapper1.eq("username",username);
         System.out.println(userMapper.exists(queryWrapper1));
        if(userMapper.exists(queryWrapper1)) {
            try {
                QueryWrapper<Warehouse> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("username", username);
                Warehouse warehouse = wareMapper.selectOne(queryWrapper2);
                QueryWrapper<StockOut> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("warehouse", warehouse.getId());
                List<StockOut> stock_out = outMapper.selectList(queryWrapper);
                TableData[] tableData = new TableData[stock_out.size()];
                for (int i=0;i<stock_out.size();i++){
                    String package_id = stock_out.get(i).getParcel();
                    String time = stock_out.get(i).getTime();
                    int x = stock_out.get(i).getX();
                    int y = stock_out.get(i).getY();
                    String location_xy = x + "," + y;
                    String address = stock_out.get(i).getAddress();
                    tableData[i] = new TableData(package_id,time,location_xy,address);
                }
                r.data("status_code",true);
                r.data("outTableData",tableData);
                return r;
            } catch (Exception E) {
                System.out.println(E);
                r.data("status_code",false);
            }
        }
        else{
            r.data("status_code",false);
        }
        return r;
    }

    //路径规划
    public static List<int[]> findPath(int[][][] warehouse, int targetX, int targetY) {
        int startX = warehouse.length / 2;  // 起点为第一列的中点
        int startY = 0;
        int endX = 0;   // 终点为第一行的中点
        int endY = warehouse[0].length / 2;
        List<int[]> path = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[warehouse.length][warehouse[0].length];
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            if (currX == endX && currY == endY) {  // 找到终点，退出循环
                path.add(new int[]{currX, currY});
                break;
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (Math.abs(i) == Math.abs(j) || currX + i < 0 || currX + i >= warehouse.length || currY + j < 0 || currY + j >= warehouse[0].length || visited[currX + i][currY + j] || warehouse[currX + i][currY + j][0] == 0) {
                        // 如果是斜向的、越界的、已经访问过的、或者是道路，跳过当前点
                        continue;
                    }

                    if (warehouse[currX + i][currY + j][0] == -1 || warehouse[currX + i][currY + j][0] == -2) {  // 如果是起点或终点
                        path.add(new int[]{currX, currY});  // 将当前点加入路径
                        path.add(new int[]{currX + i, currY + j});  // 将起点或终点加入路径
                        return path;
                    }
                    queue.add(new int[]{currX + i, currY + j});
                    visited[currX + i][currY + j] = true;
                }
            }
        }

        // 找不到路径，返回空列表
        return path;
    }
}
