---
description: MEDIUM
---

# 221. Maximal Square

Given an `m x n` binary matrix `matrix` filled with `'0'`s and `'1'`s, find the largest square containing only `'1'`s and return its area.

**Example 1:**

```text
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
```

**Example 2:**

```text
Input: matrix = [["0","1"],["1","0"]]
Output: 1
```

**Example 3:**

```text
Input: matrix = [["0"]]
Output: 0
```

**Constraints:**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 300`
- `matrix[i][j]` is `'0'` or `'1'`.

---

## Approach: Dynamic Programming

Define `dp[i][j]` as the side length of the largest square whose bottom-right corner is at cell `(i-1, j-1)` in the original matrix (using a 1-indexed DP table for boundary convenience). If `matrix[i-1][j-1] == '1'`, then `dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1`. Track the maximum side length seen and return its square.

#### Complexity Analysis

- **Time complexity: O(m * n).** Every cell is visited exactly once.
- **Space complexity: O(m * n).** The DP table is the same size as the input matrix plus one row and column of padding.

```java
public class LC_0221_MaximalSquare {
  public int maximalSquare(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
    int maxSide = 0;
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; i++) {
        if (matrix[i][j] == '1') {
          dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
          maxSide = Math.max(maxSide, dp[i][j]);
        }
      }
    }
    return maxSide * maxSide;
  }
}
```

**Key insight:** The recurrence `dp[i][j] = min(left, top, diagonal) + 1` captures the constraint that a square of side `s` at `(i,j)` requires squares of at least side `s-1` to exist ending at its left, top, and top-left neighbors simultaneously.
