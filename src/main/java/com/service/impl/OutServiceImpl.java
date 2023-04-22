package com.service.impl;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.StockOut;
import com.entity.User;
import com.entity.Warehouse;
import com.mapper.OutMapper;
import com.mapper.UserMapper;
import com.mapper.WareMapper;
import com.service.OutService;
import com.vo.R;
import com.vo.param.OutParam;
import lombok.AllArgsConstructor;
import com.vo.param.TableData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@Service
@AllArgsConstructor
public class OutServiceImpl extends ServiceImpl<OutMapper, StockOut> implements OutService {

    private final UserMapper userMapper;
    OutMapper outMapper;

    WareMapper wareMapper;
    List<StockOut> stock_out;
    // 出库请求
    @Override
    public R outStock(OutParam outParam){
        return R.ok();
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
