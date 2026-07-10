---
description: MEDIUM
---

# 74. Search a 2D Matrix

You are given an `m x n` integer matrix `matrix` with the following two properties:

- Each row is sorted in non-decreasing order.
- The first integer of each row is greater than the last integer of the previous row.

Given an integer `target`, return `true` if `target` is in `matrix` or `false` otherwise.

You must write a solution in `O(log(m * n))` time complexity.

**Example 1:**

```text
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
```

**Example 2:**

```text
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
```

**Constraints:**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 100`
- `-10^4 <= matrix[i][j] <= 10^4`
- `-10^4 <= target <= 10^4`

---

## Approach 1: Binary Search on Flattened Matrix

Treat the 2D matrix as a sorted 1D array of size `m * n`. Run standard binary search on indices `[0, m*n - 1]`. Convert any mid index to 2D coordinates with `row = mid / n` and `col = mid % n`.

#### Complexity Analysis

- **Time complexity: O(log(m * n)).** Binary search on a virtual array of `m * n` elements.
- **Space complexity: O(1).** No additional space is needed.

```java
public class LC_0074_Search2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }
    int left = 0, right = matrix.length * matrix[0].length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int row = mid / matrix[0].length;
      int col = mid % matrix[0].length;
      if (matrix[row][col] == target) {
        return true;
      } else if (matrix[row][col] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return false;
  }
}
```

**Key insight:** Because each row's first element is strictly greater than the previous row's last element, the entire matrix is one contiguous sorted sequence, making a single binary search sufficient.
