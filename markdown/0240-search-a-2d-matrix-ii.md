---
description: MEDIUM
---

# 240. Search a 2D Matrix II

Write an efficient algorithm that searches for a value `target` in an `m x n` integer matrix. This matrix has the following properties:

- Integers in each row are sorted in ascending order from left to right.
- Integers in each column are sorted in ascending order from top to bottom.

**Example 1:**

```text
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
```

**Example 2:**

```text
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false
```

**Constraints:**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= n, m <= 300`
- `-10^9 <= matrix[i][j] <= 10^9`
- All the integers in each row are sorted in ascending order.
- All the integers in each column are sorted in ascending order.
- `-10^9 <= target <= 10^9`

---

## Approach: Staircase Search (Bottom-Left Corner)

Start from the bottom-left corner of the matrix. At each step, if the current element is less than `target`, move right (to increase the value). If it is greater than `target`, move up (to decrease the value). If it equals `target`, return `true`. This eliminates one row or one column per step.

#### Complexity Analysis

- **Time complexity: O(m + n).** At most `m + n` steps are taken before the pointer goes out of bounds.
- **Space complexity: O(1).** Only two index variables are used.

```java
package com.lc;

public class LC_0240_Search2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
      return false;
    }
    int row = matrix.length - 1, col = 0;
    while (row >= 0 && col < matrix[0].length) {
      if (matrix[row][col] < target) {
        col++;
      } else if (matrix[row][col] > target) {
        row--;
      } else {
        return true;
      }
    }
    return false;
  }
}
```

**Key insight:** The bottom-left corner is the unique position where moving right strictly increases the value and moving up strictly decreases it, enabling a deterministic binary-search-like elimination without backtracking.
