package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.Place;
import com.entity.StockIn;
import com.entity.Warehouse;
import com.mapper.EnterMapper;
import com.mapper.PlaceMapper;
import com.mapper.UserMapper;
import com.mapper.WareMapper;
import com.service.EnterService;
import com.vo.R;
import com.vo.param.CheckParcelParam;
import com.vo.param.EnterParam;
import com.vo.param.InTableData;
import com.vo.param.Parcel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class EnterServiceImpl extends ServiceImpl<EnterMapper, StockIn> implements EnterService {

    private final UserMapper userMapper;
    private final EnterMapper enterMapper;
    // 待改正
    @Autowired
    private final WareMapper wareMapper;
    private final PlaceMapper placeMapper;


    // 入库请求
    @Override
    public R enterStock(EnterParam enterParam){
        // 给多个包裹信息,token
        // 返回小车列表,包裹列表，是否正常响应
        Parcel[] parcelList = enterParam.getParcelInList();
        String username = JWT.decode(enterParam.getToken()).getAudience().get(0);

        R r = new R();

        //临时用于获取仓库结构
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Warehouse w = wareMapper.selectOne(queryWrapper);

        int[][][] warehouse = this.Generate_shelvesx(w.getCapacity_x(),w.getCapacity_y());

        // 输出仓库，测试用
        for (int i = 0; i < warehouse.length; i++) {
            for (int j = 0; j < warehouse[0].length; j++) {
                System.out.print(warehouse[i][j][0] + " ");
            }
            System.out.println();
        }

        //设置起点,初始化
        FindPath find = new FindPath();
        int[] start = {1, 0};
        List<int[]> targets = new ArrayList<>();
        QueryWrapper<Place> qw = new QueryWrapper<>();
        List<int[]> targetList = new ArrayList<>();

        //循环查询得到传入的包裹地址对应的货架类型编号，未考率备用货架
        for (Parcel parcel : parcelList) {
            qw.eq("place_Name", parcel.getPlace());
            Place place = placeMapper.selectOne(qw);

            for (int i = 0; i < warehouse.length; i++) {
                for (int j = 0; j < warehouse[0].length; j++) {

                    //跳过非目标类型的货架
                    if(place.getId() != warehouse[i][j][0]){
                        continue;
                    }

                    //未加入货架阈值判定

                    if (!targetList.isEmpty()) {
                        int[] lastTarget = targetList.get(targetList.size() - 1);
                        if (Arrays.equals(lastTarget, new int[]{i, j})) {
                            continue; // 跳过重复的目标点
                        }else{
                            targetList.add(new int[]{i, j});
                        }
                    }else{
                        //货架未空时直接加入货架
                        targetList.add(new int[]{i, j});
                    }
                }
            }
        }

        System.out.println("*" + targets + "*");
        List<List<int[]>> allPaths = find.findPaths(warehouse, start, targets);
        System.out.println(allPaths);

        //将包裹信息插入数据库表中，没写呢

        return r;
    }

    // 获取入库记录表格 get
    @Override
    public R getInTable(String token){
        R r = new R();
        if(token == ""){
            return R.error();
        }
        // 鉴权，获取username
        String username = JWT.decode(token).getAudience().get(0);
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
//                queryWrapper1.eq("warehouse_id",warehouse_id);
                queryWrapper1.eq("1",warehouse_id); // 测试用
                StockIn stockIn = enterMapper.selectOne(queryWrapper1);
                System.out.println("2.warehouse_id: "+ stockIn.getWarehouse_id());
                String package_id = String.valueOf(stockIn.getPackage_id()); // 包裹id
                System.out.println("package_id: "+ package_id);
                // 获取StockIn数据，写入r中，返回
                // 包裹id在上面已经获取
                String in_time = String.valueOf(stockIn.getCreate_time());
                System.out.println("in_time: " + in_time);
                int location_x = stockIn.getLocation_x();
                int location_y = stockIn.getLocation_y();
                System.out.println("x,y: " + location_x + "," + location_y);

                String a = stockIn.toString();
                System.out.println(a);

                String location_xy = location_x + "," + location_y; // 存放货架
                String address = String.valueOf(stockIn.getAddress()); // 目的地所属地区
                InTableData inTableData = new InTableData(package_id,in_time,location_xy,address);
                r.data("status_code",true);
                r.data("inTableData",inTableData);
                return r;
            }catch (Exception E){
                System.out.println(E);
                return R.error();
            }
            // 测试结果里面 package_id，in_time，location_x和location_y都获取不到值，但address能获取
            // 就是数据的问题
        }
        return R.error();
    }

    @Override
    public R checkParcel(CheckParcelParam checkParcelParam) { return R.ok(); }

    private int[][][] Generate_shelvesx(int x, int y) {
        x = x / 10;
        y = y / 10;

        int[][][] warehouse = new int[x][y][3];
        int num = x / 2 * y / 2 / 32 - 1;
        int count = 0, code = 1;
        int record = 0;

        int start_x = x / 2;
        int end_y = y / 2;

        // 生成货架
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(i % 2 == 0){
                    warehouse[i][j][0] = 0;
                    continue;
                }

                if (j % 2 == 0 ) {
                    warehouse[i][j][0] = 0;//生成道路
                    count++;
                } else {
                    if (code < 32) {
                        warehouse[i][j][0] = code;//初始化将货架均匀的分为32个区域
                    }else {
                        warehouse[i][j][0] = 32;//剩下的区域作为备用区域
                    }

                    if (count - record > num) {
                        record = count;
                        code++;
                    }
                }
            }
        }

        return warehouse;
    }

}
