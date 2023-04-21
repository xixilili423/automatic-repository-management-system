package com.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Description: 该类用作路径规划
 */

@Service
public class FindPath {

    private FindPath(){

    }

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