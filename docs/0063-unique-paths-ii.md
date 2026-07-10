---
description: MEDIUM
---

# 63. Unique Paths II

You are given an `m x n` integer array `obstacleGrid`. A robot is initially located at the top-left corner `grid[0][0]` and tries to reach the bottom-right corner `grid[m - 1][n - 1]`. The robot can only move either down or right at any point in time.

An obstacle and space are marked as `1` or `0` respectively in `obstacleGrid`. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

**Example 1:**

```text
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
```

**Example 2:**

```text
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
```

**Constraints:**

- `m == obstacleGrid.length`
- `n == obstacleGrid[i].length`
- `1 <= m, n <= 100`
- `obstacleGrid[i][j]` is `0` or `1`

---

## Approach 1: Dynamic Programming with Obstacle Handling

Same recurrence as Unique Paths, but cells with an obstacle are set to 0 (no paths pass through them). The first row and column are propagated only up to — but not past — an obstacle.

#### Complexity Analysis

- **Time complexity: O(m * n).** Every cell is visited exactly once.
- **Space complexity: O(m * n).** A separate DP table of the same size is used.

```java
public class LC_0063_UniquePathsWithObstacle {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0;
    int[][] grid = new int[m][n];
    grid[0][0] = 1;
    // First index of every row
    for (int i = 1; i < m; i++) {
      grid[i][0] = obstacleGrid[i][0] == 1 ? 0 : grid[i - 1][0];
    }
    // First index of every column
    for (int i = 1; i < n; i++) {
      grid[0][i] = obstacleGrid[0][i] == 1 ? 0 : grid[0][i - 1];
    }
    // Now we use DP to evaluate total number of ways we can reach a particular cell
    // in the grid from its adjacent cell on right and top
    for (int row = 1; row < m; row++) {
      for (int col = 1; col < n; col++) {
        if (obstacleGrid[row][col] == 1) {
          grid[row][col] = 0;
        } else {
          grid[row][col] = grid[row][col - 1] + grid[row - 1][col];
        }
      }
    }
    return grid[m - 1][n - 1];
  }
}
```

**Key insight:** Obstacles act as hard walls that zero out the path count, and obstacles in the first row or column block all cells that lie beyond them along that edge.
