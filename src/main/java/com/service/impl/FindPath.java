package com.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description: 该类用作路径规划
 */

@Service
public class FindPath {

    public static List<int[]> findPath(int[][][] warehouse, int[] start, int[][] goals) {
        List<int[]> path = new ArrayList<>();
        int currX = start[0];
        int currY = start[1];
        int[][] visited = new int[warehouse.length][warehouse[0].length];
        visited[currX][currY] = 1;

        // 计算所有目标点
        List<int[]> targetPoints = new ArrayList<>();

        for (int[] goal : goals) {
            targetPoints.add(goal); // 移动到目标点
        }
        targetPoints.add(start); // 回到起点

        // 依次寻路经过所有目标点
        for (int[] targetPoint : targetPoints) {

            visited = new int[warehouse.length][warehouse[0].length];

            int targetX = targetPoint[0];
            int targetY = targetPoint[1];
            List<int[]> neighbors = getNeighbors(currX, currY);

            while (!neighbors.contains(new int[]{targetX, targetY})) {//未到达目标点周围就进行寻路

                int minCost = Integer.MAX_VALUE;
                int[] next = null;

                for (int[] neighbor : neighbors) {
                    int neighborX = neighbor[0];
                    int neighborY = neighbor[1];
                    if (neighborX < 0 || neighborY < 0 || neighborX >= warehouse.length || neighborY >= warehouse[0].length || warehouse[neighborX][neighborY][0] > 0) {
                        continue; // 越界了，不能作为邻居考虑
                    }
                    int cost = visited[currX][currY] + warehouse[neighborX][neighborY][0];
                    if (Math.abs(neighborX - targetX) + Math.abs(neighborY - targetY) < minCost && visited[neighborX][neighborY] == 0) {
                        next = neighbor;
                        minCost = Math.abs(neighborX - targetX) + Math.abs(neighborY - targetY);
                    }
                }

                if(next == null){
                    break;
                }

                //节点移动
                path.add(next);
                currX = next[0];
                currY = next[1];
                visited[currX][currY] = 1;

                System.out.println("["+ currX + "," + currY + "] ");

                if(currX == start[0] && currY == start[1]){//回到起点，退出循环
                    break;
                }

                neighbors = getNeighbors(currX, currY);
            }

            if(currX == start[0] && currY == start[1]){//回到起点，退出循环
                break;
            }
        }

        return path;
    }

    private static List<int[]> getNeighbors(int currX, int currY) {
        List<int[]> neighbors = new ArrayList<>();
        neighbors.add(new int[]{currX + 1, currY}); // 右侧邻居
        neighbors.add(new int[]{currX - 1, currY}); // 左侧邻居
        neighbors.add(new int[]{currX, currY + 1}); // 下方邻居
        neighbors.add(new int[]{currX, currY - 1}); // 上方邻居
        return neighbors;
    }

}
