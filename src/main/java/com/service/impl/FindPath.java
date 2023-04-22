package com.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description: 该类用作路径规划
 */

@Service
public class FindPath {

    FindPath(){

    }

    public static List<List<int[]>> findPaths(int[][][] warehouse, int[] start, List<int[]> targets) {
        List<List<int[]>> allPaths = new ArrayList<>();
        boolean[][] visited = new boolean[warehouse.length][warehouse[0].length];

        for (int[] target : targets) {
            int endX = target[0];
            int endY = target[1];

            List<int[]> path = new ArrayList<>();
            Queue<int[]> queue = new LinkedList<>();

            queue.add(start);
            visited[start[0]][start[1]] = true;

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];

                if (currX == endX && currY == endY) {
                    path.add(new int[]{currX, currY});
                    break;
                }

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (Math.abs(i) == Math.abs(j) || currX + i < 0 || currX + i >= warehouse.length || currY + j < 0 || currY + j >= warehouse[0].length || visited[currX + i][currY + j] || warehouse[currX + i][currY + j][0] == 0) {
                            continue;
                        }

                        if (warehouse[currX + i][currY + j][0] == -1 || warehouse[currX + i][currY + j][0] == -2) {
                            path.add(new int[]{currX, currY});
                            path.add(new int[]{currX + i, currY + j});
                            allPaths.add(path);
                            break;
                        }

                        queue.add(new int[]{currX + i, currY + j});
                        visited[currX + i][currY + j] = true;
                    }
                }
            }

            for (boolean[] row : visited) {
                Arrays.fill(row, false);
            }
        }

        return allPaths;
    }

}
