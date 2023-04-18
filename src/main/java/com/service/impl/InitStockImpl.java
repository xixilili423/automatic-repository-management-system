package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.Warehouse;
import com.mapper.InitStockMapper;
import com.mapper.WareMapper;
import com.service.InitStockService;
import com.vo.R;
import com.vo.param.InitStockParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

/**
 * FileName:  InitStockImpl
 * Date: 2023/04/18
 */

@Service
//@Transactional
@AllArgsConstructor
public class InitStockImpl extends ServiceImpl<InitStockMapper, Warehouse> implements InitStockService {

     private final WareMapper wareMapper;

    // 仓库初始化
    @Override
    public R initStock(InitStockParam initStockParam) {
        R r= new R();
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        String username = JWT.decode(initStockParam.getToken()).getAudience().get(0);

        queryWrapper.eq("username",username);
        Warehouse user = WareMapper.selectOne(queryWrapper);//查询用户是否创建过仓库
        if(user==null) {
            int length = initStockParam.getCapacity_x();
            int width = initStockParam.getCapacity_y();
            int[][][] warehouse = new int[length][width][3];
            int num = length / 2 * width / 2 / 32;
            int count = 0, code = 1;
            int record = 0;

            // 生成货架
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    if (i % 2 == 0 && j % 2 == 0) {
                        warehouse[i][j][0] = code;//初始化将货架均匀的分为32个区域
                        count++;
                        if (count - record > num) {
                            record = count;
                            code++;
                        }
                    } else {
                        warehouse[i][j][0] = 0;
                    }
                }
            }

            // 输出仓库
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print(warehouse[i][j][0] + " ");
                }
                System.out.println();
            }


            Warehouse ware = new Warehouse();//将创建的仓库数据插入数据库
            ware.setAvg(initStockParam.getAvg());
            ware.setCapacity_x(initStockParam.getCapacity_x());
            ware.setCapacity_y(initStockParam.getCapacity_x());
            ware.setGateMachine(initStockParam.getGateMachine());
            ware.setUsername(username);

            WareMapper.insert(ware);

        }else{//查询到用户以创建过仓库
            r.data("status_code",true);
        }
        return r;
    }

    // 获取旧用户仓库数据
    @Override
    public R getOldInitStock(String token) { return R.ok(); }

}
