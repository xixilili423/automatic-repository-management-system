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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter;
import org.springframework.stereotype.Service;
import com.controller.ApplicationContextHelperUtil;
import com.auth0.jwt.JWT;

/**
 * FileName:  InitStockImpl
 * Date: 2023/04/18
 */

@Service
//@Transactional
@AllArgsConstructor
public class InitStockImpl extends ServiceImpl<InitStockMapper, Warehouse> implements InitStockService {

    @Autowired
     private final WareMapper wareMapper;

    // 仓库初始化
    @Override
    public R initStock(InitStockParam initStockParam) {
        R r= new R();
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        String username = JWT.decode(initStockParam.getToken()).getAudience().get(0);
        queryWrapper.eq("username",username);
        Warehouse user = wareMapper.selectOne(queryWrapper);//查询用户是否创建过仓库
        if(user==null) {
            int length = initStockParam.getCapacity_x() / 10;
            int width = initStockParam.getCapacity_y() / 10;
            int[][][] warehouse = this.Generate_shelvesx(length,width);

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
            wareMapper.insert(ware);

            r.data("depository",warehouse);
        }else{//查询到用户以创建过仓库
            r.data("status_code",true);
        }
        return r;
    }


    // 获取旧用户仓库数据
    @Override
    public R getOldInitStock(String token) {
        R r= new R();
        QueryWrapper<Warehouse> queryWrapper=new QueryWrapper<>();
        String username = JWT.decode(token).getAudience().get(0);
        queryWrapper.eq("username",username);
        Warehouse user = wareMapper.selectOne(queryWrapper);//查询用户创建过仓库
        if(user==null) {
            r.data("status", "false");
        }
        else {
            int length = user.getCapacity_x() / 10;
            int width = user.getCapacity_y() / 10;
            int[][][] warehouse = Generate_shelvesx(length,width);
            r.data("status", "true");
            r.data("depository",warehouse);
        }
        return r;
    }

    private int[][][] Generate_shelvesx(int x, int y) {
        int[][][] warehouse = new int[x][y][3];
        int num = x / 2 * y / 2 / 32 - 1;
        int count = 0, code = 1;
        int record = 0;

        int start_x = x / 2, start_y = y / 2;
        int end_x = x / 2, end_y = y / 2;

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

                    // 标记起点和终点
                    if (i == start_x && j == start_y) {
                        warehouse[i][j][0] = -1;
                    } else if (i == end_x && j == end_y) {
                        warehouse[i][j][0] = -2;
                    }
                }
            }

        }

        return warehouse;
    }

}
