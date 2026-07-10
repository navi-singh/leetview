---
description: MEDIUM
---

# 54. Spiral Matrix

Given an `m x n` `matrix`, return all elements of the `matrix` in spiral order.

**Example 1:**

```text
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
```

**Example 2:**

```text
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
```

**Constraints:**

- `m == matrix.length`
- `n == matrix[0].length`
- `1 <= m, n <= 10`
- `-100 <= matrix[i][j] <= 100`

---

## Approach 1: Layer-by-Layer Boundary Shrinking

Maintain four boundary pointers: `top`, `bottom`, `left`, `right`. In each iteration of the while loop:
1. Traverse left-to-right along the top row, then increment `top`.
2. Traverse top-to-bottom along the right column, then decrement `right`.
3. If `top > bottom`, break early (no rows remain).
4. Traverse right-to-left along the bottom row, then decrement `bottom`.
5. If `right < left`, break early (no columns remain).
6. Traverse bottom-to-top along the left column, then increment `left`.

The loop continues until all `m * n` elements have been collected.

#### Complexity Analysis

- **Time complexity: O(m * n).** Every element is visited exactly once.
- **Space complexity: O(1).** Excluding the output list, only four boundary variables are used.

```java
public class LC_0054_SpiralMatrix {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<Integer>();
    if (matrix == null || matrix.length == 0) return res;
    int m = matrix.length;
    int n = matrix[0].length;
    int left = 0, right = n - 1;
    int top = 0, bottom = m - 1;
    while (res.size() < m * n) {
      for (int i = left; i <= right; i++) {
        res.add(matrix[top][i]);
      }
      top++;
      for (int i = top; i <= bottom; i++) {
        res.add(matrix[i][right]);
      }
      right--;
      if (top > bottom) {
        break;
      }
      for (int i = right; i >= left; i--) {
        res.add(matrix[bottom][i]);
      }
      bottom--;
      if (right < left) {
        break;
      }
      for (int i = bottom; i >= top; i--) {
        res.add(matrix[i][left]);
      }
      left++;
    }
    return res;
  }
}
```

**Key insight:** The mid-loop boundary checks (`top > bottom` and `right < left`) are essential for non-square matrices to avoid re-visiting elements when a single row or column is consumed as the last layer.
