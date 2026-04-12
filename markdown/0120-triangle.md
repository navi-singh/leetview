---
description: MEDIUM
---

# 120. Triangle

Given a `triangle` array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index `i` on the current row, you may move to either index `i` or index `i + 1` on the next row.

**Example 1:**

```text
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11.
```

**Example 2:**

```text
Input: triangle = [[-10]]
Output: -10
```

**Constraints:**

- `1 <= triangle.length <= 200`
- `triangle[i].length == i + 1`
- `-10^4 <= triangle[i][j] <= 10^4`

**Follow up:** Could you do this using only `O(n)` extra space, where `n` is the total number of rows in the triangle?

---

## Approach: Bottom-Up Dynamic Programming

Start with the last row of the triangle as the DP array. Work upward row by row: for each position `j` in the current row, update `res[j]` to be the value in the current row plus the minimum of `res[j]` and `res[j+1]` (the two reachable positions in the row below). After processing all rows, `res[0]` holds the answer.

#### Complexity Analysis

- **Time complexity: O(n^2).** Processing all elements across all rows of the triangle.
- **Space complexity: O(n).** A single auxiliary array of size equal to the bottom row is reused for all levels.

```java
package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0120_Triangle {
  public int minimumTotal(List<List<Integer>> triangle) {
    if (triangle.size() < 1 || triangle.get(0).size() < 1) {
      return 0;
    }
    List<Integer> res = new ArrayList<>(triangle.get(triangle.size() - 1));
    for (int row = triangle.size() - 2; row >= 0; row--) {
      for (int j = 0; j <= row; j++) {
        res.set(j, Math.min(res.get(j), res.get(j + 1)) + triangle.get(row).get(j));
      }
    }
    return res.get(0);
  }
}
```

**Key insight:** Iterating bottom-up eliminates the need to track which path led to a given cell — each cell only needs to know the minimum cost to reach the bottom from its two children, which are already computed.
