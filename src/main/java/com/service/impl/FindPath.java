package com.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: 该类用作路径规划
 */

@Service
public class FindPath {

    public static List<int[]> findPath(int[][][] warehouse, int[] start, int[][] goals) {
        List<Integer> sortedIndices = getSortedGoalIndices(start, goals);

        List<int[]> path = new ArrayList<>();
        int currX = start[0];
        int currY = start[1];
        int[][] visited = new int[warehouse.length][warehouse[0].length];
        visited[currX][currY] = 1;

        // 计算所有目标点
        List<int[]> targetPoints = new ArrayList<>();

        System.out.println("sorted goals: ");
        for (int i = 0; i < goals.length; i++) {
            targetPoints.add(goals[sortedIndices.get(i)]); // 移动到目标点
            System.out.println("[" + goals[sortedIndices.get(i)][0] + "," + goals[sortedIndices.get(i)][1] + "]");
        }
        System.out.println("sorted goals end");

        // 依次寻路经过所有目标点
        for (int[] targetPoint : targetPoints) {

            visited = new int[warehouse.length][warehouse[0].length];

            int targetX = targetPoint[0];
            int targetY = targetPoint[1];
            List<int[]> neighbors = getNeighbors(currX, currY);

            while (Math.abs(currX - targetX) + Math.abs(currY - targetY) > 1) {//未到达目标点周围就进行寻路，曼哈顿距离为1时到达目标附近，距离大于1时未到达目标点附近

                int minCost = Integer.MAX_VALUE;
                int[] next = null;

                for (int[] neighbor : neighbors) {
                    int neighborX = neighbor[0];
                    int neighborY = neighbor[1];
                    if (neighborX < 0 || neighborY < 0 || neighborX >= warehouse.length || neighborY >= warehouse[0].length || warehouse[neighborX][neighborY][0] > 0) {
                        continue; // 越界或目标为货架，不能作为邻居考虑
                    }

                    if (Math.abs(neighborX - targetX) + Math.abs(neighborY - targetY) < minCost && visited[neighborX][neighborY] == 0) {
                        next = neighbor;
                        minCost = Math.abs(neighborX - targetX) + Math.abs(neighborY - targetY);
                    }
                }

                if(next == null){//走不动被堵死了，放出回头的路并将该死路堵上
                    visited = new int[warehouse.length][warehouse[0].length];
                    visited[currX][currY] = 1;
                    path.remove(path.size() - 1);
                    path.remove(path.size() - 1);

                    continue;
                }

                //节点移动
                path.add(next);
                currX = next[0];
                currY = next[1];
                visited[currX][currY] = 1;

//                System.out.println("["+ currX + "," + currY + "] ");

                if(currX == start[0] && currY == start[1]){//回到起点，退出循环,同时避免next为起点时数组越界
                    break;
                }

                neighbors = getNeighbors(currX, currY);
            }

//            System.out.println("arrive a target`s around: [" + targetX + "," + targetY +"]");

            if(currX == start[0] && currY == start[1]){//回到起点，退出循环
                break;
            }
        }

        return path;
    }

    private static List<int[]> getNeighbors(int currX, int currY) {
        List<int[]> neighbors = new ArrayList<>();
        neighbors.add(new int[]{currX, currY + 1}); // 下方邻居
        neighbors.add(new int[]{currX + 1, currY}); // 右侧邻居
        neighbors.add(new int[]{currX, currY - 1}); // 上方邻居
        neighbors.add(new int[]{currX - 1, currY}); // 左侧邻居
        return neighbors;
    }

    public static List<Integer> getSortedGoalIndices(int[] start, int[][] goals) {
        List<Integer> indices = new ArrayList<>();
        Map<Integer, Integer> distanceMap = new HashMap<>();

        for (int i = 0; i < goals.length; i++) {
            int[] goal = goals[i];
            int distance = Math.abs(start[0] - goal[0]) + Math.abs(start[1] - goal[1]);
            distanceMap.put(i, distance);
        }

        Map<Integer, Integer> sortedDistanceMap = distanceMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        sortedDistanceMap.keySet().forEach(indices::add);

        return indices;
    }

}
