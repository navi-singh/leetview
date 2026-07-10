---
description: HARD
---

# 317. Shortest Distance from All Buildings

You are given an `m x n` grid `grid` of values `0`, `1`, or `2`, where:

* each `0` marks **an empty land** that you can pass by freely,
* each `1` marks **a building** that you cannot pass through, and
* each `2` marks **an obstacle** that you cannot pass through.

You want to build a house on an empty land that reaches all buildings in the **shortest total travel** distance. You can only move up, down, left, and right.

Return _the **shortest travel distance** for such a house_. If it is not possible to build such a house according to the above rules, return `-1`.

The **total travel distance** is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using [Manhattan Distance](http://en.wikipedia.org/wiki/Taxicab_geometry), where `distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|`.

**Example 1:** ![](https://assets.leetcode.com/uploads/2021/03/14/buildings-grid.jpg)

```text
Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.
```

**Example 2:**

```text
Input: grid = [[1,0]]
Output: 1
```

**Example 3:**

```text
Input: grid = [[1]]
Output: -1
```

```java
public class Solution {
    public int shortestDistance(int[][] grid) {

        //This is a bfs solution, bfs needs queue generally.

        int row = grid.length;
        if(row == 0) return -1;
        int col  = grid[0].length;
        if(col == 0) return -1;

        int buildingNums = 0;

        int[][] dis = new int[row][col]; // distance sum of all bulding to dis[x][y];
        int[][] num = new int[row][col]; // how many buildings can reach num[x][y]

        for(int i=0 ; i< row; i++){
            for(int j = 0; j< col; j++){
                if(grid[i][j] == 1){
                    buildingNums++;
                    bfs(grid, dis, num, i, j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(grid[i][j] == 0 && dis[i][j] != 0 && num[i][j] == buildingNums){
                    min = Math.min(min, dis[i][j]);
                }
            }
        }
        if(min < Integer.MAX_VALUE) return min;
        return -1;
    }

    private void bfs(int[][] grid, int[][] dis, int[][] num, int x, int y){
        int row = grid.length;
        int col = grid[0].length;
        int[][] neighbor = {{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        boolean[][] visited = new boolean[row][col];
        int dist = 0;
        while(!queue.isEmpty()){
            dist++;
            int size = queue.size();// all size number of node, their neigbors belongs to next dist, which for distance.
            for(int i=0 ; i<size; i++){
                int[] top = queue.poll();
                for(int j=0; j< 4;j++){
                    int k = top[0] + neighbor[j][0];
                    int l = top[1] + neighbor[j][1];
                    if(k>=0 && k< row && l >= 0 && l < col && grid[k][l] == 0 && !visited[k][l]){
                        visited[k][l] = true;
                        dis[k][l] += dist;
                        num[k][l]++;
                        queue.add(new int[]{k,l});
                    }
                }
            }
        }

    }
}
```

