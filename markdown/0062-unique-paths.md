---
description: MEDIUM
---

# 62. Unique Paths

There is a robot on an `m x n` grid. The robot is initially located at the top-left corner (i.e., `grid[0][0]`). The robot tries to move to the bottom-right corner (i.e., `grid[m - 1][n - 1]`). The robot can only move either down or right at any point in time.

Given the two integers `m` and `n`, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to `2 * 10^9`.

**Example 1:**

```text
Input: m = 3, n = 7
Output: 28
```

**Example 2:**

```text
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
```

**Constraints:**

- `1 <= m, n <= 100`

---

## Approach 1: Dynamic Programming (2D Table)

Initialize the first row and first column to 1 (there is only one way to reach any cell in the first row or column — go straight right or straight down). For every other cell, the number of paths equals the sum of paths from the cell above and the cell to the left.

#### Complexity Analysis

- **Time complexity: O(m * n).** We fill every cell in the grid exactly once.
- **Space complexity: O(m * n).** We use an auxiliary `m x n` DP table.

```java
public class LC_0062_UniquePaths {
  public int uniquePaths(int m, int n) {
    int[][] grid = new int[m][n];
    // First index of every row
    for (int i = 0; i < m; i++) {
      grid[i][0] = 1;
    }
    // First index of every column
    for (int i = 1; i < n; i++) {
      grid[0][i] = 1;
    }
    // Now we use DP to evaluate total number of ways we can reach a particular cell
    // in the grid from its adjacent cell on right and top
    for (int row = 1; row < m; row++) {
      for (int col = 1; col < n; col++) {
        grid[row][col] = grid[row][col - 1] + grid[row - 1][col];
      }
    }
    return grid[m - 1][n - 1];
  }
}
```

**Key insight:** Because the robot can only move right or down, the number of ways to reach any cell is completely determined by its left and top neighbors, making this a classic DP recurrence.
