package com.service.impl;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.Warehouse;
import com.mapper.WareMapper;
import com.service.InitStockService;
import com.vo.R;
import com.vo.param.InitData;
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
/**
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

            int length = initStockParam.getCapacity_y();//改完
            int width = initStockParam.getCapacity_x();
            int[][][] warehouse = this.Generate_shelvesx(length ,width);

            // 输出仓库
            for (int i = 0; i < warehouse.length; i++) {
                for (int j = 0; j < warehouse[0].length; j++) {
                    System.out.print(warehouse[i][j][0] + " ");
                }
                System.out.println();
            }
            Warehouse ware = new Warehouse();//将创建的仓库数据插入数据库
            ware.setAvg(initStockParam.getAvg());
            ware.setX(initStockParam.getCapacity_x());
            ware.setY(initStockParam.getCapacity_y());
            ware.setGate(initStockParam.getGateMachine());
            ware.setUsername(username);
            wareMapper.insert(ware);

            r.data("depository",warehouse);
            r.data("status_code",true);
        }else{//查询到用户以创建过仓库
            r.data("status_code",false);
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
        Warehouse user = wareMapper.selectOne(queryWrapper);// 查询用户创建过仓库
        if(user==null) {
            r.data("status", false);
        }
        else {
            System.out.println(user.getGate());
            int length = user.getY();
            int width = user.getX();//改完
            int gate_machine = user.getGate();
            int avg = user.getAvg();
            System.out.println(length+"*******"+width);
            int[][][] warehouse = Generate_shelvesx(length,width);

            r.data("status", true);
            r.data("depository",warehouse);
            InitData initData = new InitData(length,width,gate_machine,avg);
            r.data("initData",initData);
        }
        return r;
    }

    public static int[][][] Generate_shelvesx(int x, int y) {
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
                    } else {
                        warehouse[i][j][0] = 32;//剩下的区域作为备用区域
                    }
                    if (count - record > num) {
                        record = count;
                        code++;
                    }
                }
            }
        }

        // 标记起点和终点
        warehouse[0][0][0] = -1;
        warehouse[x-1][0][0] = -2;

        return warehouse;
    }

}

*/