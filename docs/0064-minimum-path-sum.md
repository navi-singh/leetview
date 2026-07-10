---
description: MEDIUM
---

# 64. Minimum Path Sum

Given a `m x n` `grid` filled with non-negative numbers, find a path from the top left to the bottom right, which minimizes the sum of all numbers along its path.

**Note:** You can only move either down or right at any point in time.

**Example 1:**

```text
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: The path 1 → 3 → 1 → 1 → 1 minimizes the sum.
```

**Example 2:**

```text
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
```

**Constraints:**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 200`
- `0 <= grid[i][j] <= 200`

---

## Approach 1: Dynamic Programming (2D Table)

Build a DP table where `dp[i][j]` holds the minimum cost to reach cell `(i, j)` from `(0, 0)`. The first row and column are prefix sums. For all other cells, take the minimum of the cost coming from above or from the left and add the current cell's value.

#### Complexity Analysis

- **Time complexity: O(m * n).** Every cell is processed once.
- **Space complexity: O(m * n).** A separate DP table of the same dimensions is allocated.

```java
public class LC_0064_MinimumPathSum {
  public int minPathSum(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int rows = grid.length;
    int cols = grid[0].length;
    int[][] dp = new int[rows][cols];
    dp[0][0] = grid[0][0];
    for (int i = 1; i < rows; i++) {
      dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (int i = 1; i < cols; i++) {
      dp[0][i] = dp[0][i - 1] + grid[0][i];
    }
    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
      }
    }
    return dp[rows - 1][cols - 1];
  }
}
```

**Key insight:** At each interior cell there are exactly two choices (come from above or come from the left), so taking the minimum of those two accumulated costs and adding the cell value yields the optimal substructure.
