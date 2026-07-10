---
description: MEDIUM
---

# 59. Spiral Matrix II

Given a positive integer `n`, generate an `n x n` matrix filled with elements from `1` to `n^2` in spiral order.

**Example 1:**

```text
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
```

**Example 2:**

```text
Input: n = 1
Output: [[1]]
```

**Constraints:**

- `1 <= n <= 20`

---

## Approach 1: Layer-by-Layer Shell Filling

Process the matrix shell by shell from the outside in. For each shell `i` (from `0` to `n/2 - 1`), the inner boundary `last = n - i - 1` defines the far edge of that layer. Four loops fill the top row, right column, bottom row (right-to-left), and left column (bottom-to-top) of that shell, each incrementing a running counter `val`. After all shells are processed, if `n` is odd the center cell is filled separately.

#### Complexity Analysis

- **Time complexity: O(n^2).** Every cell is assigned exactly once.
- **Space complexity: O(1).** Excluding the output matrix, only loop variables and `val` are used.

```java
public class LC_0059_SpiralMatrixII {
  public void print2DArray(int[][] arr) {
    for (int i = 0; i < arr.length; ++i) {
      for (int j = 0; j < arr[0].length; ++j) {
        System.out.print(arr[i][j] + ",");
      }
      System.out.println();
    }
  }

  public int[][] generateMatrix(int n) {
    int[][] res = new int[n][n];
    // iterate over the array for rows and columns
    // update the values in the array
    int val = 1;
    for (int i = 0; i < n / 2; ++i) {
      int last = n - i - 1;
      for (int top = i; top < last; ++top) {
        res[i][top] = val++;
      }
      for (int right = i; right < last; ++right) {
        res[right][last] = val++;
      }
      for (int bottom = last; bottom > i; --bottom) {
        res[last][bottom] = val++;
      }
      for (int left = last; left > i; --left) {
        res[left][i] = val++;
      }
    }
    if (n % 2 == 1) {
      res[n / 2][n / 2] = val;
    }
    return res;
  }
}
```

**Key insight:** Iterating shell by shell with explicit top/right/bottom/left loops and a single incrementing counter `val` is the inverse of reading a spiral matrix — instead of reading values out, we write them in, making the logic symmetric and easy to verify.
