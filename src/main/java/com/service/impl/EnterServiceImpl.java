package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.StockIn;
import com.entity.Warehouse;
import com.mapper.EnterMapper;
import com.mapper.UserMapper;
import com.mapper.WareMapper;
import com.service.EnterService;
import com.vo.R;
import com.vo.param.CheckParcelParam;
import com.vo.param.EnterParam;
import com.vo.param.TableData;
import com.vo.param.Parcel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vo.param.Avg;
import com.vo.param.parcelReturn;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EnterServiceImpl extends ServiceImpl<EnterMapper, StockIn> implements EnterService {

    private final UserMapper userMapper;
    private final EnterMapper enterMapper;


    // 待改正
    @Autowired
    private final WareMapper wareMapper;

    //筛选包裹（出入库不太一样）
    private Parcel[] select(Parcel[] parcel , String token){
        /**
         * 读数据库，剔除不可入库包裹，并将parcelList的id、status填写
         */


        return parcel;
    }
    //创建小车列表
    private Avg[] createAvg(int avgNumber){
        Avg[] avgList = new Avg[avgNumber];
        /**
         * 生成小车列表（id、status）
         */
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
        return null;
    }
    // 入库请求
    @Override
    public R enterStock(EnterParam enterParam){
        System.out.print(enterParam.getParcelInList()[0].getId());
        System.out.print(enterParam.getParcelInList()[0].getPlace());
        R r = new R();
        //读取数据库，获取avg数量
        int avg = 0 ;
        Avg[] avgList = createAvg(avg);//创建小车列表，应该放在初始化仓库部分
        int[][][] warehouse = null;//获得仓库结构

        Parcel[] p = select(enterParam.getParcelInList() , enterParam.getToken());//得到可入库的包裹序列
        List<Parcel[]> divideParcel = divide(p);//得到分类后的多个包裹序列
        for (Parcel[] parcels : divideParcel) {//分配小车，即avgList中的parcelList、status
            //分配一辆车后（改变小车状态），马上对其所载包裹分配货架
            distributeLocation(parcels, warehouse, enterParam.getToken()); //将返回结果赋给该小车的parcelReturn[]
        }
        for (int i=0; i<divideParcel.size();i++){//给每辆车路径规划
            //将路径规划结果返回赋给该车的route[][]
        }
        //返回总值
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

    @Override
    public R checkParcel(CheckParcelParam checkParcelParam) { return R.ok(); }

}
