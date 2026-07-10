---
description: HARD
---

# 174. Dungeon Game

The demons had captured the princess and imprisoned her in the **bottom-right room** of a `m x n` dungeon. The dungeon consists of `m x n` rooms laid out in a grid. Rooms can contain demons (negative values that drain health) or magic orbs (positive values that restore health).

The knight starts in the **top-left room** and moves only **rightward** or **downward** until reaching the princess. At any point his health must remain at least 1.

Given a 2D array `dungeon`, return the minimum initial health such that the knight can rescue the princess.

**Example 1:**

```text
Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
Output: 7
Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT -> RIGHT -> DOWN -> DOWN.
```

**Example 2:**

```text
Input: dungeon = [[0]]
Output: 1
```

**Constraints:**

- `m == dungeon.length`
- `n == dungeon[0].length`
- `1 <= m, n <= 200`
- `-1000 <= dungeon[i][j] <= 1000`

---

## Approach: Reverse Dynamic Programming

Define `dp[i][j]` as the minimum health needed upon entering cell `(i, j)` to reach the princess alive. Work backwards from the princess cell. The minimum health at any cell is `max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])` — we take the cheaper of the two exit options, subtract what the current room gives (negative rooms add cost, positive rooms reduce it), and ensure health never drops below 1.

#### Complexity Analysis

- **Time complexity: O(m * n).** Every cell is visited exactly once during the backwards DP fill.
- **Space complexity: O(m * n).** A full DP table of the same dimensions as the dungeon is maintained.

```java
package com.lc;

import java.util.Arrays;

public class LC_0174_DungeonGame {
  public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon == null || dungeon[0].length == 0) {
      return 0;
    }
    int m = dungeon.length, n = dungeon[0].length;
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[m][n - 1] = 1;
    dp[m - 1][n] = 1;
    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
      }
    }
    return dp[0][0];
  }
}
```

**Key insight:** A forward DP fails here because the minimum health at the start depends on future cells, not past ones — working backwards from the destination ensures each state depends only on already-computed values.
