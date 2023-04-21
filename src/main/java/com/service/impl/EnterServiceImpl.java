package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.StockIn;
import com.entity.User;
import com.entity.Warehouse;
import com.mapper.EnterMapper;
import com.mapper.UserMapper;
import com.mapper.WareMapper;
import com.service.EnterService;
import com.vo.R;
import com.vo.param.CheckParcelParam;
import com.vo.param.EnterParam;
import com.vo.param.InTableData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnterServiceImpl extends ServiceImpl<EnterMapper, StockIn> implements EnterService {

    private final UserMapper userMapper;
    private final EnterMapper enterMapper;
    // 待改正
    @Autowired
    private final WareMapper wareMapper;

    // 入库请求
    @Override
    public R enterStock(EnterParam enterParam){
        // 给多个包裹信息,token
        // 返回小车列表,包裹列表，是否正常响应
        R r = new R();

        //


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

}
