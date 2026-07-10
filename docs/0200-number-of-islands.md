---
description: MEDIUM
---

# 200. Number of Islands

Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

**Example 1:**

```text
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
```

**Example 2:**

```text
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
```

**Constraints:**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 300`
- `grid[i][j]` is `'0'` or `'1'`

---

## Approach: DFS Flood Fill

Iterate through every cell. When a `'1'` is found, increment the island count and launch a DFS that marks every reachable land cell as visited by overwriting it with `'X'`. This prevents double-counting without needing a separate visited array.

#### Complexity Analysis

- **Time complexity: O(m * n).** Every cell is visited at most once.
- **Space complexity: O(m * n).** In the worst case (all land), the DFS call stack can be as deep as the number of cells.

```java
public class LC_0200_NumberOfIslands {
  public int numIslands(char[][] grid) {
    if (grid == null || grid[0].length == 0) {
      return 0;
    }
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          count++;
          dfs(grid, i, j);
        }
      }
    }
    return count;
  }

  private void dfs(char[][] grid, int i, int j) {
    int m = grid.length;
    int n = grid[0].length;

    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') return;
    grid[i][j] = 'X';
    dfs(grid, i - 1, j);
    dfs(grid, i + 1, j);
    dfs(grid, i, j - 1);
    dfs(grid, i, j + 1);
  }
}
```

**Key insight:** Mutating the grid in-place (changing `'1'` to `'X'`) acts as a visited marker, eliminating the need for extra space and keeping the DFS base case simple.
